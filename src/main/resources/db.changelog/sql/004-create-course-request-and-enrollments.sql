-- liquibase formatted sql

-- changeset nemanja:004-create-course-request-and-enrollments
CREATE TABLE course_requests (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT NOT NULL,
                                 course_id INT NOT NULL,
                                 status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
                                 request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 processed_date TIMESTAMP NULL,
                                 CONSTRAINT fk_request_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                 CONSTRAINT fk_request_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE enrollments (
                             user_id INT NOT NULL,
                             course_id INT NOT NULL,
                             enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             progress_percentage INT NOT NULL,
                             is_completed TINYINT NOT NULL,
                             completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             PRIMARY KEY (user_id, course_id),
                             CONSTRAINT fk_enrollment_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);