package App;

import VIEWs.FORMs.BaseFrame;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // In your main method or initialization code:
            System.setProperty("sun.java2d.opengl", "true");
            System.setProperty("sun.java2d.noddraw", "true");

            BaseFrame baseFrame = new BaseFrame();
            baseFrame.setVisible(true);
            baseFrame.setLocation(20, 50);
        });
    }
}
