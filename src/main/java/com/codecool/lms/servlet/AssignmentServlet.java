package com.codecool.lms.servlet;

import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.Student;
import com.codecool.lms.service.PageServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submission")
public class AssignmentServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Student currentStudent = (Student) req.getSession().getAttribute("currentUser");

        String title = req.getParameter("pageTitle");
        String answer = req.getParameter("answer");
        if (currentStudent.isConnected()) {
            String repo = req.getParameter("repolist");
            if (!repo.equals("null")) {
                repo = "<a href=" + repo + ">" + repo + "</a>";
                answer = repo;
            }
        }
        int maxScore = ((AssignmentPage) PageServiceImpl.getPageService().findPageByTitle(title)).getMaxScore();

        Assignment assignment = new Assignment(currentStudent, answer, title, maxScore);

        PageServiceImpl.getPageService().addAssignmentToAssignmentPage(assignment);
        resp.sendRedirect("home");
    }
}
