package MODEL;

public class AuditLog {
    private String username;
    private String timestamp;
    private String action;
    private String details;

    public AuditLog(String username, String timestamp, String action, String details) {
        this.username = username;
        this.timestamp = timestamp;
        this.action = action;
        this.details = details;
    }

    public String getUsername() { return username; }
    public String getTimestamp() { return timestamp; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
}
