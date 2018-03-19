package com.codecool.lms.service;

import com.codecool.lms.exception.PageNotFoundException;
import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.TextPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageServiceTest {

    PageService pageService = new PageService();
    TextPage testTextPage = new TextPage("Kitties", "Kitties must be loved!");
    AssignmentPage testAssignmentPage = new AssignmentPage("Kitties Test", "Do you love kitties?", 10);

    @BeforeEach
    void setUp() {
    }

    @Test
    void addNewPage() {
        pageService.addNewPage(testTextPage);
        assertTrue(pageService.getPages().contains(testTextPage));
    }

    @Test
    void removePage() throws PageNotFoundException {
        pageService.addNewPage(testAssignmentPage);
        assertTrue(pageService.getPages().contains(testAssignmentPage));
        pageService.removePage("Kitties Test");
        assertFalse(pageService.getPages().contains(testAssignmentPage));
    }

    @Test
    void findPageByTitle() throws PageNotFoundException {
        pageService.addNewPage(testTextPage);
        pageService.addNewPage(testAssignmentPage);
        assertEquals(testTextPage, pageService.findPageByTitle("Kitties"));
        assertEquals(testAssignmentPage, pageService.findPageByTitle("Kitties Test"));
    }
}