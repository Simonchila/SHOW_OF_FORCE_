package VIEWs.PANELs;

import VIEWs.FORMs.BaseFrame;
import VIEWs.FORMs.DashBoardGUI;

import javax.swing.*;
import java.awt.event.*;

public class AdminPanel extends JPanel {

    private DashBoardGUI dashboard;
    private JPanel mainPanel;
    private JTextField searchField;
    private JTextField headerField;
    private JLabel fromLabel;
    private JTextField fromDateField;
    private JTextField toDateField;
    private JLabel toLabel;
    private JTextField statusLabel;
    private JComboBox<String> statusComboBox;
    private JScrollPane tableScrollPane;
    private JTable vehicleTable;
    private JButton userManagementButton;
    private JLabel goToLabel;
    private JButton auditLogsButton;
    private JTextField dashboardLabel;
    private JButton LogOutButton, searchButton;

    public AdminPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        addComponents();
    }

    private void addComponents() {
        mainPanel = new JPanel();
        searchField = new JTextField("search here");
        headerField = new JTextField("                        TABLE OF ALL VEHICLE");
        searchButton = new JButton("SEARCH");
        LogOutButton = new JButton("LOG OUT");
        fromLabel = new JLabel("From:");
        fromDateField = new JTextField();
        toDateField = new JTextField();
        toLabel = new JLabel("To:");
        statusLabel = new JTextField("Status:");
        statusComboBox = new JComboBox<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" });

        vehicleTable = new JTable(
                new javax.swing.table.DefaultTableModel(
                        new Object[][] {
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null}
                        },
                        new String[] { "Guard ID", "LicensePlate", "EntryTime", "ExitTime", "Status" }
                ) {
                    Class[] types = new Class[] {
                            Integer.class, String.class, String.class, String.class, String.class
                    };
                    public Class<?> getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                }
        );

        tableScrollPane = new JScrollPane(vehicleTable);

        userManagementButton = new JButton("USER MANAGEMENT");
        goToLabel = new JLabel("GO TO:");
        auditLogsButton = new JButton("AUDIT LOGS");
        dashboardLabel = new JTextField("     ADMIN DASHBOARD");

        searchButton.addActionListener(e -> {
            // Perform the search operation
            String query = searchField.getText();
            // Logic to filter vehicles based on search query
        });

        userManagementButton.addActionListener(e -> {
            // Switch to User Management Panel
            switchToUserManagementPanel();
        });

        auditLogsButton.addActionListener(e -> {
            // Switch to Audit Logs Panel
            switchToAuditLogsPanel();
        });

        LogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        // Add components to layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27)
                                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 972, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(73)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dashboardLabel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                                .addGap(184)
                                                .addComponent(LogOutButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(searchButton)
                                                .addGap(188)
                                                .addComponent(fromLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fromDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30)
                                                .addComponent(toLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(toDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(89))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(headerField, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
                                .addGap(141)
                                .addComponent(auditLogsButton, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addGap(89))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goToLabel)
                                .addGap(18)
                                .addComponent(userManagementButton)
                                .addGap(89))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(userManagementButton)
                                        .addComponent(goToLabel)
                                        .addComponent(dashboardLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LogOutButton))
                                .addGap(18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(headerField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(auditLogsButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton)
                                        .addComponent(fromLabel)
                                        .addComponent(fromDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(toLabel)
                                        .addComponent(toDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
                                .addGap(32))
        );

        GroupLayout thisLayout = new GroupLayout(this);
        this.setLayout(thisLayout);
        thisLayout.setHorizontalGroup(
                thisLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
    }

    private void switchToUserManagementPanel() {

    }

    private void switchToAuditLogsPanel() {
    }

    private void LogOutButtonActionPerformed(ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Close the current dashboard window
            dashboard.dispose();

            // Open the login frame again
            BaseFrame loginFrame = new BaseFrame();
            loginFrame.setVisible(true);
        }
    }

}
