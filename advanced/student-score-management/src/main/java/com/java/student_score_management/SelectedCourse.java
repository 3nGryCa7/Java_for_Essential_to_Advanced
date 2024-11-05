package com.java.student_score_management;

public class SelectedCourse {
    private int selectedId;
    private int courseId;
    private int studentId;

    public SelectedCourse(int selectedId, int courseId, int studentId) {
        this.selectedId = selectedId;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "SelectedCourse{" +
                "selectedId=" + selectedId +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}
