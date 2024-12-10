package com.java.student_score_management.models;

public class Student {
    private int studentId;
    private String studentNumber;
    private String name;

    public Student(int studentId, String studentNumber, String name) {
        this.studentId = studentId;
        this.studentNumber = studentNumber;
        this.name = name;
    }

    public Student() {}

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
