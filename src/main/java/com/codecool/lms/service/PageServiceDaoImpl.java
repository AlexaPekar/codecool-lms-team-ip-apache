package com.codecool.lms.service;

import com.codecool.lms.dao.DatabasePagesDao;
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
    public Page findPageByTitle(String title) {
        return null;
    }

    @Override
    public String findAnswer(AssignmentPage page, Student student) {
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
    public void editPage(String title, String content, String type, int maxScore, String oldTitle) {

    }

}
