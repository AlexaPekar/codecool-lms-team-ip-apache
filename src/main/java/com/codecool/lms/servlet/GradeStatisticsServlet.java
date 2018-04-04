package com.codecool.lms.servlet;

import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.User;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gradeStatistics")
public class GradeStatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AssignmentPage> assignmentPages = PageServiceImpl.getPageService().getAssignmentPages();
        List<Assignment> assignments = new ArrayList<>();
        List<Assignment> userAssignments = new ArrayList<>();
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("current", currentUser);
        for (AssignmentPage page : assignmentPages) {
            assignments.addAll(page.getAssignments());
        }
        for (Assignment assignment : assignments) {
            if (assignment.getStudent().equals(currentUser)) {
                userAssignments.add(assignment);
            }
        }

        req.setAttribute("userAssignments", userAssignments);
        req.getRequestDispatcher("gradeStatistics.jsp").forward(req, resp);
    }
}
