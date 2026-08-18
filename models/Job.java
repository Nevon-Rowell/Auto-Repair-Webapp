package models
@Entity
public class Job{
    @Id @GeneratedValue
    private int jobID;
    private String jobStatus;
    private String jobNotes;
    private String leadMech;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public void setJobNotes(String jobNotes) {
        this.jobNotes = jobNotes;
    }

    public String getJobNotes() {
        return jobNotes;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }
}