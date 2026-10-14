package AutoReparShop.webapp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentID")
    private Integer paymentID;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "amountPaid", nullable = false)
    private Double amountPaid;

    @Column(name = "paymentDate")
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(name = "saleID")
    private Integer saleID;

    public Payment() {}

    public Payment(String paymentMethod, Double amountPaid, Integer saleID) {
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.saleID = saleID;
        this.paymentDate = LocalDateTime.now();
    }

    public Integer getPaymentID() { return paymentID; }
    public void setPaymentID(Integer paymentID) { this.paymentID = paymentID; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(Double amountPaid) { this.amountPaid = amountPaid; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public Integer getSaleID() { return saleID; }
    public void setSaleID(Integer saleID) { this.saleID = saleID; }
}