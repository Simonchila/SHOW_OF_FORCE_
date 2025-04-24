package VIEWs.PANELs;

import javax.swing.*;
import java.awt.event.*;

public class AuditLogPanel extends JPanel implements ActionListener{

    // Variables declaration - do not modify
    private JTextField AdminDashBoardLabel;
    private JPanel AuditLog;
    private JTextField AuditLogButton;
    private JTable AuditLogTable;
    private JButton LogOutButton;
    private JButton UserManagementButton;
    private JButton VehicleLogButton;
    private JButton VehicleManagementButton;
    private JLabel GoToLabel;
    private JScrollPane jScrollPane3;
    private JButton searchButton;
    private JTextField searchTextField;

    public AuditLogPanel() {
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
        AuditLogButton = new JTextField();
        LogOutButton = new JButton();
        AdminDashBoardLabel = new JTextField();

        VehicleManagementButton.setText("VEHICLE MANAGEMENT");
        VehicleManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehicleManagementButtonActionPerformed(evt);
            }
        });

        UserManagementButton.setText("USER  MANAGEMENT");

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

        VehicleLogButton.setText("VEHICLE LOGS");
        VehicleLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                VehicleLogButtonActionPerformed(evt);
            }
        });

        AuditLogButton.setText("       AUDIT LOG");
        AuditLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AuditLogButtonActionPerformed(evt);
            }
        });

        LogOutButton.setText("LOG OUT");

        AdminDashBoardLabel.setText("    ADMIN DASHBOARD");
        AdminDashBoardLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AdminDashBoardLabelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AuditLogLayout = new javax.swing.GroupLayout(AuditLog);
        AuditLog.setLayout(AuditLogLayout);
        AuditLogLayout.setHorizontalGroup(
                AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AuditLogLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LogOutButton)
                                .addGap(207, 207, 207)
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(UserManagementButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AuditLogLayout.createSequentialGroup()
                                                .addComponent(GoToLabel)
                                                .addGap(29, 29, 29)
                                                .addComponent(VehicleManagementButton)))
                                .addGap(85, 85, 85))
                        .addGroup(AuditLogLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(VehicleLogButton)
                                .addGap(101, 101, 101))
                        .addGroup(AuditLogLayout.createSequentialGroup()
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(466, 466, 466)
                                                .addComponent(AuditLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
        AuditLogLayout.setVerticalGroup(
                AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AuditLogLayout.createSequentialGroup()
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(VehicleManagementButton)
                                                        .addComponent(GoToLabel)
                                                        .addComponent(AdminDashBoardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(21, 21, 21)
                                                .addComponent(UserManagementButton))
                                        .addGroup(AuditLogLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(LogOutButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AuditLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AuditLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchButton))
                                        .addComponent(VehicleLogButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1121, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(AuditLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 651, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(AuditLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }

    private void VehicleManagementButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void VehicleLogButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void AuditLogButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void AdminDashBoardLabelActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

