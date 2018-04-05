package com.codecool.lms.model;

import com.codecool.lms.service.PageServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.List;

public class GradeStatisticsChart {

    public GradeStatisticsChart() {}

    public DefaultCategoryDataset createDataset(User currentUser) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Assignment> currentUserAssignments = PageServiceImpl.getPageService().currentUserAssingments(currentUser);
        for (Assignment assignment : currentUserAssignments) {
            dataset.setValue(calculateGradePercentage(assignment.getGrade(),
                                                    assignment.getMaxScore()),
                                                    assignment.getTitle(),
                                                    assignment.getDate());
        }
        return dataset;
    }

    public int calculateGradePercentage(int grade, int maxScore) {
        return (grade / maxScore) * 100;
    }

    public JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
            "Grade Statistics", // chart title
            "Date", // domain axis label
            "Grade percentage", dataset,
            PlotOrientation.VERTICAL, // orientation
            true, // include legend
            true, // tooltips?
            false // URLs?
        );
        chart.setBackgroundPaint(Color.white);
        return chart;
    }
}
