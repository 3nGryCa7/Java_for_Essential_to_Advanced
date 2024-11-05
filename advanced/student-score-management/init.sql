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
('B11109060', '劉子喬'),
('B11109061', '陳悅琦'),
('B11109062', '許銘浩'),
('B11109063', '葉旭凡'),
('B11109064', '黃喬欣'),
('B11109065', '黃嘉溪'),
('B11109066', '王凡恩'),
('B11109067', '蕭俊安'),
('B11109068', '洪俊熙'),
('B11109069', '洪千棋'),
('B11109070', '許億銘'),
('B11109071', '楊梨溪'),
('B11109072', '蕭煌濱'),
('B11109073', '王鑫岳'),
('B11109074', '楊宏傑'),
('B11109075', '林慕禹'),
('B11109076', '張騫'),
('B11109077', '陳欣妮'),
('B11109078', '葉永仟'),
('B11109079', '莫月喬'),
('B11109080', '莫月濱'),
('B11109081', '張明礬'),
('B11109082', '楊訊恩'),
('B11109083', '王議和'),
('B11109084', '劉一珈');


INSERT INTO courses (course_number, semester, course_name, student_amount) VALUES
('IM1008301', '1131', '進階物件導向程式語言', 21),
('IM1008302', '1132', '資料結構', 25),
('IM1008303', '1132', '資料庫系統', 12),
('IM1008304', '1131', '演算法', 18);

INSERT INTO selected_courses (course_id, student_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11),
(1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20), (1, 21),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11),
(2, 12), (2, 13), (2, 14), (2, 15), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20), (2, 21),
(2, 22), (2, 23), (2, 24), (2, 25),
(3, 1), (3, 3), (3, 4), (3, 5), (3, 7), (3, 8), (3, 10), (3, 11), (3, 12), (3, 13), (3, 14), (3, 20),
(4, 1), (4, 5), (4, 6), (4, 10), (4, 11), (4, 12), (4, 13), (4, 14), (4, 15), (4, 16), (4, 17),
(4, 18), (4, 19), (4, 20), (4, 21), (4, 22), (4, 23), (4, 24);

-- For each student in selected_courses, generate a grade for exam_id = 1
INSERT INTO grades (course_id, student_id, exam_id, score)
SELECT course_id, student_id, 1, ROUND(60 + (RANDOM() * 40)::numeric, 1)
FROM selected_courses;


-- For each student in selected_courses, generate a grade for exam_id = 2
INSERT INTO grades (course_id, student_id, exam_id, score)
SELECT course_id, student_id, 2, ROUND(60 + (RANDOM() * 40)::numeric, 1)
FROM selected_courses;
