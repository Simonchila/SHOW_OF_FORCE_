package MODEL;

import java.time.LocalDateTime;

public class AuditLog {
    private int auditLogId;
    private int userId;
    private String action;
    private LocalDateTime timestamp;
    private String details;
    private String ipAddress;

    // Constructor
    public AuditLog(int auditLogId, int userId, String action, LocalDateTime timestamp, String details, String ipAddress) {
        this.auditLogId = auditLogId;
        this.userId = userId;
        this.action = action;
        this.timestamp = timestamp;
        this.details = details;
        this.ipAddress = ipAddress;
    }

    // Getters and setters
    public int getAuditLogId() { return auditLogId; }
    public int getUserId() { return userId; }
    public String getAction() { return action; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDetails() { return details; }
    public String getIpAddress() { return ipAddress; }

    public void setAction(String action) { this.action = action; }
    public void setDetails(String details) { this.details = details; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    @Override
    public String toString() {
        return String.format("AuditLog{auditLogId=%d, userId=%d, action='%s', timestamp=%s, details='%s', ipAddress='%s'}",
                auditLogId, userId, action, timestamp, details, ipAddress);
    }
}
