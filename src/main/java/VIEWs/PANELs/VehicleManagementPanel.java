package VIEWs.PANELs;

import CONTROLLERS.VehicleController;
import MODEL.Vehicle;
import VIEWs.FORMs.BaseFrame;
import VIEWs.FORMs.DashBoardGUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VehicleManagementPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable vehicleTable;
    private JTextField searchField;
    private JComboBox<String> filterComboBox;

    public VehicleManagementPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Create header panel
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Create center panel with table
        add(createCenterPanel(), BorderLayout.CENTER);

        // Create footer panel with action buttons
        add(createFooterPanel(), BorderLayout.SOUTH);

        // Populate table with initial data
        populateVehicleTable();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Title
        JLabel titleLabel = new JLabel("VEHICLE MANAGEMENT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(240, 240, 240));

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        navPanel.setBackground(new Color(240, 240, 240));

        JButton adminDashboardBtn = createNavButton("ADMIN DASHBOARD",
                e -> dashboard.switchToPanel(new AdminPanel(dashboard)));
        JButton auditLogBtn = createNavButton("AUDIT LOGS",
                e -> dashboard.switchToPanel(new AuditLogPanel(dashboard)));
        JButton vehicleLogBtn = createNavButton("VEHICLE LOGS",
                e -> dashboard.switchToPanel(new VehicleLogPanel(dashboard)));

        navPanel.add(adminDashboardBtn);
        navPanel.add(auditLogBtn);
        navPanel.add(vehicleLogBtn);

        // Logout button
        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.addActionListener(this::logoutAction);
        logoutButton.setPreferredSize(new Dimension(100, 30));

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(navPanel, BorderLayout.CENTER);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        return headerPanel;
    }

    private JButton createNavButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        button.setMargin(new Insets(2, 5, 2, 5));
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Add search/filter panel at the top
        centerPanel.add(createSearchFilterPanel(), BorderLayout.NORTH);

        // Add table in the center
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createSearchFilterPanel() {
        JPanel searchFilterPanel = new JPanel();
        searchFilterPanel.setLayout(new BoxLayout(searchFilterPanel, BoxLayout.X_AXIS));
        searchFilterPanel.setBackground(new Color(240, 240, 240));
        searchFilterPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        // Search components
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchField.setToolTipText("Search by license plate or status");
        searchField.addActionListener(this::searchAction);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this::searchAction);
        searchButton.setPreferredSize(new Dimension(100, 25));
        searchPanel.add(searchButton);

        // Filter components
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.add(new JLabel("Filter by:"));
        filterComboBox = new JComboBox<>(new String[]{"All Vehicles", "Parked", "Exited", "By Guard", "By Permit"});
        filterComboBox.addActionListener(this::filterAction);
        filterComboBox.setPreferredSize(new Dimension(120, 25));
        filterPanel.add(filterComboBox);

        // Add all panels to main filter panel with glue for spacing
        searchFilterPanel.add(searchPanel);
        searchFilterPanel.add(Box.createHorizontalGlue());
        searchFilterPanel.add(filterPanel);

        return searchFilterPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Vehicle Records"));

        // Create table with model
        vehicleTable = new JTable(createTableModel());
        vehicleTable.setFillsViewportHeight(true);
        vehicleTable.setRowHeight(25);
        vehicleTable.setAutoCreateRowSorter(true);

        tablePanel.add(new JScrollPane(vehicleTable), BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        // Add horizontal glue to center buttons
        footerPanel.add(Box.createHorizontalGlue());

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.addActionListener(e -> populateVehicleTable());
        styleFooterButton(refreshBtn);

        JButton addVehicleBtn = new JButton("ADD VEHICLE");
        addVehicleBtn.addActionListener(this::addVehicleAction);
        styleFooterButton(addVehicleBtn);

        JButton removeVehicleBtn = new JButton("REMOVE VEHICLE");
        removeVehicleBtn.addActionListener(this::removeVehicleAction);
        styleFooterButton(removeVehicleBtn);

        footerPanel.add(refreshBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(addVehicleBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(removeVehicleBtn);

        footerPanel.add(Box.createHorizontalGlue());

        return footerPanel;
    }

    private void styleFooterButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 35));
        button.setMaximumSize(new Dimension(150, 35));
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{"License Plate", "Entry Time", "Exit Time", "Status", "Guard ID", "Permit ID"}
        ) {
            Class<?>[] types = new Class[]{
                    String.class, String.class, String.class, String.class, Integer.class, String.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
    }

    private void populateVehicleTable() {
        DefaultTableModel model = (DefaultTableModel) vehicleTable.getModel();
        model.setRowCount(0); // Clear existing data

        try {
            List<Vehicle> vehicles = VehicleController.fetchAll();
            for (Vehicle vehicle : vehicles) {
                model.addRow(new Object[]{
                        vehicle.getLicensePlate(),
                        vehicle.getEntryTime(),
                        vehicle.getExitTime(),
                        vehicle.getStatus(),
                        vehicle.getGuardId(),
                        vehicle.getPermitId()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading vehicle data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void searchAction(ActionEvent e) {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(vehicleTable.getModel());
            vehicleTable.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            vehicleTable.setRowSorter(null);
        }
    }

    private void filterAction(ActionEvent e) {
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        if (!"All Vehicles".equals(selectedFilter)) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(vehicleTable.getModel());
            vehicleTable.setRowSorter(sorter);

            // Determine which column to filter based on selection
            int column;
            switch(selectedFilter) {
                case "Parked":
                case "Exited":
                    column = 3; // Status column
                    break;
                case "By Guard":
                    column = 4; // Guard ID column
                    break;
                case "By Permit":
                    column = 5; // Permit ID column
                    break;
                default:
                    column = 0; // Default to License Plate
            }

            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + selectedFilter, column));
        } else {
            vehicleTable.setRowSorter(null);
        }
    }

    private void addVehicleAction(ActionEvent e) {
        // Create dialog for adding a new vehicle
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JTextField licensePlateField = new JTextField();
        JTextField permitIdField = new JTextField();
        JTextField ipAddressField = new JTextField();

        panel.add(new JLabel("License Plate:"));
        panel.add(licensePlateField);
        panel.add(new JLabel("Permit ID:"));
        panel.add(permitIdField);
        panel.add(new JLabel("IP Address:"));
        panel.add(ipAddressField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Vehicle",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String licensePlate = licensePlateField.getText().trim();
                String permitId = permitIdField.getText().trim();
                String ipAddress = ipAddressField.getText().trim();

                if (licensePlate.isEmpty() || permitId.isEmpty() || ipAddress.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "All fields are required",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                int guardId = dashboard.getUser().getId(); // Get current user's ID
                //VehicleController.addVehicle(licensePlate, permitId, guardId, ipAddress);
                populateVehicleTable(); // Refresh table

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error adding vehicle: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void removeVehicleAction(ActionEvent e) {
        int selectedRow = vehicleTable.getSelectedRow();
        if (selectedRow != -1) {
            String licensePlate = (String) vehicleTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to remove vehicle: " + licensePlate + "?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean success = VehicleController.deleteVehicle(licensePlate);
                    if (success) {
                        populateVehicleTable(); // Refresh table
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Failed to remove vehicle",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Error removing vehicle: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a vehicle to remove",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );
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
}