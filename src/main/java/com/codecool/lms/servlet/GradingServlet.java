package com.codecool.lms.servlet;


import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.Page;
import com.codecool.lms.model.Student;
import com.codecool.lms.service.PageServiceImpl;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/grading")
public class GradingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AssignmentPage> assignmentPages = PageServiceImpl.getPageService().getAssignmentPages();
        List<Assignment> assignments = new ArrayList<>();
        for (AssignmentPage page : assignmentPages) {
            assignments.addAll(page.getAssignments());
        }
        req.setAttribute("assignments", assignments);
        req.getRequestDispatcher("grading.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int grade = Integer.parseInt(req.getParameter("grade"));
        String studentName = req.getParameter("student");
        String title = req.getParameter("title");
        Student student = (Student) UserServiceImpl.getUserService().findUserByName(studentName);
        AssignmentPage assignmentPage = (AssignmentPage) PageServiceImpl.getPageService().findPageByTitle(title);
        PageServiceImpl.getPageService().getAssignmentByStudentName(assignmentPage, student).setGrade(grade);
        resp.sendRedirect("grading");
    }
}
