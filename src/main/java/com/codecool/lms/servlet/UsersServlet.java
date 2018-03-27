package com.codecool.lms.servlet;

import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/users")
public class UsersServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserServiceImpl.getUserService().getUsers();
        req.setAttribute("users", users);
        req.setAttribute("current", UserServiceImpl.getUserService().getCurrentUser());
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("newName").length() > 0) {
            UserServiceImpl.getUserService().getCurrentUser().setName(req.getParameter("newName"));
        } else {
            req.getParameter(UserServiceImpl.getUserService().getCurrentUser().getName());
        }
        if (req.getParameter("newPassword").length() >= 8 &&
                req.getParameter("newPassword").equals(req.getParameter("secondPasswordToCheck"))) {
            UserServiceImpl.getUserService().getCurrentUser().setPassword(req.getParameter("newPassword"));
            req.setAttribute("message", "Your profile modified successfully.");
            req.getRequestDispatcher("redirectHome.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Invalid password. Try again.");
            req.getRequestDispatcher("redirectProfile.jsp").forward(req, resp);
        }
    }
}
