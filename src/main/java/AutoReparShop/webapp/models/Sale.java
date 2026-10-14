package AutoReparShop.webapp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SaleID")
    private Integer saleID;

    @Column(name = "SaleDate")
    private LocalDateTime saleDate = LocalDateTime.now();

    @Column(name = "TotalAmount", nullable = false)
    private Double totalAmount;

    @Column(name = "jobID", unique = true)
    private Integer jobID;

    @Column(name = "CustomerID")
    private Integer customerID;

    public Sale() {}

    public Sale(Double totalAmount, Integer jobID, Integer customerID) {
        this.totalAmount = totalAmount;
        this.jobID = jobID;
        this.customerID = customerID;
        this.saleDate = LocalDateTime.now();
    }

    public Integer getSaleID() {
        return saleID;
    }
    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Integer getJobID() {
        return jobID;
    }
    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }
    public Integer getCustomerID() {
        return customerID;
    }
    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
}