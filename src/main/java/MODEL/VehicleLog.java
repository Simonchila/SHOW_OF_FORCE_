package MODEL;

public class VehicleLog {
    private String licensePlate;
    private String entryTime;
    private String exitTime;
    private String status;
    private String loggedBy;
    private String ipAddress;

    // Constructor
    public VehicleLog(String licensePlate, String entryTime, String exitTime, String status, String loggedBy, String ipAddress) {
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
        this.loggedBy = loggedBy;
        this.ipAddress = ipAddress;
    }

    // Getters
    public String getLicensePlate() { return licensePlate; }
    public String getEntryTime() { return entryTime; }
    public String getExitTime() { return exitTime; }
    public String getStatus() { return status; }
    public String getLoggedBy() { return loggedBy; }
    public String getIpAddress() { return ipAddress; }
}
