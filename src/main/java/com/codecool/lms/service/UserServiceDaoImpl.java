package com.codecool.lms.service;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.sql.SQLException;
import java.util.List;

public class UserServiceDaoImpl implements UserService {

    DatabaseUserDao dao;

    public UserServiceDaoImpl(DatabaseUserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<User> getUsers() throws SQLException {
        return dao.findUsers();
    }

    @Override
    public boolean containsUser(String email) throws SQLException {
        for(User user : dao.findUsers()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(String name, String email, String password, String type) throws SQLException {
        dao.register(name, email, password, type);
    }

    @Override
    public User findUserByLoginData(String email, String password) throws WrongPasswordException, SQLException, UserNotFoundException {
        User userByEmail = dao.findUserByEmail(email);
        if(userByEmail != null) {
            User userByEmailAndPassword = dao.findUserByEmailAndPassword(email, password);
            if(userByEmailAndPassword != null) {
                return userByEmailAndPassword;
            } else {
                throw new WrongPasswordException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User findUserByName(String name) throws SQLException, UserNotFoundException {
        return dao.findUserByName(name);
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
