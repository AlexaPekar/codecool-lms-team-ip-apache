package com.codecool.lms.service;

import com.codecool.lms.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageServiceImpl implements PageService {

    public static final PageServiceImpl pageService = new PageServiceImpl();
    public final List<Page> pages = new ArrayList<>();
    public static int pageID = 1;
    public static int AssignmentID = 1;

    //Visible for testing
    PageServiceImpl() {
    }

    public static PageServiceImpl getPageService() {
        return pageService;
    }

    public synchronized List<Page> getPages() {
        return pages;
    }

    @Override
    public void addNewPage(String title, String content, String type, int maxscore) throws SQLException {
        pages.add(createNewPage(title, content, type, maxscore));
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

    public synchronized Assignment getAssignmentByStudent(AssignmentPage page, Student student) {
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

    public synchronized double findEvaluatedPercent(Student student) {
        int score = 0;
        int maxScore = 0;
        List<AssignmentPage> assignmentPages = getAssignmentPages();
        for (AssignmentPage page : assignmentPages) {
            if (userAlreadySubmitted(student, page)) {
                Assignment assignment = getAssignmentByStudent(page, student);
                score += assignment.getGrade();
                maxScore += assignment.getMaxScore();
            }
        }
        return GradeStatisticsChart.calculateGradePercentage((double) score, (double) maxScore);

    }

    public synchronized void removeStudentAssignments(Student student) {
        List<AssignmentPage> assignmentPages = getAssignmentPages();
        for (AssignmentPage assignmentPage : assignmentPages) {
            if (userAlreadySubmitted(student, assignmentPage)) {
                Assignment assignment = getAssignmentByStudent(assignmentPage, student);
                assignmentPage.getAssignments().remove(assignment);
            }
        }
    }

    @Override
    public synchronized void addAssignmentToAssignmentPage(User user, String title, String answer, int maxScore) throws SQLException {
        AssignmentPage assignmentPage = (AssignmentPage) findPageByTitle(title);
        Assignment assignment = new Assignment(AssignmentID, (Student) user, answer, title, maxScore);
        assignmentPage.addAssignment(assignment);
    }


    public synchronized Page createNewPage(String title, String content, String type, int maxscore) {
        if (type.equals("text")) {
            return new TextPage(pageID++, title, content);
        } else {
            return new AssignmentPage(pageID++, title, content, maxscore);
        }
    }

    public synchronized void editPage(String title, String content, String type, int maxScore, String oldTitle) {
        Page page = PageServiceImpl.getPageService().findPageByTitle(oldTitle);
        if (type.equals("text")) {
            page.setContent(content);
            page.setTitle(title);
        } else {
            page.setContent(content);
            page.setTitle(title);
            ((AssignmentPage) page).setMaxScore(maxScore);
        }
    }
}
