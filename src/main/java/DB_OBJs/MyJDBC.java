package DB_OBJs;

import java.sql.*;

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

    // Make this function to run on every LogIN and logOUT session
    public static void blacklistOverstayedVehicles() {
        String sql = """
           UPDATE VehicleLog
           SET Status = 'BlackListed'
           WHERE Status = 'Parked'
             AND ExitTime IS NULL
             AND TIMESTAMPDIFF(HOUR, EntryTime, NOW()) > 24
           """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             int updated = stmt.executeUpdate();
             System.out.println("Blacklisted " + updated + " overstayed vehicle(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
