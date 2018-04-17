package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.model.AssignmentPage;
import com.codecool.lms.model.User;
import com.codecool.lms.service.PageServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/submission")
public class AssignmentServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);

            User user = (User) req.getSession().getAttribute("currentUser");

            String title = req.getParameter("pageTitle");
            String answer = req.getParameter("answer");
            if (user.isConnected()) {
                String repo = req.getParameter("repolist");
                if (!repo.equals("null")) {
                    repo = "<a href=" + repo + ">" + repo + "</a>";
                    answer = repo;
                }
            }
            int maxScore = ((AssignmentPage) pageServiceDao.findPageByTitle(title)).getMaxScore();
            pageServiceDao.addAssignmentToAssignmentPage(user, title, answer, maxScore);
            resp.sendRedirect("home");
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
