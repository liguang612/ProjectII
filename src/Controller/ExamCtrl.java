package Controller;

import java.util.ArrayList;
import java.util.Map;

import Model.Choice;
import Model.Exam;
import Model.Question;

import Server.ExamQuery;

public class ExamCtrl {
  public static boolean createChoices(ArrayList<Choice> choices) {
    return ExamQuery.createChoices(choices, 0);
  }

  public static int createExam(Exam exam) {
    return ExamQuery.createExam(exam);
  }

  public static int createQuestion(Question question) {
    return ExamQuery.createQuestion(question);
  }

  public static boolean deleteChoice(int choiceId) {
    return ExamQuery.deleteChoice(choiceId);
  }

  public static boolean deleteExam(int examId) {
    return ExamQuery.deleteExam(examId);
  }

  public static boolean deleteQuestion(int questionId) {
    return ExamQuery.deleteQuestion(questionId);
  }

  public static ArrayList<Exam> getAllExam(int userId) {
    return ExamQuery.getAllExam(userId);
  }

  public static Map<Question, ArrayList<Choice>> getAllQuestion(int examId) {
    return ExamQuery.getAllQuestion(examId);
  }
}
