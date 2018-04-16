package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    public static final UserServiceImpl userService = new UserServiceImpl();
    public final List<User> users = new ArrayList<>();
    public final List<Day> days = new ArrayList<>();


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

    public synchronized void register(User user) throws UserAlreadyRegisteredException {
        if (!containsUser(user.getEmail())) {
            users.add(user);
        } else {
            throw new UserAlreadyRegisteredException();
        }
    }

    public synchronized User findUserByEmail(String email, String password) throws UserNotFoundException, WrongPasswordException {
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
            return new Mentor(name, email, password);
        } else {
            return new Student(name, email, password);
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

    public synchronized void addDay(Day day) {
        days.add(day);
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

    public synchronized void SetStudentListbyDate(String date, List<Student> students) {
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

    public synchronized void gradeAssignment(int grade, String studentName, String title) {
        Student student = (Student) UserServiceImpl.getUserService().findUserByName(studentName);
        AssignmentPage assignmentPage = (AssignmentPage) PageServiceImpl.getPageService().findPageByTitle(title);
        PageServiceImpl.getPageService().getAssignmentByStudentName(assignmentPage, student).setGrade(grade);
    }

    public synchronized User changeUserRole(User user, String type) {
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
            user = new Mentor(user.getName(), user.getEmail(), user.getPassword());
        } else {
            user = new Student(user.getName(), user.getEmail(), user.getPassword());
        }
        try {
            UserServiceImpl.getUserService().register(user);
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