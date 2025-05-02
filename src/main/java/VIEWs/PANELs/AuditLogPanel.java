package VIEWs.PANELs;

import CONTROLLERS.AuditController;
import MODEL.AuditLog;
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

public class AuditLogPanel extends JPanel {

    private final DashBoardGUI dashboard;
    private JTable auditLogTable;
    private JTextField searchField;

    public AuditLogPanel(DashBoardGUI dashboard) {
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
        //populateAuditLogTable();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Title
        JLabel titleLabel = new JLabel("AUDIT LOG SYSTEM");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(240, 240, 240));

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        navPanel.setBackground(new Color(240, 240, 240));

        JButton adminDashboardBtn = createNavButton("ADMIN DASHBOARD", e -> dashboard.switchToPanel(new AdminPanel(dashboard)));
        JButton userManagementBtn = createNavButton("USER MANAGEMENT", e -> dashboard.switchToPanel(new UserManagementPanel(dashboard)));
        JButton vehicleBtn = createNavButton("VEHICLES", e -> dashboard.switchToPanel(new VehicleManagementPanel(dashboard)));

        navPanel.add(adminDashboardBtn);
        navPanel.add(userManagementBtn);
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

        // Add search panel at the top
        centerPanel.add(createSearchPanel(), BorderLayout.NORTH);

        // Add table in the center
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        searchField = new JTextField(25);
        searchField.setToolTipText("Search by username, action, or time");
        searchField.addActionListener(this::searchAction);

        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this::searchAction);
        searchButton.setPreferredSize(new Dimension(100, 25));

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Audit Log Records"));

        // Create table with model
        auditLogTable = new JTable(createTableModel());
        auditLogTable.setFillsViewportHeight(true);
        auditLogTable.setRowHeight(25);
        auditLogTable.setAutoCreateRowSorter(true);

        tablePanel.add(new JScrollPane(auditLogTable), BorderLayout.CENTER);

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
        refreshBtn.addActionListener(e -> populateAuditLogTable());
        styleFooterButton(refreshBtn);

        JButton exportBtn = new JButton("EXPORT LOGS");
        exportBtn.addActionListener(this::exportLogsAction);
        styleFooterButton(exportBtn);

        JButton dumpBtn = new JButton("DUMP DATABASE");
        dumpBtn.addActionListener(this::dumpDatabaseAction);
        styleFooterButton(dumpBtn);

        footerPanel.add(refreshBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(exportBtn);
        footerPanel.add(Box.createHorizontalStrut(20));
        footerPanel.add(dumpBtn);

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
                new String[]{"Username", "Action", "Timestamp", "Details"}
        ) {
            Class<?>[] types = new Class[]{
                    String.class, String.class, String.class, String.class
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

    private void populateAuditLogTable() {
        DefaultTableModel model = (DefaultTableModel) auditLogTable.getModel();
        model.setRowCount(0); // Clear existing data

        try {
            List<AuditLog> logs = AuditController.fetchAllLogs();
            for (AuditLog log : logs) {
                model.addRow(new Object[]{
                        log.getUsername(),
                        log.getAction(),
                        log.getTimestamp(),
                        log.getDetails()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading audit logs: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void searchAction(ActionEvent e) {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(auditLogTable.getModel());
            auditLogTable.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            auditLogTable.setRowSorter(null);
        }
    }

    private void exportLogsAction(ActionEvent e) {
        AuditController.exportLogFile(this, auditLogTable);
    }

    private void dumpDatabaseAction(ActionEvent e) {
        AuditController.dumpDB(this);
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