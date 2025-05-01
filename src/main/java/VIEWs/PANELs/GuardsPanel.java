package VIEWs.PANELs;

import CONTROLLERS.VehicleController;
import MODEL.Vehicle;
import VIEWs.FORMs.BaseFrame;
import VIEWs.FORMs.DashBoardGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.io.File;
import java.util.List;

public class GuardsPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable vehicleTable;
    private JTextField searchField;

    public GuardsPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Create header panel
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Create center panel with table
        add(createTablePanel(), BorderLayout.CENTER);

        // Create footer panel with action buttons
        add(createFooterPanel(), BorderLayout.SOUTH);

        // Populate table with data
        populateVehicleTable(vehicleTable);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("GUARD DASHBOARD");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(240, 240, 240));

        // Logout button
        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.addActionListener(this::logoutAction);
        logoutButton.setPreferredSize(new Dimension(100, 30));

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Vehicle Management"));

        // Create table with model
        vehicleTable = new JTable(createTableModel());
        vehicleTable.setFillsViewportHeight(true);
        vehicleTable.setRowHeight(25);
        vehicleTable.setAutoCreateRowSorter(true);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        searchField = new JTextField(20);
        searchField.setToolTipText("Search by license plate or guard ID");

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this::searchAction);

        // Status filter
        JLabel statusLabel = new JLabel("Status:");
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"All", "Checked In", "Checked Out"});

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(new JSeparator(SwingConstants.VERTICAL));
        searchPanel.add(statusLabel);
        searchPanel.add(statusCombo);

        tablePanel.add(searchPanel, BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(vehicleTable), BorderLayout.CENTER);

        return tablePanel;
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{"Guard ID", "Permit ID", "License Plate", "Entry Time", "Exit Time", "Status"}
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setBackground(new Color(240, 240, 240));

        JButton checkInBtn = new JButton("CHECK IN");
        checkInBtn.addActionListener(this::checkInAction);
        checkInBtn.setPreferredSize(new Dimension(150, 35));

        JButton checkOutBtn = new JButton("CHECK OUT");
        checkOutBtn.addActionListener(this::checkOutAction);
        checkOutBtn.setPreferredSize(new Dimension(150, 35));

        JButton validatePermitBtn = new JButton("VALIDATE PERMIT");
        validatePermitBtn.addActionListener(this::validatePermitAction);
        validatePermitBtn.setPreferredSize(new Dimension(180, 35));

        footerPanel.add(checkInBtn);
        footerPanel.add(checkOutBtn);
        footerPanel.add(validatePermitBtn);

        return footerPanel;
    }

    private void searchAction(ActionEvent e) {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) vehicleTable.getModel());
            vehicleTable.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            vehicleTable.setRowSorter(null);
        }
    }

    private void logoutAction(ActionEvent e) {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Log Out",
                JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            dashboard.dispose();
            new BaseFrame().setVisible(true);
        }
    }

    private void checkInAction(ActionEvent evt) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField plateField = new JTextField();
        JTextField ipAddressField = new JTextField();

        panel.add(new JLabel("Enter License Plate:"));
        panel.add(plateField);
        panel.add(new JLabel("Enter IP Address:"));
        panel.add(ipAddressField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Add Vehicle Entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String licensePlateStr = plateField.getText().trim();
            String ipAddressStr = ipAddressField.getText().trim();

            if (licensePlateStr.isEmpty() || ipAddressStr.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Both license plate and IP address are required.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                int loggedBy = dashboard.getUser().getId();
                VehicleController.addVehicle(licensePlateStr, loggedBy, ipAddressStr);
                populateVehicleTable(vehicleTable); // Refresh table
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error adding vehicle: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void checkOutAction(ActionEvent evt) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField plateField = new JTextField();
        panel.add(new JLabel("Enter License Plate:"));
        panel.add(plateField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Check Out Vehicle",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String licensePlateStr = plateField.getText().trim();

            if (licensePlateStr.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "License plate is required.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                boolean deleted = VehicleController.deleteVehicle(licensePlateStr);
                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Vehicle checked out successfully.");
                    populateVehicleTable(vehicleTable); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "No vehicle found with that license plate.",
                            "Not Found",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Database error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void validatePermitAction(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select QR Code Image");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                CONTROLLERS.AdminController.validatePermitFromQRCode(selectedFile);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error validating permit: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void populateVehicleTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            List<Vehicle> vehicles = VehicleController.fetchAll();
            for (Vehicle v : vehicles) {
                model.addRow(new Object[]{
                        v.getGuardId(),
                        v.getPermitId(),
                        v.getLicensePlate(),
                        v.getEntryTime(),
                        v.getExitTime(),
                        v.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error fetching vehicle data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}