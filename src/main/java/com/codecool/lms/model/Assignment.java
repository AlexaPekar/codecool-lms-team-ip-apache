package com.codecool.lms.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment {
    private final int id;
    private final Student student;
    private final String answer;
    private int grade;
    private final String title;
    private final String date;
    private final int maxScore;

    public Assignment(int id, Student student, String answer, String title, int maxScore) {
        this.id = id;
        this.student = student;
        this.answer = answer;
        this.title = title;
        this.date = setDate();
        this.maxScore = maxScore;
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

    public static String setDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return df.format(date);
    }


    public int getMaxScore() {
        return maxScore;
    }

    public String getDate() {
        return date;
    }
}
