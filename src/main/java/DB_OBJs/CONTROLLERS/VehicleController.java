package DB_OBJs.CONTROLLERS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Constants.CommonConstants.*;

public class VehicleController {
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

    public static List<String[]> getFilteredVehicleLogs(String plateKeyword, String fromDate, String toDate, String status) {
        List<String[]> logs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            StringBuilder sql = new StringBuilder(
                    "SELECT license_plate, entry_time, guard, status FROM VehicleLog " +
                            "WHERE license_plate LIKE ? " +
                            "AND entry_time BETWEEN ? AND ? "
            );

            if (!status.equalsIgnoreCase("All")) {
                sql.append("AND status = ? ");
            }

            sql.append("ORDER BY entry_time DESC");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            stmt.setString(1, "%" + plateKeyword + "%");
            stmt.setString(2, fromDate + " 00:00:00");
            stmt.setString(3, toDate + " 23:59:59");

            if (!status.equalsIgnoreCase("All")) {
                stmt.setString(4, status);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                logs.add(new String[]{
                        rs.getString("license_plate"),
                        rs.getString("entry_time"),
                        rs.getString("guard"),
                        rs.getString("status")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs;
    }

    public static boolean addVehicle(String make, String model, String yearText, String licensePlate) throws NumberFormatException, SQLException {
        int year = Integer.parseInt(yearText); // throws NumberFormatException if invalid

        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String sql = "INSERT INTO vehicles (make, model, year, licensePlate) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, make);
        stmt.setString(2, model);
        stmt.setInt(3, year);
        stmt.setString(4, licensePlate);
        stmt.executeUpdate();
        conn.close();

        return true;
    }

    public static boolean deleteVehicle(String licensePlate) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String sql = "DELETE FROM vehicles WHERE licensePlate = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, licensePlate);
        int rows = stmt.executeUpdate();
        conn.close();

        return rows > 0;
    }
}
