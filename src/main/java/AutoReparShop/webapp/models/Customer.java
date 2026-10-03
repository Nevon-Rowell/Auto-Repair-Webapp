package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;
    private Integer loyaltyPoints = 0;

    @Column(name = "password_hash")
    private String passwordHash;


    // Empty constructor
    public Customer() {
    }
    public Integer getCustomerID() {
        return customerID;
    }
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}