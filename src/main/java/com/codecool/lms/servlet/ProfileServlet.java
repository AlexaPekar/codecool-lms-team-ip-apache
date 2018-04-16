package com.codecool.lms.servlet;

import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("user", user);
        req.setAttribute("github", user.getGitHub());
        if (user.isConnected()) {
            req.setAttribute("repos", user.getGitHub().getRepositories());
        }
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        //Type
        String type;
        if (req.getParameter("type").equals("Mentor")) {
            type = "Mentor";
        } else {
            type = "Student";
        }
        currentUser = UserServiceImpl.getUserService().changeUserRole(currentUser, type);

        //Name
        if (req.getParameter("newName").length() > 0) {
            String newName = req.getParameter("newName");
            currentUser = UserServiceImpl.getUserService().changeUserName(currentUser, newName);
        }

        //Password
        String passwordIsValid = validatePassword(req, resp);

        if (passwordIsValid.equals("Valid")) {
            String password = req.getParameter("newPassword");
            currentUser = UserServiceImpl.getUserService().changeUserPassword(currentUser, password);

        } else if (passwordIsValid.equals("Invalid")) {

            req.setAttribute("message", "Invalid password. Try again.");
            req.setAttribute("user", currentUser);
            req.setAttribute("github", currentUser.getGitHub());
            if (currentUser.isConnected()) {
                req.setAttribute("repos", currentUser.getGitHub().getRepositories());
            }
            req.getSession().setAttribute("currentUser", currentUser);
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("currentUser", currentUser);
        resp.sendRedirect("profile");
    }

    private String validatePassword(HttpServletRequest req, HttpServletResponse resp) {

        if (req.getParameter("newPassword").equals("") && req.getParameter("secondPasswordToCheck").equals("")) {
            return "No Change";
        } else if (req.getParameter("newPassword").length() >= 8 &&
                req.getParameter("newPassword").equals(req.getParameter("secondPasswordToCheck"))) {
            return "Valid";
        } else {
            return "Invalid";
        }
    }
}
