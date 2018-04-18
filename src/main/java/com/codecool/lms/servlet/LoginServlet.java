package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends AbstractServlet {


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            User currentUser = userServiceDao.findUserByLoginData(email, password);
            req.getSession().setAttribute("currentUser", currentUser);
            req.getRequestDispatcher("loginForward.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
        } catch (UserNotFoundException e) {
            req.setAttribute("message", "No user found with the given email.");
        } catch (WrongPasswordException e) {
            req.setAttribute("message", "Wrong password entered!");
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
