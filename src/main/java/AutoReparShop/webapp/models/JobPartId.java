package AutoReparShop.webapp.models;

import java.io.Serializable;
import java.util.Objects;

public class JobPartId implements Serializable {

    private int job;
    private int partId;

    public JobPartId() {}

    public JobPartId(int job, int partId) {
        this.job = job;
        this.partId = partId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPartId that = (JobPartId) o;
        return job == that.job && partId == that.partId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(job, partId);
    }

    // Getters and Setters (Updated to use int instead of Long)
    public int getJob() { return job; }
    public void setJob(int job) { this.job = job; }

    public int getPartId() { return partId; }
    public void setPartId(int partId) { this.partId = partId; }
}