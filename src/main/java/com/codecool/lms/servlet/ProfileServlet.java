package com.codecool.lms.servlet;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.model.Mentor;
import com.codecool.lms.model.Student;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("user", user);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        if (req.getParameter("type").equals("Mentor")) {
            UserServiceImpl.getUserService().deleteUser(currentUser.getName());
            Mentor mentor = new Mentor(currentUser.getName(), currentUser.getEmail(), currentUser.getPassword());
            try {
                UserServiceImpl.getUserService().register(mentor);
                req.getSession().setAttribute("currentUser", mentor);
            } catch (UserAlreadyRegisteredException e) {
                e.printStackTrace();
            }
        } else if (req.getParameter("type").equals("Student")) {
            UserServiceImpl.getUserService().deleteUser(currentUser.getName());
            Student student = new Student(currentUser.getName(), currentUser.getEmail(), currentUser.getPassword());
            try {
                UserServiceImpl.getUserService().register(student);
                req.getSession().setAttribute("currentUser", student);

            } catch (UserAlreadyRegisteredException e) {
                e.printStackTrace();
            }
        }
        currentUser = (User) req.getSession().getAttribute("currentUser");
        if (req.getParameter("newName").length() > 0) {
            currentUser.setName(req.getParameter("newName"));
        }
        if ((req.getParameter("newPassword").length() >= 8 &&
                req.getParameter("newPassword").equals(req.getParameter("secondPasswordToCheck"))) ||
                (req.getParameter("newPassword").equals("") &&
                        req.getParameter("secondPasswordToCheck").equals(""))) {
            currentUser.setPassword(req.getParameter("newPassword"));
            req.getSession().setAttribute("currentUser", currentUser);
            resp.sendRedirect("home");
        } else {
            req.setAttribute("message", "Invalid password. Try again.");
            req.getSession().setAttribute("currentUser", currentUser);
            req.getRequestDispatcher("redirectProfile.jsp").forward(req, resp);
        }
    }
}
