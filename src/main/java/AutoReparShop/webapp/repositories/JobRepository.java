package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query(value = "SELECT j.jobID, j.job_status, j.job_notes, j.license_plateno, v.model, COALESCE(SUM(js.Cost), 0) " +
            "FROM job j " +
            "JOIN Vehicle v ON j.license_plateno = v.license_plateno " +
            "JOIN Customer c ON v.customerID = c.customerID " +
            "LEFT JOIN JobService js ON j.jobID = js.JobID " +
            "WHERE c.customerID = :customerId " +
            "GROUP BY j.jobID, j.job_status, j.job_notes, j.license_plateno, v.model",
            nativeQuery = true)
    List<Object[]> findCustomerJobsRaw(@Param("customerId") int customerId);
}
