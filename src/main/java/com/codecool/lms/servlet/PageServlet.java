package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.*;
import com.codecool.lms.service.PageServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/showpage")
public class PageServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);
            String title = req.getParameter("title");

            Page myPage = pageServiceDao.findPageByTitle(title);
            req.setAttribute("page", myPage);
            User currentUser = (User) req.getSession().getAttribute("currentUser");
            req.setAttribute("current", currentUser);
            if (myPage instanceof TextPage) {
                req.getRequestDispatcher("textPage.jsp").forward(req, resp);
            } else {
                if (req.getSession().getAttribute("currentUser") instanceof Student) {
                    String answer = pageServiceDao.findAnswer((AssignmentPage) myPage, (Student) currentUser);
                    req.setAttribute("answer", answer);
                    req.setAttribute("point", pageServiceDao.findGrade((AssignmentPage) myPage, (Student) currentUser));
                }
                AssignmentPage assignmentPage = (AssignmentPage) myPage;
                boolean submitted = pageServiceDao.userAlreadySubmitted(currentUser, assignmentPage);
                req.setAttribute("userAlreadySubmitted", submitted);
                if (currentUser.isConnected()) {
                    req.setAttribute("repos", currentUser.getGitHub().getRepositories());
                }
                req.getRequestDispatcher("assignmentPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (UserNotFoundException e) {
            req.setAttribute("message", "User not found!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);
            String title = req.getParameter("title");
            String published = req.getParameter("published");
            if (published.equals("true")) {
                pageServiceDao.editPage(title, false);
            } else {
                pageServiceDao.editPage(title, true);
            }
            resp.sendRedirect("home");
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
