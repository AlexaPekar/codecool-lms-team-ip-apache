package com.codecool.lms.dao;

import com.codecool.lms.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePagesDao extends AbstractDao implements PagesDao {
    DatabasePagesDao(Connection connection) {
        super(connection);
    }


    @Override
    public TextPage fetchTextPage(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        return new TextPage(id, title, content);
    }

    @Override
    public AssignmentPage fetchAssignmentPage(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        boolean published = resultSet.getBoolean("published");
        int maxScore = resultSet.getInt("max_score");
        AssignmentPage assignmentPage = new AssignmentPage(id, title, content, maxScore);
        if (published) {
            assignmentPage.publish();
        }
        return assignmentPage;
    }

    @Override
    public List<Page> findAllPage() throws SQLException {
        List<Page> pages = new ArrayList<>();
        String assignmentSql = "SELECT * FROM assignment_pages";
        String textPageSql = "SELECT * FROM text_pages";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(assignmentSql)) {
            while (resultSet.next()) {
                pages.add(fetchAssignmentPage(resultSet));
            }
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(textPageSql)) {
            while (resultSet.next()) {
                pages.add(fetchTextPage(resultSet));
            }
        }
        return pages;
    }

    @Override
    public void insertPage(Page page) throws SQLException {
        String sql;
        if (page instanceof AssignmentPage) {
            sql = "INSERT INTO assignment_pages (title, content, published, max_score) VALUES (?, ?, ?, " + ((AssignmentPage) page).getMaxScore() + ")";
        } else {
            sql = "INSERT INTO text_pages (title, content, published) VALUES (?, ?, ?)";
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, page.getTitle());
            statement.setString(2, page.getContent());
            statement.setBoolean(3, page.isPublished());
            executeInsert(statement);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }


    @Override
    public void deletePage(String title) throws SQLException {
        String sql = "DELETE FROM assignment_pages WHERE title = ?; " +
                "DELETE FROM assignment_pages WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, title); // not sure
        }
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
