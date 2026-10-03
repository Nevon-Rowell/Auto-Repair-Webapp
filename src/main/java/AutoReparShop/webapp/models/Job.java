package AutoReparShop.webapp.models;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobID;
    private String jobStatus;
    private String jobNotes;
    private String licensePlateNO;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPart> jobParts = new ArrayList<>();

    public int getJobID(){
        return jobID;
    }

    public void setJobID(int jobID){
        this.jobID = jobID;
    }

    public void setJobNotes(String jobNotes){
        this.jobNotes = jobNotes;
    }

    public String getJobNotes() {
        return jobNotes;
    }

    public void setJobStatus(String jobStatus){
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setLicensePlateNO(String plateNO){
        this.licensePlateNO = plateNO;
    }
    public String getLicensePlateNO(){
        return licensePlateNO;
    }


}