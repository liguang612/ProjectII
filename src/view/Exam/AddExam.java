package View.Exam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.ExamCtrl;
import Model.Account;
import Model.Exam;
import Resources.Callback;
import Resources.Constants;
import Resources.Constants.FontType;
import Resources.Constants.ToastType;
import View.Components.Button;
import View.Components.Column;
import View.Components.Row;
import View.Components.Spinner;
import View.Components.TextField;

public class AddExam extends JPanel {
    Button cancel, confirm;
    TextField name, description, subject;
    Spinner open, close, easies, hards, mediums, duration;
    JCheckBox repeat, reviewed;
    JLabel total;

    public AddExam(Account user) {
        super();

        setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));
        setLayout(new BorderLayout());

        Calendar calendar = Calendar.getInstance();

        cancel = new Button("Hủy");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Callback.manageExamCallback.manageExam();
            }
        });
        cancel.setBackground(Constants.gray02);
        cancel.setForeground(Color.BLACK);
        cancel.setMargin(new Insets(12, 24, 12, 24));

        confirm = new Button("Tạo");
        confirm.setBackground(Constants.blue01);
        confirm.setForeground(Color.WHITE);
        confirm.setMargin(new Insets(12, 24, 12, 24));
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                create(user.getId());
            }
        });

        Date startDate = calendar.getTime();
        Date initialDate = startDate;
        calendar.add(Calendar.YEAR, 100);
        Date endDate = calendar.getTime();

        close = new Spinner(new SpinnerDateModel(initialDate, startDate, endDate, Calendar.MINUTE));
        close.setBorderColor(Constants.gray02);
        close.setEditor(new Spinner.DateEditor(close, "dd/MM/yyyy HH:mm"));
        close.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        open = new Spinner(new SpinnerDateModel(initialDate, startDate, endDate, Calendar.MINUTE));
        open.setBorderColor(Constants.gray02);
        open.setEditor(new Spinner.DateEditor(open, "dd/MM/yyyy HH:mm"));
        open.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        description = new TextField("Mô tả đề thi", 16);
        description.setBorderColor(Constants.gray02);
        description.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        duration = new Spinner(new SpinnerNumberModel(60, 1, 1440, 5));
        duration.setMinimumSize(new Dimension(200, 40));
        duration.setMaximumSize(new Dimension(200, 40));
        duration.setPreferredSize(new Dimension(200, 40));

        easies = new Spinner(new SpinnerNumberModel(20, 1, 1440, 1));
        easies.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                changeTotalQuestions();
            }
        });
        easies.setMaximumSize(new Dimension(200, 40));
        hards = new Spinner(new SpinnerNumberModel(10, 1, 1440, 1));
        hards.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                changeTotalQuestions();
            }
        });
        hards.setMaximumSize(new Dimension(200, 40));
        mediums = new Spinner(new SpinnerNumberModel(10, 1, 1440, 1));
        mediums.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                changeTotalQuestions();
            }
        });
        mediums.setMaximumSize(new Dimension(200, 40));

        name = new TextField("Tên đề thi", 16);
        name.setBorderColor(Constants.gray02);
        name.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        total = new JLabel(
                "Tổng: " + ((int) easies.getValue() + (int) mediums.getValue() + (int) hards.getValue()));

        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 10)), rightPanel = new JPanel();

        JLabel label1 = new JLabel("Tạo đề thi"), label2 = new JLabel("Thông tin chung"),
                label3 = new JLabel("Kho câu hỏi");
        label1.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(70f));
        label2.setFont(Constants.getFont(FontType.ROBOTO_REGULAR).deriveFont(30f));
        label3.setFont(Constants.getFont(FontType.ROBOTO_REGULAR).deriveFont(30f));

        repeat = new JCheckBox("Cho phép làm lại");

        reviewed = new JCheckBox("Cho phép xem đáp án");

        subject = new TextField("Môn học", 16);
        subject.setBorderColor(Constants.gray02);
        subject.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        panel.add(new Column(16,
                new Row(16, label2),
                name,
                description,
                subject,
                new Row(16, repeat, reviewed),
                Box.createVerticalStrut(16),
                new Row(16, new JLabel("Thời gian mở   "),
                        open,
                        Box.createHorizontalStrut(16),
                        new JLabel("Thời gian đóng   "),
                        close),
                new Row(0, new JLabel("Thời lượng làm bài   "), duration, new JLabel(" phút"),
                        Box.createHorizontalGlue()),
                Box.createVerticalStrut(16),
                new Row(16,
                        new JLabel("Sô lượng câu hỏi:\t"),
                        new JLabel("Dễ:"), easies,
                        new JLabel("Trung bình:"), mediums,
                        new JLabel("Khó:"), hards),
                new Row(0, total),
                new Row(16, cancel, confirm),
                Box.createVerticalGlue()));

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(new Row(0, label3));
        rightPanel.add(new AddQuestion(rightPanel));

        add(label1, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void changeTotalQuestions() {
        total.setText(
                "Tổng: " + ((int) easies.getValue() + (int) mediums.getValue() + (int) hards.getValue()));
    }

    void create(int userId) {
        System.out.println(Integer.parseInt(duration.getValue().toString()));

        boolean state = ExamCtrl.createExam(new Exam(
                name.getText(),
                description.getText(),
                ((Date) open.getValue()),
                ((Date) close.getValue()),
                subject.getText(),
                Integer.parseInt(duration.getValue().toString()),
                repeat.isSelected(),
                reviewed.isSelected(),
                (float) 0.0,
                Integer.parseInt(easies.getValue().toString()),
                Integer.parseInt(mediums.getValue().toString()),
                Integer.parseInt(hards.getValue().toString()),
                userId));

        if (state) {
            Callback.toastCallback.callbackToast("Tạo đề thi thành công",
                    ToastType.SUCCESS);
        } else {
            Callback.toastCallback.callbackToast("Tạo đề thi thất bại",
                    ToastType.ERROR);
        }
    }
}
