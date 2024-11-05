package com.java.student_score_management;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class CourseDetailPage extends JPanel {
    private final DatabaseManager databaseManager;
    private final Course course;
    private JTable studentTable;
    private JTable gradeTable;

    public CourseDetailPage(DatabaseManager databaseManager, Course course) {
        this.databaseManager = databaseManager;
        this.course = course;

        setLayout(new BorderLayout());

        JTextArea courseInfo = new JTextArea("Course: " + course.getCourseName() + "\n" +
                "Semester: " + course.getSemester() + "\n" +
                "Student Amount: " + course.getStudentAmount());
        courseInfo.setEditable(false);
        add(courseInfo, BorderLayout.NORTH);

        studentTable = new JTable(loadStudentData());
        JScrollPane studentScrollPane = new JScrollPane(studentTable);

        gradeTable = new JTable(loadGradeData());
        JScrollPane gradeScrollPane = new JScrollPane(gradeTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, studentScrollPane, gradeScrollPane);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = getButtonPanel();

        add(buttonPanel, BorderLayout.EAST);
    }

    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));  // 將網格設為 3 行 1 列，以包含 3 個按鈕

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(e -> showAddStudentDialog());
        buttonPanel.add(addStudentButton);

        JButton addGradeButton = new JButton("Add Grade");
        addGradeButton.addActionListener(e -> showAddGradeDialog());
        buttonPanel.add(addGradeButton);

        JButton deleteGradeButton = new JButton("Delete Grade");
        deleteGradeButton.addActionListener(e -> deleteSelectedGrade());
        buttonPanel.add(deleteGradeButton);
        return buttonPanel;
    }

    private StudentTableModel loadStudentData() {
        List<Student> students = databaseManager.getStudentsForCourse(course.getCourseId());
        return new StudentTableModel(students);
    }

    private GradeTableModel loadGradeData() {
        List<Grade> grades = databaseManager.getGradesForCourse(course.getCourseId());
        return new GradeTableModel(grades);
    }

    private void showAddStudentDialog() {
        JTextField studentNumberField = new JTextField();
        JTextField studentNameField = new JTextField();

        Object[] message = {
                "Student Number:", studentNumberField,
                "Student Name:", studentNameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String studentNumber = studentNumberField.getText();

            if (databaseManager.studentExists(studentNumber)) {
                JOptionPane.showMessageDialog(this, "Error: Student with ID " + studentNumber + " does not exist.", "Student Not Found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int studentId = databaseManager.getStudentId(studentNumber);
            databaseManager.addSelectedCourse(course.getCourseId(), studentId);
            studentTable.setModel(loadStudentData());
        }
    }

    private void showAddGradeDialog() {
        JTextField studentNumberField = new JTextField();
        JTextField examIdField = new JTextField();
        JTextField scoreField = new JTextField();

        Object[] message = {
                "Student Number:", studentNumberField,
                "Exam ID:", examIdField,
                "Score:", scoreField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Grade", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String studentNumber = studentNumberField.getText();
            String examId = examIdField.getText();
            String score = scoreField.getText();

            if (databaseManager.studentExists(studentNumber)) {
                JOptionPane.showMessageDialog(this, "Error: Student with ID " + studentNumber + " does not exist.", "Student Not Found", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int studentId = databaseManager.getStudentId(studentNumber);
            databaseManager.addGrade(course.getCourseId(), studentId, Integer.parseInt(examId), Double.parseDouble(score));
            gradeTable.setModel(loadGradeData());
        }
    }

    private void deleteSelectedGrade() {
        int selectedRow = gradeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a score to delete.", "No score Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentNumber = (String) gradeTable.getValueAt(selectedRow, 0);
        int studentId = databaseManager.getStudentId(studentNumber);
        int examId = (int) gradeTable.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected score?", "Delete Score", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            databaseManager.deleteGrade(course.getCourseId(), studentId, examId);
            gradeTable.setModel(loadGradeData());
        }
    }

}
