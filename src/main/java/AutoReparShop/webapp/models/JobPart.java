package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Job_Part")
@IdClass(JobPartId.class)
public class JobPart {

    @Id
    @ManyToOne
    @JoinColumn(name = "jobID", referencedColumnName = "jobID")
    private Job job;

    @Id
    @Column(name = "partID")
    private int partId;

    @Column(name = "quantityUsed")
    private Integer quantityUsed;

    // Getters and Setters
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public int getPartId() { return partId; } // Changed to int
    public void setPartId(int partId) { this.partId = partId; } // Changed to int

    public Integer getQuantityUsed() { return quantityUsed; }
    public void setQuantityUsed(Integer quantityUsed) { this.quantityUsed = quantityUsed; }
}