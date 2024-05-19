package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Homepage {
    private GridBagLayout gb;
    private GridBagConstraints gbc;
    private JFrame homeFrame;

    public Homepage() {
        gbc = new GridBagConstraints();
        gb = new GridBagLayout();

        homeFrame = new JFrame("Phần mềm thi trắc nghiệm");

        homeFrame.getContentPane().setBackground(Color.WHITE);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        homeFrame.setLayout(gb);
        homeFrame.setSize(1000, 500);
        homeFrame.setVisible(true);
    }
}
