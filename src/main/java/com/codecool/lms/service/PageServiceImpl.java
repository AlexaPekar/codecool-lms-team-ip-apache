package com.codecool.lms.service;

import com.codecool.lms.model.Page;

import java.util.ArrayList;
import java.util.List;

public class PageServiceImpl implements PageService {

    private static PageServiceImpl pageService = new PageServiceImpl();
    private List<Page> pages = new ArrayList<>();

    //Visible for testing
    PageServiceImpl() {}

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
}
