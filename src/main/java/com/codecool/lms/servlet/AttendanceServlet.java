package com.codecool.lms.servlet;

import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserServiceImpl.getUserService().getUsers();
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        req.setAttribute("users", users);
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserServiceImpl.getUserService().getUsers();
        List<User> selectedUsers = new ArrayList<>();

        System.out.println(req.getParameter("selected"));
        for (User usr : users) {
            if (req.getParameter("selected").equals(usr.getName())) {
                selectedUsers.add(usr);
            } else {
                System.out.println("SubmitWithoutTickingException:)");
            }
        }
    }
}
