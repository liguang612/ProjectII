package Server;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import Model.Choice;
import Model.Exam;
import Model.Question;
import Resources.Tools;

public class ExamQuery {
  public static boolean createChoices(ArrayList<Choice> choices, int questionId) {
    if (DBConnection.database != null) {
      try {
        PreparedStatement preparedStatement = DBConnection.database
            .prepareStatement("INSERT INTO CHOICE VALUES (?, ?, ?, ?)");

        for (Choice choice : choices) {
          preparedStatement.setString(1, choice.getText());
          preparedStatement.setBytes(2, Tools.imageToBytes(choice.getMedia()));
          preparedStatement.setBoolean(3, choice.getIsCorrect());
          preparedStatement.setInt(4, choice.getQuestionId());

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

  public static int createExam(Exam exam) {
    if (DBConnection.database != null) {
      try {
        PreparedStatement preparedStatement = DBConnection.database
            .prepareStatement("INSERT INTO EXAM VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, exam.getName());
        preparedStatement.setString(2, exam.getDescription());
        preparedStatement.setDate(3, new Date(exam.getOpenTime().getTime()));
        preparedStatement.setDate(4, new Date(exam.getCloseTime().getTime()));
        preparedStatement.setString(5, exam.getSubject());
        preparedStatement.setInt(6, exam.getDuration());
        preparedStatement.setBoolean(7, exam.getCanRepeat());
        preparedStatement.setBoolean(8, exam.getCanReviewed());
        preparedStatement.setInt(9, exam.getEasies());
        preparedStatement.setFloat(10, exam.getEasyPts());
        preparedStatement.setInt(11, exam.getMediums());
        preparedStatement.setFloat(12, exam.getMediumPts());
        preparedStatement.setInt(13, exam.getHards());
        preparedStatement.setFloat(14, exam.getHardPts());
        preparedStatement.setInt(15, exam.getTeacherId());

        preparedStatement.executeUpdate();

        preparedStatement = DBConnection.database.prepareStatement("SEELCT SCOPE_IDENTITY()");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getInt(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return -1;
  }

  public static int createQuestion(Question question) {
    if (DBConnection.database != null) {
      try {
        PreparedStatement preparedStatement = DBConnection.database
            .prepareStatement("INSERT INTO QUESTION VALUES(?, ?, ?, ?)");

        preparedStatement.setString(1, question.getAsk());
        preparedStatement.setBytes(2, Tools.imageToBytes(question.getMedia()));
        preparedStatement.setInt(3, question.getLevel());
        preparedStatement.setInt(4, question.getExamId());

        preparedStatement.executeUpdate();

        preparedStatement = DBConnection.database.prepareStatement("SELECT SCOPE_IDENTITY()");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getInt(1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return -1;
  }
}
