package AutoReparShop.webapp.controllers;
import java.util.List;
import AutoReparShop.webapp.models.Job;
import AutoReparShop.webapp.repositories.JobRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/jobs")
public class JobController {
    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @GetMapping
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
}
