package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.SparePart;
import AutoReparShop.webapp.repositories.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return sparePartRepository.findByIsActiveTrue();
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

    @PostMapping
    public ResponseEntity<SparePart> addSparePart(@RequestBody SparePart newPart) {
        try {
            newPart.setIsActive(true); // Ensure new parts are explicitly set to active
            SparePart savedPart = sparePartRepository.save(newPart);
            return new ResponseEntity<>(savedPart, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSparePart(@PathVariable Integer id) {
        return sparePartRepository.findById(id).map(part -> {
            part.setIsActive(false);
            sparePartRepository.save(part);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}