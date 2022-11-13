CREATE TABLE taste_tester(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(20) NOT NULL UNIQUE,

    PRIMARY KEY(id)
);

INSERT INTO taste_tester(name) VALUES ('Maddin'), ('Marvin');

CREATE TABLE taste_test(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL UNIQUE,
    order_in_total INT NOT NULL UNIQUE,

    PRIMARY KEY(id)
);

INSERT INTO taste_test(name, order_in_total) VALUES ('Rekorderlig', 1),
('Bier', 2);

CREATE TABLE round(
    id INT GENERATED ALWAYS AS IDENTITY,
    number INT NOT NULL,
    taste_test_id INT NOT NULL,
    taste_tester_id INT NOT NULL,

    PRIMARY KEY(id),
    UNIQUE(number, taste_test_id, taste_tester_id),
    CONSTRAINT fk_round_taste_test FOREIGN KEY(taste_test_id) REFERENCES taste_test(id)
);

INSERT INTO round(taste_test_id, taste_tester_id, number) VALUES (1, 2, 1), (1, 2, 2), (1, 1, 1), (1, 1, 2),
(2, 2, 1), (2, 2, 2), (2, 1, 1), (2, 1, 2);

CREATE TABLE taste_object(
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL UNIQUE,

    PRIMARY KEY(id)
);

INSERT INTO taste_object(name)
VALUES
('Rekorderlig Hollunder'),
('Rekorderlig Birne'),
('Somersby Rhabarber'),
('Rekorderlig Melone'),
('Rekorderlig Erdbeere 4,5%'),
('Rekorderlig Erdbeere 7%'),
('Rekorderlig Passionsfrucht'),
('Somersby Mango'),

('Rekorderlig Hollunder2'),
('Rekorderlig Birne2'),
('Somersby Rhabarber2'),
('Rekorderlig Melone2'),
('Rekorderlig Erdbeere 4,5%2'),
('Rekorderlig Erdbeere 7%2'),
('Rekorderlig Passionsfrucht2'),
('Somersby Mango2');

CREATE TABLE guess(
    id INT GENERATED ALWAYS AS IDENTITY,
    taste_object_tasted_id INT NOT NULL,
    taste_object_guessed_id INT NOT NULL,
    round_id INT NOT NULL,
    order_in_round INT NOT NULL,
    points INT,

    PRIMARY KEY(id),
    UNIQUE(round_id, order_in_round),
    CONSTRAINT fk_guess_taste_object_tasted FOREIGN KEY(taste_object_tasted_id) REFERENCES taste_object(id),
    CONSTRAINT fk_guess_taste_object_guessed FOREIGN KEY(taste_object_guessed_id) REFERENCES taste_object(id),
    CONSTRAINT fk_guess_round FOREIGN KEY(round_id) REFERENCES round(id) ON DELETE CASCADE
);

INSERT INTO guess(round_id, order_in_round, taste_object_tasted_id, taste_object_guessed_id, points)
VALUES
(1, 1, 6, 5, 4),
(1, 2, 4, 1, 5),
(1, 3, 3, 8, 10),
(1, 4, 8, 7, 10),
(1, 5, 1, 2, 9),
(1, 6, 2, 4, 9),
(1, 7, 7, 7, 0),
(1, 8, 5, 6, 0),

(2, 1, 2, 2, 9),
(2, 2, 1, 1, 9),
(2, 3, 3, 3, 10),
(2, 4, 7, 4, 3),
(2, 5, 4, 8, 6),
(2, 6, 5, 6, 0),
(2, 7, 8, 7, 2),
(2, 8, 6, 5, 6),

(3, 1, 1, 2, 8),
(3, 2, 6, 6, 7),
(3, 3, 8, 3, 3),
(3, 4, 2, 4, 7),
(3, 5, 3, 8, 6),
(3, 6, 7, 1, 4),
(3, 7, 4, 7, 7),
(3, 8, 5, 5, 7),

(4, 1, 8, 8, 3),
(4, 2, 3, 3, 6),
(4, 3, 1, 4, 9),
(4, 4, 6, 6, 6),
(4, 5, 4, 1, 7),
(4, 6, 2, 2, 7),
(4, 7, 5, 5, 7),
(4, 8, 7, 1, 3),




(5, 1, 12, 11, 4),
(5, 2, 10, 16, 5),
(5, 3, 9, 14, 8),
(5, 4, 14, 13, 2),
(5, 5, 16, 15, 9),
(5, 6, 15, 10, 9),
(5, 7, 13, 13, 1),
(5, 8, 11, 12, 4),

(6, 1, 9, 9, 9),
(6, 2, 16, 9, 9),
(6, 3, 15, 9, 7),
(6, 4, 13, 10, 3),
(6, 5, 10, 14, 6),
(6, 6, 11, 12, 5),
(6, 7, 14, 13, 1),
(6, 8, 12, 11, 6),

(7, 1, 9, 9, 8),
(7, 2, 12, 12, 7),
(7, 3, 14, 9, 3),
(7, 4, 15, 10, 7),
(7, 5, 16, 14, 6),
(7, 6, 13, 9, 4),
(7, 7, 10, 13, 7),
(7, 8, 11, 11, 7),

(8, 1, 14, 14, 3),
(8, 2, 12, 12, 6),
(8, 3, 15, 10, 9),
(8, 4, 12, 12, 6),
(8, 5, 10, 11, 7),
(8, 6, 16, 9, 7),
(8, 7, 11, 11, 7),
(8, 8, 13, 9, 3);

CREATE TABLE hate_prediction(
    id INT GENERATED ALWAYS AS IDENTITY,
    taste_tester_id INT NOT NULL,
    taste_test_id INT NOT NULL,
    taste_object_id INT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_hate_taste_tester FOREIGN KEY(taste_tester_id) REFERENCES taste_tester(id),
    CONSTRAINT fk_hate_taste_test FOREIGN KEY(taste_test_id) REFERENCES taste_test(id),
    CONSTRAINT fk_hate_taste_object FOREIGN KEY(taste_object_id) REFERENCES taste_object(id)
);

INSERT INTO hate_prediction (taste_tester_id, taste_test_id, taste_object_id)
VALUES (1, 1, 1), (2, 1, 7),

(1, 2, 9), (2, 2, 13);

CREATE TABLE favourite_prediction(
    id INT GENERATED ALWAYS AS IDENTITY,
    taste_tester_id INT NOT NULL,
    taste_test_id INT NOT NULL,
    taste_object_id INT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_favourite_taste_tester FOREIGN KEY(taste_tester_id) REFERENCES taste_tester(id),
    CONSTRAINT fk_favourite_taste_test FOREIGN KEY(taste_test_id) REFERENCES taste_test(id),
    CONSTRAINT fk_favourite_taste_object FOREIGN KEY(taste_object_id) REFERENCES taste_object(id)
);

INSERT INTO favourite_prediction (taste_tester_id, taste_test_id, taste_object_id)
VALUES (1, 1, 4),(2, 1, 4),

(1, 2, 10),(2, 2, 10);