package com.codecool.lms.model;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class GradeStatisticsChart extends ApplicationFrame{

    public GradeStatisticsChart() {
        super();

    }

    private static DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue();
    }

    public int calculateGradePercentage(int grade, int maxScore) {
        return (grade / maxScore) * 100;
    }
}
