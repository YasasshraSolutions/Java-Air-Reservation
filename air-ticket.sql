CREATE TABLE airport(
	airportID VARCHAR (10) NOT NULL PRIMARY KEY,
	name VARCHAR (30) NOT NULL,
	city VARCHAR (20) NOT NULL,
	apstate VARCHAR (20) NOT NULL,
	country VARCHAR (20) NOT NULL
);


CREATE TABLE flight_leg(
	leg_no VARCHAR (25) NOT NULL  PRIMARY KEY,
	leg_type CHAR(2) NOT NULL
);

CREATE TABLE airline(
	airline_ID VARCHAR (20) NOT NULL PRIMARY KEY,
	airline_name VARCHAR (15) UNIQUE NOT NULL,
	origin VARCHAR (15) NOT NULL
);

CREATE TABLE toap(
	aIDlID VARCHAR (20) NOT NULL PRIMARY KEY,
	departure_time datetime NOT NULL,
	arival_time datetime NOT NULL,
	airportID VARCHAR(10),
	leg_no  VARCHAR (25)

);


CREATE TABLE fromap(
	aIDlID VARCHAR (20) NOT NULL PRIMARY KEY,
	departure_time datetime NOT NULL,
	arival_time datetime NOT NULL,
	airportID VARCHAR(10),
	leg_no  VARCHAR (25)
);


CREATE TABLE flight(
	flight_no VARCHAR (20) NOT NULL PRIMARY KEY,
	max_seats int NOT NULL,
	airline_ID VARCHAR(20) NOT NULL
);

CREATE TABLE passenger(
	tel VARCHAR (12) NOT NULL,
	paddress VARCHAR (30) NOT NULL,
	fname VARCHAR (20) NOT NULL,
	lname VARCHAR (20) NOT NULL,
	pass_no CHAR (10) NOT NULL PRIMARY KEY,
	dob DATE NOT NULL,
	programID INT NOT NULL
);

CREATE TABLE tickets(
	ticket_no VARCHAR (10) NOT NULL PRIMARY KEY,
	seat_no INT NOT NULL,
	fair FLOAT (10) NOT NULL,
	pass_no CHAR (10) NOT NULL ,
	leg_no VARCHAR (25)	NOT NULL
);

CREATE TABLE assigned_to(
	leg_no VARCHAR (25) NOT NULL,
	flight_no VARCHAR (20)NOT NULL,
	PRIMARY KEY (leg_no,flight_no)
);
