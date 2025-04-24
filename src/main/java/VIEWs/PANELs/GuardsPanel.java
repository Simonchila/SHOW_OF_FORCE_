package VIEWs.PANELs;

import DB_OBJs.CONTROLLERS.VehicleController;
import VIEWs.DashBoardGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GuardsPanel extends JPanel {

    private DashBoardGUI dashboard;
    private JTextField makeField, modelField, yearField, licensePlateField;
    private JLabel GuardLabel;
    private JButton LogOutButton, SearchButton, addVehicleButton, deleteVehicleButton;
    private JTextField VehicleManagementLabel, searchTextField, fromDateField, toDateField, statusLabel;
    private JTable VehicleManagementTable;
    private JScrollPane jScrollPane2;
    private JComboBox<String> statusComboBox;

    public GuardsPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        makeField = new JTextField("Make");
        modelField = new JTextField("Model");
        yearField = new JTextField("Year");
        licensePlateField = new JTextField("License Plate");


        addComponents();
    }

    private void addComponents() {
        jScrollPane2 = new JScrollPane();
        VehicleManagementTable = new JTable();
        VehicleManagementLabel = new JTextField("                        VEHICLE MANAGEMENT");
        searchTextField = new JTextField("search here");
        SearchButton = new JButton("SEARCH");
        fromDateField = new JTextField();
        toDateField = new JTextField();
        statusLabel = new JTextField("Status:");
        statusComboBox = new JComboBox<>(new String[]{"All", "Checked In", "Checked Out"});
        GuardLabel = new JLabel("    GUARDs DashBoard");
        addVehicleButton = new JButton("CHECK IN");
        deleteVehicleButton = new JButton("CHECK OUT");
        LogOutButton = new JButton("LOGOUT");

        // Vehicle Table Setup
        VehicleManagementTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Guard ID", "LicensePlate", "EntryTime", "ExitTime", "Status"}
        ));
        jScrollPane2.setViewportView(VehicleManagementTable);

        // Button Action Listeners
        addVehicleButton.addActionListener(this::addVehicleButtonActionPerformed);
        deleteVehicleButton.addActionListener(this::deleteVehicleButtonActionPerformed);
        LogOutButton.setText("LOG OUT");
        LogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        // Layout Setup
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(GuardLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(SearchButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(VehicleManagementLabel, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                .addComponent(addVehicleButton)
                                                .addGap(18)
                                                .addComponent(deleteVehicleButton)
                                                .addGap(18)
                                                .addComponent(LogOutButton)))
                                .addGap(30))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(GuardLabel)
                                .addComponent(addVehicleButton)
                                .addComponent(deleteVehicleButton)
                                .addComponent(LogOutButton))
                        .addGap(20)
                        .addComponent(VehicleManagementLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(SearchButton)
                                .addComponent(statusLabel)
                                .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(20)
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
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

    private void addVehicleButtonActionPerformed(ActionEvent evt) {
        String make = makeField.getText();
        String model = modelField.getText();
        String yearText = yearField.getText();
        String licensePlate = licensePlateField.getText();

        if (make.isEmpty() || model.isEmpty() || yearText.isEmpty() || licensePlate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return;
        }

        try {
            VehicleController.addVehicle(make, model, yearText, licensePlate);
            JOptionPane.showMessageDialog(null, "Vehicle added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Year must be a valid number.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
    }

    private void deleteVehicleButtonActionPerformed(ActionEvent evt) {
        String licensePlate = licensePlateField.getText();

        if (licensePlate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "License plate is required.");
            return;
        }

        try {
            boolean deleted = VehicleController.deleteVehicle(licensePlate);
            if (deleted) {
                JOptionPane.showMessageDialog(null, "Vehicle deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No vehicle found with that license plate.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
    }

}
