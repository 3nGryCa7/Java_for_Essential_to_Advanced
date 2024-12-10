package com.java.student_score_management.models;
public class Course {
    private int courseId;
    private String courseNumber;
    private String semester;
    private String courseName;
    private int studentAmount;

    public Course(int courseId, String courseNumber, String semester, String courseName, int studentAmount) {
        this.courseId = courseId;
        this.courseNumber = courseNumber;
        this.semester = semester;
        this.courseName = courseName;
        this.studentAmount = studentAmount;
    }

    public Course() {}

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseNumber='" + courseNumber + '\'' +
                ", semester='" + semester + '\'' +
                ", courseName='" + courseName + '\'' +
                ", studentAmount=" + studentAmount +
                '}';
    }
}
