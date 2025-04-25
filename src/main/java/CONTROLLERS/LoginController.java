package CONTROLLERS;

import CONTROLLERS.Security.SecurityUtilsController;
import MODEL.User;
import java.sql.*;
import static Constants.CommonConstants.*;

public class LoginController {

    public static User validateLogin(String username, String password) {
        String query = "SELECT * FROM User WHERE Username = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) return null;

            int id = rs.getInt("UserId");
            String storedPassword = rs.getString("PasswordHash");
            String role = rs.getString("Role");
            int failedAttempts = rs.getInt("FailedLoginAttempts");
            boolean isLocked = rs.getBoolean("AccountLocked");
            String phoneNumber = rs.getString("phoneNUmber");

            if (isLocked) {
                System.out.println("🔒 Account is locked.");
                return null;
            }

            String inputHash = SecurityUtilsController.hashPassword(password);
            if (!storedPassword.equals(inputHash)) {
                failedAttempts++;
                if (failedAttempts >= 3) {
                    lockAccount(username);
                    System.out.println("❌ Too many failed attempts. Account locked.");
                } else {
                    updateFailedAttempts(username, failedAttempts);
                    System.out.println("❌ Incorrect password. Attempt " + failedAttempts + "/3");
                }
                return null;
            }

            updateFailedAttempts(username, 0);
            return new User(id, username, storedPassword, role, failedAttempts, false, phoneNumber);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
}
