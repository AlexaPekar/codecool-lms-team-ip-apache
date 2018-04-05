package com.codecool.lms.service;

import com.codecool.lms.model.*;

import java.util.ArrayList;
import java.util.List;

public class PageServiceImpl implements PageService {

    private static PageServiceImpl pageService = new PageServiceImpl();
    private List<Page> pages = new ArrayList<>();

    //Visible for testing
    PageServiceImpl() {
    }

    public static PageServiceImpl getPageService() {
        return pageService;
    }

    public synchronized List<Page> getPages() {
        return pages;
    }

    public synchronized void addNewPage(Page page) {
        pages.add(page);
    }

    public synchronized void removePage(String title) {
        pages.remove(findPageByTitle(title));
    }

    public synchronized Page findPageByTitle(String title) {
        for (Page page : pages) {
            if (page.getTitle().equals(title)) {
                return page;
            }
        }
        return null;
    }

    public synchronized String findAnswer(AssignmentPage page, Student student) {
        for (Assignment assignment : page.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(student.getEmail())) {
                return assignment.getAnswer();
            }
        }
        return null;
    }

    public synchronized String findGrade(AssignmentPage page, Student student) {
        for (Assignment assignment : page.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(student.getEmail())) {
                if (assignment.getGrade() != 0) {
                    return Integer.toString(assignment.getGrade());
                }
            }
        }
        return "-";
    }

    public synchronized List<AssignmentPage> getAssignmentPages() {
        List<AssignmentPage> assignmentPages = new ArrayList<>();
        for (Page page : pages) {
            if (page instanceof AssignmentPage) {
                assignmentPages.add((AssignmentPage) page);
            }
        }
        return assignmentPages;
    }

    public synchronized Assignment getAssignmentByStudentName(AssignmentPage page, Student student) {
        for (Assignment assignment : page.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(student.getEmail())) {
                return assignment;
            }
        }
        return null;
    }

    public synchronized List<AssignmentPage> findSubmittedPages(User user) {
        List<AssignmentPage> assignmentPages = new ArrayList<>();
        for (Page page : pages) {
            if (page instanceof AssignmentPage) {
                for (Assignment assignment : ((AssignmentPage) page).getAssignments()) {
                    if (assignment.getStudent().getEmail() == user.getEmail()) {
                        assignmentPages.add((AssignmentPage) page);
                    }
                }
            }
        }
        return assignmentPages;
    }

    public synchronized List<Assignment> currentUserAssingments(User currentUser) {
        List<AssignmentPage> assignmentPages = PageServiceImpl.getPageService().getAssignmentPages();
        List<Assignment> assignments = new ArrayList<>();
        List<Assignment> userAssignments = new ArrayList<>();
        for (AssignmentPage page : assignmentPages) {
            assignments.addAll(page.getAssignments());
        }
        for (Assignment assignment : assignments) {
            if (assignment.getStudent().getEmail().equals(currentUser.getEmail())) {
                userAssignments.add(assignment);
            }
        }
        return userAssignments;
    }

    public synchronized boolean userAlreadySubmitted(User user, AssignmentPage assignmentPage) {
        for (Assignment assignment : assignmentPage.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public synchronized List<Assignment> getAssignments() {
        List<AssignmentPage> assignmentPages = getAssignmentPages();
        List<Assignment> assignments = new ArrayList<>();
        for (AssignmentPage page : assignmentPages) {
            assignments.addAll(page.getAssignments());
        }
        return assignments;
    }
}
