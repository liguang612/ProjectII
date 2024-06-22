package Controller;

import Model.Exam;
import Server.ExamQuery;

public class ExamCtrl {
  public static boolean createExam(Exam exam) {
    return ExamQuery.createExam(exam);
  }
}
