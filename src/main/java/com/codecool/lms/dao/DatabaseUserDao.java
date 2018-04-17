package com.codecool.lms.dao;

import com.codecool.lms.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {
    DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email, password, connected, type FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
        }
        return null;
    }

    //Service
    @Override
    public boolean containsUser(String email) {
        return false;
    }

    @Override
    public void register(String name, String email, String password, String type) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users ('name', email, password, connected, 'type') VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setBoolean(4, false);
            statement.setString(5, type);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    //inserts new day(date) to days table
    @Override
    public void insertDay(String date) {

    }

    //TODO: write to interface
    //inserts dayid, studentids to attendance table
    public void insertAttendance(Day day, List<Student> students) {

    }

    @Override
    public List<Day> getDays() {
        return null;
    }


    @Override
    public Day findDayByDate(String date) {
        return null;
    }

    @Override
    public void updateAttendance(Day day, List<Student> students) {
        //
        //Day -> deleteDayFromAttendance(Day) -> insertAttendance(new Day(Day.getID, Day.getDate, students))

    }

    //Delete rows from attendance table by dayID
    public void deleteDayFromAttendance(Day day) {

    }

    @Override
    public List<Student> getStudents() {
        return null;
    }

    //set grade in assignment table
    @Override
    public void gradeAssignment(int grade, int studentID, int assignmentPageID) {
    }

    @Override
    public User changeUserRole(User user, String type) {
        return null;
    }

    @Override
    public User changeUserName(User user, String newName) {
        return null;
    }

    @Override
    public User changeUserPassword(User user, String password) {
        return null;
    }

    public User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        boolean connected = resultSet.getBoolean("connected");
        String type = resultSet.getString("type");
        if (type.equals("Mentor")) {
            return new Mentor(id, name, email, password);
        } else if (type.equals("Student")) {
            return new Student(id, name, email, password);
        }
        return null;
    }

    //TODO: implement fetchDay
    public Day fetchDay(ResultSet resultSet) {
        return null;
    }

    @Override
    public void connectUserWithGithub(User user, GitHub gitHub) {

    }

    @Override
    public void disconnectUserFromGithub(User user) {

    }
}
