package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Question;
import Resources.Tools;

public class QuizQuery {
  public static ArrayList<Question> generateQuiz(int examId) {
    ArrayList<Question> questions = new ArrayList<>();
    if (DBConnection.database != null) {
      try {
        PreparedStatement preparedStatement = DBConnection.database
            .prepareStatement("SELECT * FROM QUESTION WHERE EXAM = ?");

        preparedStatement.setInt(1, examId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          questions.add(new Question(resultSet.getInt(1),
              resultSet.getString(2),
              resultSet.getBytes(3) == null ? null : Tools.BytesToImage(resultSet.getBytes(3)),
              resultSet.getInt(4),
              examId));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return questions;
  }
}
