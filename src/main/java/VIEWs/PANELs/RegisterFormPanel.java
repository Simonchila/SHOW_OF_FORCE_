package VIEWs.PANELs;

import CONTROLLERS.MESSAGEs.MessageController;
import CONTROLLERS.UserController;
import VIEWs.FORMs.BaseFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormPanel extends JPanel {

    private BaseFrame parentFrame;
    private JLabel statusLabel;

    public RegisterFormPanel(BaseFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new MigLayout("wrap 1", "[grow, center]", "[]30[]15[]15[]15[]20[]20[]30[]"));
        setBackground(Constants.CommonConstants.BACKGROUND_COLOR);
        addGuiComponents();
    }

    private void addGuiComponents() {
        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        add(statusLabel, "growx, gaptop 5");

        JLabel titleLabel = new JLabel("CREATE ACCOUNT");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 38));
        titleLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        add(titleLabel, "gaptop 30, gapbottom 20");

        JTextField usernameField = new JTextField(20);
        styleField(usernameField);

        JPasswordField passwordField = new JPasswordField(20);
        styleField(passwordField);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        styleField(confirmPasswordField);

        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Admin", "Guard"});
        styleComboBox(roleComboBox);

        add(labeledComponent("Username:", usernameField));
        add(labeledComponent("Password:", passwordField));
        add(labeledComponent("Confirm Password:", confirmPasswordField));
        add(labeledComponent("Role:", roleComboBox));

        JButton registerButton = new JButton("REGISTER");
        styleButton(registerButton);
        registerButton.addActionListener(e -> handleRegister(usernameField, passwordField, confirmPasswordField, roleComboBox));
        add(registerButton, "gaptop 25, width 250!");

        JLabel loginLabel = new JLabel("Already have an account? Login here");
        styleLink(loginLabel);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.setPanel(new LoginFormPanel(parentFrame));
            }
        });
        add(loginLabel, "gaptop 15");
    }

    private JPanel labeledComponent(String labelText, JComponent field) {
        JPanel panel = new JPanel(new MigLayout("insets 0, wrap 1", "[grow,fill]", "[]5[]"));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(Constants.CommonConstants.TEXT_COLOR);
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        panel.add(label);
        panel.add(field);

        return panel;
    }

    private void handleRegister(JTextField usernameField, JPasswordField passwordField, JPasswordField confirmPasswordField, JComboBox<String> roleComboBox) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
        String role = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            MessageController.showToast(parentFrame, "Please fill in all fields.", Color.RED);
            return;
        }

        if (!password.equals(confirmPassword)) {
            MessageController.showToast(parentFrame, "Passwords do not match.", Color.RED);
            return;
        }

        try {
            boolean success = UserController.register(username, password, role);
            if (success) {
                MessageController.showToast(parentFrame, "Registration successful!", new Color(0, 200, 0));
                Timer delayTimer = new Timer(2000, evt -> parentFrame.setPanel(new LoginFormPanel(parentFrame)));
                delayTimer.setRepeats(false);
                delayTimer.start();
            } else {
                MessageController.showToast(parentFrame, "Registration failed. Username may already exist.", Color.RED);
            }
        } catch (Exception ex) {
            MessageController.showToast(parentFrame, "An error occurred: " + ex.getMessage(), Color.RED);
            ex.printStackTrace();
        }
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Dialog", Font.PLAIN, 22));
        field.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        field.setForeground(Constants.CommonConstants.TEXT_COLOR);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Dialog", Font.PLAIN, 20));
        comboBox.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        comboBox.setForeground(Constants.CommonConstants.TEXT_COLOR);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Dialog", Font.BOLD, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBackground(Constants.CommonConstants.PRIMARY_COLOR);
        button.setForeground(Color.decode("#98c379"));
        button.setFocusPainted(false);
    }

    private void styleLink(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setForeground(Constants.CommonConstants.TEXT_COLOR);
        label.setFont(new Font("Dialog", Font.PLAIN, 16));
    }
}
