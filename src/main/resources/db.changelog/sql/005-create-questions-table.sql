-- liquibase formatted sql

-- changeset nemanja:005-create-questions-table
CREATE TABLE questions
(
    id             INT AUTO_INCREMENT,
    text           VARCHAR(255),
    options        TEXT,
    correct_answer VARCHAR(255),
    test_id        INT,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_questions PRIMARY KEY (id)
);

ALTER TABLE questions
    ADD CONSTRAINT fk_questions_on_test
        FOREIGN KEY (test_id) REFERENCES `tests` (id);

INSERT INTO questions (text, options, correct_answer, test_id, created_at, updated_at)
VALUES ('Šta je JSX?',
        'JavaScript XML,Java Syntax Extension,JSON XML,JavaScript Extension',
        'JavaScript XML',
        2,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('Koja funkcija se koristi za kreiranje React komponente?',
        'createComponent(),React.create(),function Component(),new Component()',
        'function Component()',
        2,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('Koji hook se koristi za state u funkcionalnim komponentama?',
        'useEffect,useState,useContext,useReducer',
        'useState',
        2,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('Šta je props u React-u?',
        'Globalni state,Ulazni podaci komponente,CSS stilovi,Event handler',
        'Ulazni podaci komponente',
        2,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       ('Kako se koristi useEffect za mounting?',
        'useEffect(() => {}),useEffect(() => {}, []),useEffect([]),useEffect({})',
        'useEffect(() => {}, [])',
        2,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);