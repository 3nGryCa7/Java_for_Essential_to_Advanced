package com.java.student_score_management;

import com.java.student_score_management.models.Course;
import com.java.student_score_management.models.Grade;
import com.java.student_score_management.models.SelectedCourse;
import com.java.student_score_management.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5444/score_management";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static synchronized DatabaseManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void addCourse(String courseCode, String semester, String courseName, int studentAmount) {
        String sql = "INSERT INTO courses (course_code, semester, course_name, student_amount) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, courseCode);
            stmt.setString(2, semester);
            stmt.setString(3, courseName);
            stmt.setInt(4, studentAmount);
            stmt.executeUpdate();
            System.out.println("Succeed add course: " + courseName);
        } catch (SQLException e) {
            System.out.println("Failed to add course: " + e.getMessage());
        }
    }

    public void updateStudentAmount(int course_id) {
        String sql = "UPDATE courses SET student_amount = (" +
                "SELECT COUNT(*) FROM selected_courses WHERE course_id = ?" +
                ") WHERE course_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, course_id);
            stmt.setInt(2, course_id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to add student into course: " + e.getMessage());
        }
    }

    public void addSelectedCourse(int course_id, int student_id) {
        String sql = "INSERT INTO selected_courses (course_id, student_id) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.executeUpdate();
            System.out.println("Succeed add student_id=" + student_id + " into course: " + course_id);

        } catch (SQLException e) {
            System.out.println("Failed to add student into course: " + e.getMessage());
        }
    }

    public void addStudent(String studentNumber, String name) throws SQLException {
        String sql = "INSERT INTO students (student_number, name) VALUES (?, ?)";

        if (studentExists(studentNumber)) {
            throw new SQLException("Duplicate student number");
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentNumber);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("Succeed add student: " + name);

        } catch (SQLException e) {
            throw new SQLException("database error.");
        }
    }

    public void addGrade(int courseId, int studentId, int examId, double score) {
        String sql = "INSERT INTO grades (course_id, student_id, exam_id, score) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, examId);
            stmt.setDouble(4, score);
            stmt.executeUpdate();
            System.out.println("Succeed add score: " + score + " for student " + studentId);
        } catch (SQLException e) {
            System.out.println("Failed to add score: " + e.getMessage());
        }
    }

    public void deleteGrade(int courseId, int studentId, int examId) {
        String sql = "DELETE FROM grades WHERE course_id = ? AND student_id = ? AND exam_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, examId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete grade: " + e.getMessage());
        }
    }


    public Course getCourseById(int courseId) {
        String sql = "SELECT course_code, course_name, semester, student_amount FROM courses WHERE course_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            courseId, rs.getString("course_code"),
                            rs.getString("course_name"), rs.getString("semester"),
                            rs.getInt("student_amount"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to find course: " + e.getMessage());
        }
        return null;
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT student_number, name FROM students WHERE student_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            studentId, rs.getString("student_number"),
                            rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to find student: " + e.getMessage());
        }
        return null;
    }


    public int getStudentId(String studentNumber) {
        int studentId = -1;
        String sql = "SELECT student_id FROM students where student_number = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                studentId = rs.getInt("student_id");
            }
        } catch (SQLException e) {
            System.out.println("Failed to find student: " + e.getMessage());
        }

        return studentId;
    }

    public boolean courseExists(String course_code, String semester) {
        String sql = "SELECT 1 FROM courses WHERE course_code = ? AND semester = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course_code);
            stmt.setString(2, semester);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Failed to check if course exists: " + e.getMessage());
        }
        return false;
    }

    public boolean studentExists(String studentNumber) {
        String sql = "SELECT 1 FROM students WHERE student_number = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, studentNumber);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Failed to check if student exists: " + e.getMessage());
        }
        return false;
    }

    public boolean studentExistsInCourse(int studentId, int courseId) {
        String sql = "SELECT 1 FROM selected_courses WHERE student_id = ? AND course_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("[ERROR] Database: " + e.getMessage());
        }
        return false;
    }

    public boolean studentExistsInCourseExam(int studentId, int courseId, int examId) {
        String sql = "SELECT 1 FROM grades WHERE student_id = ? AND course_id = ? AND exam_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setInt(3, examId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("[ERROR] Database: " + e.getMessage());
        }
        return false;
    }

    public boolean examExists(int courseId, int examId) {
        String sql = "SELECT 1 FROM grades WHERE course_id = ? AND exam_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, examId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Failed to check if exam exists: " + e.getMessage());
        }
        return false;
    }

    public List<Double> getGrades(int course_id, int exam_id) {
        List<Double> scores = new ArrayList<>();
        String sql = "SELECT score FROM grades WHERE course_id =? AND exam_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, course_id);
            stmt.setInt(2, exam_id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    scores.add(rs.getDouble("score"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch grades: " + e.getMessage());
        }
        return scores;
    }


    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"), rs.getString("course_code"),
                        rs.getString("semester"), rs.getString("course_name"),
                        rs.getInt("student_amount")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch all courses: " + e.getMessage());
        }
        return courses;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"), rs.getString("student_number"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch all students: " + e.getMessage());
        }
        return students;
    }


    public List<Student> getStudentsForCourse(int courseId) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students s " +
                "JOIN selected_courses sc ON s.student_id = sc.student_id " +
                "WHERE sc.course_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"), rs.getString("student_number"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch students for the course: " + e.getMessage());
        }
        return students;
    }

    public List<Grade> getGradesForCourse(int courseId) {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.grade_id, g.course_id, g.student_id, g.exam_id, g.score, s.student_number " +
                "FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "WHERE g.course_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                grades.add(new Grade(
                        rs.getInt("grade_id"), courseId,
                        rs.getInt("student_id"), rs.getInt("exam_id"),
                        rs.getDouble("score"), rs.getString("student_number")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch grades: " + e.getMessage());
        }
        return grades;
    }

    public List<SelectedCourse> getSelectedCoursesForStudent(int studentId) {
        List<SelectedCourse> courses = new ArrayList<>();
        String sql = "SELECT sc.selected_id, sc.course_id " +
                "FROM selected_courses sc " +
                "JOIN courses c ON sc.course_id = c.course_id " +
                "WHERE sc.student_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    courses.add(new SelectedCourse(
                            rs.getInt("selected_id"), rs.getInt("course_id"),
                            studentId));
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve courses for student: " + e.getMessage());
        }

        return courses;
    }


    public List<Course> getCoursesForStudent(int studentId) {
        List<Course> courses = new ArrayList<>();
        List<SelectedCourse> selectedCourses = getSelectedCoursesForStudent(studentId);

        for (SelectedCourse selectedCourse : selectedCourses) {
            int courseId = selectedCourse.getCourseId();
            Course course = getCourseById(courseId);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("The database is closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close database: " + e.getMessage());
            }
        }
    }
}
