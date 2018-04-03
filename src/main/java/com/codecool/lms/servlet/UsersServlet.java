package com.codecool.lms.servlet;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.model.Mentor;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserService;
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
        User currentUser = UserServiceImpl.getUserService().getCurrentUser();
        if (req.getParameter("type").equals("Mentor")) {
            UserServiceImpl.getUserService().deleteUser(currentUser.getName());
            Mentor mentor = new Mentor(currentUser.getName(),currentUser.getEmail(),currentUser.getPassword());
            try {
                UserServiceImpl.getUserService().register(mentor);
            } catch (UserAlreadyRegisteredException e) {
                e.printStackTrace();
            }
        }else{
            UserServiceImpl.getUserService().deleteUser(currentUser.getName());
            Student student = new Student(currentUser.getName(), currentUser.getEmail(), currentUser.getPassword());
            try {
                UserServiceImpl.getUserService().register(student);
            } catch (UserAlreadyRegisteredException e) {
                e.printStackTrace();
            }
        }
        if (req.getParameter("newName").length() > 0) {
            currentUser.setName(req.getParameter("newName"));
        } else {
            req.getParameter(currentUser.getName());
        }
        if (req.getParameter("newPassword").length() >= 8 &&
                req.getParameter("newPassword").equals(req.getParameter("secondPasswordToCheck"))) {
            currentUser.setPassword(req.getParameter("newPassword"));
            resp.sendRedirect("home");
        } else {
            req.setAttribute("message", "Invalid password. Try again.");
            req.getRequestDispatcher("redirectProfile.jsp").forward(req, resp);
        }

    }
}
