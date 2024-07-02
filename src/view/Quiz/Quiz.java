package View.Quiz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import Controller.QuizCtrl;
import Model.Exam;
import Model.Question;
import Resources.Constants;
import View.Components.Button;
import View.Components.Column;
import View.Components.Dialog;
import View.Components.RoundedPanel;
import View.Components.Row;

public class Quiz extends JFrame {
  ArrayList<Button> questionButtons = new ArrayList<>();
  ArrayList<Question> questions;
  Button submit;
  RoundedPanel controlPanel;

  public Quiz(Exam exam, JFrame parentFrame) {
    super();

    GridBagConstraints gbc = new GridBagConstraints();
    JPanel leftPanel = new JPanel(), rightPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(rightPanel);

    getContentPane().setBackground(Constants.gray01);
    setAlwaysOnTop(true);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    getContentPane().setLayout(new GridBagLayout());
    setResizable(false);
    setUndecorated(true);
    setSize(1000, 500);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent we) {
        setEnabled(false);

        new Dialog(Quiz.this, "Bạn chưa nộp bài, nộp bài?", x -> {
          Quiz.this.dispose();
          return true;
        });
      }

      public void windowClosed(WindowEvent we) {
        parentFrame.setEnabled(true);
        parentFrame.toFront();
      }
    });

    controlPanel = new RoundedPanel(16, Constants.gray02);
    controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    controlPanel.setLayout(new GridLayout(0, 5, 10, 10));
    for (int i = 0; i < exam.getQuestionCount(); i++) {
      Button button = new Button("" + (i + 1));
      button.setBorderColor(Constants.gray02);
      button.setRadius(10);
      button.addActionListener(e -> {
      });

      questionButtons.add(button);

      controlPanel.add(button);
    }

    submit = new Button("Nộp bài");
    submit.setBackground(Constants.blue01);
    submit.setForeground(Color.WHITE);

    leftPanel.add(new Column(12, controlPanel,
        new Row(0, Box.createHorizontalGlue(), submit, Box.createHorizontalGlue())));

    questions = QuizCtrl.generateQuiz(exam);

    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    for (int i = 0; i < questions.size(); i++) {
      rightPanel.add(new QuestionItem(questions.get(i), i, x -> {
        questionButtons.get(x).setBackground(Constants.blue01);
        questionButtons.get(x).setForeground(Color.WHITE);

        return true;
      }));
      rightPanel.add(new JSeparator());
    }

    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 11;
    gbc.weighty = 1;
    getContentPane().add(leftPanel, gbc);

    gbc.gridx = 1;
    gbc.weightx = 23;
    getContentPane().add(scrollPane, gbc);

    setVisible(true);
  }
}
