package VIEWs;

import CONTROLLERS.LoginController;
import MODEL.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFormGUI extends BaseFrame {
    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    @Override
    protected void addGuiComponents() {
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(Constants.CommonConstants.PRIMARY_COLOR);
        loginButton.setBounds(125, 520, 250, 50);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = LoginController.validateLogin(username, password);
            if (user != null) {
                LoginFormGUI.this.dispose();
                DashBoardGUI dashBoard = new DashBoardGUI(user);
                dashBoard.setVisible(true);
                JOptionPane.showMessageDialog(dashBoard, "Login Successfully!");
            } else {
                JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Failed!");
            }
        });

        add(loginButton);

        JLabel registerLabel = new JLabel("Not a User? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        registerLabel.setBounds(125, 600, 250, 30);

        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LoginFormGUI.this.dispose();
                new RegisterFormGUI().setVisible(true);
            }
        });

        add(registerLabel);
    }
}
