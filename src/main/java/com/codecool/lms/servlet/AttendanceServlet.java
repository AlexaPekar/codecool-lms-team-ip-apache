package com.codecool.lms.servlet;

import com.codecool.lms.model.Day;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserServiceImpl.getUserService().getUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> selectedStudents = new ArrayList<>();
        //System.out.println(req.getParameter("selected"));

        String[] studentNames = req.getParameterValues("selected");
        for (String name : studentNames) {
            selectedStudents.add((Student) UserServiceImpl.getUserService().findUserByName(name));
        }
        UserServiceImpl.getUserService().addDay(new Day(selectedStudents));
        System.out.println(UserServiceImpl.getUserService().getDays().size());
        for (Student s : selectedStudents) {
            System.out.println(s.getName());
        }
    }
}
