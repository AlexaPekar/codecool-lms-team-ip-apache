package com.codecool.lms.servlet;

import com.codecool.lms.model.Assignment;
import com.codecool.lms.model.GradeStatisticsChart;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import com.codecool.lms.service.PageServiceImpl;
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
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;

@WebServlet("/gradeStatistics")
public class GradeStatisticsServlet extends AbstractServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        req.setAttribute("current", currentUser);
        List<Assignment> userAssignments = PageServiceImpl.getPageService().currentUserAssingments(currentUser);

        req.setAttribute("userAssignments", userAssignments);
        double max = PageServiceImpl.getPageService().findEvaluatedPercent((Student) req.getSession().getAttribute("currentUser"));
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
    }
}
