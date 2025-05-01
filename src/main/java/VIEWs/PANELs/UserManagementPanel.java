package VIEWs.PANELs;

import CONTROLLERS.UserController;
import MODEL.User;
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

public class UserManagementPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable userTable;
    private JTextField searchField;
    private JComboBox<String> filterComboBox;

    public UserManagementPanel(DashBoardGUI dashboard) {
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
        populateUserTable();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Title
        JLabel titleLabel = new JLabel("USER MANAGEMENT");
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
        JButton vehicleBtn = createNavButton("VEHICLES",
                e -> dashboard.switchToPanel(new VehicleManagementPanel(dashboard)));

        navPanel.add(adminDashboardBtn);
        navPanel.add(auditLogBtn);
        navPanel.add(vehicleBtn);

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
        searchField.setToolTipText("Search by username or role");
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
        filterComboBox = new JComboBox<>(new String[]{"All Users", "Active", "Disabled", "Admin", "Guard", "Viewers"});
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
        tablePanel.setBorder(BorderFactory.createTitledBorder("User Records"));

        // Create table with model
        userTable = new JTable(createTableModel());
        userTable.setFillsViewportHeight(true);
        userTable.setRowHeight(25);
        userTable.setAutoCreateRowSorter(true);

        tablePanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

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
        refreshBtn.addActionListener(e -> populateUserTable());
        styleFooterButton(refreshBtn);

        JButton addUserBtn = new JButton("ADD USER");
        addUserBtn.addActionListener(this::addUserAction);
        styleFooterButton(addUserBtn);

        JButton deleteUserBtn = new JButton("DELETE USER");
        deleteUserBtn.addActionListener(this::deleteUserAction);
        styleFooterButton(deleteUserBtn);

        footerPanel.add(refreshBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(addUserBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(deleteUserBtn);

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

                new String[]{"Id", "Username", "Role", "Last Password Change", "Failed Logins", "Status", "Phone Number"}
        ) {
            final Class<?>[] types = new Class[]{
                    Integer.class, String.class, String.class, String.class, Integer.class, String.class, String.class
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

    private void populateUserTable() {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear existing data

        try {
            List<User> users = UserController.fetchAllUsers();
            for (User user : users) {
                String status = (Boolean) user.getStatus() ? "Disabled" : "Active";
                model.addRow(new Object[]{
                        user.getId(),
                        user.getUsername(),
                        user.getRole(),
                        user.getLastPasswordChange(),
                        user.getFailedLoginAttempts(),
                        status,  // accountLocked
                        user.getPhoneNumber()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading user data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void searchAction(ActionEvent e) {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(userTable.getModel());
            userTable.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            userTable.setRowSorter(null);
        }
    }

    private void filterAction(ActionEvent e) {
        String selectedFilter = (String) filterComboBox.getSelectedItem();
        if (!"All Users".equals(selectedFilter)) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(userTable.getModel());
            userTable.setRowSorter(sorter);

            int column = selectedFilter.equals("Active") || selectedFilter.equals("Disabled") ? 4 : 1;
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + selectedFilter, column));
        } else {
            userTable.setRowSorter(null);
        }
    }

    private void addUserAction(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Add user functionality will be implemented here");
    }

    private void deleteUserAction(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = userTable.convertRowIndexToModel(selectedRow);
            String username = (String) userTable.getModel().getValueAt(modelRow, 0);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete user: " + username + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    boolean success = UserController.deleteUser(username);
                    if (success) {
                        populateUserTable();
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Failed to delete user",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Error deleting user: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a user to delete",
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
