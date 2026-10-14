package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.*;
import AutoReparShop.webapp.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final SaleRepository saleRepository;
    private final PaymentRepository paymentRepository;
    private final JobRepository jobRepository;
    private final VehicleRepository vehicleRepository;
    private final JobServiceRepository jobServiceRepository;
    private final JobPartRepository jobPartRepository;
    private final SparePartRepository sparePartRepository;

    public PaymentController(SaleRepository saleRepository,
                             PaymentRepository paymentRepository,
                             JobRepository jobRepository,
                             VehicleRepository vehicleRepository,
                             JobServiceRepository jobServiceRepository,
                             JobPartRepository jobPartRepository,
                             SparePartRepository sparePartRepository) {
        this.saleRepository = saleRepository;
        this.paymentRepository = paymentRepository;
        this.jobRepository = jobRepository;
        this.vehicleRepository = vehicleRepository;
        this.jobServiceRepository = jobServiceRepository;
        this.jobPartRepository = jobPartRepository;
        this.sparePartRepository = sparePartRepository;
    }

    // 1. Settle Job & Transfer to Sale Table
    @PostMapping("/settle-job/{jobId}")
    public ResponseEntity<?> settleJobPayment(@PathVariable int jobId, @RequestBody Map<String, Object> payload) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Calculate total service + part costs logged for this job
        double serviceTotal = 0.0;
        List<JobService> services = jobServiceRepository.findByJob_JobID(jobId);
        for (JobService js : services) {
            serviceTotal += js.getCost();
        }

        double partsTotal = 0.0;
        List<JobPart> parts = jobPartRepository.findByJob_JobID(jobId);
        for (JobPart jp : parts) {
            if (jp.getSparePart() != null) {
                partsTotal += jp.getQuantityUsed() * jp.getSparePart().getCostPrice();
            }
        }

        double totalAmount = serviceTotal + partsTotal;
        if (payload.containsKey("amount") && payload.get("amount") != null) {
            double customAmount = Double.parseDouble(payload.get("amount").toString());
            if (customAmount > 0) totalAmount = customAmount;
        }

        // Trace Customer via Vehicle License Plate
        Integer customerId = null;
        if (job.getLicensePlateNO() != null) {
            Optional<Vehicle> vehicleOpt = vehicleRepository.findById(job.getLicensePlateNO());
            if (vehicleOpt.isPresent()) {
                customerId = vehicleOpt.get().getCustomerID();
            }
        }

        // Mark Job Completed
        job.setJobStatus("Completed");
        jobRepository.save(job);

        // Record/Update in Sale table
        Sale sale = saleRepository.findByJobID(jobId).orElse(new Sale());
        sale.setJobID(jobId);
        sale.setTotalAmount(totalAmount);
        sale.setCustomerID(customerId);
        Sale savedSale = saleRepository.save(sale);

        // Log Payment
        String method = payload.getOrDefault("paymentMethod", "CARD").toString();
        Payment payment = new Payment(method, totalAmount, savedSale.getSaleID());
        paymentRepository.save(payment);

        return ResponseEntity.ok(Map.of(
                "message", "Job payment processed & transferred to Sale table successfully!",
                "saleID", savedSale.getSaleID(),
                "totalBilled", totalAmount
        ));
    }

    // 2. Checkout Spare Parts
    @PostMapping("/checkout-parts")
    public ResponseEntity<?> checkoutSpareParts(@RequestBody Map<String, Object> payload) {
        List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");
        if (items == null || items.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Cart is empty"));
        }

        double grandTotal = 0.0;
        Integer customerId = payload.get("customerId") != null ? Integer.parseInt(payload.get("customerId").toString()) : null;
        String paymentMethod = payload.getOrDefault("paymentMethod", "CARD").toString();

        // Deduct inventory & calculate sum
        for (Map<String, Object> item : items) {
            int partId = Integer.parseInt(item.get("partID").toString());
            int qty = Integer.parseInt(item.get("quantity").toString());

            SparePart part = sparePartRepository.findById(partId)
                    .orElseThrow(() -> new RuntimeException("Part not found with ID: " + partId));

            if (part.getStockQuantity() < qty) {
                return ResponseEntity.badRequest().body(Map.of("message", "Insufficient stock for " + part.getPartName()));
            }

            part.setStockQuantity(part.getStockQuantity() - qty);
            sparePartRepository.save(part);

            grandTotal += part.getSellingPrice() * qty;
        }

        // Save Sale (jobID is null for standalone parts orders)
        Sale sale = new Sale(grandTotal, null, customerId);
        Sale savedSale = saleRepository.save(sale);

        // Save Payment
        Payment payment = new Payment(paymentMethod, grandTotal, savedSale.getSaleID());
        paymentRepository.save(payment);

        return ResponseEntity.ok(Map.of(
                "message", "Spare parts purchase completed successfully!",
                "saleID", savedSale.getSaleID(),
                "totalAmount", grandTotal
        ));
    }
}