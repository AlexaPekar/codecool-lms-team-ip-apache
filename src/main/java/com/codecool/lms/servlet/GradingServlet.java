package com.codecool.lms.servlet;


import com.codecool.lms.model.Assignment;
import com.codecool.lms.service.PageServiceImpl;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/grading")
public class GradingServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Assignment> assignments = PageServiceImpl.getPageService().getAssignments();
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        req.setAttribute("assignments", assignments);
        req.getRequestDispatcher("grading.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int grade = Integer.parseInt(req.getParameter("grade"));
        String studentName = req.getParameter("student");
        String title = req.getParameter("title");
        UserServiceImpl.getUserService().gradeAssignment(grade, studentName, title);
        resp.sendRedirect("grading");
    }
}
