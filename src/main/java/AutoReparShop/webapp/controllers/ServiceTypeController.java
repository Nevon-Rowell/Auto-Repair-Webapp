package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.ServiceType;
import AutoReparShop.webapp.repositories.ServiceTypeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceTypeController {

    private final ServiceTypeRepository serviceRepository;

    public ServiceTypeController(ServiceTypeRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public List<ServiceType> getAllServices() {
        return serviceRepository.findAll();
    }
}