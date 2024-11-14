package com.java.student_score_management;

import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class ChartGenerator {

    public ChartGenerator() {}

    public void displayGradeDistributionChart(List<Double> grades) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Grades", grades.stream().mapToDouble(Double::doubleValue).toArray(), 10);

        JFreeChart chart = ChartFactory.createHistogram(
                "Grade Distribution",
                "Grade",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Grade Distribution Chart");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public double calculateMean(List<Double> grades) {
        return grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calculateStandardDeviation(List<Double> grades, double mean) {
        return Math.sqrt(grades.stream()
                .mapToDouble(grade -> Math.pow(grade - mean, 2))
                .average()
                .orElse(0.0));
    }

}