package VIEWs.PANELs;

import VIEWs.DashBoardGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

public class VehicleLogPanel extends JPanel {

    // Variables declaration - do not modify
    private DashBoardGUI dashboard;
    private javax.swing.JTextField AdminDashBoardLabel;
    private javax.swing.JButton AuditLogButton;
    private javax.swing.JLabel GoToLabel;
    private javax.swing.JButton LogOutButton;
    private javax.swing.JPanel VehicleLog;
    private javax.swing.JTextField VehicleLogLabel;
    private javax.swing.JTable VehicleLogsTable;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JLabel filterByLabel;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration

    public VehicleLogPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        addComponents();
    }

    private void addComponents() {

        VehicleLog = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        VehicleLogsTable = new javax.swing.JTable();
        filterComboBox = new javax.swing.JComboBox<>();
        filterByLabel = new javax.swing.JLabel();
        AuditLogButton = new javax.swing.JButton();
        GoToLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        AdminDashBoardLabel = new javax.swing.JTextField();
        VehicleLogLabel = new javax.swing.JTextField();
        LogOutButton = new javax.swing.JButton();

        VehicleLogsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "LicensePlate", "EntryTime", "ExitTime", "Status", "LoggedBy", "IpAddress"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(VehicleLogsTable);

        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        filterByLabel.setText("FILTER BY");

        AuditLogButton.setText("AUDIT LOG");

        GoToLabel.setText("GO TO");

        searchTextField.setText("search here");
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setText("SEARCH");

        AdminDashBoardLabel.setText("ADMIN DASHBOARD");

        VehicleLogLabel.setText("     VEHICLE LOGS");

        LogOutButton.setText("LOG OUT");
        LogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VehicleLogLayout = new javax.swing.GroupLayout(VehicleLog);
        VehicleLog.setLayout(VehicleLogLayout);
        VehicleLogLayout.setHorizontalGroup(
                VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VehicleLogLayout.createSequentialGroup()
                                .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                                .addGap(138, 138, 138)
                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(searchButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(filterByLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33))
                                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                                .addGap(103, 103, 103)
                                                .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(LogOutButton)
                                                .addGap(239, 239, 239)
                                                .addComponent(GoToLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(AuditLogButton))
                                .addGap(133, 133, 133))
                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                                .addGap(495, 495, 495)
                                                .addComponent(VehicleLogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        VehicleLogLayout.setVerticalGroup(
                VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                .addContainerGap(129, Short.MAX_VALUE)
                                .addComponent(VehicleLogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(filterByLabel))
                                .addGap(32, 32, 32)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61))
                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(AuditLogButton)
                                                .addComponent(GoToLabel)
                                                .addComponent(LogOutButton))
                                        .addGroup(VehicleLogLayout.createSequentialGroup()
                                                .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(105, 105, 105)
                                                .addGroup(VehicleLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(searchButton))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1138, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(VehicleLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 743, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(VehicleLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }

    // Handle search when enter is pressed
    private void searchTextFieldActionPerformed(ActionEvent evt) {
        String searchQuery = searchTextField.getText().trim();
        if (!searchQuery.isEmpty()) {
            performSearch(searchQuery);
        }
    }

    // Perform the actual search/filtering logic
    private void performSearch(String searchQuery) {
        // This could be the logic to search in the user management table
        // For example, filter the rows based on the search query
        // Assuming you have access to the table model or data

        // Example of filtering logic:
        DefaultTableModel model = (DefaultTableModel) VehicleLogsTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        VehicleLogsTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchQuery));
    }

    // Handle filter combo box visibility changes (maybe reset filters when hidden)
    private void filterComboBox(ComponentEvent evt) {
        // Reset filter when combo box is hidden (optional)
        filterComboBox.setSelectedIndex(0); // Reset to default value
    }

    private void LogOutButtonActionPerformed(ActionEvent evt) {
        // For example, log out the user and navigate back to the login screen
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Perform actual log out actions here (e.g., reset session, navigate to login screen)
            dashboard.switchToPanel("LoginPanel");
        }
    }
}
