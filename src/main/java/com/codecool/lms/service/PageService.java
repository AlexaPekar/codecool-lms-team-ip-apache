package com.codecool.lms.service;

import com.codecool.lms.model.*;

import java.sql.SQLException;
import java.util.List;

interface PageService {

    List<Page> getPages() throws SQLException;

    void addNewPage(String title, String content, String type, int maxscore) throws SQLException;

    void removePage(String title) throws SQLException;

    Page findPageByTitle(String title);

    String findAnswer(AssignmentPage page, Student student);

    String findGrade(AssignmentPage page, Student student);

    List<AssignmentPage> getAssignmentPages();

    Assignment getAssignmentByStudentName(AssignmentPage page, Student student);

    List<AssignmentPage> findSubmittedPages(User user);

    List<Assignment> currentUserAssingments(User currentUser);

    boolean userAlreadySubmitted(User user, AssignmentPage assignmentPage);

    List<Assignment> getAssignments();

    double findEvaluatedPercent(Student student);

    void removeStudentAssignments(Student student);

    void addAssignmentToAssignmentPage(Assignment assignment);

    void editPage(String title, String content, String type, int maxScore, String oldTitle);

}
