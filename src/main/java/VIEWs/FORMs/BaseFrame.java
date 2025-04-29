package VIEWs.FORMs;

import VIEWs.PANELs.LoginFormPanel;

import javax.swing.*;

public class BaseFrame extends JFrame {

    public BaseFrame() {
        setTitle("User Authentication");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new java.awt.BorderLayout());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPanel(new LoginFormPanel(this));  // Start with login panel
    }

    public void setPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void onLoginSuccess(MODEL.User user) {
        // When login succeeds, open dashboard and close this frame
        SwingUtilities.invokeLater(() -> {
            DashBoardGUI dashboard = new DashBoardGUI(user);
            dashboard.setVisible(true);
            this.dispose();
        });
    }
}
