package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.GitHub;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;

import java.sql.SQLException;
import java.util.List;

interface UserService {

    List<User> getUsers() throws SQLException;

    boolean containsUser(String email) throws SQLException;

    void register(String name, String email, String password, String type) throws SQLException, UserAlreadyRegisteredException;

    User findUserByLoginData(String email, String password) throws WrongPasswordException, SQLException, UserNotFoundException;

    User findUserByName(String name) throws SQLException, UserNotFoundException;

    void addDay(String date, List<Student> students) throws SQLException;

    List<Day> getDays() throws SQLException;

    boolean dayExist(String date) throws SQLException;

    Day findDayByDate(String date) throws SQLException;

    void updateAttendance(String date, List<Student> students) throws SQLException;

    List<Student> getStudents() throws SQLException;

    List<Student> createAttendStudentList(String[] studentNames) throws SQLException, UserNotFoundException;

    User connectUserWithGithub(User user, String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created, String[] htmls, String[] names, String[] stars, String[] watchers, String[] forks) throws SQLException;

    User disconnectUserFromGithub(User user, GitHub gitHub) throws SQLException;

    User changeUserRole(User user, String type) throws SQLException, UserNotFoundException;

    User changeUserName(User user, String newName) throws SQLException, UserNotFoundException;

    User changeUserPassword(User user, String password) throws SQLException, UserNotFoundException;
}
