package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.LoginRequest;
import AutoReparShop.webapp.models.Customer;
import AutoReparShop.webapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allows your HTML/JS frontend to connect
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest) {
        // 1. Find the user by their email
        Optional<Customer> customerOpt = customerRepository.findByEmail(loginRequest.getEmail());

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();

            // 2. Check if the provided password matches the hashed password in the DB
            if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPasswordHash())) {

                // Login successful - use a standard HashMap to prevent NullPointerExceptions
                java.util.Map<String, Object> response = new java.util.HashMap<>();
                response.put("status", "success");
                response.put("message", "Login successful");
                response.put("customerId", customer.getCustomerID());
                response.put("firstName", customer.getFirstName()); // Safely handles null

                return ResponseEntity.ok(response);
            }
        }

        // 3. Login failed (either email not found, or password doesn't match)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(java.util.Map.of("status", "error", "message", "Invalid email or password"));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody LoginRequest signupRequest) {

        // 1. Check if a user with this email already exists
        if (customerRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "error", "message", "Email is already in use"));
        }

        // 2. Create a new Customer entity
        Customer newCustomer = new Customer();
        newCustomer.setEmail(signupRequest.getEmail());

        // 3. Hash the password before saving it to the database!
        // Never store plain-text passwords.
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        newCustomer.setPasswordHash(hashedPassword);

        // Optional: Set default values for other fields if needed
        // newCustomer.setFirstName("New User");

        // 4. Save the customer to the SQL database
        customerRepository.save(newCustomer);

        // 5. Return success message
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Account created successfully"
        ));
    }
}