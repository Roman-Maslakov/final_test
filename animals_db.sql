DROP DATABASE IF EXISTS Friends_of_human;
CREATE DATABASE Friends_of_human;
USE Friends_of_human;

CREATE TABLE Animal_class
(	
	id INT PRIMARY KEY AUTO_INCREMENT,
    class VARCHAR(65)
);

INSERT INTO Animal_class (class) 
VALUES
	("Pets"),
	("Pack animal");

CREATE TABLE Cats
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Cats (animal_name, commands, birthday, class_id) 
VALUES
	("Barselo", "kitty-kitty", "2021-07-17", 1),
	("Marselo", "kitty-kitty", "2020-09-23", 1);
    
CREATE TABLE Dogs
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Dogs (animal_name, commands, birthday, class_id) 
VALUES
	("Bark", "voice, sit", "2020-05-14", 1),
    ("Funky", "voice, sit, fahs", "2017-02-05", 1),
	("Drag", "sit", "2021-09-13", 1);

CREATE TABLE Hamsters
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Hamsters (animal_name, commands, birthday, class_id) 
VALUES
	("Mister", "listen", "2023-03-19", 1);

CREATE TABLE Donkeys
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Donkeys (animal_name, commands, birthday, class_id) 
VALUES
	("Danny", null, "2019-03-19", 2);

CREATE TABLE Camels
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Camels (animal_name, commands, birthday, class_id) 
VALUES
	("Stinky", "lay", "2018-03-19", 2);

CREATE TABLE Horses
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Horses (animal_name, commands, birthday, class_id) 
VALUES
	("Windy", "whoa, walk, trot", "2016-07-16", 2),
    ("Sharp", "whoa, walk", "2022-04-12", 2),
    ("Gunner", null, "2024-01-04", 2);
    
DROP TABLE Camels;

INSERT INTO Horses (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Donkeys;
CREATE TABLE Ungulates AS 
SELECT * FROM Horses;
DROP TABLE Horses;

CREATE TABLE Young_animals
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    Age_years INT,
    Age_months INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO Young_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Cats
WHERE birthday < CURDATE() - INTERVAL 1 YEAR AND birthday > CURDATE() - INTERVAL 3 YEAR;

INSERT INTO Young_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Dogs
WHERE birthday < CURDATE() - INTERVAL 1 YEAR AND birthday > CURDATE() - INTERVAL 3 YEAR;

INSERT INTO Young_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Hamsters
WHERE birthday < CURDATE() - INTERVAL 1 YEAR AND birthday > CURDATE() - INTERVAL 3 YEAR;

INSERT INTO Young_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Ungulates
WHERE birthday < CURDATE() - INTERVAL 1 YEAR AND birthday > CURDATE() - INTERVAL 3 YEAR;

UPDATE Young_animals
SET Age_years =  FLOOR(datediff(curdate(), birthday) / 365);
UPDATE Young_animals
SET Age_months = FLOOR((datediff(curdate(), birthday) % 365) / 30);

CREATE TABLE All_animals
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(65),
    commands VARCHAR(65),
    birthday DATE,
    class_id INT,
    FOREIGN KEY (class_id)
	REFERENCES Animal_class(id)
);

INSERT INTO All_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Cats;

INSERT INTO All_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Dogs;

INSERT INTO All_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Hamsters;

INSERT INTO All_animals (animal_name, commands, birthday, class_id) 
SELECT animal_name, commands, birthday, class_id FROM Ungulates;