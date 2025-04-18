package GUIs;

import Constants.CommonConstants;
import DB_OBJs.MyJDBC;
import DB_OBJs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFormGUI extends BaseFrame {
    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    protected void addGuiComponents() {
        JLabel loginLabel = new JLabel("LOGIN");

        //configure the component's x, y position and
        //width/height values relative to GUI
        loginLabel.setBounds(0, 25, 520, 100);

        //change the font color
        loginLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);

        //change the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        //center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        //add component to GUI
        add(loginLabel);

        //create username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        usernameField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);

        //create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        //create password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setBackground(Constants.CommonConstants.SECONDARY_COLOR);
        passwordField.setForeground(Constants.CommonConstants.TEXT_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);

        //create login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));

        //change the cursor to a hand when hover over the button
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(Constants.CommonConstants.PRIMARY_COLOR);
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get username
                String username = usernameField.getText();

                //get password
                String password = new String(passwordField.getPassword());

                // validate login
                User user = MyJDBC.validateLogin(username, password);

                // if user is null it means invalid otherwise it is a valid account
                if (user != null) {
                    // dispose this gui
                    LoginFormGUI.this.dispose();

                    // launch bank app gui
                    DashBoardGUI dashBoard = new DashBoardGUI(user);
                    dashBoard.setVisible(true);

                    // show success dialog
                    JOptionPane.showMessageDialog(dashBoard, "Login Successfully!");

                } else {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Failed!");
                }
            }
        });

        add(loginButton);

        //create register label (used to load the register GUI)
        JLabel registerLabel = new JLabel("Not a User? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(Constants.CommonConstants.TEXT_COLOR);

        //add functionality so that when clicked it will launch the register form gui
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this GUI
                LoginFormGUI.this.dispose();

                //launch the register GUI
                new RegisterFormGUI().setVisible(true);
            }
        });

        registerLabel.setBounds(125, 600, 250, 30);
        add(registerLabel);

    }

}

