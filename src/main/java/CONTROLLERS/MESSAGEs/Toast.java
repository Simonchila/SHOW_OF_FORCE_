package CONTROLLERS.MESSAGEs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toast extends JWindow {

    private final int SHOW_TIME = 2000; // milliseconds
    private final int ANIMATION_DURATION = 300; // milliseconds
    private final int START_Y_OFFSET = 50; // pixels from top
    private final int SLIDE_OFFSET = 20; // pixels to slide in from right

    public Toast(JFrame parentFrame, String message, Color bgColor) {
        super(parentFrame);

        // Create content panel with rounded corners
        JPanel panel = new RoundedPanel(10, bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        panel.setLayout(new BorderLayout());

        // Create message label
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label, BorderLayout.CENTER);

        setContentPane(panel);
        pack();

        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        setBackground(new Color(0, 0, 0, 0)); // Transparent background

        // Initial position (just off screen to the right)
        int x = parentFrame.getX() + parentFrame.getWidth() + SLIDE_OFFSET;
        int y = parentFrame.getY() + START_Y_OFFSET;
        setLocation(x, y);
    }

    public void showToast() {
        // Target position (centered horizontally)
        Point endPos = new Point(
                ((Window)getParent()).getX() + (((Window)getParent()).getWidth() - getWidth()) / 2,
                ((Window)getParent()).getY() + START_Y_OFFSET
        );

        // Slide in from right animation
        slideHorizontal(getLocation(), endPos, ANIMATION_DURATION, () -> {
            // After slide in, wait for SHOW_TIME then slide out left
            Timer stayTimer = new Timer(SHOW_TIME, e -> slideOutLeft());
            stayTimer.setRepeats(false);
            stayTimer.start();
        });
    }

    private void slideOutLeft() {
        Point start = getLocation();
        Point end = new Point(start.x - getWidth() - SLIDE_OFFSET, start.y);

        slideHorizontal(start, end, ANIMATION_DURATION, () -> dispose());
    }

    private void slideHorizontal(Point start, Point end, int duration, Runnable onComplete) {
        final long startTime = System.currentTimeMillis();
        final int totalDistanceX = end.x - start.x;

        Timer animTimer = new Timer(16, null); // ~60fps
        animTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsed = currentTime - startTime;

                if (elapsed >= duration) {
                    setLocation(end);
                    animTimer.stop();
                    onComplete.run();
                } else {
                    // Cubic easing for smooth motion
                    float progress = (float) elapsed / duration;
                    progress = easeOutCubic(progress);

                    int currentX = start.x + (int) (totalDistanceX * progress);
                    setLocation(currentX, start.y);
                }
            }
        });

        setVisible(true);
        animTimer.start();
    }

    private float easeOutCubic(float t) {
        return (float) (1 - Math.pow(1 - t, 3));
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;

        public RoundedPanel(int radius, Color bgColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(bgColor);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2d.dispose();
        }
    }
}