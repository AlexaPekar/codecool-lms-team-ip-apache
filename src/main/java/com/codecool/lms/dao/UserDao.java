package com.codecool.lms.dao;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.*;

import java.util.List;

public interface UserDao {

    List<User> findUsers();

    boolean containsUser(String email);

    void register(User user) throws UserAlreadyRegisteredException;

    User findUserByEmail(String email, String password) throws UserNotFoundException, WrongPasswordException;

    User createUser(String email, String name, String password, String type);

    User findUserByName(String name);

    void addDay(Day day);

    List<Day> getDays();

    boolean dayExist(String date);

    Day findDayByDate(String date);

    void SetStudentListbyDate(String date, List<Student> students);

    void deleteUser(String username);

    List<Student> getStudents();

    List<Student> createAttendStudentList(String[] studentNames);

    List<Repository> createRepositoryList(String[] htmls, String[] names, String[] stars, String[] watchers, String[] forks);

    GitHub createGithub(String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created, List<Repository> repositories);

    void connectUserWithGithub(User user, GitHub gitHub);

    void disconnectUserFromGithub(User user);

    void gradeAssignment(int grade, String studentName, String title);

    User changeUserRole(User user, String type);

    User changeUserName(User user, String newName);

    User changeUserPassword(User user, String password);
}
