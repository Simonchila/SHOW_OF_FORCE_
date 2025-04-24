package VIEWs.PANELs;

import VIEWs.DashBoardGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;

public class ViewersPanel extends JPanel{

    private DashBoardGUI dashboard;
    private JButton LogOutButton;
    private JLabel VehicleManagementSystemLabel;
    private JLabel fromLabel;
    private JTextField fromTextField;
    private JScrollPane jScrollPane2;
    private JButton searchButton;
    private JTextField searchTextField;
    private JComboBox<String> statusCombox;
    private JLabel statusLabel;
    private JTextField toDateField;
    private JLabel toLabel;
    private JTable vehicleManagementSystem;

    public ViewersPanel(DashBoardGUI dashboard) {
        this.dashboard = dashboard;
        addComponents();
    }

    private void addComponents() {
        jScrollPane2 = new JScrollPane();
        vehicleManagementSystem = new JTable();
        searchTextField = new JTextField();
        searchButton = new JButton("SEARCH");
        toLabel = new JLabel("To:");
        toDateField = new JTextField();
        fromLabel = new JLabel("From:");
        fromTextField = new JTextField();
        statusLabel = new JLabel("Status:");
        statusCombox = new JComboBox<>(new String[]{"All", "Entered", "Exited", "Pending"});
        VehicleManagementSystemLabel = new JLabel("VEHICLE MANAGEMENT SYSTEM");
        LogOutButton = new JButton("LOG OUT");

        setLayout(new GroupLayout(this));
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String[]{"Guard ID", "LicensePlate", "EntryTime", "ExitTime", "Status"}
        ) {
            Class<?>[] types = new Class[]{
                    Integer.class, String.class, String.class, String.class, String.class
            };
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        vehicleManagementSystem.setModel(model);
        jScrollPane2.setViewportView(vehicleManagementSystem);

        fromTextField.addActionListener(this::fromTextFieldActionPerformed);
        searchButton.addActionListener(this::searchButtonActionPerformed);
        searchTextField.addActionListener(this::searchTextFieldActionPerformed);
        statusCombox.addActionListener(this::statusComboxActionPerformed);
        LogOutButton.setText("LOG OUT");
        LogOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 940, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchButton)
                                        .addGap(30)
                                        .addComponent(toLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(toDateField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30)
                                        .addComponent(fromLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fromTextField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30)
                                        .addComponent(statusLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusCombox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(VehicleManagementSystemLabel)
                                        .addGap(300)
                                        .addComponent(LogOutButton)))
                        .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(VehicleManagementSystemLabel)
                        .addComponent(LogOutButton))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton)
                        .addComponent(toLabel)
                        .addComponent(toDateField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(fromLabel)
                        .addComponent(fromTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusLabel)
                        .addComponent(statusCombox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
        );
    }

    private void fromTextFieldActionPerformed(ActionEvent evt) {
        // logic here if needed
    }

    private void searchTextFieldActionPerformed(ActionEvent evt) {
        performSearch(searchTextField.getText().trim());
    }

    private void searchButtonActionPerformed(ActionEvent evt) {
        String searchQuery = searchTextField.getText().trim();
        if (!searchQuery.isEmpty()) {
            performSearch(searchQuery);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a search query", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void statusComboxActionPerformed(ActionEvent evt) {
        // Optional: update table based on status filter
    }

    private void LogOutButtonActionPerformed(ActionEvent evt) {
        // For example, log out the user and navigate back to the login screen
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // Perform actual log out actions here (e.g., reset session, navigate to login screen)
            dashboard.switchToPanel("LoginPanel");
        }
    }

    private void performSearch(String query) {
        DefaultTableModel model = (DefaultTableModel) vehicleManagementSystem.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        vehicleManagementSystem.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }
}
