package com.java.student_score_management;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GradeTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Student Number", "Exam ID", "Score"};
    private List<Grade> grades;

    public GradeTableModel(List<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public int getRowCount() {
        return grades.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grade grade = grades.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> grade.getStudentNumber();
            case 1 -> grade.getExamId();
            case 2 -> grade.getScore();
            default -> throw new IndexOutOfBoundsException("Column index out of bounds");
        };
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
        fireTableDataChanged();
    }
}
