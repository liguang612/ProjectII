package View.Exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Model.Exam;
import Resources.Constants;
import Resources.Constants.FontType;
import View.Components.Button;
import View.Components.Column;
import View.Components.RoundedPanel;
import View.Components.Row;

public class ViewExam extends JPanel {
  public ViewExam(Exam exam) {
    super(new BorderLayout());

    Button cancel = new Button("Quay lại"), confirm = new Button("Làm bài"), view = new Button("Xem kết quả");
    Calendar calendar = Calendar.getInstance();
    JLabel name = new JLabel(exam.getName()), outOfTime = new JLabel("Đã quá thời hạn làm bài");
    RoundedPanel wrapper = new RoundedPanel(18, Constants.gray02);

    cancel.setBackground(Constants.gray02);

    confirm.setBackground(Constants.blue01);
    confirm.setForeground(Color.WHITE);

    name.setFont(Constants.getFont(FontType.QUICKSAND_BOLD));

    outOfTime.setFont(outOfTime.getFont().deriveFont(Font.ITALIC));

    view.setBackground(Constants.neutral02);

    wrapper.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    wrapper.setLayout(new GridLayout(1, 1));
    wrapper.setMaximumSize(new Dimension(578, 667));
    wrapper.setMinimumSize(new Dimension(578, 667));
    wrapper.add(new Column(12, name,
        new JSeparator(),
        new JLabel(exam.getDescription()),
        new JLabel("<html>Thời gian: <b>" + exam.getDuration() + "</b></html>",
            new ImageIcon(Constants.imagePath + "ic_time.png"), JLabel.LEFT),
        new Row(16,
            new JLabel(
                "<html>Số câu hỏi: <b>" + (exam.getEasies() + exam.getMediums() + exam.getHards()) + "</b></html>",
                new ImageIcon(Constants.imagePath + "ic_question.png"), JLabel.LEFT),
            new JLabel("<html>Tổng điểm: <b>" + exam.getTotal() + "</b></html>",
                new ImageIcon(Constants.imagePath + "ic_medal.png"), JLabel.LEFT)),
        new JLabel("Môn học", new ImageIcon(Constants.imagePath + "ic_subject.png"), JLabel.LEFT),
        new JSeparator(),
        exam.getCloseTime().after(calendar.getTime()) && exam.getOpenTime().before(calendar.getTime())
            ? new Row(16, Box.createHorizontalGlue(), cancel, confirm)
            : new Row(16, Box.createHorizontalGlue(), outOfTime, view)));

    add(Box.createHorizontalStrut(467), BorderLayout.WEST);
    add(Box.createHorizontalStrut(467), BorderLayout.EAST);
    add(Box.createVerticalStrut(153), BorderLayout.NORTH);
    add(Box.createVerticalStrut(153), BorderLayout.SOUTH);
    add(wrapper, BorderLayout.CENTER);
  }
}
