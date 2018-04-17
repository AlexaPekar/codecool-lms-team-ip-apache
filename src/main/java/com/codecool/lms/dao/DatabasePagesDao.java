package com.codecool.lms.dao;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePagesDao extends AbstractDao implements PagesDao {
    public DatabasePagesDao(Connection connection) {
        super(connection);
    }


    @Override
    public TextPage fetchTextPage(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        boolean published = resultSet.getBoolean("published");
        TextPage textPage = new TextPage(id, title, content);
        if (published) {
            textPage.publish();
        }
        return textPage;

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
    public Assignment fetchAssignment(ResultSet resultSet) throws SQLException, UserNotFoundException {
        int id = resultSet.getInt("id");
        int student_id = resultSet.getInt("student_id");
        String answer = resultSet.getString("answer");
        String title = resultSet.getString("title");
        String date = resultSet.getString("date");
        int maxScore = resultSet.getInt("max_score");
        int grade = resultSet.getInt("grade");
        Student student = (Student) new DatabaseUserDao(connection).findUserById(student_id);
        Assignment assignment = new Assignment(id, student, answer, title, maxScore);
        if (grade != 0) {
            assignment.setGrade(grade);
        }
        return assignment;

    }

    @Override
    public List<Page> findAllPage() throws SQLException {
        List<Page> pages = new ArrayList<>();
        String assignmentSql = "SELECT * FROM assignment_pages";
        String textPageSql = "SELECT * FROM text_pages";
        try (Statement AssignmentPageStatement = connection.createStatement();
             ResultSet AssignmentPageResultSet = AssignmentPageStatement.executeQuery(assignmentSql)) {
            while (AssignmentPageResultSet.next()) {
                pages.add(fetchAssignmentPage(AssignmentPageResultSet));
            }
            try (Statement textPageStatement = connection.createStatement();
                 ResultSet textPageResultSet = textPageStatement.executeQuery(textPageSql)) {
                while (textPageResultSet.next()) {
                    pages.add(fetchTextPage(textPageResultSet));
                }
                return pages;
            }
        }
    }

    @Override
    public void insertPage(String title, String content, int maxScore) throws SQLException {
        String sql = "INSERT INTO assignment_pages (title, content, published, max_score) VALUES (?, ?, ?, ?)";
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setBoolean(3, false);
            statement.setInt(4, maxScore);
            executeInsert(statement);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void insertPage(String title, String content) throws SQLException {
        String sql = "INSERT INTO text_pages (title, content, published) VALUES (?, ?, ?)";
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setBoolean(3, false);
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
            statement.setString(2, title);
        }
    }

    @Override
    public Page findByTitle(String title) throws SQLException {
        Page page = null;
        String textSql = "SELECT * FROM text_pages WHERE title = ?";
        String assignmentSql = "SELECT * FROM assignments_pages WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(textSql)) {
            statement.setString(1, "title");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                page = fetchTextPage(resultSet);
            }
        }
        if (page == null) {
            try (PreparedStatement statement = connection.prepareStatement(assignmentSql)) {
                statement.setString(1, "title");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    page = fetchAssignmentPage(resultSet);
                }
            }
        }
        return page;
    }

    @Override
    public String findAnswerByPage(AssignmentPage page, Student student) throws SQLException {
        String result;
        String sql = "SELECT answer FROM assignments WHERE student_id = ? AND assignment_page_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getId());
            statement.setInt(2, page.getId());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.getString("answer");
        }
        return result;
    }

    @Override
    public String findGrade(AssignmentPage page, Student student) throws SQLException {
        String result;
        String sql = "SELECT grade FROM assignments WHERE student_id = ? AND assignment_page_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getId());
            statement.setInt(2, page.getId());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.getString("grade");
        }
        return result;
    }

    @Override
    public List<AssignmentPage> findAssignmentPages() throws SQLException {
        List<AssignmentPage> pages = new ArrayList<>();
        String sql = "SELECT * FROM assignment_pages";
        try (Statement AssignmentPageStatement = connection.createStatement();
             ResultSet AssignmentPageResultSet = AssignmentPageStatement.executeQuery(sql)) {
            while (AssignmentPageResultSet.next()) {
                pages.add(fetchAssignmentPage(AssignmentPageResultSet));
            }
        }
        return pages;
    }

    @Override
    public Assignment findAssignmentByStudent(AssignmentPage page, Student student) throws SQLException, UserNotFoundException {
        Assignment assignment;
        String sql = "SELECT * FROM assignments WHERE student_id =? AND assignment_page_id = ?";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            assignment = fetchAssignment(resultSet);
        }
        return assignment;
    }


    @Override
    public List<AssignmentPage> findSubmittedPages(User user) throws SQLException {
        List<AssignmentPage> assignmentPages = new ArrayList<>();
        String sql = "SELECT * FROM assignment_pages JOIN assignments ON assignment_pages.id" +
                " = assignment.assignment_pages_id WHERE student_id = ?";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                assignmentPages.add(fetchAssignmentPage(resultSet));
            }
        }
        return assignmentPages;
    }

    @Override
    public List<Assignment> currentUserAssignments(User currentUser) throws SQLException, UserNotFoundException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments WHERE student_id = ?";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                assignments.add(fetchAssignment(resultSet));
            }
        }
        return assignments;
    }

    @Override
    public List<Assignment> getAssignments() throws SQLException, UserNotFoundException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                assignments.add(fetchAssignment(resultSet));
            }
        }
        return assignments;
    }

    @Override
    public double findSumOfGrades(Student student) throws SQLException {
        double result = 0;
        String sql = "SELECT SUM(grade) AS result FROM assignments WHERE student_id = ? AND grade IS NOT NULL";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getId());
            ResultSet resultSet = statement.executeQuery();
            int intResult = resultSet.getInt("result");
            result = ((double) intResult);
        }
        return result;
    }

    @Override
    public void removeStudentAssignments(Student student) throws SQLException {
        String sql = "DELETE  FROM assignments WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getId());
            statement.executeQuery();
        }
    }

    @Override
    public void insertAssignment(Student student, AssignmentPage assignmentPage, String title, String answer, int maxScore, String date) throws SQLException {
        String sql = "INSERT INTO assignments (assignment_page_id,student_id,answer,title, date, max_score) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, assignmentPage.getId());
            statement.setInt(2, student.getId());
            statement.setString(3, answer);
            statement.setString(4, title);
            statement.setString(5, date);
            statement.setInt(6, maxScore);
            statement.executeQuery();
        }
    }


    @Override
    public void updatePage(String title, String content, String oldTitle) throws SQLException {
        String sql = "UPDATE text_pages SET title = ?,content = ? WHERE title =?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setString(3, oldTitle);
            statement.executeQuery();
        }

    }

    @Override
    public void updatePage(String title, String content, int maxScore, String oldTitle) throws SQLException {
        String sql = "UPDATE assignment_pages SET title = ?,content = ?,max_score =? WHERE title =?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, content);
            statement.setInt(3, maxScore);
            statement.setString(4, oldTitle);
            statement.executeQuery();
        }
    }
}
