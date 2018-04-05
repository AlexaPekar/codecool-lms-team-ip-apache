package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.Mentor;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService = new UserServiceImpl();
    private List<User> users = new ArrayList<>();
    private List<Day> days = new ArrayList<>();


    //Visible for testing
    UserServiceImpl() {}

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
}