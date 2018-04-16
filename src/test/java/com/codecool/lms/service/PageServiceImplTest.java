package com.codecool.lms.service;

import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.TextPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageServiceImplTest {

    private PageServiceImpl pageServiceImpl;
    private TextPage testTextPage;
    private AssignmentPage testAssignmentPage;
    private Assignment testAssignment;
    private Student testStudent;

    @BeforeEach
    void setUp() {
        pageServiceImpl = new PageServiceImpl();
        testTextPage = new TextPage("Kitties", "Kitties must be loved!");
        testAssignmentPage = new AssignmentPage("Kitties Test", "Do you love kitties?", 10);
        pageServiceImpl.addNewPage(testAssignmentPage);
        testStudent = new Student(2, "Steve", "steveo@gmail.com", "asdasdasd");
        testAssignment = new Assignment(testStudent, "good answer", "Kitties Test",10);
        testAssignmentPage.addAssignment(testAssignment);
        testAssignment.setGrade(10);
    }

    @Test
    void addNewPage() {
        pageServiceImpl.addNewPage(testTextPage);
        assertTrue(pageServiceImpl.getPages().contains(testTextPage));
    }

    @Test
    void removePage() {
        assertTrue(pageServiceImpl.getPages().contains(testAssignmentPage));
        pageServiceImpl.removePage("Kitties Test");
        assertFalse(pageServiceImpl.getPages().contains(testAssignmentPage));
    }

    @Test
    void findPageByTitle() {
        pageServiceImpl.addNewPage(testTextPage);
        pageServiceImpl.addNewPage(testAssignmentPage);
        assertEquals(testTextPage, pageServiceImpl.findPageByTitle("Kitties"));
        assertEquals(testAssignmentPage, pageServiceImpl.findPageByTitle("Kitties Test"));
        //TODO EXCEPTION
    }

    @Test
    void findAnswer() {
        assertEquals(testStudent.getName(), testAssignment.getStudent().getName());
        assertEquals("good answer", pageServiceImpl.findAnswer(testAssignmentPage, testStudent));
    }

    @Test
    void findGrade() {
        int grade = 0;
        testAssignmentPage.addAssignment(testAssignment);
        pageServiceImpl.addNewPage(testAssignmentPage);
        for (Assignment assignment: testAssignmentPage.getAssignments()) {
            grade = assignment.getGrade();
        }
        assertEquals(grade, testAssignment.getGrade());
        assertEquals(grade, Integer.parseInt(pageServiceImpl.findGrade(testAssignmentPage, testStudent)));
    }

    @Test
    void getAssignmentPages() {
        List<AssignmentPage> assignmentPages = new ArrayList<>();
        assignmentPages.add(testAssignmentPage);
        assertEquals(assignmentPages, pageServiceImpl.getAssignmentPages());
    }

    @Test
    void getAssignmentByStudentName() {
        assertEquals(testAssignment, pageServiceImpl.getAssignmentByStudentName(testAssignmentPage, testStudent));
    }

    @Test
    void findSubmittedPages() {
        List<AssignmentPage> assignmentPages = new ArrayList<>();
        assignmentPages.add(testAssignmentPage);
        assertEquals(assignmentPages, pageServiceImpl.findSubmittedPages(testStudent));
    }

    @Test
    void userAlreadySubmitted() {
        assertTrue(pageServiceImpl.userAlreadySubmitted(testStudent, testAssignmentPage));
    }
}
