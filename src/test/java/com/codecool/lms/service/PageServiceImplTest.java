/*package com.codecool.lms.service;

import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.TextPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PageServiceImplTest {

    PageServiceImpl pageServiceImpl;
    TextPage testTextPage;
    AssignmentPage testAssignmentPage;

    @BeforeEach
    void setUp() {
        pageServiceImpl = new PageServiceImpl();
        testTextPage = new TextPage("Kitties", "Kitties must be loved!");
        testAssignmentPage = new AssignmentPage("Kitties Test", "Do you love kitties?", 10);
    }

    @Test
    void addNewPage() {
        pageServiceImpl.addNewPage(testTextPage);
        assertTrue(pageServiceImpl.getPages().contains(testTextPage));
    }

    @Test
    void removePage() {
        pageServiceImpl.addNewPage(testAssignmentPage);
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
}
*/