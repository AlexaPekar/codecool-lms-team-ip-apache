package com.codecool.lms.model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class GradeStatisticsChart {

    public GradeStatisticsChart() {}

    public DefaultCategoryDataset createDataset(int grade, int maxScore, String row, String column) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(calculateGradePercentage(grade, maxScore), row, column);
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
