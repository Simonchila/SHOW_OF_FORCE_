package GUIs;

import DB_OBJs.MyJDBC;
import DB_OBJs.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashBoardGUI extends BaseFrame {
    private final User user;
    private JTable vehicleTable;
    private DefaultTableModel tableModel;

    public DashBoardGUI(User user) {
        super("Dashboard - " + user.getUsername());
        this.user = user;
    }


    @Override
    protected void addGuiComponents() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"License Plate", "Entry Time", "Guard"}, 0);
        vehicleTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(vehicleTable);
        add(scrollPane, BorderLayout.CENTER);

        refreshTable();
    }

    private void refreshTable() {
        List<String[]> data = MyJDBC.getAllVehicleLogs();
        tableModel.setRowCount(0);
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }
}
