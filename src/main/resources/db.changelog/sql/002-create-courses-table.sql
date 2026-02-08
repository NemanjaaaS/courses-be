-- liquibase formatted sql

-- changeset nemanja:001-create-users-table
CREATE TABLE courses (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         short_description VARCHAR(500),
                         category VARCHAR(50) NOT NULL,
                         level VARCHAR(50) NOT NULL,
                         duration_hours INT NOT NULL,
                         price DOUBLE PRECISION NOT NULL,
                         rating DOUBLE PRECISION,
                         enrolled_count INT NOT NULL DEFAULT 0,
                         active BOOLEAN NOT NULL DEFAULT TRUE,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO courses (
    title,
    short_description,
    category,
    level,
    duration_hours,
    price,
    rating,
    enrolled_count,
    active,
    created_at,
    updated_at
) VALUES
-- 1. Web Development Fundamentals
(
    'Web Development Fundamentals',
    'Naučite osnove web razvoja sa HTML, CSS i JavaScript.',
    'PROGRAMMING',
    'BEGINNER',
    8,
    4999,
    4.8,
    245,
    true,
    NOW(),
    NOW()
),

-- 2. React & TypeScript Mastery
(
    'React & TypeScript Mastery',
    'Napredni kurs za izgradnju modernih web aplikacija.',
    'PROGRAMMING',
    'INTERMEDIATE',
    12,
    7999,
    4.9,
    189,
    true,
    NOW(),
    NOW()
),

-- 3. Python za Data Science
(
    'Python za Data Science',
    'Analiza podataka i mašinsko učenje sa Python-om.',
    'DATA_SCIENCE',
    'INTERMEDIATE',
    15,
    8999,
    4.7,
    312,
    true,
    NOW(),
    NOW()
),

-- 4. UI/UX Dizajn Principi
(
    'UI/UX Dizajn Principi',
    'Kreirajte korisničke interfejse koji oduševljavaju.',
    'DESIGN',
    'BEGINNER',
    6,
    3999,
    4.6,
    178,
    true,
    NOW(),
    NOW()
),

-- 5. DevOps & Cloud Computing
(
    'DevOps & Cloud Computing',
    'Docker, Kubernetes i AWS za moderne aplikacije.',
    'DEVOPS',
    'ADVANCED',
    20,
    12999,
    4.8,
    134,
    true,
    NOW(),
    NOW()
),

-- 6. Cybersecurity Essentials
(
    'Cybersecurity Essentials',
    'Zaštitite sisteme od modernih pretnji.',
    'SECURITY',
    'INTERMEDIATE',
    10,
    6999,
    4.5,
    98,
    true,
    NOW(),
    NOW()
);
