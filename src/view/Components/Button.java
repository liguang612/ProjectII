package View.Components;

import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
    private int radius = 16;

    public Button(String text) {
        super(text);

        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
    }

    public Button(String text, ImageIcon icon) {
        super(text, icon);

        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
    }

    public void setRadius(int radius) {
        this.radius = radius;

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth(), height = getHeight();
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;
        FontMetrics fm = graphics.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(getText(), graphics);

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width, height, radius, radius);
        graphics.setColor(getForeground());

        ImageIcon icon = (ImageIcon) getIcon();
        int iconHeight = 0, iconWidth = 0;
        if (icon != null) {
            iconWidth = icon.getIconWidth();
            iconHeight = icon.getIconHeight();
            int x = (width - iconWidth - (int) rect.getWidth()) / 2;
            int y = (height - iconHeight - (int) rect.getHeight()) / 2 + fm.getAscent();

            graphics.drawImage(icon.getImage(), x, y, null);

            graphics.drawString(getText(), (getWidth() - (int) rect.getWidth() + iconWidth) / 2,
                    (getHeight() - (int) rect.getHeight() + iconHeight) / 2);
        } else {
            graphics.drawString(getText(), (getWidth() - (int) rect.getWidth() + iconWidth) / 2,
                    (getHeight() - (int) rect.getHeight() + iconHeight) / 2 + fm.getAscent());
        }
    }
}