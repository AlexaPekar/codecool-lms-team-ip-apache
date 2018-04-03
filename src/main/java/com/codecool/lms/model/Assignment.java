package com.codecool.lms.model;

public class Assignment {

    private Student student;
    private String answer;
    private int grade;
    private String title;


    public Assignment(Student student, String answer, String title) {
        this.student = student;
        this.answer = answer;
        this.title = title;
    }


    public Student getStudent() {
        return student;
    }

    public String getAnswer() {
        return answer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }
}
