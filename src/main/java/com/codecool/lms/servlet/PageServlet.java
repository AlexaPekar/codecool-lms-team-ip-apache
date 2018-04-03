package com.codecool.lms.servlet;

import com.codecool.lms.model.*;
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

        Page myPage = PageServiceImpl.getPageService().findPageByTitle(title);
        req.setAttribute("page", myPage);
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        if (myPage instanceof TextPage) {
            req.getRequestDispatcher("textPage.jsp").forward(req, resp);
        } else {
            req.setAttribute("userAlreadySubmitted", false);
            if (req.getSession().getAttribute("currentUser") instanceof Student) {
                String answer = PageServiceImpl.getPageService().findAnswer((AssignmentPage) myPage, (Student) req.getSession().getAttribute("currentUser"));
                req.setAttribute("answer", answer);
                if ((PageServiceImpl.getPageService().findGrade((AssignmentPage) myPage, (Student) req.getSession().getAttribute("currentUser"))) != null) {
                    req.setAttribute("point", PageServiceImpl.getPageService().findGrade((AssignmentPage) myPage, (Student) req.getSession().getAttribute("currentUser")));
                }
            }
            AssignmentPage assignmentPage = (AssignmentPage) myPage;
            for (Assignment assignment : assignmentPage.getAssignments()) {
                if (assignment.getStudent().equals(req.getSession().getAttribute("currentUser"))) {
                    req.setAttribute("userAlreadySubmitted", true);
                }
            }
            req.getRequestDispatcher("assignmentPage.jsp").forward(req, resp);
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
        resp.sendRedirect("home");
    }
}
