package com.codecool.lms.service;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.*;

import java.sql.SQLException;
import java.util.List;

public class PageServiceDaoImpl implements PageService {

    DatabasePagesDao dao;

    public PageServiceDaoImpl(DatabasePagesDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Page> getPages() throws SQLException {
        return dao.findAllPage();
    }

    @Override
    public void addNewPage(String title, String content, String type, int maxscore) throws SQLException {
        if (type.equals("text")) {
            dao.insertPage(title, content);
        } else {
            dao.insertPage(title, content, maxscore);
        }
    }


    @Override
    public void removePage(String title) throws SQLException {
        dao.deletePage(title);
    }

    @Override
    public Page findPageByTitle(String title) throws SQLException {
        return dao.findByTitle(title);
    }

    @Override
    public String findAnswer(AssignmentPage page, Student student) throws SQLException {
        //dao.findAnswerByPage(page, student);
        return " ";
    }

    @Override
    public String findGrade(AssignmentPage page, Student student) throws SQLException {
        //return dao.findGrade(page, student);
        return " ";
    }

    @Override
    public List<AssignmentPage> getAssignmentPages() throws SQLException {
        return dao.findAssignmentPages();
    }

    @Override
    public Assignment getAssignmentByStudent(AssignmentPage page, Student student) throws SQLException, UserNotFoundException {
        return dao.findAssignmentByStudent(page, student);
    }

    @Override
    public List<AssignmentPage> findSubmittedPages(User user) throws SQLException {
        return dao.findSubmittedPages(user);
    }

    @Override
    public List<Assignment> currentUserAssingments(User currentUser) throws SQLException, UserNotFoundException {
        return dao.currentUserAssignments(currentUser);
    }

    @Override
    public boolean userAlreadySubmitted(User user, AssignmentPage assignmentPage) throws SQLException, UserNotFoundException {
        List<Assignment> assignments = currentUserAssingments(user);
        for (Assignment assignment : assignments) {
            if (assignment.getTitle().equals(assignmentPage.getTitle())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Assignment> getAssignments() throws SQLException, UserNotFoundException {
        return dao.getAssignments();
    }

    @Override
    public double findEvaluatedPercent(Student student) throws SQLException {
        double sum = dao.findSumOfGrades(student);
        double maxScore = dao.findSumOfMaxScore(student);
        return GradeStatisticsChart.calculateGradePercentage(sum, maxScore);
    }

    @Override
    public void removeStudentAssignments(Student student) throws SQLException {
        dao.removeStudentAssignments(student);
    }

    @Override
    public void addAssignmentToAssignmentPage(User user, String title, String answer, int maxScore) throws SQLException {
        AssignmentPage assignmentPage = (AssignmentPage) dao.findByTitle(title);
        String date = Assignment.setDate();
        dao.insertAssignment((Student) user, assignmentPage, title, answer, maxScore, date);
    }

    @Override
    public void editPage(String title, String content, String type, int maxScore, String oldTitle) throws SQLException {
        if (type.equals("text")) {
            dao.updatePage(title, content, oldTitle);
        } else {
            dao.updatePage(title, content, maxScore, oldTitle);
        }
    }

    @Override
    public void gradeAssignment(Student student, int grade, String title) throws SQLException {
        AssignmentPage assignmentPage = (AssignmentPage) dao.findByTitle(title);
        dao.gradeAssignment(student, grade, assignmentPage);
    }

    public void editPage(String title, boolean published) throws SQLException {
        dao.updatePage(title, published);

    }
}
