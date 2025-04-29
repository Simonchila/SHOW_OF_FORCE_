package VIEWs.PANELs;

import CONTROLLERS.MESSAGEs.MessageController;
import CONTROLLERS.UserController;
import MODEL.User;
import javax.swing.*;
import VIEWs.FORMs.BaseFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;

public class LoginFormPanel extends JPanel {

    private BaseFrame parentFrame;

    public LoginFormPanel(BaseFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new MigLayout("fill", "center", "center"));
        setBackground(Constants.CommonConstants.BACKGROUND_COLOR);
        add(createLoginCard(), "width 500!, height 600!");
    }

    private JPanel createLoginCard() {
        JPanel card = new JPanel(new MigLayout("wrap 1", "[grow,fill]", "[]20[]20[]20[]30[]10[]"));
        card.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        card.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        card.setOpaque(true);

        JLabel loginLabel = new JLabel("Welcome Back");
        loginLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(loginLabel, "gaptop 10");

        JTextField usernameField = new JTextField();
        styleField(usernameField);
        card.add(labeledComponent("Username", usernameField));

        JPasswordField passwordField = new JPasswordField();
        styleField(passwordField);
        card.add(labeledComponent("Password", passwordField));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setBackground(Constants.CommonConstants.PRIMARY_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                MessageController.showToast(parentFrame, "Please fill in all fields.", Color.RED);
                return;
            }

            try {
                boolean success = UserController.login(username, password);

                if (success) {
                    User user = UserController.getUserByUsername(username);
                    parentFrame.onLoginSuccess(user);
                } else {
                    MessageController.showToast(parentFrame, "Invalid username or password.", Color.RED);
                }
            } catch (Exception ex) {
                MessageController.showToast(parentFrame, "An error occurred: " + ex.getMessage(), Color.RED);
                ex.printStackTrace();
            }
        });

        card.add(loginButton, "gaptop 10, height 45!");

        JLabel registerLabel = new JLabel("<HTML><U>Don't have an account? Register</U></HTML>");
        registerLabel.setForeground(Constants.CommonConstants.TEXT_COLOR.brighter());
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                parentFrame.setPanel(new RegisterFormPanel(parentFrame));
            }
            public void mouseEntered(MouseEvent e) {
                registerLabel.setForeground(Constants.CommonConstants.PRIMARY_COLOR);
            }
            public void mouseExited(MouseEvent e) {
                registerLabel.setForeground(Constants.CommonConstants.TEXT_COLOR.brighter());
            }
        });

        card.add(registerLabel, "gaptop 20");

        return card;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Dialog", Font.PLAIN, 18));
        field.setBackground(new Color(240, 240, 240));
        field.setForeground(Color.DARK_GRAY);
        field.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private JPanel labeledComponent(String labelText, JComponent field) {
        JPanel panel = new JPanel(new MigLayout("wrap 2", "[][grow,fill]", "[]"));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(Constants.CommonConstants.TEXT_COLOR);
        label.setFont(new Font("Dialog", Font.BOLD, 16));

        panel.add(label);
        panel.add(field);

        return panel;
    }
}
