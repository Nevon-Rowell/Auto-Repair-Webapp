package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.LoginRequest;
import AutoReparShop.webapp.models.Customer;
import AutoReparShop.webapp.models.Employee;
import AutoReparShop.webapp.repositories.CustomerRepository;
import AutoReparShop.webapp.repositories.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();

        // 1. Check if the user is a Customer
        Optional<Customer> customerOpt = customerRepository.findByEmail(loginRequest.getEmail());
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), customer.getPasswordHash())) {
                response.put("status", "success");
                response.put("id", customer.getCustomerID());
                response.put("role", "CUSTOMER"); // Explicitly define the role
                return ResponseEntity.ok(response);
            }
        }

        // 2. Check if the user is an Employee (Mechanic, Admin, etc.)
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(loginRequest.getEmail());
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), employee.getPasswordHash())) {
                response.put("status", "success");
                response.put("id", employee.getEmployeeID());
                response.put("role", employee.getRole()); // Dynamically pull their specific role
                return ResponseEntity.ok(response);
            }
        }

        // 3. Login failed
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
    @PostMapping("/register-employee")
    public ResponseEntity<?> registerEmployee(@RequestBody Map<String, String> payload) {
        // 1. Check if an employee with this email already exists
        if (employeeRepository.findByEmail(payload.get("email")).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("status", "error", "message", "Employee email is already in use"));
        }

        // 2. Map the JSON payload to a new Employee entity
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(payload.get("firstName"));
        newEmployee.setLastName(payload.get("lastName"));
        newEmployee.setEmail(payload.get("email"));
        newEmployee.setRole(payload.get("role")); // "MECHANIC", "RECEPTIONIST", or "ADMIN"

        // 3. Securely hash the plain-text password before saving[cite: 3]
        String hashedPassword = passwordEncoder.encode(payload.get("password"));
        newEmployee.setPasswordHash(hashedPassword);

        // 4. Save the employee to the database
        employeeRepository.save(newEmployee);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Employee account created successfully"
        ));
    }
}