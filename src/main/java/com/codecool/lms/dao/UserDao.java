package com.codecool.lms.dao;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> findUsers() throws SQLException;

    void register(String name, String email, String password, String type) throws SQLException;

    User findUserByEmail(String email) throws SQLException;

    User findUserByEmailAndPassword(String email, String password) throws SQLException, WrongPasswordException;

    User findUserByName(String name) throws SQLException, UserNotFoundException;

    User findUserById(int id) throws SQLException, UserNotFoundException;

    void insertDay(String date) throws SQLException;

    void insertAttendance(Day day, List<Student> students) throws SQLException;

    List<Day> findDays() throws SQLException;

    Day findDayByDate(String date) throws SQLException;

    void updateAttendance(Day day, List<Student> students) throws SQLException;

    List<Student> findStudents() throws SQLException;

    void connectUserWithGithub(User user, GitHub gitHub);

    void disconnectUserFromGithub(User user);

    void changeUserRole(User user, String type) throws SQLException;

    void changeUserName(User user, String newName) throws SQLException;

    void changeUserPassword(User user, String password) throws SQLException;

    Day fetchDay(ResultSet resultSet) throws SQLException;

    List<Integer> findStudentIdsByDayId(int dayId) throws SQLException;

    List<Student> findStudentById(List<Integer> studentIds) throws SQLException;

    User fetchUser(ResultSet resultSet) throws SQLException;
}
