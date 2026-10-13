package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.*;
import AutoReparShop.webapp.repositories.JobRepository;
import AutoReparShop.webapp.repositories.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository jobRepository;
    @Autowired
    private AutoReparShop.webapp.repositories.JobServiceRepository jobServiceRepository;
    @Autowired
    private AutoReparShop.webapp.repositories.JobPartRepository jobPartRepository;
    @Autowired
    private AutoReparShop.webapp.repositories.ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private AutoReparShop.webapp.repositories.SparePartRepository sparePartRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @GetMapping
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    @PostMapping
    public Job CreateJob(@RequestBody Job NewJob){
        return jobRepository.save(NewJob);
    }
    @PutMapping("/{id}")
    public Job UpdateJobStatus(@PathVariable int id,@RequestBody Job UpdatedJob){
        Job existingJob = jobRepository.findById(id).get();
        existingJob.setJobStatus(UpdatedJob.getJobStatus());
        return jobRepository.save(existingJob);
    }
    @DeleteMapping("/{id}")
    public String DeleteJob(@PathVariable int id){
        jobRepository.deleteById(id);
        return "Job Was successfully deleted!";
    }
    @GetMapping("/customer/{customerId}")
    public List<CustDashDTO> getCustomerDashboard(@PathVariable int customerId) {
        List<Object[]> rawData = jobRepository.findCustomerJobsRaw(customerId);
        List<CustDashDTO> dashboardData = new java.util.ArrayList<>();

        for (Object[] row : rawData) {
            int jobID = ((Number) row[0]).intValue();
            String status = (String) row[1];
            String notes = (String) row[2];
            String plate = (String) row[3];
            String vehicleName = row[4] + " " + row[5];
            String customerName = row[6] + " " + row[7];
            double price = ((Number) row[8]).doubleValue();

            dashboardData.add(new CustDashDTO(jobID, status, notes, plate, vehicleName, customerName, price));
        }
        return dashboardData;
    }
    // --- BRIDGE TABLE ENDPOINTS -
    // 1. Add a Service to a Job
    @GetMapping("/{jobId}/services")
    public List<java.util.Map<String, Object>> getJobServices(@PathVariable int jobId) {
        List<JobService> services = jobServiceRepository.findByJob_JobID(jobId);
        List<java.util.Map<String, Object>> response = new java.util.ArrayList<>();

        for (JobService js : services) {
            response.add(java.util.Map.of(
                    "serviceName", js.getServiceType().getServiceName(),
                    "cost", js.getCost()
            ));
        }
        return response;
    }

    @GetMapping("/{jobId}/parts")
    public List<java.util.Map<String, Object>> getJobParts(@PathVariable int jobId) {
        List<JobPart> parts = jobPartRepository.findByJob_JobID(jobId);
        List<java.util.Map<String, Object>> response = new java.util.ArrayList<>();

        for (JobPart jp : parts) {
            response.add(java.util.Map.of(
                    "partName", jp.getSparePart().getPartName(),
                    "quantity", jp.getQuantityUsed(),
                    "price", jp.getSparePart().getCostPrice()
            ));
        }
        return response;
    }

    // --- ADD ITEMS TO ACTIVE JOB ---
    @PostMapping("/{jobId}/services")
    public org.springframework.http.ResponseEntity<?> addServiceToJob(@PathVariable int jobId, @RequestBody java.util.Map<String, Integer> payload) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        ServiceType service = serviceTypeRepository.findById(payload.get("serviceID")).orElseThrow();

        JobService js = new JobService();
        js.setJob(job);
        js.setServiceType(service);
        js.setCost(service.getBasePrice()); // Pulls default cost from ServiceType catalog

        jobServiceRepository.save(js);
        return org.springframework.http.ResponseEntity.ok().build();
    }

    @PostMapping("/{jobId}/parts")
    public org.springframework.http.ResponseEntity<?> addPartToJob(@PathVariable int jobId, @RequestBody java.util.Map<String, Integer> payload) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        SparePart part = sparePartRepository.findById(payload.get("partID")).orElseThrow();

        JobPart jp = new JobPart();
        jp.setJob(job);
        jp.setSparePart(part);
        jp.setQuantityUsed(payload.get("quantityUsed"));

        jobPartRepository.save(jp);
        return org.springframework.http.ResponseEntity.ok().build();
    }
}
