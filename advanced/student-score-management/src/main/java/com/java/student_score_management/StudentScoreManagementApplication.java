package com.java.student_score_management;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class StudentScoreManagementApplication extends JFrame {
    private DatabaseManager databaseManager;

    public StudentScoreManagementApplication() {
        try {
            databaseManager = DatabaseManager.getInstance();
        } catch (SQLException e) {
            System.out.println("Failed to initialize database: " + e.getMessage());
            System.exit(1);
        }

        setTitle("Student Score Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Functions");
        JMenuItem courseMenuItem = new JMenuItem("Courses");
        JMenuItem studentMenuItem = new JMenuItem("Students");

        courseMenuItem.addActionListener(e -> showCoursePage());

        studentMenuItem.addActionListener(e -> showStudentPage());

        menu.add(courseMenuItem);
        menu.add(studentMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void showCoursePage() {
        CoursePage coursePage = new CoursePage(databaseManager);
        setContentPane(coursePage);
        revalidate();
    }

    private void showStudentPage() {
        StudentPage studentPage = new StudentPage(databaseManager);
        setContentPane(studentPage);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentScoreManagementApplication frame = new StudentScoreManagementApplication();
            frame.setVisible(true);
        });
    }
}
