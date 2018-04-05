package com.codecool.lms.servlet;

import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.User;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gradeStatistics")
public class GradeStatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("current", currentUser);
        List<Assignment> userAssignments = PageServiceImpl.getPageService().currentUserAssingments(currentUser);

        req.setAttribute("userAssignments", userAssignments);
        req.getRequestDispatcher("gradeStatistics.jsp").forward(req, resp);
    }
}
