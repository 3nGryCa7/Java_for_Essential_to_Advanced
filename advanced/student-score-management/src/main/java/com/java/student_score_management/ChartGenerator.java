package com.java.student_score_management;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import java.awt.*;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;


public class ChartGenerator {
    public static void generateGradeDistributionChart(double[] scores) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Scores", scores, 10);

        JFreeChart chart = ChartFactory.createHistogram(
                "Score Distribution", // Chart title
                "Score", // X-axis label
                "Number", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // Show legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Score Distribution Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
