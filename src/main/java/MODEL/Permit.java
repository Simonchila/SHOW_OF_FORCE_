package MODEL;

import java.time.LocalDateTime;

public class Permit {
    private int permitId;
    private int userId;
    private String permitType;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
    private String status;
    private int issuedBy;  // Reference to the guard or system that issued the permit
    private String vehicleLicensePlate; // Optional, for vehicle-specific permits

    // Constructor
    public Permit(int permitId, int userId, String permitType, LocalDateTime validFrom, LocalDateTime validTo, String status, int issuedBy, String vehicleLicensePlate) {
        this.permitId = permitId;
        this.userId = userId;
        this.permitType = permitType;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.status = status;
        this.issuedBy = issuedBy;
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    // Getters and setters
    public int getPermitId() { return permitId; }
    public int getUserId() { return userId; }
    public String getPermitType() { return permitType; }
    public LocalDateTime getValidFrom() { return validFrom; }
    public LocalDateTime getValidTo() { return validTo; }
    public String getStatus() { return status; }
    public int getIssuedBy() { return issuedBy; }
    public String getVehicleLicensePlate() { return vehicleLicensePlate; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Permit{permitId=%d, userId=%d, permitType='%s', validFrom=%s, validTo=%s, status='%s', issuedBy=%d, vehicleLicensePlate='%s'}",
                permitId, userId, permitType, validFrom, validTo, status, issuedBy, vehicleLicensePlate);
    }
}
