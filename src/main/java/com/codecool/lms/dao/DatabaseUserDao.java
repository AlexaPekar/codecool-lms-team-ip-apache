package com.codecool.lms.dao;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {
    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
        }
        return users;
    }

    @Override
    public void register(String name, String email, String password, String type) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO users (name, email, password, connected, type) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setBoolean(4, false);
            statement.setString(5, type);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fetchUser(resultSet);
            }
        }
        return null;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws SQLException, WrongPasswordException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return fetchUser(resultSet);
            }
        }
        throw new WrongPasswordException();
    }


    @Override
    public User findUserByName(String name) throws SQLException, UserNotFoundException {
        String sql = "SELECT * FROM users WHERE name = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return fetchUser(resultSet);
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findUserById(int id) throws SQLException, UserNotFoundException {
        String sql = "SELECT * FROM users WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return fetchUser(resultSet);
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public void insertDay(String date) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO days (date) VALUES (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public List<Day> findDays() throws SQLException {
        List<Day> days = new ArrayList<>();
        String sql = "SELECT id, \"date\" FROM days;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                days.add(fetchDay(resultSet));
            }
        }
        return days;
    }

    @Override
    public Day findDayByDate(String date) throws SQLException {
        String sql = "SELECT id, \"date\" FROM days WHERE \"date\" = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fetchDay(resultSet);
            }
        }
        return null;
    }

    @Override
    public void updateAttendance(Day day, List<Student> students) throws SQLException {
        deleteDayFromAttendance(day);
        insertAttendance(day, students);
    }

    public void insertAttendance(Day day, List<Student> students) throws SQLException {
        String sql = "INSERT INTO attendance (day_id, student_id) VALUES (?, ?);";
        for (Student student : students) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, day.getId());
                statement.setInt(2, student.getId());
                statement.executeUpdate();
            }
        }
    }

    public void deleteDayFromAttendance(Day day) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "DELETE FROM attendance WHERE day_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, day.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public List<Student> findStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE type = 'Student';";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                students.add((Student) fetchUser(resultSet));
            }
        }
        return students;
    }

    @Override
    public void changeUserRole(User user, String type) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET \"type\" = ? WHERE \"id\" = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

    }

    @Override
    public void changeUserName(User user, String newName) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET \"name\" = ? WHERE \"id\" = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void changeUserPassword(User user, String password) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET \"password\" = ? WHERE \"id\" = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, password);
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void changeUserConnectionState(User user, boolean connected) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE users SET connected = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, connected);
            statement.setInt(2, user.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public User fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        if (email.equals("") || email == null) {
            return null;
        }
        String password = resultSet.getString("password");
        boolean connected = resultSet.getBoolean("connected");
        String type = resultSet.getString("type");
        if (type.equals("Mentor")) {
            Mentor mentor = new Mentor(id, name, email, password);
            if (connected) {
                mentor.setConnected(true);
            }
            return mentor;
        } else if (type.equals("Student")) {
            Student student = new Student(id, name, email, password);
            if (connected) {
                student.setConnected(true);
            }
            return student;
        }
        return null;
    }

    //TODO: implement fetchDay
    public Day fetchDay(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String date = resultSet.getString("date");
        List<Student> students = findStudentById(findStudentIdsByDayId(id));
        return new Day(id, students, date);
    }

    @Override
    public List<Integer> findStudentIdsByDayId(int dayId) throws SQLException {
        List<Integer> studentIds = new ArrayList<>();
        String sql = "SELECT student_id FROM attendance WHERE day_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dayId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                studentIds.add(resultSet.getInt("student_id"));
            }
        }
        return studentIds;
    }

    @Override
    public List<Student> findStudentById(List<Integer> studentIds) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE id = ? AND type = 'Student';";
        for (int studentId : studentIds) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    students.add((Student) fetchUser(resultSet));
                }
            }
        }
        return students;
    }

    @Override
    public void insertGithub(User user, String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created) throws SQLException {
        String sql = "INSERT INTO githubs (student_id, avatar, html, repos, gists, followers, following, company, blog, location, created) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, avatar);
            statement.setString(3, html);
            statement.setInt(4, repos);
            statement.setInt(5, gists);
            statement.setInt(6, followers);
            statement.setInt(7, following);
            statement.setString(8, company);
            statement.setString(9, blog);
            statement.setString(10, location);
            statement.setString(11, created);
            statement.executeUpdate();
        }
    }

    @Override
    public GitHub findGithubByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM githubs WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return fetchGitHub(resultSet);
            }
        }
        return null;
    }

    @Override
    public GitHub fetchGitHub(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String avatar = resultSet.getString("avatar");
        String html = resultSet.getString("html");
        int repos = resultSet.getInt("repos");
        int gists = resultSet.getInt("gists");
        int followers = resultSet.getInt("followers");
        int following = resultSet.getInt("following");
        String company = resultSet.getString("blog");
        String blog = resultSet.getString("blog");
        String location = resultSet.getString("location");
        String created = resultSet.getString("created");
        List<Repository> repositories = findRepositoriesByGitHubId(id);


        return new GitHub(id, avatar, html, repos, gists, followers, following, company, blog, location, created, repositories);
    }

    @Override
    public void deleteGithubByUserId(int userId) throws SQLException {
        String sql = "DELETE  FROM githubs WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }


    @Override
    public void insertRepository(String html, String name, String star, String watcher, String fork, int githubID) throws SQLException {
        String sql = "INSERT INTO repositories (github_id, html, name, stars, watchers, forks) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, githubID);
            statement.setString(2, html);
            statement.setString(3, name);
            statement.setInt(4, Integer.parseInt(star));
            statement.setInt(5, Integer.parseInt(watcher));
            statement.setString(6, fork);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Repository> findRepositoriesByGitHubId(int githubId) throws SQLException {
        List<Repository> repositories = new ArrayList<>();
        String sql = "SELECT * FROM repositories WHERE github_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, githubId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                repositories.add(fetchRepository(resultSet));
            }
        }
        return repositories;
    }

    @Override
    public Repository fetchRepository(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String html = resultSet.getString("html");
        String name = resultSet.getString("name");
        int stars = resultSet.getInt("stars");
        int watchers = resultSet.getInt("watchers");
        String forks = resultSet.getString("forks");
        return new Repository(id, html, name, stars, watchers, forks);
    }

    @Override
    public void deleteRepositoriesbygithubId(int githubId) throws SQLException {
        String sql = "DELETE  FROM repositories WHERE github_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, githubId);
            statement.executeUpdate();
        }
    }

    @Override
    public void disconnectUserFromGithub(User user) {

    }
}
