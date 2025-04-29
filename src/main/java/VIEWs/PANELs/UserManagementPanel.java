package VIEWs.PANELs;

import CONTROLLERS.UserController;
import VIEWs.FORMs.DashBoardGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;

public class UserManagementPanel extends JPanel{

    // Variables declaration - do not modify
    private DashBoardGUI dashboard;
    private JTextField AdminDashBoardLabel;
    private JToggleButton AuditLogButton;
    private JButton addUserButton;
    private JButton DeleteUser;
    private JLabel GoToLabel;
    private JPanel UserManagementPanel;
    private JButton VehicleManagementButton;
    private JComboBox<String> filterComboBox;
    private JLabel filterbyLabel;
    private JScrollPane jScrollPane1;
    private JTextField jTextField15;
    private JButton logOutButton;
    private JButton searchButton;
    private JTextField searchTextField;
    private JTable userManagementTable;
    // End of variables declaration

    public UserManagementPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        addComponents();
    }

    private void addComponents() {

        UserManagementPanel = new JPanel();
        VehicleManagementButton = new JButton();
        AuditLogButton = new JToggleButton();
        GoToLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        userManagementTable = new JTable();
        searchTextField = new JTextField();
        searchButton = new JButton();
        filterComboBox = new JComboBox<>();
        AdminDashBoardLabel = new JTextField();
        filterbyLabel = new JLabel();
        jTextField15 = new JTextField();
        logOutButton = new JButton();
        addUserButton = new JButton();
        DeleteUser = new JButton();

        VehicleManagementButton.setText("VEHICLE MANAGEMENT");
        VehicleManagementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehicleManagementButtonActionPerformed(evt);
            }
        });

        AuditLogButton.setText("AUDIT LOGS");
        AuditLogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AuditLogButtonActionPerformed(evt);
            }
        });

        GoToLabel.setText("GO TO:");

        userManagementTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String [] {
                        "USERNAME", "ROLE", "LAST PASSWORD CHANGE", "FAILED LOGIN ATTEMPTS", "ACCOUNT STATUS"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userManagementTable);
        if (userManagementTable.getColumnModel().getColumnCount() > 0) {
            userManagementTable.getColumnModel().getColumn(0).setResizable(false);
            userManagementTable.getColumnModel().getColumn(1).setResizable(false);
            userManagementTable.getColumnModel().getColumn(2).setResizable(false);
            userManagementTable.getColumnModel().getColumn(3).setResizable(false);
            userManagementTable.getColumnModel().getColumn(4).setResizable(false);
        }

        searchTextField.setText("Search here");
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchButton.setText("SEARCH");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filterComboBox.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                filterComboBoxComponentHidden(evt);
            }
        });

        AdminDashBoardLabel.setText("ADMIN DASHBOARD");
        AdminDashBoardLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminDashBoardLabelActionPerformed(evt);
            }
        });

        filterbyLabel.setText("FILTER BY");

        jTextField15.setText("           USER MANAGEMENT");
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        logOutButton.setText("LOG OUT");
        logOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        addUserButton.setText("AddUser");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserButtonActionPerformed(evt);
            }
        });

        DeleteUser.setText("DeleteUser");
        DeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserManagementPanelLayout = new javax.swing.GroupLayout(UserManagementPanel);
        UserManagementPanel.setLayout(UserManagementPanelLayout);
        UserManagementPanelLayout.setHorizontalGroup(
                UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UserManagementPanelLayout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchButton)
                                                                .addGap(39, 39, 39)
                                                                .addComponent(addUserButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(DeleteUser)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(filterbyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(2, 2, 2)
                                                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(AuditLogButton))
                                                                .addGap(30, 30, 30))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                                .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(242, 242, 242)
                                                                .addComponent(logOutButton)
                                                                .addGap(151, 151, 151)
                                                                .addComponent(GoToLabel)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(VehicleManagementButton)))
                                                .addGap(39, 39, 39))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(364, 364, 364))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30))))
        );
        UserManagementPanelLayout.setVerticalGroup(
                UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UserManagementPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(VehicleManagementButton)
                                        .addComponent(GoToLabel)
                                        .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(logOutButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AuditLogButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(filterbyLabel)
                                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchButton)
                                                .addComponent(addUserButton)
                                                .addComponent(DeleteUser)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(UserManagementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(UserManagementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }

    private void LogOutButtonActionPerformed(ActionEvent evt) {
        // For example, log out the user and navigate back to the login screen
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Perform actual log out actions here (e.g., reset session, navigate to login screen)
            dashboard.switchToPanel("LoginPanel");
        }
    }

    private void AuditLogButtonActionPerformed(ActionEvent evt) {
        dashboard.switchToPanel("AuditLogPanel");
    }

    private void VehicleManagementButtonActionPerformed(ActionEvent evt) {
        dashboard.switchToPanel("VehicleManagementPanel");
    }

    // Handle search when enter is pressed
    private void searchTextFieldActionPerformed(ActionEvent evt) {
        String searchQuery = searchTextField.getText().trim();
        if (!searchQuery.isEmpty()) {
            performSearch(searchQuery);
        }
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

    // Perform the actual search/filtering logic
    private void performSearch(String searchQuery) {
        // This could be the logic to search in the user management table
        // For example, filter the rows based on the search query
        // Assuming you have access to the table model or data

        // Example of filtering logic:
        DefaultTableModel model = (DefaultTableModel) userManagementTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        userManagementTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchQuery));
    }

    // Handle filter combo box visibility changes (maybe reset filters when hidden)
    private void filterComboBoxComponentHidden(ComponentEvent evt) {
        // Reset filter when combo box is hidden (optional)
        filterComboBox.setSelectedIndex(0); // Reset to default value
    }

    // Handle Admin Dashboard label action, possibly navigate to another panel
    private void AdminDashBoardLabelActionPerformed(ActionEvent evt) {
        // Navigate back to the Admin Dashboard panel
        // This is just an example action; adjust as per your dashboard setup
        dashboard.switchToPanel("AdminDashboardPanel");
    }

    // Handle JTextField 15 action, maybe used for the user management title or label
    private void jTextField15ActionPerformed(ActionEvent evt) {
        // For instance, display a message or perform an action when Enter is pressed
        String currentText = jTextField15.getText().trim();
        if (!currentText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You typed: " + currentText, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void AddUserButtonActionPerformed(ActionEvent evt) {
        // Implement logic to show RegisterFormGUI or add user
        //new RegisterFormPanel(); // Example: Launch registration form
    }

    private void DeleteUserActionPerformed(ActionEvent evt) {
        int selectedRow = userManagementTable.getSelectedRow();
        if (selectedRow != -1) {
            // Implement user deletion logic here
            String username = userManagementTable.getValueAt(selectedRow, 0).toString();
            // Use UserController to delete user by username
            UserController.deleteUser(username);
            ((DefaultTableModel) userManagementTable.getModel()).removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }

    protected void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}

