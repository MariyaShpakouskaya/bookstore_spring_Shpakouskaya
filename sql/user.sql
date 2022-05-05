/*
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
*/

CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO roles (name)
VALUES ('ADMIN'),
('MANAGER'),
('CUSTOMER');

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	role_id BIGINT REFERENCES roles,
	deleted BOOLEAN DEFAULT false NOT NULL
);

INSERT INTO users (first_name, last_name, email, password, role_id)
VALUES
('Maryia', 'Fedorova', 'cooking@mail.ru', 'Qwerty1', (SELECT id FROM roles WHERE name = 'CUSTOMER')),
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
('Nikita', 'Shevtsov', 'Nikita_Shevtsov_78@yahoo.com', 'ErtyfhgY5', (SELECT id FROM roles WHERE name = 'CUSTOMER'));

/*
TRUNCATE users CASCADE;
TRUNCATE roles CASCADE;
*/