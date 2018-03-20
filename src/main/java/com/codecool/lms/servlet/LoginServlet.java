package com.codecool.lms.servlet;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserServiceImpl userServiceImpl = UserServiceImpl.getUserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        String password = req.getParameter("password");

        if (userServiceImpl.containsUser(email)) {
            try {
                userServiceImpl.setCurrentUser(userServiceImpl.findUserByEmail(email, password));
                req.getRequestDispatcher("home.jsp").forward(req, resp);

            } catch (UserNotFoundException e) {
                req.setAttribute("message", "No user found with the given email.");
            } catch (WrongPasswordException e) {
                req.setAttribute("message", "Wrong password entered!");
            }
        } else {
            req.setAttribute("message", "No user found with the given email.");
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);


    }
}
