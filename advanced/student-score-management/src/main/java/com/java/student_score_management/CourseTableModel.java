package com.java.student_score_management;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CourseTableModel extends AbstractTableModel {
    private final List<Course> courses;
    private final String[] columnNames = {"Course Number", "Semester", "Course Name", "Student Amount"};

    public CourseTableModel(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public int getRowCount() {
        return courses.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Course course = courses.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> course.getCourseNumber();
            case 1 -> course.getSemester();
            case 2 -> course.getCourseName();
            case 3 -> course.getStudentAmount();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Course getCourseAt(int rowIndex) {
        return courses.get(rowIndex);
    }
}
