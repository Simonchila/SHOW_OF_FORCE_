package CONTROLLERS;

import MODEL.AuditLog;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Constants.CommonConstants.*;

public class AuditController {

    public static void dumpDB(JPanel parentPanel){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save SQL Backup File");
        int userSelection = fileChooser.showSaveDialog(parentPanel);  // Pass parent frame here

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String backupFilePath = fileToSave.getAbsolutePath();


            String dbHost = "localhost"; // replace if needed

            // Build command
            String dumpCommand = String.format(
                    "mysqldump -h%s -u%s -p%s %s -r \"%s.sql\"",
                    dbHost, DB_USERNAME, DB_PASSWORD, DB_URL, backupFilePath
            );

            try {
                Process runtimeProcess = Runtime.getRuntime().exec(dumpCommand);
                int processComplete = runtimeProcess.waitFor();

                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(parentPanel, "Database backup successful!");
                } else {
                    JOptionPane.showMessageDialog(parentPanel, "Could not create backup.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(parentPanel,"Error during backup: " + e.getMessage());
            }
        }
    }

    // This method will accept the file path as an argument from the View
    public static void exportLogFile(JPanel parentPanel, JTable auditLogTable) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF");
        int userSelection = fileChooser.showSaveDialog(parentPanel);  // Pass parent frame here

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                float fontSize = 12;
                float leading = 1.5f * fontSize;

                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float xStart = margin;
                float yPosition = yStart;

                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.newLineAtOffset(xStart, yPosition);

                // Header
                for (int col = 0; col < auditLogTable.getColumnCount(); col++) {
                    contentStream.showText(auditLogTable.getColumnName(col) + "  ");
                }
                contentStream.newLineAtOffset(0, -leading);

                // Rows
                for (int row = 0; row < auditLogTable.getRowCount(); row++) {
                    for (int col = 0; col < auditLogTable.getColumnCount(); col++) {
                        Object val = auditLogTable.getValueAt(row, col);
                        contentStream.showText((val == null ? "" : val.toString()) + "  ");
                    }
                    contentStream.newLineAtOffset(0, -leading);
                }

                contentStream.endText();
                contentStream.close();

                document.save(fileToSave + ".pdf");
                JOptionPane.showMessageDialog(parentPanel, "Exported to PDF successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(parentPanel, "Error exporting PDF: " + e.getMessage());
            }
        }
    }

    public static List<AuditLog> fetchAllLogs() throws Exception {
        List<AuditLog> auditLogs = new ArrayList<>();

        String query = """
        SELECT u.Username,
               a.Timestamp, 
               a.Action, 
               a.Details
           FROM AuditLog a
           JOIN User u ON a.UserId = u.UserId
           ORDER BY a.Timestamp DESC;
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AuditLog log = new AuditLog(
                        rs.getString("Username"),
                        rs.getString("Timestamp"),
                        rs.getString("Action"),
                        rs.getString("Details")
                );
                auditLogs.add(log);
            }

        } catch (Exception e) {
            throw new Exception("Failed to fetch audit logs: " + e.getMessage(), e);
        }

        return auditLogs;
    }
}
