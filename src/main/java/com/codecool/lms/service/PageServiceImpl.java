package com.codecool.lms.service;

import com.codecool.lms.model.*;

import java.util.ArrayList;
import java.util.List;

public class PageServiceImpl implements PageService {

    private static PageServiceImpl pageService = new PageServiceImpl();
    private List<Page> pages = new ArrayList<>();

    //Visible for testing
    PageServiceImpl() {
        pages.add(new AssignmentPage("AssignmentTest1", "Kiscica is cute", 50));
        pages.add(new AssignmentPage("AssignmentTest2", "Kiskutya is cute", 90));
        pages.add(new TextPage("TextTest2", "Kiskutya is cute"));
        pages.add(new TextPage("TextTest1", "Kiscica is cute"));
    }

    public static PageServiceImpl getPageService() {
        return pageService;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void addNewPage(Page page) {
        pages.add(page);
    }

    public void removePage(String title) {
        pages.remove(findPageByTitle(title));
    }

    public Page findPageByTitle(String title) {
        for (Page page : pages) {
            if (page.getTitle().equals(title)) {
                return page;
            }
        }
        return null;
    }

    public String findAnswer(AssignmentPage page, Student student) {
        for (Assignment assignment : page.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(student.getEmail())) {
                return assignment.getAnswer();
            }
        }
        return null;
    }

    public String findGrade(AssignmentPage page, Student student) {
        for (Assignment assignment : page.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(student.getEmail())) {
                return Integer.toString(assignment.getGrade());
            }
        }
        return "-";
    }

    public List<AssignmentPage> getAssignmentPages() {
        List<AssignmentPage> assignmentPages = new ArrayList<>();
        for (Page page : pages) {
            if (page instanceof AssignmentPage) {
                assignmentPages.add((AssignmentPage) page);
            }
        }
        return assignmentPages;
    }

    public Assignment getAssignmentByStudentName(AssignmentPage page, Student student) {
        for (Assignment assignment : page.getAssignments()) {
            if (assignment.getStudent().getEmail().equals(student.getEmail())) {
                return assignment;
            }
        }
        return null;
    }
}
