package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceDaoImpl;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            userServiceDao.register(name, email, password, type);
            req.setAttribute("message", "Registration is successful, you can login now!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserAlreadyRegisteredException e) {
            req.setAttribute("message", "You are already registered!");
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
