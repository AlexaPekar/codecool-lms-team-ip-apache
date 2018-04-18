package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public static final UserServiceImpl userService = new UserServiceImpl();
    public final List<User> users = new ArrayList<>();
    public final List<Day> days = new ArrayList<>();
    public static int userId = 1;
    public static int dayId = 1;


    //Visible for testing
    UserServiceImpl() {
    }

    public static UserServiceImpl getUserService() {
        return userService;
    }

    public synchronized List<User> getUsers() {
        return users;
    }

    public synchronized boolean containsUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(String name, String email, String password, String type) throws SQLException, UserAlreadyRegisteredException {
        if (!containsUser(email)) {
            if (type.equals("Mentor")) {
                users.add(new Mentor(userId++, name, email, password));
            } else {
                users.add(new Student(userId++, name, email, password));
            }
        } else {
            throw new UserAlreadyRegisteredException();
        }
    }

    @Override
    public User findUserByLoginData(String email, String password) throws UserNotFoundException, SQLException, WrongPasswordException {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
                throw new WrongPasswordException();
            }
        }
        throw new UserNotFoundException();
    }

    public synchronized User createUser(String email, String name, String password, String type) {
        if (type.equals("Mentor")) {
            return new Mentor(1, name, email, password);
        } else {
            return new Student(2, name, email, password);
        }
    }

    public synchronized User findUserByName(String name) {
        User user = null;
        for (User usr : users) {
            if (usr.getName().equals(name)) {
                user = usr;
            }
        }
        return user;
    }

    @Override
    public void addDay(String date, List<Student> students) throws SQLException {
        days.add(new Day(dayId++, students, date));
    }

    public synchronized List<Day> getDays() {
        return days;
    }

    public synchronized boolean dayExist(String date) {
        for (Day d : days) {
            if (d.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public synchronized Day findDayByDate(String date) {
        for (Day d : days) {
            if (d.getDate().equals(date)) {
                return d;
            }
        }
        return null;
    }

    public synchronized void updateAttendance(String date, List<Student> students) {
        UserServiceImpl.getUserService().findDayByDate(date).setStudents(students);
    }

    public synchronized void deleteUser(String username) {
        users.remove(findUserByName(username));
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }

    public synchronized List<Student> createAttendStudentList(String[] studentNames) {
        List<Student> selectedStudents = new ArrayList<>();
        if (studentNames != null) {
            for (String name : studentNames) {
                selectedStudents.add((Student) UserServiceImpl.getUserService().findUserByName(name));
            }
        }
        return selectedStudents;
    }

    public synchronized List<Repository> createRepositoryList(String[] htmls, String[] names, String[] stars, String[] watchers, String[] forks) {
        List<Repository> repositories = new ArrayList<>();
        if (htmls != null) {
            for (int i = 0; i < htmls.length; i++) {
                repositories.add(new Repository(htmls[i], names[i], Integer.parseInt(stars[i]), Integer.parseInt(watchers[i]), forks[i]));
            }
        }
        return repositories;
    }

    public synchronized GitHub createGithub(String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created, List<Repository> repositories) {
        return new GitHub(avatar, html, repos, gists, followers, following, company, blog, location, created, repositories);
    }

    public synchronized void connectUserWithGithub(User user, GitHub gitHub) {
        user.setConnected(true);
        user.setGitHub(gitHub);
    }

    public synchronized void disconnectUserFromGithub(User user) {
        user.setConnected(false);
        user.setGitHub(null);
    }

    public synchronized User changeUserRole(User user, String type) throws SQLException {
        if (user instanceof Mentor && type.equals("Mentor")) {
            return user;
        } else if (user instanceof Student && type.equals("Student")) {
            return user;
        }
        UserServiceImpl.getUserService().deleteUser(user.getName());
        if (user instanceof Student) {
            PageServiceImpl.getPageService().removeStudentAssignments((Student) user);
        }
        if (type.equals("Mentor")) {
            user = new Mentor(1, user.getName(), user.getEmail(), user.getPassword());
        } else {
            user = new Student(2, user.getName(), user.getEmail(), user.getPassword());
        }
        try {
            UserServiceImpl.getUserService().register(user.getName(), user.getEmail(), user.getPassword(), type);
        } catch (UserAlreadyRegisteredException e) {
            e.printStackTrace();
        }
        return user;
    }

    public synchronized User changeUserName(User user, String newName) {
        user.setName(newName);
        return user;
    }

    public synchronized User changeUserPassword(User user, String password) {
        user.setPassword(password);
        return user;
    }
}