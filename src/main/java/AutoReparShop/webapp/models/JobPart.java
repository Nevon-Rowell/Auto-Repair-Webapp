package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Job_Part")
@IdClass(JobPartId.class) // Links to the composite key class
public class JobPart {

    @Id
    @ManyToOne
    @JoinColumn(name = "jobID", referencedColumnName = "jobid")
    private Job job;

    @Id
    @Column(name = "partID")
    private Long partId; // Use a Long here assuming you don't have a Part entity class yet

    @Column(name = "quantityUsed")
    private Integer quantityUsed;

    // Getters and Setters
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public Long getPartId() { return partId; }
    public void setPartId(Long partId) { this.partId = partId; }

    public Integer getQuantityUsed() { return quantityUsed; }
    public void setQuantityUsed(Integer quantityUsed) { this.quantityUsed = quantityUsed; }
}