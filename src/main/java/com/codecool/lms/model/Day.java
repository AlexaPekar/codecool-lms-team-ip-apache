package com.codecool.lms.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Day {

    private String date;
    private List<Student> students;

    public Day( List<Student> students) {
        this.date = setDate();
        this.students = students;
    }

    public String getDate() {
        return date;
    }

    public String setDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String str = simpleDateFormat.format(date);
        return str;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        for (Student student:students) {
            for (Student stdnt:this.students) {
                if(!student.getEmail().equals(stdnt.getEmail())){
                    this.students.add(student);
                }
            }
        }
        this.students = students;
    }
}
