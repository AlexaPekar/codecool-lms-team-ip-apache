package com.codecool.lms.model;

import java.util.ArrayList;
import java.util.List;

public class AssignmentPage extends Page {

    private int maxScore;
    private final List<Assignment> assignments;

    public AssignmentPage(int id, String title, String content, int maxScore) {
        super(id, title, content);
        this.maxScore = maxScore;
        assignments = new ArrayList<>();
    }


    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }


}
