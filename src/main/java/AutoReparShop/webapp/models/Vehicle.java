package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehicle")
public class Vehicle {

    // The license plate is naturally unique, serving as the Primary Key
    @Id
    @Column(name = "license_plateno", length = 20)
    private String licensePlateNO;

    @Column(name = "Brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "model", nullable = false, length = 50)
    private String model;

    @Column(name = "vehicleYear")
    private Integer vehicleYear;

    // This foreign key links directly to Customer.java's customerID
    @Column(name = "customerID")
    private Integer customerID;

    // Empty constructor required by JPA
    public Vehicle() {
    }

    public Vehicle(String licensePlateNO, String brand, String model, Integer vehicleYear, Integer customerID) {
        this.licensePlateNO = licensePlateNO;
        this.brand = brand;
        this.model = model;
        this.vehicleYear = vehicleYear;
        this.customerID = customerID;
    }

    // Getters and Setters
    public String getLicensePlateNO() {
        return licensePlateNO;
    }

    public void setLicensePlateNO(String licensePlateNO) {
        this.licensePlateNO = licensePlateNO;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}
