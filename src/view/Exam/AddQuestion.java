package View.Exam;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Resources.Constants;
import Resources.Tools;
import View.Components.Button;
import View.Components.Column;
import View.Components.RoundedPanel;
import View.Components.Row;
import View.Components.TextArea;
import View.Components.TextField;

public class AddQuestion extends RoundedPanel {
    private Button add, addChoice, delete;
    private TextArea ask;
    private TextField mark;
    private JLabel media;
    private JRadioButton easy, hard, medium;

    public AddQuestion(JPanel prevPanel) {
        super(16);

        ButtonGroup bg = new ButtonGroup();

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add = new Button("Thêm");
        add.setBackground(Constants.blue01);
        add.setForeground(Color.WHITE);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                prevPanel.add(new AddQuestion(prevPanel));
                remove(getComponentCount() - 1);
                add(new Row(0, delete));

                prevPanel.revalidate();
                prevPanel.repaint();
            }
        });

        addChoice = new Button("Thêm phương án",
                Tools.resize(new ImageIcon(Constants.imagePath + "ic_add.png"), 40, 40));
        addChoice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                add(new ChoiceSetting(AddQuestion.this), getComponentCount() - 2);

                revalidate();
                repaint();
            }
        });

        ask = new TextArea("Nội dung câu hỏi", 16);
        ask.setBorderColor(Constants.gray02);

        delete = new Button("Xóa");
        delete.setBackground(Constants.red01);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                prevPanel.remove(AddQuestion.this);

                prevPanel.revalidate();
                prevPanel.repaint();
            }
        });

        easy = new JRadioButton("Dễ");
        easy.setSelected(true);
        hard = new JRadioButton("Khó");
        medium = new JRadioButton("Trung bình");

        bg.add(easy);
        bg.add(medium);
        bg.add(hard);

        media = new JLabel("Thêm hình ảnh");
        media.setBackground(Constants.gray02);
        media.setCursor(new Cursor(Cursor.HAND_CURSOR));
        media.setForeground(Color.BLUE);
        media.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                ImageIcon ii = Tools.pickImage(getHeight() / 2, getWidth() / 2);

                if (ii != null) {
                    media.setText(null);
                    media.setIcon(ii);
                }
            }
        });

        mark = new TextField("Điểm", 16);
        mark.setBorderColor(Constants.gray02);

        add(new Column(8,
                ask,
                new Row(16, media),
                mark,
                new Row(16, easy, medium, hard)));
        add(new Row(0, addChoice));
        add(Box.createVerticalStrut(16));
        add(Box.createVerticalStrut(16));
        add(new Row(0, add));
    }

    class ChoiceSetting extends RoundedPanel {
        private Button delete;
        private JCheckBox isCorrect;
        private JLabel media;
        private TextField text;

        ChoiceSetting(RoundedPanel parent) {
            super(16);

            setBackground(Constants.gray01);
            setLayout(new GridLayout(1, 1));

            delete = new Button("", Tools.resize(new ImageIcon(Constants.imagePath + "ic_delete.png"), 28, 28));
            delete.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    parent.remove(ChoiceSetting.this);

                    parent.revalidate();
                    parent.repaint();
                }
            });

            isCorrect = new JCheckBox();
            isCorrect.setBackground(Color.WHITE);

            media = new JLabel("Thêm hình ảnh");
            media.setBackground(Constants.gray02);
            media.setCursor(new Cursor(Cursor.HAND_CURSOR));
            media.setForeground(Color.BLUE);
            media.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    ImageIcon ii = Tools.pickImage(getHeight() / 2, getWidth() / 2);

                    if (ii != null) {
                        media.setText(null);
                        media.setIcon(ii);
                    }
                }
            });

            text = new TextField("Đáp án", 14);
            text.setBorderColor(Constants.gray02);

            add(new Row(10,
                    isCorrect,
                    new Column(16, text, media),
                    delete));
        }

        public boolean isCorrect() {
            return isCorrect.isSelected();
        };
    }
}
