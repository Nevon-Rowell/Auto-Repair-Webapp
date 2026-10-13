package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.SparePart;
import AutoReparShop.webapp.repositories.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
@CrossOrigin(origins = "*") // Crucial for letting your HTML talk to Spring Boot
public class SparePartController {

    @Autowired
    private SparePartRepository sparePartRepository;

    @GetMapping
    public List<SparePart> getAllParts() {
        // This grabs all parts from your database and sends them to the HTML
        return sparePartRepository.findAll();
    }

}