DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS person CASCADE;

CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company 
VALUES (1, 'BKZ'),(2, 'ZSD'), (3, 'Sber'), (4, 'Korus'), (5, 'Lenta');

INSERT INTO person
VALUES (1, 'Vasiliy', 3), (2, 'Rodion', 5), (3, 'Dimon', 1),
(4, 'Clava', 1), (5, 'ARS', 1), (6, 'Boris', 5),
(7, 'Ivan', 5), (8, 'Ksuha', 4), (9, 'Maxim', 4), (10, 'Marat', 2);

--  задача 1

SELECT p.name as person, c.name as company
FROM person as p
JOIN company as c ON p.company_id = c.id
WHERE company_id <> 5;

-- задача 2

CREATE VIEW people_count_in_company AS
SELECT c.name as company, COUNT(p.company_id)
FROM person as p
JOIN company as c ON p.company_id = c.id
GROUP BY c.name;

SELECT pc.company, pc.count as max_person
FROM people_count_in_company as pc
WHERE pc.count = (
	SELECT MAX(people_count_in_company.count) 
	FROM people_count_in_company);
	
	



