package com.codecool.lms.servlet;

import com.codecool.lms.model.GradeStatisticsChart;
import com.codecool.lms.model.User;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;


@WebServlet("/gradeChart")
public class GradeChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GradeStatisticsChart gradeStatisticsChart = new GradeStatisticsChart();
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("current", currentUser);
        DefaultCategoryDataset dataset = gradeStatisticsChart.createDataset(currentUser);
        JFreeChart chart = gradeStatisticsChart.createChart(dataset);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream b64os = Base64.getEncoder().wrap(baos);
        ChartUtils.writeChartAsPNG(b64os, chart, 1024, 768);
        String b64 = new String(baos.toByteArray());

        req.setAttribute("b64", b64);
        req.getRequestDispatcher("gradeStatistics.jsp").forward(req, resp);
    }
}
