package com.java.student_score_management.pages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.student_score_management.DatabaseManager;
import com.java.student_score_management.models.Course;
import com.java.student_score_management.models.Student;
import com.java.student_score_management.table_models.CourseTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class CoursePage extends JPanel {
    private final DatabaseManager databaseManager;
    private JTable courseTable;

    @SuppressWarnings("unused")
    public CoursePage(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        setLayout(new BorderLayout());

        courseTable = new JTable(loadCourseData());
        JScrollPane courseScrollPane = new JScrollPane(courseTable);
        add(courseScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

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
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right (optional)

        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(e -> showAddCourseDialog());
        buttonPanel.add(addCourseButton);

        JButton importCourseButton = new JButton("Import Course");
        importCourseButton.addActionListener(e -> showImportCourseDialog());
        buttonPanel.add(importCourseButton);
        return buttonPanel;
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

        Object[] message = {
                "Course Code:", courseCodeField,
                "Semester:", semesterField,
                "Course Name:", nameField,
        };

        int option = JOptionPane.showConfirmDialog(this, message, "New Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            String courseCode = courseCodeField.getText();
            String semester = semesterField.getText();
            String courseName = nameField.getText();

            if (courseName == null || courseName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Course name cannot be empty or blank.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (databaseManager.courseExists(courseCode, semester)) {
                JOptionPane.showMessageDialog(this,
                        "The course already exists.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            databaseManager.addCourse(courseCode, semester, courseName);
            courseTable.setModel(loadCourseData());
        }
    }


    private void showImportCourseDialog() {
        // Step 1: Let the user select a JSON file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Course Data JSON File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Step 2: Parse JSON file into list of students
                List<Course> courses = parseJsonFile(selectedFile);

                // Step 3: Verify structure
                for (Course course : courses) {
                    if (!isValidCourse(course)) {
                        JOptionPane.showMessageDialog(this,
                                "Invalid course data structure: " + course,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Step 4: Insert into the database
                for (Course course : courses) {
                    if (!databaseManager.courseExists(course.getCourseNumber(), course.getCourseName())) {
                        databaseManager.addCourse(course.getCourseNumber(), course.getSemester(), course.getCourseName());
                    }
                }

                courseTable.setModel(loadCourseData());
                JOptionPane.showMessageDialog(this, "Courses imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Failed to import students: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private List<Course> parseJsonFile(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<Course>>() {});
    }

    private boolean isValidCourse(Course course) {
        return course.getCourseName() != null && course.getCourseNumber().matches("IM\\d{7}") &&
                course.getSemester() != null && !course.getCourseNumber().trim().isEmpty();
    }
}
