package VIEWs;

import DB_OBJs.CONTROLLERS.UserController;
import MODEL.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFormGUI extends BaseFrame {

    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }

    @Override
    protected void addGuiComponents() {
        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 265, 400, 25);
        passwordLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 300, 450, 55);
        passwordField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(30, 370, 400, 25);
        confirmPasswordLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        confirmPasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(30, 405, 450, 55);
        confirmPasswordField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        confirmPasswordField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        confirmPasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(confirmPasswordField);

        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setBounds(30, 475, 400, 25);
        roleLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        roleLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(roleLabel);

        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Guard"});
        roleComboBox.setBounds(30, 510, 450, 45);
        roleComboBox.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        roleComboBox.setForeground(Constants.CommonConstants.TEXT_COLOR);
        roleComboBox.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(roleComboBox);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(Constants.CommonConstants.PRIMARY_COLOR);
        registerButton.setBounds(125, 580, 250, 50);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
            String role = (String) roleComboBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showError("Please fill in all fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showError("Passwords do not match.");
                return;
            }

            try {
                User user = new User(username, password, role);
                boolean success = UserController.register(user.getUsername(), user.getPassword(), user.getRole());

                if (success) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    this.dispose();
                    new LoginFormGUI().setVisible(true);
                } else {
                    showError("Registration failed. Username may already exist.");
                }
            } catch (Exception ex) {
                showError("An error occurred: " + ex.getMessage());
                ex.printStackTrace(); // Optional: For debugging
            }
        });

        add(registerButton);

        JLabel loginLabel = new JLabel("Already Registered? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(125, 650, 250, 30);

        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();
                new LoginFormGUI().setVisible(true);
            }
        });

        add(loginLabel);
    }

}
