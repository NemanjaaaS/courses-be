-- liquibase formatted sql

-- changeset nemanja:006-create-enrolled-tests-table
CREATE TABLE enrolled_tests (
                                test_id INT NOT NULL,
                                user_id INT NOT NULL,
                                is_passed TINYINT(1) DEFAULT 0,
                                percentage INT DEFAULT 0,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (test_id, user_id),
                                FOREIGN KEY (test_id) REFERENCES tests(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);