package MODEL;

public class User {
    private  int id;
    private String username;
    private String password;
    private String role;
    private int failedLoginAttempts;
    private boolean accountLocked;

    // Constructor for new users (default role: Viewer)
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.failedLoginAttempts = 0;
        this.accountLocked = false;
    }


    // Constructor for loading user from database
    public User(int id, String username, String password, String role, int failedLoginAttempts, boolean accountLocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.failedLoginAttempts = failedLoginAttempts;
        this.accountLocked = accountLocked;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public boolean getAccountLocked() {
        return accountLocked;
    }

    // Setters / Modifiers
    public void setRole(String role) {
        this.role = role;
    }

    public void setFailedLoginAttempts(int attempts) {
        this.failedLoginAttempts = attempts;
    }

    public void incrementFailedLoginAttempts() {
        this.failedLoginAttempts++;
    }

    public void setAccountLocked(boolean value) {
        this.accountLocked = value;
    }
}
