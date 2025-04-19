package App;


import VIEWs.LoginFormGUI;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {

        // Testing DataBase connectivity
        // MyJDBC.testConnection();

        // app launcher
        SwingUtilities.invokeLater(() -> {
            // Testing LoginGui
            // instantiate a LoginFormGUI obj and make it visible

           // Testing the DashBoard GUI
           //new DashBoardGUI(new User(2,"Tracker_Admin", "admin123")).setVisible(true);
           //new DashBoardGUI(new User("newuser2", "password", "Admin")).setVisible(true);

            new LoginFormGUI().setVisible(true);

        });
    }
}