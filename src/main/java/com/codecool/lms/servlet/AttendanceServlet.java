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
        List<Student> students = UserServiceImpl.getUserService().getStudents();

        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        if (!UserServiceImpl.getUserService().dayExist(simpleDateFormat.format(date))) {
            List<Student> studentList = new ArrayList<>();
            Day today = new Day(studentList, simpleDateFormat.format(date));
            UserServiceImpl.getUserService().addDay(today);
        }
        Day day = UserServiceImpl.getUserService().findDayByDate(simpleDateFormat.format(date));

        req.setAttribute("attendanceDate", simpleDateFormat.format(date));
        req.setAttribute("currentDate", simpleDateFormat.format(date));
        req.setAttribute("users", students);
        req.setAttribute("here", day.getStudents());
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserServiceImpl.getUserService().getUsers();
        List<Student> students = UserServiceImpl.getUserService().getStudents();
        req.setAttribute("current", req.getSession().getAttribute("currentUser"));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String attendanceDate = req.getParameter("attendanceDate");
        if (!UserServiceImpl.getUserService().dayExist(attendanceDate)) {
            List<Student> studentList = new ArrayList<>();
            UserServiceImpl.getUserService().addDay(new Day(studentList, attendanceDate));
        }
        Day day = UserServiceImpl.getUserService().findDayByDate(attendanceDate);

        req.setAttribute("attendanceDate", attendanceDate);
        req.setAttribute("currentDate", simpleDateFormat.format(date));
        req.setAttribute("users", students);
        req.setAttribute("here", day.getStudents());
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }
}
