
-- changeset nemanja:006-insert-remaining-questions

INSERT INTO questions (text, options, correct_answer, test_id, created_at, updated_at)
VALUES

-- =========================
-- TEST 1 (HTML & CSS)
-- =========================
('Šta znači HTML?',
 'HyperText Markup Language,HighText Machine Language,Hyper Transfer Markup Language,Home Tool Markup Language',
 'HyperText Markup Language',
 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koji tag se koristi za link?',
 '<link>,<a>,<href>,<url>',
 '<a>',
 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Kako se u CSS-u definiše klasa?',
 '.classname,#classname,classname,*classname',
 '.classname',
 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koja CSS osobina menja boju teksta?',
 'background-color,font-color,color,text-color',
 'color',
 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Šta radi display: flex?',
 'Pravi grid layout,Omogućava fleksibilni raspored elemenata,Sakriva element,Centrira tekst',
 'Omogućava fleksibilni raspored elemenata',
 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),


-- =========================
-- TEST 3 (Python Data Science)
-- =========================
('Koja biblioteka se koristi za rad sa podacima u tabelarnom obliku?',
 'NumPy,Pandas,Matplotlib,TensorFlow',
 'Pandas',
 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koja funkcija se koristi za učitavanje CSV fajla u Pandas-u?',
 'read_csv(),load_csv(),open_csv(),import_csv()',
 'read_csv()',
 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Šta vraća df.head()?',
 'Zadnjih 5 redova,Prvih 5 redova,Broj redova,Opis kolona',
 'Prvih 5 redova',
 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koja biblioteka se koristi za vizualizaciju podataka?',
 'NumPy,Seaborn,Requests,Flask',
 'Seaborn',
 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Kako se definiše funkcija u Python-u?',
 'function myFunc():,def myFunc():,create myFunc():,func myFunc():',
 'def myFunc():',
 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),


-- =========================
-- TEST 4 (UI/UX)
-- =========================
('Šta znači UX?',
 'User XML,User Experience,Universal Experience,User Extension',
 'User Experience',
 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Šta je wireframe?',
 'Finalni dizajn,Sirova verzija interfejsa,Backend model,Tipografija',
 'Sirova verzija interfejsa',
 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koji princip se odnosi na doslednost dizajna?',
 'Contrast,Consistency,Hierarchy,Balance',
 'Consistency',
 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Šta je CTA?',
 'Click Text Action,Call To Action,Create Text Area,Control Type Access',
 'Call To Action',
 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koji alat se često koristi za UI dizajn?',
 'Figma,Postman,Git,MySQL',
 'Figma',
 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),


-- =========================
-- TEST 5 (DevOps & Cloud)
-- =========================
('Šta je CI/CD?',
 'Continuous Integration / Continuous Deployment,Cloud Infrastructure / Cloud Database,Code Internal / Code Deploy,Central Integration / Central Deployment',
 'Continuous Integration / Continuous Deployment',
 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koji alat se koristi za verzionisanje koda?',
 'Docker,Jenkins,Git,Kubernetes',
 'Git',
 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Šta je Docker?',
 'Cloud provajder,Alat za kontejnerizaciju,Baza podataka,CI server',
 'Alat za kontejnerizaciju',
 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Koja platforma je cloud provajder?',
 'AWS,React,Spring,MySQL',
 'AWS',
 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

('Šta je Kubernetes?',
 'Baza podataka,Sistem za orkestraciju kontejnera,Programski jezik,CI alat',
 'Sistem za orkestraciju kontejnera',
 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);