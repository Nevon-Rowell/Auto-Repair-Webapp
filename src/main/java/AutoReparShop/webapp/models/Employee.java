package AutoReparShop.webapp.models;

import jakarta.persistence.*;
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID;
    private String firstName;
    private String lastName;
    private int salary;
    private int contactNo;
    @Column(unique = true, nullable = false)
    private String email;
    private String passwordHash;
    private String role;

    // Generate getters and setters for all fields
    public void  setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public int getEmployeeID(){
        return employeeID;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole(){
        return role;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
