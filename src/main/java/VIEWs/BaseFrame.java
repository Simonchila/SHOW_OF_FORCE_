package VIEWs;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {

    public BaseFrame(String title) {
        initialize(title);
    }

    private void initialize(String title) {
        setTitle(title);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Absolute layout (not recommended, but OK for now)
        setResizable(false);
        setLocationRelativeTo(null);
    }

    protected abstract void addGuiComponents();

    protected void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
