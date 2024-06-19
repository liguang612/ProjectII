package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import Resources.Constants;
import Resources.Constants.FontType;
import View.Components.TextField;

public class Homepage extends JPanel {
    boolean isSearched = false;
    JPanel leftPanel;
    TextField searchBox = new TextField(new ImageIcon(Constants.imagePath + "search.png"),
            "Tìm kiếm đề thi", 14);

    public Homepage() {
        super();

        Animator animator = new Animator(1000, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                searchBox.setLocation(searchBox.getX(), 600 - (int) (500 * Math.pow(fraction, 0.5)));
            }
        });
        animator.setResolution(0);

        setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        setLayout(new GridLayout(1, 2, 0, 10));

        JLabel label1 = new JLabel("<html>Cùng ôn luyện các bài thi trắc nghiệm với IntelliQuiz</html>");
        label1.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(70f));
        label1.setHorizontalAlignment(JLabel.CENTER);

        searchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }

            @Override
            public void keyTyped(KeyEvent ke) {
                if (!searchBox.getText().isEmpty()) {
                    if (!isSearched) {
                        leftPanel.remove(label1);
                        isSearched = true;
                        animator.start();

                        leftPanel.revalidate();
                        leftPanel.repaint();
                    }
                } else {
                    isSearched = false;
                    animator.cancel();
                    leftPanel.add(label1, 1);

                    leftPanel.revalidate();
                    leftPanel.repaint();
                }
            }
        });
        searchBox.setBackground(Constants.gray01);
        searchBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        searchBox.setPreferredSize(new Dimension(600, 60));
        searchBox.setRadius(5);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(label1);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(searchBox);
        leftPanel.add(Box.createVerticalGlue());

        add(leftPanel);
        add(new JLabel(new ImageIcon(Constants.imagePath + "homePage.png")));
    }
}
