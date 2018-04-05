package com.codecool.lms.service;

import com.codecool.lms.model.*;

import java.util.List;

public interface PageService {

    List<Page> getPages();

    void addNewPage(Page page);

    void removePage(String title);

    Page findPageByTitle(String title);

    String findAnswer(AssignmentPage page, Student student);

    String findGrade(AssignmentPage page, Student student);

    List<AssignmentPage> getAssignmentPages();

    Assignment getAssignmentByStudentName(AssignmentPage page, Student student);

    List<AssignmentPage> findSubmittedPages(User user);

    List<Assignment> currentUserAssingment(User currentUser);

    boolean userAlreadySubmitted(User user, AssignmentPage assignmentPage);

    List<Assignment> getAssignments();

}
