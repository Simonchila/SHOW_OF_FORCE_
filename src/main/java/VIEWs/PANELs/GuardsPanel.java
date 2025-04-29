package VIEWs.PANELs;

import CONTROLLERS.VehicleController;
import VIEWs.FORMs.DashBoardGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import java.io.File;

public class GuardsPanel extends JPanel {

    private DashBoardGUI dashboard;
    private JLabel GuardLabel;
    private JButton LogOutButton, SearchButton, addVehicleButton, deleteVehicleButton, validatePermitButton, generatePermitButton;
    private JTextField VehicleManagementLabel, searchTextField, fromDateField, toDateField, statusLabel;
    private JTable VehicleManagementTable;
    private JScrollPane jScrollPane2;
    private JComboBox<String> statusComboBox;

    public GuardsPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
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
        validatePermitButton = new JButton("VALIDATE PERMIT");
        generatePermitButton = new JButton("GENERATE PERMIT");
        LogOutButton = new JButton("LOG OUT");

        // Vehicle Table Setup
        VehicleManagementTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Guard ID", "LicensePlate", "EntryTime", "ExitTime", "Status"}
        ));
        jScrollPane2.setViewportView(VehicleManagementTable);

        // Button Action Listeners
        addVehicleButton.addActionListener(this::addVehicleButtonActionPerformed);
        deleteVehicleButton.addActionListener(this::deleteVehicleButtonActionPerformed);
        validatePermitButton.addActionListener(this::validatePermitButtonActionPerformed);
        //generatePermitButton.addActionListener(this::generatePermitButtonActionPerformed);
        LogOutButton.addActionListener(this::LogOutButtonActionPerformed);

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
                                                .addComponent(validatePermitButton)
                                                .addGap(18)
                                                .addComponent(generatePermitButton)
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
                                .addComponent(validatePermitButton)
                                .addComponent(generatePermitButton)
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

    private void addVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Step 1: Create a panel with a field
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField plateField = new JTextField();
        JTextField ipAddressField = new JTextField();

        JLabel licensePlate = new JLabel("Enter License Plate: ");
        JLabel IpAddress = new JLabel("Enter IP Address: ");

        panel.add(licensePlate);
        panel.add(plateField);
        panel.add(IpAddress);
        panel.add(ipAddressField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Vehicle Entry",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String licensePlateStr = plateField.getText().trim();
            String ipAddressStr = ipAddressField.getText().trim();

            if (licensePlateStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "License plate is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (ipAddressStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ip Address is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                int loggedBy = dashboard.getUser().getId();

                CONTROLLERS.VehicleController.addVehicle(licensePlateStr, loggedBy, ipAddressStr);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // User cancelled
            JOptionPane.showMessageDialog(null, "Vehicle entry cancelled.");
        }
    }

    private void deleteVehicleButtonActionPerformed(ActionEvent evt) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField plateField = new JTextField();
        JLabel plateLabel = new JLabel("Enter License Plate: ");

        panel.add(plateLabel);
        panel.add(plateField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Do You Really Want to DELETE This Vehicle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String licensePlateStr = plateField.getText().trim();

            if (licensePlateStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "License plate is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                boolean deleted = VehicleController.deleteVehicle(licensePlateStr);
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

    private void validatePermitButtonActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select QR Code Image");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            CONTROLLERS.AdminController.validatePermitFromQRCode(selectedFile);
        }
    }
/*
    // this function as bdd
    private void generatePermitButtonActionPerformed(ActionEvent evt){
        String permitData = permitDataField.getText();

        if (permitData.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the permit data.");
            return;
        }

        // Open file chooser to specify where to save the QR code image
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save QR Code");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Ensure the file ends with .png
            if (!fileToSave.getName().endsWith(".png")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
            }

            // Call the controller to generate the QR code
            AdminController.generateQRCode(permitData, fileToSave);
        }
    }
*/
}
