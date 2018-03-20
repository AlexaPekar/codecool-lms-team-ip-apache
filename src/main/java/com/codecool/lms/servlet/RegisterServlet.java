package com.codecool.lms.servlet;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserServiceImpl userServiceImpl = UserServiceImpl.getUserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        User user = userServiceImpl.createUser(email, name, password, type);
        try {
            userServiceImpl.register(user);
            req.setAttribute("message", "Registration is successful, you can login now!");
        } catch (UserAlreadyRegisteredException e) {
            req.setAttribute("message", "You are already registered!");
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
