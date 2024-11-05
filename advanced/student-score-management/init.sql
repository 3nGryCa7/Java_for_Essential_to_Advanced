CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    student_number VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT unique_student_number UNIQUE (student_number)
);

CREATE TABLE selected_courses (
    selected_id SERIAL PRIMARY KEY,
    course_id INTEGER NOT NULL,     -- refer to table `courses`
    student_id INTEGER NOT NULL,    -- refer to table `students`
    CONSTRAINT unique_course_student UNIQUE (course_id, student_id)
);

CREATE TABLE grades (
    grade_id SERIAL PRIMARY KEY,
    course_id INTEGER NOT NULL,     -- refer to table `courses`
    student_id INTEGER NOT NULL,    -- refer to table `students`
    exam_id INT NOT NULL,
    score DECIMAL(5, 2) NOT NULL,
    CONSTRAINT unique_student_course_exam UNIQUE (course_id, student_id, exam_id)
);

CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_number VARCHAR(10) NOT NULL,
    semester VARCHAR(4) NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    student_amount INT NOT NULL,
    CONSTRAINT unique_course_semester UNIQUE (course_number, semester)
);

INSERT INTO students (student_number, name) VALUES
('B11109088', '王鑫岳'),
('B11109089', '楊宏傑'),
('B11109070', '許億銘'),
('B11109079', '蕭煌濱'),
('B11109069', '洪千棋');

INSERT INTO courses (course_number, semester, course_name, student_amount) VALUES
('IM1008301', '1131', '進階物件導向程式語言', 30),
('IM1008302', '1132', '資料結構', 25),
('IM1008303', '1132', '資料庫系統', 28),
('IM1008304', '1131', '演算法', 55);

INSERT INTO selected_courses (course_id, student_id) VALUES
(1, 1), (1, 2), (2, 1), (3, 3), (4, 2), (2, 3);

INSERT INTO grades (course_id, student_id, exam_id, score) VALUES
(1, 1, 1, 90.0), (1, 2, 1, 85.0), (2, 1, 1, 88.0), (3, 3, 1, 78.5),  (4, 2, 1, 92.0), (2, 3, 1, 82.0);
