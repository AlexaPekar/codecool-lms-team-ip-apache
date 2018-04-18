package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabaseUserDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import com.codecool.lms.service.UserServiceDaoImpl;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);
            List<Student> students = userServiceDao.getStudents();

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String attendanceDate = req.getParameter("attendanceDate");
            if (attendanceDate == null) {
                attendanceDate = simpleDateFormat.format(date);
            }

            if (!userServiceDao.dayExist(attendanceDate)) {
                List<Student> studentList = new ArrayList<>();
                userServiceDao.addDay(attendanceDate, studentList);
            }

            Day day = userServiceDao.findDayByDate(attendanceDate);
            req.setAttribute("current", req.getSession().getAttribute("currentUser"));
            req.setAttribute("attendanceDate", attendanceDate);
            req.setAttribute("currentDate", simpleDateFormat.format(date));
            req.setAttribute("users", students);
            req.setAttribute("here", day.getStudents());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserServiceDaoImpl userServiceDao = new UserServiceDaoImpl(userDao);

            String[] studentNames = req.getParameterValues("selected");
            List<Student> selectedStudents = userServiceDao.createAttendStudentList(studentNames);
            String attendanceDate = req.getParameter("attendance");
            userServiceDao.updateAttendance(attendanceDate, selectedStudents);

            List<Student> students = userServiceDao.getStudents();
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            Day day = userServiceDao.findDayByDate(attendanceDate);

            req.setAttribute("current", req.getSession().getAttribute("currentUser"));
            req.setAttribute("attendanceDate", attendanceDate);
            req.setAttribute("currentDate", simpleDateFormat.format(date));
            req.setAttribute("users", students);
            req.setAttribute("here", day.getStudents());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }
}
