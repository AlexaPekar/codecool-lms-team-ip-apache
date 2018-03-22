package com.codecool.lms.servlet;

import com.codecool.lms.exception.PageNotFoundException;
import com.codecool.lms.model.Page;
import com.codecool.lms.model.TextPage;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showpage")
public class PageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        try {
            Page myPage = PageServiceImpl.getPageService().findPageByTitle(title);
            req.setAttribute("page", myPage);
            if (myPage instanceof TextPage) {
                req.getRequestDispatcher("textPage.jsp").forward(req, resp);
            } else {

                req.getRequestDispatcher("assignmentPage.jsp").forward(req, resp);
            }
        } catch (PageNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String published = req.getParameter("published");
            if (published.equals("true")) {
                PageServiceImpl.getPageService().findPageByTitle(title).depublish();
                req.setAttribute("message", "Page is unpublished");
            } else {
                PageServiceImpl.getPageService().findPageByTitle(title).publish();
                req.setAttribute("message", "Page is published");
            }
            Page myPage = PageServiceImpl.getPageService().findPageByTitle(title);

            req.setAttribute("page", myPage);
        req.getRequestDispatcher("redirectHome.jsp").forward(req, resp);
    }
}
