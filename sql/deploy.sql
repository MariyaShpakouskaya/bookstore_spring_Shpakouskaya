CREATE TABLE covers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE books (
id BIGSERIAL PRIMARY KEY,
	isbn VARCHAR(50) UNIQUE NOT NULL,
	author VARCHAR(100) NOT NULL,
	title VARCHAR(100) NOT NULL,
	price DECIMAL(6,2) DEFAULT 0.0 NOT NULL,
	cover_id BIGINT REFERENCES covers,
	deleted BOOLEAN DEFAULT false NOT NULL
);

INSERT INTO covers (name)
VALUES ('SOFT'),
('HARD'),
('SPECIAL');

INSERT INTO books (isbn, author, title, price, cover_id)
VALUES
('5-273-00064-5', 'I.Garin', 'Nitshe', '23.20', '2'),
('5-699-13926-5','M.Cvetaeva', 'Poem', '28.90', '1'),
('5-17-004641-3', 'E.Uspensky', 'Uncle Fyodor is favorite girl', '15.36', '2'),
('978-5-17-101151-2', 'S.King', 'The shining', '33.30', '3'),
('9781509847556', 'A.Jeffrey', 'Mightier than the sword', '26.73', '2'),
('9780099580799', 'M.Puzo', 'The Sicilian', '9.99', '1'),
('9780008280857', 'D.Silva', 'The new girl', '14.30', '3'),
('9780008280802', 'D.Silva', 'The order', '16.23', '1'),
('9781405936255', 'A.North', 'The shadow friend', '18.26', '1'),
('9780751540529', 'L.Dawson', 'His other lover', '20.63', '2'),
('9780099552772', 'D.Szalay', 'Spring', '22.14', '1'),
('9780571231522', 'P.Carey', 'His illegal self', '19.20', '3'),
('9780571202881','M.Dibdin', 'Blood rain', '26.15', '2'),
('9780571240753', 'P.Auster', 'Man in the dark', '23.98', '1'),
('9780747585275', 'R.Ford', 'Women with men', '33.15', '2'),
('978-5-17-147928-2', 'G.Orwell', 'Animal farm', '12.60', '2'),
('9781785512223', 'V.Voronchenko', 'Faberge museum', '56.30', '1'),
('978-5-6046934-9-0', 'H.Wells', 'The invisible man', '24.30', '2'),
('978-5-519-02604-8', 'J.London', 'Martin Eden', '45.18', '3'),
('9780500517772', 'A.Johnson', 'Improbable libraries', '34.20', '2');