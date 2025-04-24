package VIEWs;

import MODEL.User;
import VIEWs.PANELs.AdminPanel;

import javax.swing.*;

public class DashBoardGUI extends JFrame {
    private User user;

    public DashBoardGUI(User user) {
        this.user = user;
        // Call to initialize the frame
        initUI();
    }

    private void initUI() {
        // Check the user's role by calling the getRole() method
        if ("admin".equalsIgnoreCase(user.getRole())) {
            // If the user is an admin, display the AdminPanel
            AdminPanel adminPanel = new AdminPanel();
            setContentPane(adminPanel);
        } else {
            // If the user is not an admin, display a simple message
            JPanel nonAdminPanel = new JPanel();
            nonAdminPanel.add(new JLabel("You do not have admin privileges."));
            setContentPane(nonAdminPanel);
        }

        // Set frame properties
        setTitle("Dashboard");
        setSize(700, 700);
        setLocationRelativeTo(null);  // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
