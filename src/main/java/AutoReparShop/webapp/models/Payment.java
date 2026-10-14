package AutoReparShop.webapp.models;

<<<<<<< Updated upstream
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
=======
public class Payment {
}
package autorepairshop.billing.model;

import jakarta.persistence.*;
        import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Payment settles either a Sale (parts) or a Job (repair). Exactly one of the two FKs is set. */
@Entity @Table(name = "Payment")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID") private Long paymentId;
    @Column(name = "Amount") private BigDecimal amount;
    @Column(name = "PaymentDate") private LocalDateTime paymentDate;
    @Column(name = "Method") private String method;
    @Column(name = "JobID") private Long jobId;
    @Column(name = "SaleID") private Long saleId;
    // --- extra gateway columns (added automatically by ddl-auto=update) ---
    @Column(name = "Status") private String status;               // SUCCESS / FAILED
    @Column(name = "TransactionRef") private String transactionRef;
    @Column(name = "CardLast4", length = 4) private String cardLast4; // NEVER store the full card number / CVV

    public Long getPaymentId() { return paymentId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal a) { this.amount = a; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime d) { this.paymentDate = d; }
    public String getMethod() { return method; }
    public void setMethod(String m) { this.method = m; }
    public Long getJobId() { return jobId; }
    public void setJobId(Long j) { this.jobId = j; }
    public Long getSaleId() { return saleId; }
    public void setSaleId(Long s) { this.saleId = s; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String t) { this.transactionRef = t; }
    public String getCardLast4() { return cardLast4; }
    public void setCardLast4(String c) { this.cardLast4 = c; }
}
>>>>>>> Stashed changes
