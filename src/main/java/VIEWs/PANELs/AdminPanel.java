package VIEWs.PANELs;

import VIEWs.DashBoardGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminPanel extends JPanel implements ActionListener {

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
    private JButton logoutButton;

    public AdminPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        addComponents();
    }

    private void addComponents() {
        mainPanel = new JPanel();
        searchField = new JTextField("search here");
        headerField = new JTextField("                        TABLE OF ALL VEHICLE");
        JButton searchButton = new JButton("SEARCH");
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
        logoutButton = new JButton("LOG OUT");

        searchButton.addActionListener(e -> {
            // Perform the search operation
            String query = searchField.getText();
            // Logic to filter vehicles based on search query
        });

        userManagementButton.addActionListener(e -> dashboard.switchToPanel("UserManagementPanel"));
        auditLogsButton.addActionListener(e -> dashboard.switchToPanel("AuditLogPanel"));
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    AdminPanel.this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
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
                                                .addComponent(logoutButton))
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
                                        .addComponent(logoutButton))
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions if necessary
    }
}
