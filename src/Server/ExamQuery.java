package Server;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import Model.Choice;
import Model.Exam;

import Resources.Tools;

public class ExamQuery {
  public static boolean createChoices(ArrayList<Choice> choices, int questionId) {
    if (DBConnection.database != null) {
      try {
        PreparedStatement preparedStatement = DBConnection.database
            .prepareStatement("INSERT INTO CHOICE VALUES (?, ?, ?, ?)");

        preparedStatement.setInt(4, questionId);

        for (Choice choice : choices) {
          preparedStatement.setString(1, choice.getText());
          preparedStatement.setBytes(2, Tools.imageToBytes(choice.getMedia()));
          preparedStatement.setBoolean(3, choice.getIsCorrect());

          preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        return true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return false;
  }

  public static boolean createExam(Exam exam) {
    if (DBConnection.database != null) {
      try {
        PreparedStatement preparedStatement = DBConnection.database
            .prepareStatement("INSERT INTO EXAM VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, exam.getName());
        preparedStatement.setString(2, exam.getDescription());
        preparedStatement.setDate(3, new Date(exam.getOpenTime().getTime()));
        preparedStatement.setDate(4, new Date(exam.getCloseTime().getTime()));
        preparedStatement.setString(5, exam.getSubject());
        preparedStatement.setInt(6, exam.getDuration());
        preparedStatement.setBoolean(7, exam.getCanReviewed());
        preparedStatement.setFloat(8, exam.getTotal());
        preparedStatement.setInt(9, exam.getEasies());
        preparedStatement.setInt(10, exam.getMediums());
        preparedStatement.setInt(11, exam.getHards());
        preparedStatement.setInt(12, exam.getTeacherId());
        preparedStatement.setBoolean(13, exam.getCanRepeat());

        preparedStatement.executeUpdate();

        return true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return false;
  }
}
