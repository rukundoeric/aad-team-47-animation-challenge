package com.example.quizme.Models;

import java.util.ArrayList;

public class Answer {
  private ArrayList<AnswerItem> answers;
  private int question_id;

  public Answer(ArrayList<AnswerItem> answers, int question_id) {
    this.answers = answers;
    this.question_id = question_id;
  }

  public ArrayList<AnswerItem> getAnswers() {
    return answers;
  }

  public void setAnswers(ArrayList<AnswerItem> answers) {
    this.answers = answers;
  }

  public int getQuestion_id() {
    return question_id;
  }

  public void setQuestion_id(int question_id) {
    this.question_id = question_id;
  }
}
