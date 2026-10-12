package AutoReparShop.webapp.repositories;

import AutoReparShop.webapp.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query(value = "SELECT j.jobID, j.jobStatus, j.jobNotes, j.license_plateno, v.make, v.model, c.first_name, c.last_name, COALESCE(SUM(js.Cost), 0) " +
            "FROM job j " +
            "JOIN Vehicle v ON j.license_plateno = v.vehicleLicensePlate " +
            "JOIN Customer c ON v.customerID = c.customerID " +
            "LEFT JOIN JobService js ON j.jobID = js.JobID " +
            "WHERE c.customerID = :customerId " +
            "GROUP BY j.jobID, j.jobStatus, j.jobNotes, j.license_plateno, v.make, v.model, c.first_name, c.last_name",
            nativeQuery = true)
    List<Object[]> findCustomerJobsRaw(@Param("customerId") int customerId);
}
