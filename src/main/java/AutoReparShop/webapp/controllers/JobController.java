package AutoReparShop.webapp.controllers;

import AutoReparShop.webapp.models.CustDashDTO;
import AutoReparShop.webapp.models.Job;
import AutoReparShop.webapp.models.Sale;
import AutoReparShop.webapp.repositories.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/customer/{customerId}")
    public List<CustDashDTO> getCustomerDashboard(@PathVariable int customerId) {
        List<Object[]> rawData = jobRepository.findCustomerJobsRaw(customerId);
        List<CustDashDTO> dashboardData = new java.util.ArrayList<>();

        for (Object[] row : rawData) {
            int jobID = ((Number) row[0]).intValue();
            String status = (String) row[1];
            String notes = (String) row[2];
            String plate = (String) row[3];
            String vehicleName = row[4] + " " + row[5];
            String customerName = row[6] + " " + row[7];
            double price = ((Number) row[8]).doubleValue();

            dashboardData.add(new CustDashDTO(jobID, status, notes, plate, vehicleName, customerName, price));
        }
        return dashboardData;
    }
}
