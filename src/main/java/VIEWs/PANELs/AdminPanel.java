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
import java.awt.event.*;
import java.util.List;

public class AdminPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable vehicleTable;
    private JTextField searchField;

    public AdminPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Create header panel
        add(createHeaderPanel(), BorderLayout.NORTH);

        // Create center panel with table
        add(createTablePanel(), BorderLayout.CENTER);

        // Create footer panel
        add(createFooterPanel(), BorderLayout.SOUTH);

        // Populate table with data
        populateVehicleTable(vehicleTable);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Title and logout
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("ADMIN DASHBOARD");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(240, 240, 240));

        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.addActionListener(this::logoutAction);
        logoutButton.setPreferredSize(new Dimension(100, 30));

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Vehicle Records"));

        // Create table with model
        vehicleTable = new JTable(createTableModel());
        vehicleTable.setFillsViewportHeight(true);
        vehicleTable.setRowHeight(25);
        vehicleTable.setAutoCreateRowSorter(true);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        searchField = new JTextField(20);
        searchField.setToolTipText("Search by any field");

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this::searchAction);

        // Date filter components
        JLabel fromLabel = new JLabel("From:");
        JTextField fromDateField = new JTextField(10);
        JLabel toLabel = new JLabel("To:");
        JTextField toDateField = new JTextField(10);

        // Status filter
        JLabel statusLabel = new JLabel("Status:");
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"All", "Parked", "Exited"});

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(new JSeparator(SwingConstants.VERTICAL));
        searchPanel.add(fromLabel);
        searchPanel.add(fromDateField);
        searchPanel.add(toLabel);
        searchPanel.add(toDateField);
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

        JButton userManagementBtn = new JButton("USER MANAGEMENT");
        userManagementBtn.addActionListener(e -> dashboard.switchToPanel(new UserManagementPanel(dashboard)));
        userManagementBtn.setPreferredSize(new Dimension(180, 35));

        JButton vehicleManagementBtn = new JButton("VEHICLE MANAGEMENT");
        vehicleManagementBtn.addActionListener(e -> dashboard.switchToPanel(new VehicleManagementPanel(dashboard)));
        vehicleManagementBtn.setPreferredSize(new Dimension(180, 35));

        JButton auditLogsBtn = new JButton("AUDIT LOGS");
        auditLogsBtn.addActionListener(e -> dashboard.switchToPanel(new AuditLogPanel(dashboard)));
        auditLogsBtn.setPreferredSize(new Dimension(180, 35));

        footerPanel.add(userManagementBtn);
        footerPanel.add(vehicleManagementBtn);
        footerPanel.add(auditLogsBtn);

        return footerPanel;
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
            JOptionPane.showMessageDialog(this, "Error fetching vehicle data: " + e.getMessage());
        }
    }
}