package DB_OBJs.CONTROLLERS;

import DB_OBJs.Security.SecurityUtils;

import java.sql.*;
import static Constants.CommonConstants.*;

public class UserController {

    public static boolean register(String username, String password, String role) {
        if (checkUser(username)) return false;

        String hashedPassword = SecurityUtils.hashPassword(password);

        String query = "INSERT INTO User (Username, PasswordHash, Role, FailedLoginAttempts, AccountLocked) VALUES (?, ?, ?, 0, false)";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, role);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUser(String username) {
        String query = "SELECT 1 FROM User WHERE username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
