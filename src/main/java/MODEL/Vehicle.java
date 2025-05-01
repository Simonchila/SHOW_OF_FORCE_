package MODEL;

import jdk.jfr.Timestamp;

public class Vehicle {
    private int guardId;
    private final int permitId;
    private String licensePlate;
    private String entryTime;
    private String exitTime;
    private String status;

    public Vehicle(int guardId, int permitId, String licensePlate, String entryTime, String exitTime, String status) {
        this.guardId = guardId;
        this.permitId = permitId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
    }

    // Getters
    public int getGuardId() { return guardId; }
    public int getPermitId(){ return permitId; }
    public String getLicensePlate() { return licensePlate; }
    public String getEntryTime() { return entryTime; }
    public String getExitTime() { return exitTime; }
    public String getStatus() { return status; }

    // Setters
    public void setGuardId(int guardId) { this.guardId = guardId; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public void setEntryTime(Timestamp entryTime) { this.entryTime = String.valueOf(entryTime); }
    public void setExitTime(Timestamp exitTime) { this.exitTime = String.valueOf(exitTime); }
    public void setStatus(String status) { this.status = status; }
}

