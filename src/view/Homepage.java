package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import View.Components.Header;

public class Homepage {
    private GridBagLayout gb = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private Home home;
    private JFrame homeFrame;

    public Homepage() {

        homeFrame = new JFrame("Phần mềm thi trắc nghiệm");
        homeFrame.getContentPane().setBackground(Color.WHITE);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homeFrame.setLayout(gb);
        homeFrame.setSize(1000, 500);

        home = new Home();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        homeFrame.add(new Header(), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 962;
        homeFrame.add(home, gbc);

        homeFrame.setVisible(true);
    }
}
