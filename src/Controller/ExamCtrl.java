package Controller;

import java.util.ArrayList;

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
}
