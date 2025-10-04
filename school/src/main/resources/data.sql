SET
REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE student;
TRUNCATE TABLE teacher;

SET
REFERENTIAL_INTEGRITY TRUE;
ALTER TABLE student ALTER COLUMN student_id RESTART WITH 1;
ALTER TABLE teacher ALTER COLUMN teacher_id RESTART WITH 1;

--INSERT INTO teacher (teacher_id, teacher_name) VALUES (1, 'John');
--INSERT INTO teacher (teacher_id, teacher_name) VALUES (2, 'Irvan');
INSERT INTO teacher (teacher_name) VALUES ('John');
INSERT INTO teacher (teacher_name) VALUES ('Irvan');

--INSERT INTO student (student_id, student_name) VALUES (1, 'Alice');
--INSERT INTO student (student_id, student_name) VALUES (2, 'Bob');
--INSERT INTO student (student_id, student_name) VALUES (3, 'Charlie');
--INSERT INTO student (student_id, student_name) VALUES (4, 'David');
--INSERT INTO student (student_id, student_name) VALUES (5, 'Eve');
--INSERT INTO student (student_id, student_name) VALUES (6, 'Frank');
INSERT INTO student (student_name) VALUES ('Alice');
INSERT INTO student (student_name) VALUES ('Bob');
INSERT INTO student (student_name) VALUES ('Charlie');
INSERT INTO student (student_name) VALUES ('David');
INSERT INTO student (student_name) VALUES ('Eve');
INSERT INTO student (student_name) VALUES ('Frank');