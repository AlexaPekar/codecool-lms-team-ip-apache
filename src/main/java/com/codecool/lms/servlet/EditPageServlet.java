package com.codecool.lms.servlet;

import com.codecool.lms.model.Page;
import com.codecool.lms.model.TextPage;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/edit")
public class EditPageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageName = req.getParameter("page");
        Page page = PageServiceImpl.getPageService().findPageByTitle(pageName);
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        req.setAttribute("page", page);
        if (page instanceof TextPage) {
            req.getRequestDispatcher("editTextPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("editAssignmentPage.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        String title = req.getParameter("title");
        String oldTitle = req.getParameter("old-title");
        String content = req.getParameter("content");
        int maxPoint = Integer.parseInt(req.getParameter("maxScore"));
        PageServiceImpl.getPageService().editPage(title, content, type, maxPoint, oldTitle);
        resp.sendRedirect("home");
    }
}

