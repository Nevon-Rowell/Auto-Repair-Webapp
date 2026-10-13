package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.SparePart;
import AutoReparShop.webapp.repositories.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parts")
@CrossOrigin(origins = "*") // Crucial for letting your HTML talk to Spring Boot
public class SparePartController {

    @Autowired
    private SparePartRepository sparePartRepository;

    @GetMapping
    public List<SparePart> getAllParts() {
        return sparePartRepository.findAll();
    }

    //  Updates the stock balance when the manager saves a new quantity
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePartStock(@PathVariable int id, @RequestBody java.util.Map<String, Integer> payload) {

        Optional<SparePart> existingPartOpt = sparePartRepository.findById(id);

        if (existingPartOpt.isPresent()) {
            SparePart existingPart = existingPartOpt.get();

            // Bypass the model entirely and just grab the exact number we sent
            if (payload.containsKey("stockQuantity")) {
                existingPart.setStockQuantity(payload.get("stockQuantity"));
                sparePartRepository.save(existingPart);
                return ResponseEntity.ok(existingPart);
            } else {
                return ResponseEntity.badRequest().body("Missing stockQuantity in request");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}