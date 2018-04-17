package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.service.PageServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/create")
public class CreateServlet extends AbstractServlet {


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);
            String type = req.getParameter("type");
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            int maxPoint = Integer.parseInt(req.getParameter("maxScore"));
            resp.sendRedirect("home");

            pageServiceDao.addNewPage(title, content, type, maxPoint);
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        if (type.equals("text")) {
            req.getRequestDispatcher("createTextInHome.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("createAssignmentInHome.jsp").forward(req, resp);
        }
    }
}
