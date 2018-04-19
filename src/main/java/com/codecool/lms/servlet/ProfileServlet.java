package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import com.codecool.lms.service.PageServiceDaoImpl;
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

@WebServlet("/profile")
public class ProfileServlet extends AbstractServlet {

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

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            DatabasePagesDao pagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(pagesDao);

            //Type
            String type;
            if (req.getParameter("type").equals("Mentor")) {
                pageServiceDao.removeStudentAssignments((Student) currentUser);
                type = "Mentor";
            } else {
                type = "Student";
            }
            try {
                currentUser = userServiceDao.changeUserRole(currentUser, type);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }

            //Name
            if (req.getParameter("newName").length() > 0) {
                String newName = req.getParameter("newName");
                currentUser = userServiceDao.changeUserName(currentUser, newName);
            }

            //Password
            String passwordIsValid = validatePassword(req, resp);

            if (passwordIsValid.equals("Valid")) {
                String password = req.getParameter("newPassword");
                currentUser = userServiceDao.changeUserPassword(currentUser, password);

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


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
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
