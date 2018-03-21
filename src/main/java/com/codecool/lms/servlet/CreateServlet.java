package com.codecool.lms.servlet;

import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.TextPage;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/create")
public class CreateServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        if (type.equals("text")) {
            TextPage myTextPage = new TextPage(title, content);
            PageServiceImpl.getPageService().addNewPage(myTextPage);
        } else {
            int maxPoint = Integer.parseInt(req.getParameter("maxpoint"));
            AssignmentPage assignmentPage = new AssignmentPage(title, content, maxPoint);
            PageServiceImpl.getPageService().addNewPage(assignmentPage);
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
