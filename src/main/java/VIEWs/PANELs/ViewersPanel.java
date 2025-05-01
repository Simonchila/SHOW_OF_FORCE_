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
import java.util.List;

public class ViewersPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable vehicleTable;
    private JTextField searchField;
    private JTextField fromDateField;
    private JTextField toDateField;
    private JComboBox<String> statusComboBox;

    public ViewersPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Create header panel
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Create center panel with search filters and table
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
        JLabel titleLabel = new JLabel("VEHICLE MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        searchField.setToolTipText("Search by license plate or guard ID");
        searchField.addActionListener(this::searchAction);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this::searchAction);
        searchButton.setPreferredSize(new Dimension(100, 25));
        searchPanel.add(searchButton);

        // Date filters
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        datePanel.setBackground(new Color(240, 240, 240));
        datePanel.add(new JLabel("Date Range:"));
        datePanel.add(new JLabel("From:"));
        fromDateField = new JTextField(10);
        fromDateField.setToolTipText("Enter start date (YYYY-MM-DD)");
        datePanel.add(fromDateField);
        datePanel.add(new JLabel("To:"));
        toDateField = new JTextField(10);
        toDateField.setToolTipText("Enter end date (YYYY-MM-DD)");
        datePanel.add(toDateField);

        // Status filter
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        statusPanel.setBackground(new Color(240, 240, 240));
        statusPanel.add(new JLabel("Status:"));
        statusComboBox = new JComboBox<>(new String[]{"All", "Entered", "Exited", "Pending"});
        statusComboBox.addActionListener(this::statusFilterAction);
        statusComboBox.setPreferredSize(new Dimension(120, 25));
        statusPanel.add(statusComboBox);

        // Add all panels to main filter panel with glue for spacing
        searchFilterPanel.add(searchPanel);
        searchFilterPanel.add(Box.createHorizontalGlue());
        searchFilterPanel.add(datePanel);
        searchFilterPanel.add(Box.createHorizontalGlue());
        searchFilterPanel.add(statusPanel);

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

        JButton exportBtn = new JButton("EXPORT DATA");
        exportBtn.addActionListener(this::exportDataAction);
        styleFooterButton(exportBtn);

        JButton printBtn = new JButton("PRINT REPORT");
        printBtn.addActionListener(this::printReportAction);
        styleFooterButton(printBtn);

        footerPanel.add(refreshBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(exportBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(printBtn);

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
                new String[]{"Guard ID", "Permit ID", "License Plate", "Entry Time", "Exit Time", "Status"}
        ) {
            Class<?>[] types = new Class[]{
                    Integer.class, Integer.class, String.class, String.class, String.class, String.class
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
                        vehicle.getGuardId(),
                        vehicle.getPermitId(),
                        vehicle.getLicensePlate(),
                        vehicle.getEntryTime(),
                        vehicle.getExitTime(),
                        vehicle.getStatus()
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

    private void statusFilterAction(ActionEvent e) {
        String selectedStatus = (String) statusComboBox.getSelectedItem();
        if (!"All".equals(selectedStatus)) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(vehicleTable.getModel());
            vehicleTable.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + selectedStatus, 4)); // Column 4 is Status
        } else {
            vehicleTable.setRowSorter(null);
        }
    }

    private void exportDataAction(ActionEvent e) {
        // Implement export functionality
        JOptionPane.showMessageDialog(this, "Export functionality will be implemented here");
    }

    private void printReportAction(ActionEvent e) {
        // Implement print functionality
        JOptionPane.showMessageDialog(this, "Print functionality will be implemented here");
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