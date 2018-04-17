package com.codecool.lms.service;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.*;

import java.sql.SQLException;
import java.util.List;

interface PageService {

    List<Page> getPages() throws SQLException;

    void addNewPage(String title, String content, String type, int maxscore) throws SQLException;

    void removePage(String title) throws SQLException;

    Page findPageByTitle(String title) throws SQLException;

    String findAnswer(AssignmentPage page, Student student) throws SQLException;

    String findGrade(AssignmentPage page, Student student) throws SQLException;

    List<AssignmentPage> getAssignmentPages() throws SQLException;

    Assignment getAssignmentByStudent(AssignmentPage page, Student student) throws SQLException, UserNotFoundException;

    List<AssignmentPage> findSubmittedPages(User user) throws SQLException;

    List<Assignment> currentUserAssingments(User currentUser) throws SQLException, UserNotFoundException;

    boolean userAlreadySubmitted(User user, AssignmentPage assignmentPage) throws SQLException, UserNotFoundException;

    List<Assignment> getAssignments() throws SQLException, UserNotFoundException;

    double findEvaluatedPercent(Student student) throws SQLException;

    void removeStudentAssignments(Student student) throws SQLException;

    void addAssignmentToAssignmentPage(User user, String title, String answer, int maxScore) throws SQLException;

    void editPage(String title, String content, String type, int maxScore, String oldTitle) throws SQLException;

}
