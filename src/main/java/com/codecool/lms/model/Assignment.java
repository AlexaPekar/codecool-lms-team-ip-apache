package com.codecool.lms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment {

    private Student student;
    private String answer;
    private int grade;
    private String title;
    private String data;


    public Assignment(Student student, String answer, String title) {
        this.student = student;
        this.answer = answer;
        this.title = title;
        this.data = setDate();
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

    public String setDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str = df.format(date);
        return str;
    }

}
