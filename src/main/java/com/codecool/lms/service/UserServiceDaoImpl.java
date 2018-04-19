package com.codecool.lms.service;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.GitHub;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceDaoImpl implements UserService {

    DatabaseUserDao dao;

    public UserServiceDaoImpl(DatabaseUserDao dao) {
        this.dao = dao;
    }

    @Override
    public synchronized List<User> getUsers() throws SQLException {
        return dao.findUsers();
    }

    @Override
    public synchronized boolean containsUser(String email) throws SQLException {
        for(User user : dao.findUsers()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(String name, String email, String password, String type) throws SQLException, UserAlreadyRegisteredException {
        User alreadyRegisteredUser = dao.findUserByEmail(email);
        if (alreadyRegisteredUser == null) {
            dao.register(name, email, password, type);
        } else {
            throw new UserAlreadyRegisteredException();
        }
    }

    @Override
    public User findUserByLoginData(String email, String password) throws WrongPasswordException, SQLException, UserNotFoundException {
        User userByEmail = dao.findUserByEmail(email);
        if(userByEmail != null) {
            User userByEmailAndPassword = dao.findUserByEmailAndPassword(email, password);
            if(userByEmailAndPassword != null) {
                if (userByEmailAndPassword.isConnected()) {
                    userByEmailAndPassword.setGitHub(dao.findGithubByUserId(userByEmailAndPassword.getId()));
                }
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
    public void addDay(String date, List<Student> students) throws SQLException {
        dao.insertDay(date);
        dao.insertAttendance(dao.findDayByDate(date), students);
    }

    @Override
    public List<Day> getDays() throws SQLException {
        return dao.findDays();
    }

    @Override
    public boolean dayExist(String date) throws SQLException {
        for (Day day : dao.findDays()) {
            if (day.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Day findDayByDate(String date) throws SQLException {
        return dao.findDayByDate(date);
    }

    @Override
    public void updateAttendance(String date, List<Student> students) throws SQLException {
        dao.updateAttendance(findDayByDate(date), students);
    }

    @Override
    public List<Student> getStudents() throws SQLException {

        return dao.findStudents();
    }

    @Override
    public List<Student> createAttendStudentList(String[] studentNames) throws SQLException, UserNotFoundException {
        List<Student> students = new ArrayList<>();
        for (String name : studentNames) {
            students.add((Student) findUserByName(name));
        }
        return students;
    }

    @Override
    public User connectUserWithGithub(User user, String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created, String[] htmls, String[] names, String[] stars, String[] watchers, String[] forks) throws SQLException {
        dao.insertGithub(user, avatar, html, repos, gists, followers, following, company, blog, location, created);
        GitHub gitHub = dao.findGithubByUserId(user.getId());
        if (htmls != null) {
            for (int i = 0; i < htmls.length; i++) {
                dao.insertRepository(htmls[i], names[i], stars[i], watchers[i], forks[i], gitHub.getId());
            }
            gitHub = dao.findGithubByUserId(user.getId());
        }
        dao.changeUserConnectionState(user, true);
        user.setGitHub(gitHub);
        user.setConnected(true);
        return user;
    }

    @Override
    public User disconnectUserFromGithub(User user, GitHub gitHub) throws SQLException {
        dao.deleteRepositoriesbygithubId(gitHub.getId());
        dao.deleteGithubByUserId(user.getId());
        dao.changeUserConnectionState(user, false);
        user.setGitHub(null);
        user.setConnected(false);
        return user;
    }

    @Override
    public User changeUserRole(User user, String type) throws SQLException, UserNotFoundException {
        dao.changeUserRole(user, type);
        return dao.findUserById(user.getId());
    }

    @Override
    public User changeUserName(User user, String newName) throws SQLException, UserNotFoundException {
        dao.changeUserName(user, newName);
        return dao.findUserById(user.getId());
    }

    @Override
    public User changeUserPassword(User user, String password) throws SQLException, UserNotFoundException {
        dao.changeUserPassword(user, password);
        return dao.findUserById(user.getId());
    }
}
