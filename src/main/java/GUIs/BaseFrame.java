package GUIs;

import DB_OBJs.*;
import javax.swing.*;

public abstract class BaseFrame extends JFrame {

    // Constructor when a User object is available
    public BaseFrame(String title) {
        initialize(title);
    }

    // Initialize the frame with common properties
    private void initialize(String title) {
        // Set the title of the frame
        setTitle(title);

        // Set size (in pixels)
        setSize(700, 700);

        // Terminate the program when the GUI is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set layout to null for absolute positioning (though it's not recommended in modern GUIs)
        setLayout(null);

        // Prevent GUI from being resized
        setResizable(false);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Call the subclass's addGuiComponents() to add specific components
        addGuiComponents();
    }

    // This method will need to be defined by the subclass
    // when inherited from BaseFrame
    protected abstract void addGuiComponents();

    // Show an error message in a dialog box
    protected void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
