package com.example.quizme.Models;

public class Course {
  private ProposedAnswer proposedAnswer;
  private Question question;

  public ProposedAnswer getProposedAnswer() {
    return proposedAnswer;
  }

  public void setProposedAnswer(ProposedAnswer proposedAnswer) {
    this.proposedAnswer = proposedAnswer;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
