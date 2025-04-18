package DB_OBJs;

import java.time.LocalDateTime;

public class VehicleLog {
    private final int logId;
    private final String licensePlate;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime; // Nullable
    private final int guardId;
    private String status;

    public VehicleLog(int logId, String licensePlate, LocalDateTime entryTime, LocalDateTime exitTime, int guardId, String status) {
        this.logId = logId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.guardId = guardId;
        this.status = status;
    }

    // Getters and setters
    public int getLogId() { return logId; }
    public String getLicensePlate() { return licensePlate; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public int getGuardId() { return guardId; }
    public String getStatus() { return status; }

    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public void setStatus(String status) { this.status = status; }
}

