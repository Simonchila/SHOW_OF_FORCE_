package VIEWs;

import DB_OBJs.CONTROLLERS.VehicleController;
import DB_OBJs.MyJDBC;
import MODEL.User;
import App.CustomTableRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class DashBoardGUI extends BaseFrame {
    private final User user;
    private JTable vehicleTable;
    private DefaultTableModel tableModel;
    private JTextField searchField, licensePlateField;
    private JComboBox<String> statusFilter;
    private JTextField dateFromField, dateToField;

    public DashBoardGUI(User user) {
        super("Dashboard - " + user.getUsername());
        this.user = user;
        addGuiComponents();
    }

    @Override
    protected void addGuiComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        if (user.getRole().equals("Guard") || user.getRole().equals("Admin")) {
            licensePlateField = new JTextField(10);
            JButton logBtn = new JButton("Log Entry");

            logBtn.addActionListener(e -> {
                String plate = licensePlateField.getText().trim();
                if (!plate.matches("[A-Z]{3}-\\d{4}")) {
                    showError("Invalid license plate format. Use format: ABC-1234");
                    return;
                }
                VehicleController.logVehicleEntry(plate, user.getUsername());
                licensePlateField.setText("");
                refreshTable();
            });

            topPanel.add(new JLabel("Plate:"));
            topPanel.add(licensePlateField);
            topPanel.add(logBtn);
        }

        searchField = new JTextField(10);
        dateFromField = new JTextField("2023-01-01", 8);
        dateToField = new JTextField("2025-12-31", 8);
        statusFilter = new JComboBox<>(new String[]{"All", "Parked", "Exited"});

        topPanel.add(new JLabel("Search Plate:"));
        topPanel.add(searchField);
        topPanel.add(new JLabel("From:"));
        topPanel.add(dateFromField);
        topPanel.add(new JLabel("To:"));
        topPanel.add(dateToField);
        topPanel.add(new JLabel("Status:"));
        topPanel.add(statusFilter);

        add(topPanel, BorderLayout.NORTH);

        DocumentListener liveFilter = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterLogs(); }
            public void removeUpdate(DocumentEvent e) { filterLogs(); }
            public void changedUpdate(DocumentEvent e) { filterLogs(); }
        };

        searchField.getDocument().addDocumentListener(liveFilter);
        dateFromField.getDocument().addDocumentListener(liveFilter);
        dateToField.getDocument().addDocumentListener(liveFilter);
        statusFilter.addActionListener(e -> filterLogs());

        tableModel = new DefaultTableModel(new String[]{"License Plate", "Entry Time", "Role: " + user.getRole(), "Status"}, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };

        vehicleTable = new JTable(tableModel);
        vehicleTable.setDefaultRenderer(Object.class, new CustomTableRenderer());

        vehicleTable.setToolTipText("");
        vehicleTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = vehicleTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    String entryTime = tableModel.getValueAt(row, 1).toString();
                    vehicleTable.setToolTipText("Logged at " + entryTime + " by " + user.getRole());
                }
            }
        });

        add(new JScrollPane(vehicleTable), BorderLayout.CENTER);
        refreshTable();
    }

    private void refreshTable() {
        List<String[]> data = VehicleController.getAllVehicleLogs();
        populateTable(data);
    }

    private void filterLogs() {
        String keyword = searchField.getText().trim();
        String fromDate = dateFromField.getText().trim();
        String toDate = dateToField.getText().trim();
        String status = statusFilter.getSelectedItem().toString();

        List<String[]> filtered = VehicleController.getFilteredVehicleLogs(keyword, fromDate, toDate, status);
        populateTable(filtered);
    }

    private void populateTable(List<String[]> data) {
        tableModel.setRowCount(0);
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }
}
