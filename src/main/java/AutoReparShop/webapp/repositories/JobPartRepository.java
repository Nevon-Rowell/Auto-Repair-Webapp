package AutoReparShop.webapp.repositories;
import AutoReparShop.webapp.models.JobPart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobPartRepository extends JpaRepository<JobPart, JobPart.JobPartId> {
    List<JobPart> findByJob_JobID(int jobID);
}