package com.java.student_score_management.models;
public class Grade {
    private int gradeId;
    private int courseId;
    private int studentId;
    private int examId;
    private double score;
    private String studentNumber;

    public Grade(int gradeId, int courseId, int studentId, int examId, double score, String studentNumber) {
        this.gradeId = gradeId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.examId = examId;
        this.score = score;
        this.studentNumber = studentNumber;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
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

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", examId=" + examId +
                ", score=" + score +
                ", studentNumber='" + studentNumber + '\'' +
                '}';
    }
}

