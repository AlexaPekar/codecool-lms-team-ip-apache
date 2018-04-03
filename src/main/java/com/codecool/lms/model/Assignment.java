package com.codecool.lms.model;

public class Assignment {

    private Student student;
    private String answer;
    private int grade;


    public Assignment(Student student, String answer) {
        this.student = student;
        this.answer = answer;
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
}
