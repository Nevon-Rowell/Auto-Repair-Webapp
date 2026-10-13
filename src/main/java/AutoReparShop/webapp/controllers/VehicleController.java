package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.Vehicle;
import AutoReparShop.webapp.repositories.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping("/customer/{customerId}")
    public List<Vehicle> getCustomerVehicles(@PathVariable Integer customerId) {
        return vehicleRepository.findByCustomerID(customerId);
    }

    @PostMapping
    public ResponseEntity<?> registerVehicle(@RequestBody Vehicle vehicle) {
        if (vehicle.getLicensePlateNO() == null || vehicle.getLicensePlateNO().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "License plate is required"));
        }
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(Map.of("message", "Vehicle registered successfully", "vehicle", vehicle));
    }
}