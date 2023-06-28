DROP TABLE IF EXISTS runner;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS team;

--if any errors before COMMIT,
--roll back to here.
BEGIN TRANSACTION; 

CREATE TABLE team (
  	team_id SERIAL NOT NULL PRIMARY KEY,
  	school_name VARCHAR(50) NOT NULL, 
  	mascot VARCHAR(50)
);

CREATE TABLE runner (
	runner_id SERIAL NOT NULL PRIMARY KEY,
	team_id INT REFERENCES team(team_id),
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL
);

CREATE TABLE coach (
	coach_id SERIAL NOT NULL PRIMARY KEY,
	team_id INT REFERENCES team(team_id),
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	email VARCHAR(50), 
	phone VARCHAR(15)	
);

INSERT INTO team(school_name, mascot) VALUES ('Brophy', 'Broncos');
INSERT INTO team(school_name, mascot) VALUES ('Bellarmine', 'Bells');
INSERT INTO team(school_name, mascot) VALUES ('Saint Ignatius', 'Wildcats');

--Coaches
INSERT INTO coach(team_id, first_name, last_name, email, phone)
	VALUES (1, 'Bill', 'Doebler', 'bdoebler@brophy.org', '602.555.1212');
INSERT INTO coach(team_id, first_name, last_name, email, phone)
	VALUES (2, 'Patrick', 'McCrystle', 'pmccrystle@brophy.org', '650.555.1234');
INSERT INTO coach(team_id, first_name, last_name, email, phone)
	VALUES (3, 'Jim', 'Arenas', 'jarenas@saintignatius.org', '216.555.7890');

--Brophy Runners
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Jack', 'Rae');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Artur', 'Janko');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Ninad', 'Iso');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Jaquan', 'Yu');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Nico', 'Chao');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Brady', 'Hari');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Neil', 'Smith');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Sven', 'Louis');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Jon', 'Mirche');
INSERT INTO runner(team_id, first_name, last_name) VALUES (1, 'Henry', 'Kuzma');

--Bellarmine Runners
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Sparrow', 'Milian');
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Emil', 'Yusef');
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Edmund', 'Aidan');
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Mario', 'Ferari');
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Tyrell', 'Jackson');
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Juan', 'Garcia');
INSERT INTO runner(team_id, first_name, last_name) VALUES (2, 'Stephen', 'Jackson');

--St. Ignatius Runners
INSERT INTO runner(team_id, first_name, last_name) VALUES (3, 'Michael', 'Burt');
INSERT INTO runner(team_id, first_name, last_name) VALUES (3, 'Matias', 'Carrol');
INSERT INTO runner(team_id, first_name, last_name) VALUES (3, 'Reggie', 'Kenton');
INSERT INTO runner(team_id, first_name, last_name) VALUES (3, 'Kieron', 'Felini');
INSERT INTO runner(team_id, first_name, last_name) VALUES (3, 'Jim', 'Klay');
COMMIT;

