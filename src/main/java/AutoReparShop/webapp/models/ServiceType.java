package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ServiceType")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceID;
    @Column(name = "serviceName")
    private String serviceName;
    @Column(name = "basePrice")
    private double basePrice;
    @Column(name = "servicedescription")
    private String serviceDescription;

    // Getters and Setters
    public int getServiceID() {
        return serviceID;
    }
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    public String getServiceDescription() {
        return serviceDescription;
    }
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}