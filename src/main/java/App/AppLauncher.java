package App;

import MODEL.User;
import VIEWs.DashBoardGUI;
import VIEWs.LoginFormGUI;
import VIEWs.PANELs.AdminPanel;
import VIEWs.PANELs.AuditLogPanel;
import VIEWs.PANELs.UserManagementPanel;
import VIEWs.PANELs.VehicleManagementPanel;

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
            // new DashBoardGUI(new User("newuser3", "password", "admin")).setVisible(true);
            // new DashBoardGUI(new User("newuser2", "password", "Admin")).setVisible(true);

            // new LoginFormGUI().setVisible(true);

            // test AuditLogPanel
            JFrame frame = new JFrame("Audit Log Panel");
            VehicleManagementPanel auditPanel = new VehicleManagementPanel();

            // Set the content pane of the frame to the AuditLogPanel
            frame.setContentPane(auditPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);  // Center the frame
            frame.setVisible(true);
        });
    }
}
