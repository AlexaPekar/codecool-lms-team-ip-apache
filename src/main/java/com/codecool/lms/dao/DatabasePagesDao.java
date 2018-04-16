package com.codecool.lms.dao;

import com.codecool.lms.model.*;

import java.sql.Connection;
import java.util.List;

public class DatabasePagesDao extends AbstractDao implements PagesDao {
    DatabasePagesDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Page> findAllPage() {
        return null;
    }

    @Override
    public void insertPage(Page page) {

    }

    @Override
    public void deletePage(String title) {

    }

    @Override
    public Page findByTitle(String title) {
        return null;
    }

    @Override
    public String findAnswerbyPage(AssignmentPage page, Student student) {
        return null;
    }

    @Override
    public String findGrade(AssignmentPage page, Student student) {
        return null;
    }

    @Override
    public List<AssignmentPage> getAssignmentPages() {
        return null;
    }

    @Override
    public Assignment getAssignmentByStudentName(AssignmentPage page, Student student) {
        return null;
    }

    @Override
    public List<AssignmentPage> findSubmittedPages(User user) {
        return null;
    }

    @Override
    public List<Assignment> currentUserAssingments(User currentUser) {
        return null;
    }

    @Override
    public boolean userAlreadySubmitted(User user, AssignmentPage assignmentPage) {
        return false;
    }

    @Override
    public List<Assignment> getAssignments() {
        return null;
    }

    @Override
    public double findEvaluatedPercent(Student student) {
        return 0;
    }

    @Override
    public void removeStudentAssignments(Student student) {

    }

    @Override
    public void addAssignmentToAssignmentPage(Assignment assignment) {

    }

    @Override
    public Page createNewPage(String title, String content, String type, int maxscore) {
        return null;
    }

    @Override
    public void editPage(String title, String content, String type, int maxScore, String oldTitle) {

    }
}
