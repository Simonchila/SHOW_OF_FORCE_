package CONTROLLERS;

import CONTROLLERS.SECURITYs.SecurityUtilsController;
import MODEL.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Constants.CommonConstants.*;

public class UserController {

    public static boolean register(String username, String password, String role) {
        initialize_db();

        if (checkUser(username)) return false;

        String hashedPassword = SecurityUtilsController.hashPassword(password);

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

    public static boolean login(String username, String password) {
        initialize_db();

        String query = "SELECT PasswordHash, AccountLocked FROM User WHERE Username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("PasswordHash");
                boolean isLocked = rs.getBoolean("AccountLocked");

                if (isLocked) {
                    System.out.println("Account is locked.");
                    return false;
                }

                boolean passwordMatch = SecurityUtilsController.verifyPassword(password, storedHash);
                return passwordMatch;
            } else {
                return false; // User not found
            }
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

    public static boolean addUser(String username, String role, String passwordHash, String phoneNumber) {
        String query = "INSERT INTO User (Username, Role, PasswordHash, PhoneNumber) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, role);
            stmt.setString(3, passwordHash);
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                stmt.setString(4, phoneNumber);
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error adding user: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(String username) {
        if (!checkUser(username)) {
            return false; // User doesn't exist
        }

        String query = "DELETE FROM User WHERE Username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Only return true if at least one row was deleted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

/*    public static boolean deleteUser(String username) {
        System.out.println("Attempting to delete user: " + username); // Debug

        String query = "DELETE FROM User WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username.trim());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected); // Debug

            if (rowsAffected > 0) {
                conn.commit(); // Explicit commit if needed
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("SQL Error deleting user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }*/

    public static User getUserByUsername(String username) {
        initialize_db();

        String query = "SELECT * FROM User WHERE Username = ?";
        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("UserId");
                String fetchedUsername = rs.getString("Username");
                String password = rs.getString("PasswordHash");
                String role = rs.getString("Role");
                String lastPasswordChange = rs.getString("LastPasswordChange");
                int failedAttempts = rs.getInt("FailedLoginAttempts");
                boolean accountLocked = rs.getBoolean("AccountLocked");
                String phoneNumber = rs.getString("PhoneNumber");

                return new User(id, fetchedUsername, password, role, lastPasswordChange, failedAttempts, accountLocked, phoneNumber);
            } else {
                return null; // No such user
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initialize_db(){
        // intatially slow down the application to allow proper database intialization
        try {
            // Allow DB to finish initializing
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static List<User> fetchAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();

        String query = "SELECT UserId, Username, PasswordHash, Role, LastPasswordChange, FailedLoginAttempts, AccountLocked, PhoneNumber FROM User";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("UserId");
                String username = rs.getString("Username");
                String password = rs.getString("PasswordHash");
                String role = rs.getString("Role");
                String lastPasswordChange = rs.getString("LastPasswordChange");
                int failedLoginAttempts = rs.getInt("FailedLoginAttempts");
                boolean isLocked = rs.getBoolean("AccountLocked");
                String phoneNumber = rs.getString("PhoneNumber");

                User user = new User(id, username, password, role, lastPasswordChange, failedLoginAttempts, isLocked, phoneNumber);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return userList;
    }
}
