package GUIs;

import DB_OBJs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends BaseFrame {
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }

    protected void addGuiComponents() {
        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 130, 400, 25);
        usernameLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 160, 450, 40);
        usernameField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameLabel);
        add(usernameField);

        // Role
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(30, 210, 400, 25);
        roleLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        roleLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField roleField = new JTextField();
        roleField.setBounds(30, 240, 450, 40);
        roleField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        roleField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        roleField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(roleLabel);
        add(roleField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 290, 400, 25);
        passwordLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 320, 450, 40);
        passwordField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);
        add(passwordField);

        // Re-enter Password
        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(30, 370, 400, 25);
        rePasswordLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(30, 400, 450, 40);
        rePasswordField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        rePasswordField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rePasswordLabel);
        add(rePasswordField);

        // Register Button
        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(Constants.CommonConstants.PRIMARY_COLOR);
        registerButton.setBounds(125, 470, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String role = roleField.getText().trim();
                String password = new String(passwordField.getPassword());
                String rePassword = new String(rePasswordField.getPassword());

                if (validateUserInput(username, role, password, rePassword)) {
                    if (MyJDBC.register(username, password, role)) {
                        RegisterFormGUI.this.dispose();
                        LoginFormGUI loginFormGUI = new LoginFormGUI();
                        loginFormGUI.setVisible(true);
                        JOptionPane.showMessageDialog(loginFormGUI, "Registered Account Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "Error: Username already taken");
                    }
                } else {
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,
                            "Error: Username must be at least 6 characters,\n" +
                                    "Role must not be empty, and passwords must match.");
                }
            }
        });
        add(registerButton);

        // Login Link
        JLabel loginLabel = new JLabel("Have an Account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(125, 540, 250, 30);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();
                new LoginFormGUI().setVisible(true);
            }
        });
        add(loginLabel);
    }

    private boolean validateUserInput(String username, String role, String password, String rePassword) {
        return !username.isEmpty()
                && !role.isEmpty()
                && username.length() >= 6
                && password.equals(rePassword);
    }
}
