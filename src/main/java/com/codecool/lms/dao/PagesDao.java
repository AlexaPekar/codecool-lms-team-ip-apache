package com.codecool.lms.dao;

import com.codecool.lms.model.*;

import java.util.List;

public interface PagesDao {
    List<Page> findAllPage();

    void insertPage(Page page);

    void deletePage(String title);

    Page findByTitle(String title);

    String findAnswerbyPage(AssignmentPage page, Student student);

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

    Page createNewPage(String title, String content, String type, int maxscore);

    void editPage(String title, String content, String type, int maxScore, String oldTitle);
}
