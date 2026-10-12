package AutoReparShop.webapp.models;

public class CustDashDTO {
    private int jobID;
    private String jobStatus;
    private String jobNotes;
    private String licensePlate;
    private String vehicleInfo;
    private double price;

    public CustDashDTO(int jobID, String jobStatus, String jobNotes, String licensePlate, String vehicleInfo, String customerName, double price) {
        this.jobID = jobID;
        this.jobStatus = jobStatus;
        this.jobNotes = jobNotes;
        this.licensePlate = licensePlate;
        this.vehicleInfo = vehicleInfo;
        this.price = price;
    }

    // Standard Getters
    public int getJobID() { return jobID; }
    public String getJobStatus() { return jobStatus; }
    public String getJobNotes() { return jobNotes; }
    public String getLicensePlate() { return licensePlate; }
    public String getVehicleInfo() { return vehicleInfo; }
    public double getPrice() { return price; }
}