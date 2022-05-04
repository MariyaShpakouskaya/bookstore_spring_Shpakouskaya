/*
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS covers;
*/

CREATE TABLE IF NOT EXISTS covers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO covers (name)
VALUES ('SOFT'),
('HARD'),
('SPECIAL');

CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
	isbn VARCHAR(50) UNIQUE NOT NULL,
	author VARCHAR(100) NOT NULL,
	title VARCHAR(100) NOT NULL,
	price DECIMAL(8,2) DEFAULT 0.0 NOT NULL,
	cover_id BIGINT REFERENCES covers,
	deleted BOOLEAN DEFAULT false NOT NULL
);

INSERT INTO books (isbn, author, title, cover_id, price )
VALUES
('5-273-00064-5', 'I.Garin', 'Nitshe', (SELECT id FROM covers WHERE name = 'HARD'), '23.20'),
('5-699-13926-5','M.Cvetaeva', 'Poem', (SELECT id FROM covers WHERE name = 'SOFT'), '28.90'),
('5-17-004641-3', 'E.Uspensky', 'Uncle Fyodor is favorite girl', (SELECT id FROM covers WHERE name = 'HARD'), '15.36'),
('978-5-17-101151-2', 'S.King', 'The shining', (SELECT id FROM covers WHERE name = 'SPECIAL'), '33.30'),
('9781509847556', 'A.Jeffrey', 'Mightier than the sword', (SELECT id FROM covers WHERE name = 'SOFT'), '26.73'),
('9780099580799', 'M.Puzo', 'The Sicilian', (SELECT id FROM covers WHERE name = 'SOFT'), '9.99'),
('9780008280857', 'D.Silva', 'The new girl', (SELECT id FROM covers WHERE name = 'SPECIAL'), '14.30'),
('9780008280802', 'D.Silva', 'The order', (SELECT id FROM covers WHERE name = 'SOFT'), '16.23'),
('9781405936255', 'A.North', 'The shadow friend', (SELECT id FROM covers WHERE name = 'SOFT'), '18.26'),
('9780751540529', 'L.Dawson', 'His other lover', (SELECT id FROM covers WHERE name = 'HARD'), '20.63'),
('9780099552772', 'D.Szalay', 'Spring', (SELECT id FROM covers WHERE name = 'SOFT'), '22.14'),
('9780571231522', 'P.Carey', 'His illegal self', (SELECT id FROM covers WHERE name = 'SPECIAL'), '19.20'),
('9780571202881','M.Dibdin', 'Blood rain', (SELECT id FROM covers WHERE name = 'HARD'), '26.15'),
('9780571240753', 'P.Auster', 'Man in the dark', (SELECT id FROM covers WHERE name = 'SOFT'), '23.98'),
('9780747585275', 'R.Ford', 'Women with men', (SELECT id FROM covers WHERE name = 'HARD'), '33.15'),
('978-5-17-147928-2', 'G.Orwell', 'Animal farm', (SELECT id FROM covers WHERE name = 'HARD'), '12.60'),
('9781785512223', 'V.Voronchenko', 'Faberge museum', (SELECT id FROM covers WHERE name = 'SOFT'), '56.30'),
('978-5-6046934-9-0', 'H.Wells', 'The invisible man', (SELECT id FROM covers WHERE name = 'HARD'), '24.30'),
('978-5-519-02604-8', 'J.London', 'Martin Eden', (SELECT id FROM covers WHERE name = 'SPECIAL'), '45.18'),
('9780500517772', 'A.Johnson', 'Improbable libraries', (SELECT id FROM covers WHERE name = 'HARD'), '34.20');

/*
TRUNCATE books CASCADE;
TRUNCATE covers CASCADE;
*/