-- liquibase formatted sql

-- changeset nemanja:001-create-users-table
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(100) NOT NULL,
                       last_name  VARCHAR(100) NOT NULL,
                       email      VARCHAR(150) NOT NULL UNIQUE,
                       password   VARCHAR(255) NOT NULL,
                       role       VARCHAR(50) NOT NULL
);

CREATE INDEX idx_users_email ON users(email);

INSERT INTO users (
    first_name,
    last_name,
    email,
    password,
    role
) VALUES (
             'Nemanja',
             'Stefanovic',
             'nemanjaaas99@gmail.com',
             '$2a$10$f/OU9xipCYGXdE5uBNvA1e0/nq0XPwnSXSAa/zSl06c11l3991adC',
             'ADMIN'
         );

INSERT INTO users (
    first_name,
    last_name,
    email,
    password,
    role
) VALUES (
             'Jovan',
             'Bojovic',
             'jovanbojovic999@gmail.com',
             '$2a$10$QOigssdsc4scuXPBYfLY5O7FqUNn/I7lujg7icBQ78PA8YzmsSF5W',
             'USER'
);
