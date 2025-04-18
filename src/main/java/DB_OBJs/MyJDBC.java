package DB_OBJs;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Constants.CommonConstants.*;

public class MyJDBC {

    public static void testConnection() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            System.out.println("✅ Successfully connected to the database.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public static boolean register(String username, String password, String role) {
        if (checkUser(username)) return false;
        // hash the password before insertion
        String hashedPassword = hashPassword(password);

        // Query to insert the values into the database
        String query = "INSERT INTO User (Username, PasswordHash, Role, FailedLoginAttempts, AccountLocked) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setInt(4, 0);
            stmt.setBoolean(5, false);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUser(String username) {
        String query = "SELECT * FROM User WHERE username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User validateLogin(String username, String password) {
        String query = "SELECT * FROM User WHERE Username = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // If no user is found with that username
            if (!rs.next()) {
                return null;  // Returning null if user doesn't exist
            }

            // Extract values from the result set
            int id = rs.getInt("UserId");
            String storedPassword = rs.getString("PasswordHash");
            String role = rs.getString("Role");
            int failedAttempts = rs.getInt("FailedLoginAttempts");
            boolean isLocked = rs.getBoolean("AccountLocked");

            // Check if the account is locked
            if (isLocked) {
                System.out.println("🔒 Account is locked.");
                return null;
            }

            // Check if the password matches
            if (!storedPassword.equals(password)) {
                failedAttempts++;

                // If failed login attempts reach 3, lock the account
                if (failedAttempts >= 3) {
                    lockAccount(username);
                    System.out.println("❌ Too many failed attempts. Account locked.");
                } else {
                    updateFailedAttempts(username, failedAttempts);
                    System.out.println("❌ Incorrect password. Attempt " + failedAttempts + "/3");
                }
                return null;
            }

            // Reset failed attempts on successful login
            updateFailedAttempts(username, 0);

            // Return user details if login is successful
            return new User(id, username, storedPassword, role, failedAttempts, isLocked);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;  // Handle SQL exception gracefully
        }
    }

    private static void updateFailedAttempts(String username, int attempts) {
        String query = "UPDATE User SET failedLoginAttempts = ? WHERE username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, attempts);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void lockAccount(String username) {
        String query = "UPDATE User SET accountLocked = true WHERE username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValidLicensePlate(String plate) {
        return plate.matches("^[A-Z0-9]{1,7}$");
    }

    public static void logVehicleEntry(String licensePlate, String guard) {
        if (!isValidLicensePlate(licensePlate)) {
            System.out.println("Invalid license plate format.");
            return;
        }

        String query = "INSERT INTO VehicleLog (LicensePlate, EntryTime, Guard) VALUES (?, NOW(), ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, licensePlate);
            ps.setString(2, guard);
            ps.executeUpdate();

            System.out.println("Vehicle logged: " + licensePlate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getAllVehicleLogs() {
        List<String[]> data = new ArrayList<>();
        String query = """
        SELECT v.LicensePlate, v.EntryTime, u.Username AS Guard
        FROM VehicleLog v
        JOIN User u ON v.LoggedBy = u.UserId
        WHERE u.Role = 'Guard'
    """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                data.add(new String[]{
                        rs.getString("LicensePlate"),
                        rs.getString("EntryTime"),
                        rs.getString("Guard")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static List<String[]> getFilteredVehicleLogs(String keyword) {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT LicensePlate, EntryTime, Guard FROM VehicleLog WHERE LicensePlate LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(new String[]{
                        rs.getString("LicensePlate"),
                        rs.getString("EntryTime"),
                        rs.getString("Guard")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

}
