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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");

        Page myPage = PageServiceImpl.getPageService().findPageByTitle(title);
        req.setAttribute("page", myPage);
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        if (myPage instanceof TextPage) {
            req.getRequestDispatcher("textPage.jsp").forward(req, resp);
        } else {
            if (req.getSession().getAttribute("currentUser") instanceof Student) {
                String answer = PageServiceImpl.getPageService().findAnswer((AssignmentPage) myPage, (Student) req.getSession().getAttribute("currentUser"));
                req.setAttribute("answer", answer);
                req.setAttribute("point", PageServiceImpl.getPageService().findGrade((AssignmentPage) myPage, (Student) req.getSession().getAttribute("currentUser")));
            }
            AssignmentPage assignmentPage = (AssignmentPage) myPage;
            boolean submitted = PageServiceImpl.getPageService().userAlreadySubmitted((User) req.getSession().getAttribute("currentUser"), assignmentPage);
            req.setAttribute("userAlreadySubmitted", submitted);
            if (((User) req.getSession().getAttribute("currentUser")).isConnected()) {
                req.setAttribute("repos", ((User) req.getSession().getAttribute("currentUser")).getGitHub().getRepositories());
            }
            req.getRequestDispatcher("assignmentPage.jsp").forward(req, resp);
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        String published = req.getParameter("published");
        if (published.equals("true")) {
            PageServiceImpl.getPageService().findPageByTitle(title).depublish();
        } else {
            PageServiceImpl.getPageService().findPageByTitle(title).publish();
        }
        Page myPage = PageServiceImpl.getPageService().findPageByTitle(title);

        req.setAttribute("page", myPage);
        resp.sendRedirect("home");
    }
}
