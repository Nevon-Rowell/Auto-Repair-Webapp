package AutoReparShop.webapp.controllers;
import java.util.List;
import AutoReparShop.webapp.models.Job;
import AutoReparShop.webapp.repositories.JobRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @GetMapping
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    @PostMapping
    public Job CreateJob(@RequestBody Job NewJob){
        return jobRepository.save(NewJob);
    }
    @PutMapping("/{id}")
    public Job UpdateJobStatus(@PathVariable int id,@RequestBody Job UpdatedJob){
        Job existingJob = jobRepository.findById(id).get();
        existingJob.setJobStatus(UpdatedJob.getJobStatus());
        return jobRepository.save(existingJob);
    }
    @DeleteMapping("/{id}")
    public String DeleteJob(@PathVariable int id){
        jobRepository.deleteById(id);
        return "Job Was successfully deleted!";
    }
}
