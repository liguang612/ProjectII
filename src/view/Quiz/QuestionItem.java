package View.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Controller.QuizCtrl;
import Model.Choice;
import Model.Question;

public class QuestionItem extends JPanel {
  ArrayList<Choice> choices;

  public QuestionItem(Question question, int n, Function<Integer, Boolean> callback) {
    super();

    ButtonGroup bg = new ButtonGroup();

    setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    add(new JLabel("Câu hỏi " + n + ": " + question.getAsk()));
    if (question.getMedia() != null) {
      add(new JLabel(question.getMedia()));
    }

    choices = QuizCtrl.getAllChoices(question.getId());
    Collections.shuffle(choices);

    if (isMultiChoice(choices)) {
      for (Choice choice : choices) {
        JCheckBox checkBox = new JCheckBox(choice.getText());
        checkBox.addItemListener(e -> {
          callback.apply(n);
        });

        add(checkBox);
        if (choice.getMedia() != null) {
          add(new JLabel(choice.getMedia()));
        }
      }
    } else {
      for (Choice choice : choices) {
        JRadioButton radioButton = new JRadioButton(choice.getText());
        radioButton.addItemListener(e -> {
          callback.apply(n);
        });

        add(radioButton);
        bg.add(radioButton);
        if (choice.getMedia() != null) {
          add(new JLabel(choice.getMedia()));
        }
      }
    }
  }

  private boolean isMultiChoice(ArrayList<Choice> choices) {
    int i = 0;

    for (Choice choice : choices) {
      i += choice.getIsCorrect() ? 1 : 0;
    }

    return i > 1;
  }
}
