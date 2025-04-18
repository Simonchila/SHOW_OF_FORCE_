package GUIs;

import DB_OBJs.MyJDBC;
import DB_OBJs.User;

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
    private JTextField searchField;
    private JTextField licensePlateField;

    public DashBoardGUI(User user) {
        super("Dashboard - " + user.getUsername());
        this.user = user;

        addGuiComponents();
    }

    @Override
    protected void addGuiComponents() {
        setLayout(new BorderLayout());

        // Top panel for vehicle logging and search
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        if (user.getRole().equals("Guard") || user.getRole().equals("Admin")) {
            licensePlateField = new JTextField(15);
            JButton logBtn = new JButton("Log Vehicle Entry");

            logBtn.addActionListener(e -> {
                String plate = licensePlateField.getText().trim();
                if (!plate.isEmpty()) {
                    MyJDBC.logVehicleEntry(plate, user.getUsername());
                    licensePlateField.setText("");
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "License plate cannot be empty.");
                }
            });

            topPanel.add(new JLabel("License Plate:"));
            topPanel.add(licensePlateField);
            topPanel.add(logBtn);
        }

        // Search functionality
        searchField = new JTextField(20);
        topPanel.add(new JLabel("Search by Plate:"));
        topPanel.add(searchField);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterLogs(); }
            public void removeUpdate(DocumentEvent e) { filterLogs(); }
            public void changedUpdate(DocumentEvent e) { filterLogs(); }
        });

        add(topPanel, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"License Plate", "Entry Time", "Guard"}, 0);
        vehicleTable = new JTable(tableModel);
        add(new JScrollPane(vehicleTable), BorderLayout.CENTER);

        refreshTable();
    }

    private void refreshTable() {
        List<String[]> data = MyJDBC.getAllVehicleLogs();
        tableModel.setRowCount(0);
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }

    private void filterLogs() {
        String keyword = searchField.getText().trim();
        List<String[]> filteredData = keyword.isEmpty()
                ? MyJDBC.getAllVehicleLogs()
                : MyJDBC.getFilteredVehicleLogs(keyword);

        tableModel.setRowCount(0);
        for (String[] row : filteredData) {
            tableModel.addRow(row);
        }
    }
}
