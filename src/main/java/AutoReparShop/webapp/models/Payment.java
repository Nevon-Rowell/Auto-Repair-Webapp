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

    @Column(name = "jobID")
    private Integer jobID;

    @Column(name = "status")
    private String status;

    @Column(name = "transactionRef")
    private String transactionRef;

    @Column(name = "cardLast4", length = 4)
    private String cardLast4;

    // Default Constructor
    public Payment() {}

    // Convenience Constructor matching PaymentController calls
    public Payment(String paymentMethod, Double amountPaid, Integer saleID) {
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.saleID = saleID;
        this.paymentDate = LocalDateTime.now();
    }

    // Getters and Setters
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

    public Integer getJobID() { return jobID; }
    public void setJobID(Integer jobID) { this.jobID = jobID; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }

    public String getCardLast4() { return cardLast4; }
    public void setCardLast4(String cardLast4) { this.cardLast4 = cardLast4; }
}


