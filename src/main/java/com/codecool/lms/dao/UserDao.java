package com.codecool.lms.dao;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> findUsers() throws SQLException;

    boolean containsUser(String email);

    void register(User user) throws UserAlreadyRegisteredException, SQLException;

    User findUserByEmail(String email);

    User findUserByName(String name);

    void insertDay(Day day);

    List<Day> getDays();

    Day findDayByDate(String date);

    void updateAttendance(Day day, List<Student> students);

    List<Student> getStudents();

    void connectUserWithGithub(User user, GitHub gitHub);

    void disconnectUserFromGithub(User user);

    void gradeAssignment(int grade, int studentID, int assignmentPageID);

    User changeUserRole(User user, String type);

    User changeUserName(User user, String newName);

    User changeUserPassword(User user, String password);

    Day fetchDay(ResultSet resultSet);

    User fetchUser(ResultSet resultSet) throws SQLException;
}
