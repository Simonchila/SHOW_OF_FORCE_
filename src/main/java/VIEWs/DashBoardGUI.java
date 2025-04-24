package VIEWs;

import MODEL.User;
import VIEWs.PANELs.*;
import javax.swing.*;
import java.awt.*;

public class DashBoardGUI extends JFrame {

    private User user;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public DashBoardGUI(User user) {
        this.user = user;
        addGuiComponents();
    }

    protected void addGuiComponents(){
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        switch (user.getRole().toLowerCase()) {
            case "admin":
                AdminPanel adminPanel = new AdminPanel(this);
                UserManagementPanel userManagementPanel = new UserManagementPanel(this);
                AuditLogPanel auditLogPanel = new AuditLogPanel(this);
                VehicleManagementPanel vehicleManagementPanel = new VehicleManagementPanel(this);

                cardPanel.add(adminPanel, "AdminPanel");
                cardPanel.add(userManagementPanel, "UserManagementPanel");
                cardPanel.add(auditLogPanel, "AuditLogPanel");
                cardPanel.add(vehicleManagementPanel, "VehicleManagementPanel");

                cardLayout.show(cardPanel, "AdminPanel");
                break;

            case "guard":
                GuardsPanel guardsPanel = new GuardsPanel(this);
                cardPanel.add(guardsPanel, "GuardsPanel");
                cardLayout.show(cardPanel, "GuardsPanel");
                break;

            default:
                ViewersPanel viewerPanel = new ViewersPanel(this); // Replace with your actual viewer panel
                cardPanel.add(viewerPanel, "ViewersPanel");
                cardLayout.show(cardPanel, "ViewerPanel");
                break;

        }

        setContentPane(cardPanel);
        setTitle("Dashboard");
        setSize(900, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void switchToPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }
}
