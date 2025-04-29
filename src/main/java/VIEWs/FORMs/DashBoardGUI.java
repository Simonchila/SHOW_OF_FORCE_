package VIEWs.FORMs;

import MODEL.User;
import VIEWs.PANELs.AdminPanel;
import VIEWs.PANELs.GuardsPanel;

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
            setPanel(new AdminPanel(this));
        } else if ("Guard".equalsIgnoreCase(user.getRole())) {
            setPanel(new GuardsPanel(this));
        }
    }

    public void setPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public User getUser() {
        return user;
    }

    public void switchToPanel(String auditLogPanel) {

    }
}
