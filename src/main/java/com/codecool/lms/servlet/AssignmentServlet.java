package com.codecool.lms.servlet;

import com.codecool.lms.model.*;
import com.codecool.lms.service.PageServiceImpl;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submission")
public class AssignmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student currentStudent = (Student) UserServiceImpl.getUserService().getCurrentUser();

        String title = req.getParameter("pageTitle");
        String answer = req.getParameter("answer");

        Assignment assignment = new Assignment(currentStudent, answer);

        AssignmentPage page = (AssignmentPage) PageServiceImpl.getPageService().findPageByTitle(title);
        page.addAssignment(assignment);

        req.setAttribute("message", "Submission is successful!");
        req.getRequestDispatcher("redirectHome.jsp").forward(req, resp);
    }
}
