package com.codecool.lms.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends Page {

    private int maxScore;
    private String answer;
    private List<Assignment> assignments;

    public AssignmentPage(String title, String content, int maxScore) {
        super(title, content);
        this.maxScore = maxScore;
        assignments = new ArrayList<>();
    }


    public int getMaxScore() {
        return maxScore;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }
}
