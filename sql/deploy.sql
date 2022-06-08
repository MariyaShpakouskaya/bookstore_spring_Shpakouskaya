/*
TRUNCATE books CASCADE;
TRUNCATE covers CASCADE;
TRUNCATE users CASCADE;
TRUNCATE roles CASCADE;
TRUNCATE orders CASCADE;
TRUNCATE items_order CASCADE;
TRUNCATE status CASCADE;
*/

INSERT INTO covers (id, name)
VALUES ('0', 'SOFT'),
       ('1', 'HARD'),
       ('2', 'SPECIAL');

INSERT INTO roles (id, name)
VALUES ('0', 'ADMIN'),
       ('1', 'MANAGER'),
       ('2', 'CUSTOMER');

INSERT INTO status (id, name)
VALUES ('0', 'CREATED'),
       ('1', 'PROCESSED'),
       ('2', 'COMPLETED');

INSERT INTO books (isbn, author, title, cover_id, price)
VALUES ('5-273-00064-5', 'I.Garin', 'Nitshe', (SELECT id FROM covers WHERE name = 'HARD'), '23.20'),
       ('5-699-13926-5', 'M.Cvetaeva', 'Poem', (SELECT id FROM covers WHERE name = 'SOFT'), '28.90'),
       ('5-17-004641-3', 'E.Uspensky', 'Uncle Fyodor is favorite girl', (SELECT id FROM covers WHERE name = 'HARD'),
        '15.36'),
       ('978-5-17-101151-2', 'S.King', 'The shining', (SELECT id FROM covers WHERE name = 'SPECIAL'), '33.30'),
       ('9781509847556', 'A.Jeffrey', 'Mightier than the sword', (SELECT id FROM covers WHERE name = 'SOFT'), '26.73'),
       ('9780099580799', 'M.Puzo', 'The Sicilian', (SELECT id FROM covers WHERE name = 'SOFT'), '9.99'),
       ('9780008280857', 'D.Silva', 'The new girl', (SELECT id FROM covers WHERE name = 'SPECIAL'), '14.30'),
       ('9780008280802', 'D.Silva', 'The order', (SELECT id FROM covers WHERE name = 'SOFT'), '16.23'),
       ('9781405936255', 'A.North', 'The shadow friend', (SELECT id FROM covers WHERE name = 'SOFT'), '18.26'),
       ('9780751540529', 'L.Dawson', 'His other lover', (SELECT id FROM covers WHERE name = 'HARD'), '20.63'),
       ('9780099552772', 'D.Szalay', 'Spring', (SELECT id FROM covers WHERE name = 'SOFT'), '22.14'),
       ('9780571231522', 'P.Carey', 'His illegal self', (SELECT id FROM covers WHERE name = 'SPECIAL'), '19.20'),
       ('9780571202881', 'M.Dibdin', 'Blood rain', (SELECT id FROM covers WHERE name = 'HARD'), '26.15'),
       ('9780571240753', 'P.Auster', 'Man in the dark', (SELECT id FROM covers WHERE name = 'SOFT'), '23.98'),
       ('9780747585275', 'R.Ford', 'Women with men', (SELECT id FROM covers WHERE name = 'HARD'), '33.15'),
       ('978-5-17-147928-2', 'G.Orwell', 'Animal farm', (SELECT id FROM covers WHERE name = 'HARD'), '12.60'),
       ('9781785512223', 'V.Voronchenko', 'Faberge museum', (SELECT id FROM covers WHERE name = 'SOFT'), '56.30'),
       ('978-5-6046934-9-0', 'H.Wells', 'The invisible man', (SELECT id FROM covers WHERE name = 'HARD'), '24.30'),
       ('978-5-519-02604-8', 'J.London', 'Martin Eden', (SELECT id FROM covers WHERE name = 'SPECIAL'), '45.18'),
       ('9780500517772', 'A.Johnson', 'Improbable libraries', (SELECT id FROM covers WHERE name = 'HARD'), '34.20');

INSERT INTO users (first_name, last_name, email, password, role_id)
VALUES ('Maryia', 'Fedorova', 'cooking@mail.ru', 'Qwerty1', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Tatiana', 'Samsonova', 'lady_fairy@gmail.com', 'RTYhuj15', (SELECT id FROM roles WHERE name = 'MANAGER')),
       ('Nikita', 'Kolosov', 'NikitaKol85@mail.ru', 'Dpkghj85G', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Matvey', 'Irigin', 'Five_tools@mail.ru', 'fhTujh52', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Iryna', 'Magey', 'lesty@yandex.by', 'Rtyhg56', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Nicolay', 'Slizh', 'under_Dog@yandex.by', 'Evgen589', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Maya', 'Petrova', 'hate.evething@mail.ru', 'Ytrhgfnbv123', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Efrem', 'Nosov', 'party1997@gmail.com', 'Tirujg159', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Vladislav', 'Kukushkin', 'krukol@list.ru', 'Tdnjndjfb89', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Timofey', 'Gorelov', 'fly895@mail.ru', '85fhfjfk', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Sasha', 'Nikitina', 'gerryOpen@yahoo.com', 'Asdfgh15', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Tonya', 'Rey', 'filePloy@mail.ru', 'Astel96', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Sofiya', 'Avgustyna', 'kimberly@mail.ru', 'Hytgbfn55hh', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Vladislav', 'Lebedev', 'gladeTwo@gmail.com', 'Yqhhhhs85', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Dzenis', 'Dred', 'familyDrive88@mail.ru', 'pepepe7878', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Olya', 'Sivtsova', 'coolll999@gmail.com', 'Qwerty5663', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Sergey', 'Opelin', 'dobby555@list.ru', 'wwuutt', (SELECT id FROM roles WHERE name = 'MANAGER')),
       ('Liza', 'Breyd', 'try_again@yahoo.com', '123456', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Petr', 'Budnic', 'Ti_12_52@mail.ru', '5674fhfhf', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Yuliya', 'Konovalova', 'Pabloking@mail.ru', '85Hey96', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
       ('Nikita', 'Shevtsov', 'Nikita_Shevtsov_78@yahoo.com', 'ErtyfhgY5',
        (SELECT id FROM roles WHERE name = 'CUSTOMER'));

INSERT INTO orders(user_id, totalCost, timestamp, status_id)
VALUES ((SELECT id FROM users WHERE email = 'NikitaKol85@mail.ru'), '53.78', '2022-05-26',
        (SELECT id FROM status WHERE name = 'COMPLETED')),
       ((SELECT id FROM users WHERE email = 'gladeTwo@gmail.com'), '50.13', '2022-05-23',
        (SELECT id FROM status WHERE name = 'PROCESSED')),
       ((SELECT id FROM users WHERE email = 'Ti_12_52@mail.ru'), '14.30', '2022-05-22',
        (SELECT id FROM status WHERE name = 'PROCESSED')),
       ((SELECT id FROM users WHERE email = 'Pabloking@mail.ru'), '12.60', '2022-04-26',
        (SELECT id FROM status WHERE name = 'PROCESSED')),
       ((SELECT id FROM users WHERE email = 'filePloy@mail.ru'), '19.20', '2022-04-25',
        (SELECT id FROM status WHERE name = 'PROCESSED'));


INSERT INTO order_items(order_id, book_id, quantity, price)
VALUES ('1', (SELECT id FROM books WHERE title = 'His other lover'), '1',
        (SELECT price FROM books WHERE title = 'His other lover')),
       ('1', (SELECT id FROM books WHERE title = 'Women with men'), '1',
        (SELECT price FROM books WHERE title = 'Women with men')),
       ('2', (SELECT id FROM books WHERE title = 'Blood rain'), '1',
        (SELECT price FROM books WHERE title = 'Blood rain')),
       ('2', (SELECT id FROM books WHERE title = 'Man in the dark'), '1',
        (SELECT price FROM books WHERE title = 'Man in the dark')),
       ('3', (SELECT id FROM books WHERE title = 'The new girl'), '1',
        (SELECT price FROM books WHERE title = 'The new girl')),
       ('4', (SELECT id FROM books WHERE title = 'Animal farm'), '1',
        (SELECT price FROM books WHERE title = 'Animal farm')),
       ('5', (SELECT id FROM books WHERE title = 'His illegal self'), '1',
        (SELECT price FROM books WHERE title = 'His illegal self'));
