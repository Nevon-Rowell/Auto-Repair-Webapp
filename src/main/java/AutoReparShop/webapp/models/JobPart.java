package AutoReparShop.webapp.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(JobPart.JobPartId.class)
@Table(name = "Job_Part")
public class JobPart {

    @Id
    @ManyToOne
    @JoinColumn(name = "jobID")
    private Job job;

    @Id
    @ManyToOne
    @JoinColumn(name = "partID")
    private SparePart sparePart;

    private int quantityUsed;

    // Getters and Setters
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    public SparePart getSparePart() { return sparePart; }
    public void setSparePart(SparePart sparePart) { this.sparePart = sparePart; }
    public int getQuantityUsed() { return quantityUsed; }
    public void setQuantityUsed(int quantityUsed) { this.quantityUsed = quantityUsed; }

    public static class JobPartId implements Serializable {
        private int job;
        private int sparePart;
        public JobPartId() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JobPartId that = (JobPartId) o;
            return job == that.job && sparePart == that.sparePart;
        }
        @Override
        public int hashCode() {
            return Objects.hash(job, sparePart);
        }
    }
}