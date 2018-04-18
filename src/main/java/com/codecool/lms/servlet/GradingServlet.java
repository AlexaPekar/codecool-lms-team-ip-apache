package com.codecool.lms.servlet;


import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.Student;
import com.codecool.lms.service.PageServiceDaoImpl;
import com.codecool.lms.service.UserServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/grading")
public class GradingServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);

            List<Assignment> assignments = pageServiceDao.getAssignments();
            req.setAttribute("current", req.getSession().getAttribute("currentUser"));
            req.setAttribute("assignments", assignments);
            req.getRequestDispatcher("grading.jsp").forward(req, resp);
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
            DatabaseUserDao databaseUserDao = new DatabaseUserDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(databaseUserDao);
            int grade = Integer.parseInt(req.getParameter("grade"));
            String studentName = req.getParameter("student");
            Student student = (Student) userServiceDao.findUserByName(studentName);
            String title = req.getParameter("title");
            pageServiceDao.gradeAssignment(student, grade, title);
            resp.sendRedirect("grading");
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (UserNotFoundException e) {
            req.setAttribute("message", "User not found!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }
}
