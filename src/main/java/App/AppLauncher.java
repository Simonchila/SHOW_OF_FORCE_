package App;

import VIEWs.FORMs.BaseFrame;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BaseFrame baseFrame = new BaseFrame();
            baseFrame.setVisible(true);
            baseFrame.setLocation(20, 50);
        });
    }
}
