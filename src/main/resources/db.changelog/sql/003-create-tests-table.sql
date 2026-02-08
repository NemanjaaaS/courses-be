-- liquibase formatted sql

-- changeset nemanja:003-create-tests-table
CREATE TABLE tests (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       number_of_questions INT,
                       duration_minutes INT,
                       passing_score_percentage INT,
                       course_id INT,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_test_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- changeset nemanja:003-insert-tests-data
-- Mapiranje testova na osnovu tvojih inserata u 'courses' tabelu

-- 1. Test za Web Development Fundamentals (ID: 1)
INSERT INTO tests (title, number_of_questions, duration_minutes, passing_score_percentage, course_id)
VALUES ('HTML & CSS Test', 5, 30, 70, 1);

-- 2. Test za React & TypeScript Mastery (ID: 2)
INSERT INTO tests (title, number_of_questions, duration_minutes, passing_score_percentage, course_id)
VALUES ('React Fundamentals Test', 5, 45, 75, 2);

-- 3. Test za Python za Data Science (ID: 3)
INSERT INTO tests (title, number_of_questions, duration_minutes, passing_score_percentage, course_id)
VALUES ('React Fundamentals Test', 5, 45, 75, 3);

-- 4. Test za UI/UX Dizajn Principi (ID: 4)
INSERT INTO tests (title, number_of_questions, duration_minutes, passing_score_percentage, course_id)
VALUES ('React Fundamentals Test', 5, 45, 75, 4);

-- 5. Test za DevOps & Cloud Computing (ID: 5)
INSERT INTO tests (title, number_of_questions, duration_minutes, passing_score_percentage, course_id)
VALUES ('React Fundamentals Test', 5, 45, 75, 5);