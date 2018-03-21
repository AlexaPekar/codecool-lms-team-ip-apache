package com.codecool.lms.servlet;

import com.codecool.lms.exception.PageNotFoundException;
import com.codecool.lms.model.Page;
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
        String type = req.getParameter("type");
        String title = req.getParameter("title");
        try {
            Page myPage = PageServiceImpl.getPageService().findPageByTitle(title);
            req.setAttribute("page", myPage);
        } catch (PageNotFoundException e) {
            e.printStackTrace();
        }
        if (type.equals("text")) {
            req.getRequestDispatcher("textPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("assignmentPage.jsp").forward(req, resp);
        }
    }
}
