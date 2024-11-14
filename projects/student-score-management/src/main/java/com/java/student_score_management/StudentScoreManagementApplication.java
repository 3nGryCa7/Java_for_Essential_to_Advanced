package com.java.student_score_management;

import com.java.student_score_management.pages.CoursePage;
import com.java.student_score_management.pages.StudentPage;
import com.java.student_score_management.utils.Constants;

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
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel(new GridLayout(1, 8));
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuPanel);

        JMenuItem courseMenuItem = new JMenuItem("Courses");
        courseMenuItem.setHorizontalAlignment(SwingConstants.CENTER);

        JMenuItem studentMenuItem = new JMenuItem("Students");
        studentMenuItem.setHorizontalAlignment(SwingConstants.CENTER);

        courseMenuItem.addActionListener(e -> showCoursePage());
        studentMenuItem.addActionListener(e -> showStudentPage());

        menuPanel.add(courseMenuItem);
        menuPanel.add(studentMenuItem);
        setJMenuBar(menuBar);
        setVisible(true);
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
