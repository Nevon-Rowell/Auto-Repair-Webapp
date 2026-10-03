package AutoReparShop.webapp.models; // Adjust to your actual package

import java.io.Serializable;
import java.util.Objects;

public class JobPartId implements Serializable {

    private Long job;      // Matches the name of the Job property in JobPart
    private Long partId;   // Matches the name of the partId property in JobPart

    public JobPartId() {}

    public JobPartId(Long job, Long partId) {
        this.job = job;
        this.partId = partId;
    }

    // JPA requires equals() and hashCode() for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPartId that = (JobPartId) o;
        return Objects.equals(job, that.job) && Objects.equals(partId, that.partId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(job, partId);
    }

    // Getters and Setters
    public Long getJob() { return job; }
    public void setJob(Long job) { this.job = job; }
    public Long getPartId() { return partId; }
    public void setPartId(Long partId) { this.partId = partId; }
}