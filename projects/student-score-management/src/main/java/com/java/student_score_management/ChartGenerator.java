package com.java.student_score_management;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static com.java.student_score_management.utils.Constants.FRAME_400;
import static com.java.student_score_management.utils.Constants.FRAME_600;

public class ChartGenerator {

    public ChartGenerator() {}

    private JPanel createCombinedChartPanel(List<Double> grades) {
        JPanel combinedPanel = new JPanel(new GridLayout(2, 1));
        combinedPanel.add(createHistogramPanel(grades));
        combinedPanel.add(createLineChartPanel(grades));

        return combinedPanel;
    }

    private ChartPanel createHistogramPanel(List<Double> grades) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Grades", grades.stream().mapToDouble(Double::doubleValue).toArray(), 10);

        JFreeChart chart = ChartFactory.createHistogram(
                "Distribution",
                "Grade",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createLineChartPanel(List<Double> grades) {
        XYSeries series = new XYSeries("Grades Trend");
        for (int i = 0; i < grades.size(); i++) {
            series.add(i + 1, grades.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Line Chart",
                "Index",
                "Grade",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        XYPlot plot = lineChart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);

        return new ChartPanel(lineChart);
    }

    private JPanel createStatisticsPanel(List<Double> grades) {
        JPanel statsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        double mean = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double stdDev = Math.sqrt(grades.stream()
                .mapToDouble(g -> Math.pow(g - mean, 2))
                .average().orElse(0));
        double max = grades.stream().mapToDouble(Double::doubleValue).max().orElse(0);
        double min = grades.stream().mapToDouble(Double::doubleValue).min().orElse(0);

        statsPanel.add(new JLabel("Mean:"));
        statsPanel.add(new JLabel(String.format("%.2f", mean)));

        statsPanel.add(new JLabel("Standard Deviation:"));
        statsPanel.add(new JLabel(String.format("%.2f", stdDev)));

        statsPanel.add(new JLabel("Maximum:"));
        statsPanel.add(new JLabel(String.format("%.2f", max)));

        statsPanel.add(new JLabel("Minimum:"));
        statsPanel.add(new JLabel(String.format("%.2f", min)));

        return statsPanel;
    }

    private void saveAsImage(Component component, String filename) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        component.paint(graphics);
        graphics.dispose();

        try {
            ImageIO.write(image, "png", new File(filename));
            JOptionPane.showMessageDialog(null, "Chart saved as: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving chart: " + e.getMessage());
        }
    }

    public void showCharts(List<Double> grades) {
        JFrame frame = new JFrame("Grade Statistics Charts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel chartPanel = createCombinedChartPanel(grades);
        frame.add(chartPanel, BorderLayout.CENTER);

        JPanel statsPanel = createStatisticsPanel(grades);

        JButton saveButton = new JButton("Save as Image");
        saveButton.addActionListener(e -> saveAsImage(chartPanel, "grade_statistics.png"));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statsPanel, BorderLayout.WEST);
        bottomPanel.add(saveButton, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(FRAME_400, FRAME_600);

        frame.setVisible(true);
    }

}