package com.codecool.lms.servlet;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.service.UserService;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        String password = req.getParameter("password");

        if (userService.containsUser(email)) {
            try {
                userService.setCurrentUser(userService.findUserByEmail(email, password));
                req.getRequestDispatcher("home.html").forward(req, resp);

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
