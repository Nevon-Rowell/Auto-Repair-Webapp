package AutoReparShop.webapp.repositories;
import AutoReparShop.webapp.models.JobService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobServiceRepository extends JpaRepository<JobService, JobService.JobServiceId> {
    List<JobService> findByJob_JobID(int jobID);
}