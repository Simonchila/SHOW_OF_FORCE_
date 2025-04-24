package App;

import MODEL.User;
import VIEWs.DashBoardGUI;
import VIEWs.LoginFormGUI;
import VIEWs.PANELs.AdminPanel;
import VIEWs.PANELs.UserManagementPanel;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        // Testing DataBase connectivity
        // MyJDBC.testConnection();

        // app launcher
        SwingUtilities.invokeLater(() -> {
            // Create an instance of DashBoardGUI
            //DashBoardGUI dashboard = new DashBoardGUI(new User("newuser3", "password", "admin"));
            new LoginFormGUI().setVisible(true);

            // Create an instance of AdminPanel and pass the dashboard
            // AdminPanel adminPanel = new AdminPanel(dashboard);
            // UserManagementPanel USP = new UserManagementPanel();


            /* Set up the frame
            JFrame frame = new JFrame("Admin Panel");
            frame.setContentPane(USP);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);  // Center the frame
            frame.setVisible(true);

             */
        });
    }
}
