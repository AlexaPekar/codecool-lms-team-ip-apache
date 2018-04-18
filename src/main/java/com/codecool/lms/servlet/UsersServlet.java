package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceDaoImpl;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/users")
public class UsersServlet extends AbstractServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            List<User> users = userServiceDao.getUsers();
            req.setAttribute("users", users);
            req.setAttribute("current", req.getSession().getAttribute("currentUser"));
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        req.getRequestDispatcher("changeProfile.jsp").forward(req, resp);
    }
}
