package com.codecool.lms.servlet;

import com.codecool.lms.model.Day;
import com.codecool.lms.model.Student;
import com.codecool.lms.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/attend")
public class AttendServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] studentNames = req.getParameterValues("selected");
        List<Student> selectedStudents = UserServiceImpl.getUserService().createAttendStudentList(studentNames);
        String date = req.getParameter("attendance");
        if (UserServiceImpl.getUserService().dayExist(date)) {
            UserServiceImpl.getUserService().findDayByDate(date).setStudents(selectedStudents);
        } else {
            UserServiceImpl.getUserService().addDay(new Day(selectedStudents, date));
        }
        resp.sendRedirect("attendance");
    }

}
