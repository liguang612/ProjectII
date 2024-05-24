package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constants;
import Resources.Constants.FontType;
import View.Components.GradientBorder;
import View.Components.RoundedPanel;

public class Login extends JPanel {
    private RoundedPanel signInPanel;

    public Login() {
        super(new BorderLayout());

        JLabel label1 = new JLabel("Lets you Sign In"), label2 = new JLabel("Chào mừng bạn quay trở lại");
        label1.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(37f));
        label2.setFont(Constants.getFont(FontType.QUICKSAND_REGULAR).deriveFont(28));

        signInPanel = new RoundedPanel(18);
        signInPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        signInPanel.setBackground(Color.WHITE);
        signInPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(),
                new GradientBorder(32, new Color(0x3E14142B, true), new Color(0x0014142B, true))));
        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        signInPanel.setMaximumSize(new Dimension(578, 667));
        signInPanel.setShadow(0, 32, 72);
        signInPanel.add(label1);
        signInPanel.add(label2);

        setBackground(Constants.neutral01);
        setLayout(new BorderLayout());

        add(Box.createHorizontalStrut(467), BorderLayout.WEST);
        add(Box.createHorizontalStrut(467), BorderLayout.EAST);
        add(Box.createVerticalStrut(153), BorderLayout.NORTH);
        add(Box.createVerticalStrut(153), BorderLayout.SOUTH);
        add(signInPanel, BorderLayout.CENTER);
    }
}
