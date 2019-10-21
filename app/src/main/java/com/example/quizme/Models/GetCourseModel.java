package com.example.quizme.Models;

public class GetCourseModel {
   private String icon;
   private String name;
   private Integer no_questions;

    public GetCourseModel() {
    }

    public GetCourseModel(String icon, String name, Integer no_questions) {
        this.icon = icon;
        this.name = name;
        this.no_questions = no_questions;
    }

    public Integer getNo_questions() {
        return no_questions;
    }

    public void setNo_questions(Integer no_questions) {
        this.no_questions = no_questions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
