package com.java.student_score_management.pages;

import com.java.student_score_management.DatabaseManager;
import com.java.student_score_management.models.Course;
import com.java.student_score_management.table_models.CourseTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class CoursePage extends JPanel {
    private final DatabaseManager databaseManager;
    private JTable courseTable;

    public CoursePage(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        setLayout(new BorderLayout());

        courseTable = new JTable(loadCourseData());
        JScrollPane courseScrollPane = new JScrollPane(courseTable);
        add(courseScrollPane, BorderLayout.CENTER);

        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = courseTable.getSelectedRow();
                if (row >= 0) {
                    Course selectedCourse = ((CourseTableModel) courseTable.getModel()).getCourseAt(row);
                    showCourseDetailPage(selectedCourse);
                }
            }
        });

        JButton addCourseButton = new JButton("New Course");
        addCourseButton.addActionListener(e -> showAddCourseDialog());
        add(addCourseButton, BorderLayout.SOUTH);
    }

    private CourseTableModel loadCourseData() {
        List<Course> courses = databaseManager.getAllCourses();
        return new CourseTableModel(courses);
    }

    private void showCourseDetailPage(Course course) {
        JFrame courseDetailFrame = new JFrame(course.getCourseName() + " Details");
        courseDetailFrame.add(new CourseDetailPage(databaseManager, course));
        courseDetailFrame.setSize(600, 400);
        courseDetailFrame.setVisible(true);
    }

    private void showAddCourseDialog() {
        JTextField courseCodeField = new JTextField();
        JTextField semesterField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField capacityField = new JTextField();

        Object[] message = {
                "Course Code:", courseCodeField,
                "Semester:", semesterField,
                "Course Name:", nameField,
                "Student Amount:", capacityField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "New Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            String courseCode = courseCodeField.getText();
            String semester = semesterField.getText();
            String courseName = nameField.getText();
            int studentAmount = Integer.parseInt(capacityField.getText());

            if (databaseManager.courseExists(courseCode, semester)) {
                JOptionPane.showMessageDialog(this, "The course already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            databaseManager.addCourse(courseCode, semester, courseName, studentAmount);
            courseTable.setModel(loadCourseData());
        }
    }
}
