package VIEWs.PANELs;

import CONTROLLERS.AuditController;
import VIEWs.DashBoardGUI;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class AuditLogPanel extends JPanel{

    // Variables declaration - do not modify
    private DashBoardGUI dashboard;
    private JTextField AdminDashBoardLabel;
    private JButton ExportLogButton;
    private JButton dumpDbButton;
    private JPanel AuditLog;
    private JTextField AuditLogLabel;
    private JTable AuditLogTable;
    private JButton LogOutButton;
    private JButton UserManagementButton;
    private JButton VehicleLogButton;
    private JButton VehicleManagementButton;
    private JLabel GoToLabel;
    private JScrollPane jScrollPane3;
    private JButton searchButton;
    private JTextField searchTextField;

    public AuditLogPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        addComponents();
    }

    private void addComponents() {

        AuditLog = new JPanel();
        VehicleManagementButton = new JButton();
        UserManagementButton = new JButton();
        GoToLabel = new JLabel();
        jScrollPane3 = new JScrollPane();
        AuditLogTable = new JTable();
        searchTextField = new JTextField();
        searchButton = new JButton();
        VehicleLogButton = new JButton();
        ExportLogButton = new JButton();
        dumpDbButton = new JButton();
        AuditLogLabel = new JTextField();
        LogOutButton = new JButton();
        AdminDashBoardLabel = new JTextField();

        VehicleManagementButton.setText("VEHICLE MANAGEMENT");
        VehicleManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehicleManagementButtonActionPerformed(evt);
            }
        });

        UserManagementButton.setText("USER  MANAGEMENT");
        UserManagementButton.addActionListener(e -> dashboard.switchToPanel("UserManagementPanel"));
        GoToLabel.setText("GO TO:");


        AuditLogTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "USERNAME", "ACTION", "TIME"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(AuditLogTable);

        searchTextField.setText("search here");

        searchButton.setText("SEARCH");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        VehicleLogButton.setText("VEHICLE LOGS");
        VehicleLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                VehicleLogButtonActionPerformed(evt);
            }
        });

        AuditLogLabel.setText("       AUDIT LOG");

        LogOutButton.setText("LOG OUT");
        LogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        AdminDashBoardLabel.setText("    ADMIN DASHBOARD");
        AdminDashBoardLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminDashBoardLabelActionPerformed(evt);
            }
        });

        ExportLogButton.setText("EXPORT");
        ExportLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ExportLogButtonActionPerformed(evt);
            }
        });

        dumpDbButton.setText("DUMP");
        dumpDbButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dumpDbButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AuditLogLayout = new javax.swing.GroupLayout(AuditLog);
        AuditLog.setLayout(AuditLogLayout);
        AuditLogLayout.setHorizontalGroup(
                AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AuditLogLayout.createSequentialGroup()
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(searchButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ExportLogButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(213, 213, 213)
                                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(AuditLogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AuditLogLayout.createSequentialGroup()
                                                                .addComponent(LogOutButton)
                                                                .addGap(16, 16, 16)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)))
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AuditLogLayout.createSequentialGroup()
                                                .addComponent(VehicleLogButton)
                                                .addGap(100, 100, 100))
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(UserManagementButton)
                                                                .addGroup(AuditLogLayout.createSequentialGroup()
                                                                        .addComponent(GoToLabel)
                                                                        .addGap(29, 29, 29)
                                                                        .addComponent(VehicleManagementButton)))
                                                        .addComponent(dumpDbButton))
                                                .addGap(85, 85, 85))))
        );
        AuditLogLayout.setVerticalGroup(
                AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AuditLogLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(VehicleManagementButton)
                                        .addComponent(GoToLabel)
                                        .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LogOutButton))
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(UserManagementButton)
                                                .addGap(15, 15, 15)
                                                .addComponent(VehicleLogButton))
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(AuditLogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton)
                                        .addComponent(ExportLogButton)
                                        .addComponent(dumpDbButton))
                                .addGap(477, 477, 477))
        );

        AuditLogTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null},
                        {null, null, null},
                        {null, null, null},
                        {null, null, null}
                },
                new String [] {
                        "USERNAME", "ACTION", "TIME"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(AuditLogTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(AuditLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(183, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(AuditLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(14, Short.MAX_VALUE)))
        );
    }

    private void VehicleManagementButtonActionPerformed(ActionEvent evt) {
        dashboard.switchToPanel("VehicleManagementPanel");
    }

    private void VehicleLogButtonActionPerformed(ActionEvent evt) {
        dashboard.switchToPanel("VehicleLogPanel");
    }

    // Handle search button click
    private void searchButtonActionPerformed(ActionEvent evt) {
        String searchQuery = searchTextField.getText().trim();
        if (!searchQuery.isEmpty()) {
            performSearch(searchQuery);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a search query", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void AdminDashBoardLabelActionPerformed(ActionEvent evt){
        dashboard.switchToPanel("AdminPanel");
    }

    // Perform the actual search/filtering logic
    private void performSearch(String searchQuery) {
        // This could be the logic to search in the user management table
        // For example, filter the rows based on the search query
        // Assuming you have access to the table model or data

        // Example of filtering logic:
        DefaultTableModel model = (DefaultTableModel) AuditLogTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        AuditLogTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchQuery));
    }

    private void LogOutButtonActionPerformed(ActionEvent evt) {
        // For example, log out the user and navigate back to the login screen
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Perform actual log out actions here (e.g., reset session, navigate to login screen)
            dashboard.switchToPanel("LoginPanel");
        }
    }

    private void ExportLogButtonActionPerformed(ActionEvent evt) {
        // Call the controller method with the necessary view components
        AuditController.exportLogFile(this, AuditLogTable);  // Passing the parent frame (this) and JTable
    }

    private void dumpDbButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Call the controller method with the necessary parent frame
        AuditController.dumpDB(this);  // Passing the parent frame (this)
    }

}

