package App;

import MODEL.User;
import VIEWs.DashBoardGUI;
import VIEWs.LoginFormGUI;
import VIEWs.PANELs.AdminPanel;
import VIEWs.PANELs.AuditLogPanel;
import VIEWs.PANELs.GuardsPanel;
import VIEWs.PANELs.UserManagementPanel;

import javax.swing.*;
import java.util.Set;

public class AppLauncher {
    public static void main(String[] args) {
        // Testing DataBase connectivity
        // MyJDBC.testConnection();

        // app launcher
        SwingUtilities.invokeLater(() -> {
            // Create an instance of DashBoardGUI
            DashBoardGUI dashboard = new DashBoardGUI(new User("newuser3", "password", "admin"));

            // main statement to run project
            //new LoginFormGUI().setVisible(true);


            // Create an instance of AdminPanel and pass the dashboard
            //AdminPanel adminPanel = new AdminPanel(dashboard);
            GuardsPanel USP = new GuardsPanel(dashboard);


            JFrame frame = new JFrame("Admin Panel");
            frame.setContentPane(USP);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);  // Center the frame
            frame.setVisible(true);

        });
    }
}
