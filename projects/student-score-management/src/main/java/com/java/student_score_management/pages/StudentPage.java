package com.java.student_score_management.pages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.student_score_management.DatabaseManager;
import com.java.student_score_management.models.Course;
import com.java.student_score_management.models.Student;
import com.java.student_score_management.table_models.StudentTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentPage extends JPanel {
    private final DatabaseManager databaseManager;
    private JTable studentTable;

    @SuppressWarnings("unused")
    public StudentPage(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        setLayout(new BorderLayout());

        studentTable = new JTable(loadStudentData());
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        add(studentScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    showStudentCoursesDialog();
                }
            }
        });
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the right (optional)

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> showAddStudentDialog());
        buttonPanel.add(addStudentButton);

        JButton importStudentButton = new JButton("Import Student");
        importStudentButton.addActionListener(e -> showImportStudentDialog());
        buttonPanel.add(importStudentButton);
        return buttonPanel;
    }

    private StudentTableModel loadStudentData() {
        List<Student> students = databaseManager.getAllStudents();
        return new StudentTableModel(students);
    }

    private void showAddStudentDialog() {
        JTextField studentNumberField = new JTextField();
        JTextField nameField = new JTextField();

        Object[] message = {
                "Student Number:", studentNumberField,
                "Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String studentNumber = studentNumberField.getText();
            String name = nameField.getText();

            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Student name cannot be empty or blank.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (databaseManager.studentExists(studentNumber)) {
                JOptionPane.showMessageDialog(this,
                        "Duplicate student number " + studentNumber,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                databaseManager.addStudent(studentNumber, name);
                studentTable.setModel(loadStudentData());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Failed to new student with error: ：" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                e.printStackTrace();
            }
        }

    }

    private void showImportStudentDialog() {
        // Step 1: Let the user select a JSON file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Student Data JSON File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Step 2: Parse JSON file into list of students
                List<Student> students = parseJsonFile(selectedFile);

                // Step 3: Verify structure
                for (Student student : students) {
                    if (!isValidStudent(student)) {
                        JOptionPane.showMessageDialog(this,
                                "Invalid student data structure: " + student,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Step 4: Insert into the database
                for (Student student : students) {
                    if (!databaseManager.studentExists(student.getStudentNumber())) {
                        databaseManager.addStudent(student.getStudentNumber(), student.getName());
                    }
                }

                studentTable.setModel(loadStudentData());
                JOptionPane.showMessageDialog(this, "Students imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Failed to import students: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private List<Student> parseJsonFile(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, new TypeReference<List<Student>>() {});
    }

    private boolean isValidStudent(Student student) {
        // Validate that student_number follows the format "B" + 8 digits
        return student.getStudentNumber() != null && student.getStudentNumber().matches("B\\d{8}") &&
                student.getName() != null && !student.getName().trim().isEmpty();
    }

    private void showStudentCoursesDialog() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }

        String studentNumber = (String) studentTable.getValueAt(selectedRow, 0);
        int studentId = databaseManager.getStudentId(studentNumber);

        List<Course> courses = databaseManager.getCoursesForStudent(studentId);

        StringBuilder courseInfo = new StringBuilder();
        for (Course course : courses) {
            courseInfo.append("Course: ").append(course.getCourseName())
                    .append(" | Semester: ").append(course.getSemester())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(this,
                !courseInfo.isEmpty() ? courseInfo.toString() : "No courses found for this student.",
                "Courses for Student " + studentNumber,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
