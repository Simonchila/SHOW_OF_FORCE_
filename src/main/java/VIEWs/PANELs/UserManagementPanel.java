package VIEWs.PANELs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserManagementPanel extends JPanel  implements ActionListener {

    // Variables declaration - do not modify
    private JTextField AdminDashBoardLabel;
    private JToggleButton AuditLogButton;
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

    public UserManagementPanel() {
        addComponents();
    }


    private void addComponents() {

        UserManagementPanel = new javax.swing.JPanel();
        VehicleManagementButton = new javax.swing.JButton();
        AuditLogButton = new javax.swing.JToggleButton();
        GoToLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userManagementTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        filterComboBox = new javax.swing.JComboBox<>();
        AdminDashBoardLabel = new javax.swing.JTextField();
        filterbyLabel = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        logOutButton = new javax.swing.JButton();

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

        javax.swing.GroupLayout UserManagementPanelLayout = new javax.swing.GroupLayout(UserManagementPanel);
        UserManagementPanel.setLayout(UserManagementPanelLayout);
        UserManagementPanelLayout.setHorizontalGroup(
                UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(UserManagementPanelLayout.createSequentialGroup()
                                .addContainerGap(24, Short.MAX_VALUE)
                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(UserManagementPanelLayout.createSequentialGroup()
                                                                                .addGap(242, 242, 242)
                                                                                .addComponent(logOutButton)
                                                                                .addGap(151, 151, 151)
                                                                                .addComponent(GoToLabel))
                                                                        .addGroup(UserManagementPanelLayout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(searchButton)))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(VehicleManagementButton))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(AuditLogButton)
                                                                        .addGroup(UserManagementPanelLayout.createSequentialGroup()
                                                                                .addComponent(filterbyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(22, 22, 22)))
                                                                .addGap(30, 30, 30)))
                                                .addGap(39, 39, 39))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserManagementPanelLayout.createSequentialGroup()
                                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(364, 364, 364))))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(filterbyLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
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
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(UserManagementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }

    private void AuditLogButtonActionPerformed(ActionEvent evt) {
    }

    private void VehicleManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void filterComboBoxComponentHidden(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
    }

    private void AdminDashBoardLabelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

