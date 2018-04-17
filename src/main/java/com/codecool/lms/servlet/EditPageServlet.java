package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.model.Page;
import com.codecool.lms.model.TextPage;
import com.codecool.lms.service.PageServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/edit")
public class EditPageServlet extends AbstractServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);
            String pageName = req.getParameter("page");
            Page page = pageServiceDao.findPageByTitle(pageName);
            req.setAttribute("current", req.getSession().getAttribute("currentUser"));
            req.setAttribute("page", page);
            if (page instanceof TextPage) {
                req.getRequestDispatcher("editTextPage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("editAssignmentPage.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);
            String type = req.getParameter("type");
            String title = req.getParameter("title");
            String oldTitle = req.getParameter("old-title");
            String content = req.getParameter("content");
            int maxPoint = Integer.parseInt(req.getParameter("maxScore"));
            pageServiceDao.editPage(title, content, type, maxPoint, oldTitle);
            resp.sendRedirect("home");
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}

