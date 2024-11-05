package com.java.student_score_management;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class StudentPage extends JPanel {
    private final DatabaseManager databaseManager;
    private JTable studentTable;

    public StudentPage(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        setLayout(new BorderLayout());

        studentTable = new JTable(loadStudentData());
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        add(studentScrollPane, BorderLayout.CENTER);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> showAddStudentDialog());
        add(addStudentButton, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    showStudentCoursesDialog();
                }
            }
        });
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
            databaseManager.addStudent(studentNumber, name);
            studentTable.setModel(loadStudentData());
        }
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
