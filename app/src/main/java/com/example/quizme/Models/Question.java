package com.example.quizme.Models;

import java.util.ArrayList;

public class Question {
  private ArrayList<QuestionItem> questions;

  public ArrayList<QuestionItem> getQuestions() {
    return questions;
  }

  public void setQuestions(ArrayList<QuestionItem> questions) {
    this.questions = questions;
  }
}
