CREATE TABLE books (
id BIGSERIAL PRIMARY KEY,
	isbn VARCHAR(50) NOT NULL,
	author VARCHAR(100) NOT NULL,
	title VARCHAR(100) NOT NULL,
	deleted BOOLEAN DEFAULT false NOT NULL
);

INSERT into books (isbn, author, title)
VALUES
('5-273-00064-5', 'I.Garin', 'Nitshe'),
('5-699-13926-5','M.Cvetaeva', 'Poem'),
('5-17-004641-3', 'E.Uspensky', 'Uncle Fyodor is favorite girl'),
('978-5-17-101151-2', 'S.King', 'The shining'),
('9781509847556', 'A.Jeffrey', 'Mightier than the sword'),
('9780099580799', 'M.Puzo', 'The Sicilian'),
('9780008280857', 'D.Silva', 'The new girl'),
('9780008280802', 'D.Silva', 'The order'),
('9781405936255', 'A.North', 'The shadow friend'),
('9780751540529', 'L.Dawson', 'His other lover'),
('9780099552772', 'D.Szalay', 'Spring'),
('9780571231522', 'P.Carey', 'His illegal self'),
('9780571202881','M.Dibdin', 'Blood rain'),
('9780571240753', 'P.Auster', 'Man in the dark'),
('9780747585275', 'R.Ford', 'Women with men'),
('978-5-17-147928-2', 'G.Orwell', 'Animal farm'),
('9781785512223', 'V.Voronchenko', 'Faberge museum'),
('978-5-6046934-9-0', 'H.Wells', 'The invisible man'),
('978-5-519-02604-8', 'J.London', 'Martin Eden'),
('9780500517772', 'A.Johnson', 'Improbable libraries');

