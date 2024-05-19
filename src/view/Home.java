package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constants;
import Resources.Constants.FontType;
import View.Components.TextField;

public class Home extends JPanel {
    JPanel leftPanel;
    TextField searchBox = new TextField(new ImageIcon(Constants.imagePath + "search.png"),
            "Tìm kiếm đề thi", 14);

    public Home() {
        super();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
        setLayout(new GridLayout(1, 2, 0, 10));

        JLabel label1 = new JLabel("<html>Cùng ôn luyện các bài thi trắc nghiệm với IntelliQuiz</html>");
        label1.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(70f));
        label1.setHorizontalAlignment(JLabel.CENTER);

        searchBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        searchBox.setPreferredSize(new Dimension(600, 60));

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
