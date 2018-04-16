package com.codecool.lms.dao;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.sql.Connection;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {
    DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> findUsers() {
        return null;
    }

    @Override
    public boolean containsUser(String email) {
        return false;
    }

    @Override
    public void register(User user) throws UserAlreadyRegisteredException {

    }

    @Override
    public User findUserByEmail(String email, String password) throws UserNotFoundException, WrongPasswordException {
        return null;
    }

    @Override
    public User createUser(String email, String name, String password, String type) {
        return null;
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    @Override
    public void addDay(Day day) {

    }

    @Override
    public List<Day> getDays() {
        return null;
    }

    @Override
    public boolean dayExist(String date) {
        return false;
    }

    @Override
    public Day findDayByDate(String date) {
        return null;
    }

    @Override
    public void SetStudentListbyDate(String date, List<Student> students) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public List<Student> getStudents() {
        return null;
    }

    @Override
    public List<Student> createAttendStudentList(String[] studentNames) {
        return null;
    }

    @Override
    public List<Repository> createRepositoryList(String[] htmls, String[] names, String[] stars, String[] watchers, String[] forks) {
        return null;
    }

    @Override
    public GitHub createGithub(String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created, List<Repository> repositories) {
        return null;
    }

    @Override
    public void connectUserWithGithub(User user, GitHub gitHub) {

    }

    @Override
    public void disconnectUserFromGithub(User user) {

    }

    @Override
    public void gradeAssignment(int grade, String studentName, String title) {

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
}
