package com.codecool.lms.service;

import com.codecool.lms.exception.PageNotFoundException;
import com.codecool.lms.model.Page;
import java.util.List;

public interface PageService {

    List<Page> getPages();
    void addNewPage(Page page);
    void removePage(String title) throws PageNotFoundException;
    Page findPageByTitle(String title) throws PageNotFoundException;

}
