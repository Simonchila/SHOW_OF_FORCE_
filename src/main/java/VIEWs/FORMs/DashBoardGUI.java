package VIEWs.FORMs;

import MODEL.User;
import VIEWs.PANELs.AdminPanel;
import VIEWs.PANELs.GuardsPanel;
import VIEWs.PANELs.ViewersPanel;

import javax.swing.*;

public class DashBoardGUI extends JFrame {

    private User user;

    public DashBoardGUI(User user) {
        this.user = user;
        setTitle("Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout());

        if ("Admin".equalsIgnoreCase(user.getRole())) {
            switchToPanel(new AdminPanel(this));
        } else if ("Guard".equalsIgnoreCase(user.getRole())) {
            switchToPanel(new GuardsPanel(this));
        } else {
            switchToPanel(new ViewersPanel(this));
        }
    }

    public void switchToPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public User getUser() {
        return user;
    }

}
