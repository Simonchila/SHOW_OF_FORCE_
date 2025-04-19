package App;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomTableRenderer extends DefaultTableCellRenderer {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String entryTimeStr = (String) table.getValueAt(row, 1);  // Assuming column 1 is "entry_time"
        String guard = (String) table.getValueAt(row, 2);         // Assuming column 2 is "guard"

        try {
            LocalDateTime entryTime = LocalDateTime.parse(entryTimeStr, formatter);
            Duration duration = Duration.between(entryTime, LocalDateTime.now());

            if (duration.toHours() > 24) {
                c.setBackground(new Color(255, 102, 102)); // Light red
            } else {
                c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
            }

            // Tooltip shows entry time and guard
            if (c instanceof JComponent) {
                ((JComponent) c).setToolTipText("Logged by: " + guard + " at " + entryTimeStr);
            }
        } catch (Exception e) {
            c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
        }

        return c;
    }
}
