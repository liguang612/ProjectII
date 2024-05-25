package View.Components;

import javax.swing.*;

import Resources.Constants;

import java.awt.*;

public class RoundedPanel extends JPanel {
    private int radius;
    private boolean showBorder;
    private int shadowX, shadowY, shadowExtended;

    public RoundedPanel(int radius) {
        super();
        this.radius = radius;

        setOpaque(true);
    }

    public RoundedPanel(int radius, boolean showBorder) {
        super();
        this.radius = radius;
        this.showBorder = showBorder;

        setOpaque(true);
    }

    public void setShadow(int x, int y, int shadowExtended) {
        this.shadowX = x;
        this.shadowY = y;
        this.shadowExtended = shadowExtended;

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw shadow
        graphics.setColor(new Color(0, 0, 0, 21));
        graphics.fillRoundRect(shadowX, shadowY, width + shadowExtended, height + shadowExtended, radius, radius);

        // Rounded
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width, height, radius, radius);

        // Border
        if (showBorder) {
            graphics.setColor(Constants.gray02);

            graphics.drawArc(0, 0, radius, radius, 90, 90);
            graphics.drawArc(width - radius - 1, 0, radius, radius, 0, 90);
            graphics.drawArc(width - radius - 1, height - radius, radius, radius, 270, 90);
            graphics.drawArc(0, height - radius, radius, radius, 180, 90);

            graphics.drawLine(radius / 2, 0, width - radius / 2, 0);
            graphics.drawLine(0, radius / 2, 0, height - radius / 2);
            graphics.drawLine(radius / 2, height - 1, width - radius / 2, height - 1);
            graphics.drawLine(width - 1, radius / 2, width - 1, height - radius / 2);
        }
    }
}