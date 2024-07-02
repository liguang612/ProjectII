package View.Quiz;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.QuizCtrl;
import Model.Choice;
import Model.Question;
import View.Components.Column;

public class QuestionItem extends JPanel {
  ArrayList<Choice> choices;

  public QuestionItem(Question question, int n, Function<Integer, Boolean> callback) {
    super(new GridLayout(1, 1));

    setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

    choices = QuizCtrl.getAllChoices(question.getId());

    add(new Column(10, new JLabel("Câu hỏi " + n + ": " + question.getAsk()),
        question.getMedia() == null ? new JLabel() : new JLabel(question.getMedia())));
  }
}
