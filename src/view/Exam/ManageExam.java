package View.Exam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Resources.Callback;
import Resources.Constants;
import Resources.Tools;
import Resources.Constants.FontType;
import View.Components.Button;

public class ManageExam extends JPanel {
    private Button addExam;

    public ManageExam() {
        super();

        setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Quản lý đề thi");
        label1.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(70f));
        add(label1, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(0, 2, 16, 8));

        addExam = new Button("Tạo đề thi", Tools.resize(new ImageIcon(Constants.imagePath + "ic_add.png"), 40, 40));
        addExam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Callback.createExamCallback.createExam();
            }
        });
        addExam.setPreferredSize(new Dimension(0, 60));
        panel.add(addExam);

        if (panel.getComponentCount() < 10) {
            int tmp = panel.getComponentCount();
            for (int i = 0; i < 10 - tmp; i++) {
                JLabel label = new JLabel();

                panel.add(label);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }
}
