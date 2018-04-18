package com.codecool.lms.dao;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PagesDao {

    TextPage fetchTextPage(ResultSet resultSet) throws SQLException;

    AssignmentPage fetchAssignmentPage(ResultSet resultSet) throws SQLException;

    List<Page> findAllPage() throws SQLException;

    void insertPage(String title, String content) throws SQLException;

    void insertPage(String title, String content, int maxScore) throws SQLException;

    void deletePage(String title) throws SQLException;

    Page findByTitle(String title) throws SQLException;

    String findAnswerByPage(AssignmentPage page, Student student) throws SQLException;

    String findGrade(AssignmentPage page, Student student) throws SQLException;

    List<AssignmentPage> findAssignmentPages() throws SQLException;

    Assignment findAssignmentByStudent(AssignmentPage page, Student student) throws SQLException, UserNotFoundException;

    Assignment fetchAssignment(ResultSet resultSet) throws SQLException, UserNotFoundException;

    List<AssignmentPage> findSubmittedPages(User user) throws SQLException;

    List<Assignment> currentUserAssignments(User currentUser) throws SQLException, UserNotFoundException;

    List<Assignment> getAssignments() throws SQLException, UserNotFoundException;

    double findSumOfGrades(Student student) throws SQLException;

    double findSumOfMaxScore(Student student) throws SQLException;

    void removeStudentAssignments(Student student) throws SQLException;

    void insertAssignment(Student student, AssignmentPage assignmentPage, String title, String answer, int maxScore, String date) throws SQLException;

    void updatePage(String title, String content,int maxScore, String oldTitle) throws SQLException;

    void updatePage(String title, String content,String oldTitle) throws SQLException;

    void updatePage(String title, boolean published) throws SQLException;

    void gradeAssignment(Student student, int grade, AssignmentPage assignmentPage) throws SQLException;
}
