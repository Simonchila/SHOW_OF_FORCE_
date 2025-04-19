package DB_OBJs;

import MODEL.User;

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

}
