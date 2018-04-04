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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserServiceImpl.getUserService().getUsers();
        List<Student> students = new ArrayList<>();
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        req.setAttribute("currentDate", simpleDateFormat.format(date));

        req.setAttribute("users", students);
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> selectedStudents = new ArrayList<>();
        String[] studentNames = req.getParameterValues("selected");
        for (String name : studentNames) {
            selectedStudents.add((Student) UserServiceImpl.getUserService().findUserByName(name));
        }
        String date = req.getParameter("attendanceDate");
        if (UserServiceImpl.getUserService().dayExist(date)) {
            UserServiceImpl.getUserService().findDayByDate(date).setStudents(selectedStudents);
        } else {
            UserServiceImpl.getUserService().addDay(new Day(selectedStudents, date));
        }

        for (Student s : selectedStudents) {
            System.out.println(s.getName());
        }
    }
}
