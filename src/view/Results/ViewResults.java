package View.Results;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.QuizCtrl;
import Model.Attempt;

public class ViewResults extends JPanel {
  public ViewResults(int userId) {
    super();

    JPanel wrapper = new JPanel(new GridLayout(0, 1));
    JScrollPane scrollPane = new JScrollPane(wrapper);

    setBorder(BorderFactory.createEmptyBorder(50, 100, 0, 100));

    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    for (Attempt attempt : QuizCtrl.getAllAttempt(userId)) {
      wrapper.add(new ResultItem(attempt));
      wrapper.add(Box.createVerticalStrut(16));
    }
  }
}
