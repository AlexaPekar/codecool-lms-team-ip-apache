package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    boolean containsUser(String email);

    void register(User user) throws UserAlreadyRegisteredException;

    User findUserByEmail(String email, String password) throws UserNotFoundException, WrongPasswordException;

    User createUser(String email, String name, String password, String type);

    User findUserByName(String name);

    void addDay(Day day);

    List<Day> getDays();

    boolean dayExist(String date);

    Day findDayByDate(String date);

    void deleteUser(String username);

    List<Student> getStudents();

    List<Student> createAttendStudentList(String[] studentNames);
}
