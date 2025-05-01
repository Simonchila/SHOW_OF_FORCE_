package CONTROLLERS;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.Result;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Hashtable;

import static Constants.CommonConstants.*;

public class AdminController {

    public static void validatePermitFromQRCode(File qrFile) {
        try {
            // Decode QR
            BufferedImage bufferedImage = ImageIO.read(qrFile);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result qrResult = new MultiFormatReader().decode(bitmap);

            String licensePlate = qrResult.getText();  // Expect license plate from QR

            // DB Connection Setup
            String dbURL  = DB_URL;
            String dbUser = DB_USERNAME;
            String dbPass = DB_PASSWORD;

            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
            String sql = "SELECT * FROM Permit WHERE LicensePlate = ? AND ValidUntil > NOW()";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String permitDetails = "Permit ID: " + rs.getInt("PermitId") + "\n"
                        + "Expiry Date: " + rs.getDate("ExpiryDate") + "\n"
                        + "Issued At: " + rs.getTimestamp("IssuedAt") + "\n"
                        + "Valid Until: " + rs.getTimestamp("ValidUntil") + "\n"
                        + "License Plate: " + rs.getString("LicensePlate");
                JOptionPane.showMessageDialog(null, "✅ VALID PERMIT\n\n" + permitDetails);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid or Expired Permit for: " + licensePlate);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (NotFoundException nf) {
            JOptionPane.showMessageDialog(null, "No QR code found in image.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Validation Error: " + e.getMessage());
        }
    }

    public static void generateQRCode(String permitData, File saveFile) {
        try {
            // Create the QR code
            int width = 300;  // QR Code width
            int height = 300; // QR Code height
            String charset = "UTF-8"; // Encoding for the QR code

            // Set up hints for QR code generation
            Hashtable<EncodeHintType, String> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, charset);

            // Create a BitMatrix (matrix of pixels) for the QR Code
            BitMatrix bitMatrix = new MultiFormatWriter().encode(permitData, BarcodeFormat.QR_CODE, width, height, hintMap);

            // Create the BufferedImage from the BitMatrix
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            // Set the pixels of the image
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, (bitMatrix.get(x, y)) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // Save the image to a file
            File outputfile = new File(saveFile + ".png");
            ImageIO.write(image, "PNG", outputfile);

            JOptionPane.showMessageDialog(null, "QR Code saved successfully at: " + outputfile.getAbsolutePath());

        } catch (WriterException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating QR Code: " + e.getMessage());
        }
    }

}
