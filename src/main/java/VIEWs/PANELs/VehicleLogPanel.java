package VIEWs.PANELs;

import CONTROLLERS.VehicleController;
import MODEL.VehicleLog;
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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class VehicleLogPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable vehicleLogTable;
    private JTextField searchField;
    private JComboBox<String> filterComboBox;

    public VehicleLogPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        populateVehicleLogTable();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("VEHICLE LOGS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(240, 240, 240));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        navPanel.setBackground(new Color(240, 240, 240));

        JButton adminDashboardBtn = createNavButton("ADMIN DASHBOARD",
                e -> dashboard.switchToPanel(new AdminPanel(dashboard)));
        JButton auditLogBtn = createNavButton("AUDIT LOGS",
                e -> dashboard.switchToPanel(new AuditLogPanel(dashboard)));

        navPanel.add(adminDashboardBtn);
        navPanel.add(auditLogBtn);

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

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        centerPanel.add(createSearchFilterPanel(), BorderLayout.NORTH);
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createSearchFilterPanel() {
        JPanel searchFilterPanel = new JPanel();
        searchFilterPanel.setLayout(new BoxLayout(searchFilterPanel, BoxLayout.X_AXIS));
        searchFilterPanel.setBackground(new Color(240, 240, 240));
        searchFilterPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        searchField.setToolTipText("Search by license plate, status, or IP address");
        searchField.addActionListener(this::searchAction);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this::searchAction);
        searchButton.setPreferredSize(new Dimension(100, 25));
        searchPanel.add(searchButton);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        filterPanel.setBackground(new Color(240, 240, 240));
        filterPanel.add(new JLabel("Filter by:"));
        filterComboBox = new JComboBox<>(new String[]{"All Logs", "Parked", "Exited", "By Guard", "By IP"});
        filterComboBox.addActionListener(this::filterAction);
        filterComboBox.setPreferredSize(new Dimension(120, 25));
        filterPanel.add(filterComboBox);

        searchFilterPanel.add(searchPanel);
        searchFilterPanel.add(Box.createHorizontalGlue());
        searchFilterPanel.add(filterPanel);

        return searchFilterPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Vehicle Log Records"));

        vehicleLogTable = new JTable(createTableModel());
        vehicleLogTable.setFillsViewportHeight(true);
        vehicleLogTable.setRowHeight(25);
        vehicleLogTable.setAutoCreateRowSorter(true);

        tablePanel.add(new JScrollPane(vehicleLogTable), BorderLayout.CENTER);
        return tablePanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        footerPanel.add(Box.createHorizontalGlue());

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.addActionListener(e -> populateVehicleLogTable());
        styleFooterButton(refreshBtn);

        JButton exportBtn = new JButton("EXPORT LOGS");
        exportBtn.addActionListener(this::exportLogsAction);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    private DefaultTableModel createTableModel() {
        return new DefaultTableModel(
                new Object[][]{},
                new String[]{"License Plate", "Entry Time", "Exit Time", "Status", "Logged By", "IP Address"}
        ) {
            Class<?>[] types = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void populateVehicleLogTable() {
        DefaultTableModel model = (DefaultTableModel) vehicleLogTable.getModel();
        model.setRowCount(0);

        try {
            List<VehicleLog> logs = VehicleController.fetchAllVehicleLogs();
            for (VehicleLog log : logs) {
                model.addRow(new Object[]{
                        log.getLicensePlate(),
                        log.getEntryTime(),
                        log.getExitTime(),
                        log.getStatus(),
                        log.getLoggedBy(),
                        log.getIpAddress()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading vehicle logs: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void searchAction(ActionEvent e) {
        String query = searchField.getText().trim();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(vehicleLogTable.getModel());
        vehicleLogTable.setRowSorter(sorter);

        if (!query.isEmpty()) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            sorter.setRowFilter(null);
        }
    }

    private void filterAction(ActionEvent e) {
        String selected = (String) filterComboBox.getSelectedItem();
        if (!"All Logs".equals(selected)) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(vehicleLogTable.getModel());
            vehicleLogTable.setRowSorter(sorter);
            int column;
            switch (selected) {
                case "Parked":
                case "Exited":
                    column = 3;
                    break;
                case "By Guard":
                    column = 4;
                    selected = ""; // remove "By Guard" literal filtering
                    break;
                case "By IP":
                    column = 5;
                    selected = "";
                    break;
                default:
                    column = 0;
            }
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + selected, column));
        } else {
            vehicleLogTable.setRowSorter(null);
        }
    }

    private void exportLogsAction(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Vehicle Logs");
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                    TableModel model = vehicleLogTable.getModel();
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        writer.print(model.getColumnName(i) + "\t");
                    }
                    writer.println();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        for (int j = 0; j < model.getColumnCount(); j++) {
                            writer.print(model.getValueAt(i, j) + "\t");
                        }
                        writer.println();
                    }
                    JOptionPane.showMessageDialog(this, "Logs exported successfully.");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void printReportAction(ActionEvent e) {
        try {
            boolean printed = vehicleLogTable.print();
            if (printed) {
                JOptionPane.showMessageDialog(this, "Print successful.");
            } else {
                JOptionPane.showMessageDialog(this, "Print canceled.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Printing failed: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
