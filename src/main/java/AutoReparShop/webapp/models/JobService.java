package AutoReparShop.webapp.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(JobService.JobServiceId.class)
@Table(name = "JobService")
public class JobService {

    @Id
    @ManyToOne
    @JoinColumn(name = "jobID")
    private Job job;

    @Id
    @ManyToOne
    @JoinColumn(name = "serviceID")
    private ServiceType serviceType;

    private double cost;

    // Getters and Setters
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType serviceType) { this.serviceType = serviceType; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    // Composite Key Class required by Spring Boot
    public static class JobServiceId implements Serializable {
        private int job;
        private int serviceType;
        public JobServiceId() {}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JobServiceId that = (JobServiceId) o;
            return job == that.job && serviceType == that.serviceType;
        }
        @Override
        public int hashCode() {
            return Objects.hash(job, serviceType);
        }
    }
}