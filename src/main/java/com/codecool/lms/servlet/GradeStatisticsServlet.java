package com.codecool.lms.servlet;

import com.codecool.lms.dao.DatabasePagesDao;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.GradeStatisticsChart;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import com.codecool.lms.service.PageServiceDaoImpl;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;

@WebServlet("/gradeStatistics")
public class GradeStatisticsServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePagesDao databasePagesDao = new DatabasePagesDao(connection);
            PageServiceDaoImpl pageServiceDao = new PageServiceDaoImpl(databasePagesDao);


            User currentUser = (User) req.getSession().getAttribute("currentUser");
            req.setAttribute("current", currentUser);
            List<Assignment> userAssignments = pageServiceDao.currentUserAssingments(currentUser);


            req.setAttribute("userAssignments", userAssignments);
            double max = pageServiceDao.findEvaluatedPercent((Student) currentUser);
            DecimalFormat df2 = new DecimalFormat(".##");
            req.setAttribute("max", df2.format(max));


            //Chart
            GradeStatisticsChart gradeStatisticsChart = new GradeStatisticsChart();
            DefaultCategoryDataset dataset = gradeStatisticsChart.createDataset(currentUser);
            JFreeChart chart = gradeStatisticsChart.createChart(dataset);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream b64os = Base64.getEncoder().wrap(baos);
            ChartUtils.writeChartAsPNG(b64os, chart, 780, 500);
            String b64 = new String(baos.toByteArray());

            req.setAttribute("b64", b64);


            req.getRequestDispatcher("gradeStatistics.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (UserNotFoundException e) {
            req.setAttribute("message", "User not found!");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
