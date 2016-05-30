CREATE TABLE users
(
	id           INTEGER auto_increment,
	firstname    VARCHAR(50),
	lastname     VARCHAR(50),
	email        VARCHAR(50) NOT NULL,
	password     VARCHAR(128) NOT NULL,
	is_approved  INTEGER NOT NULL,
	points       INTEGER NOT NULL,
	group_number INTEGER,
	is_archived  INTEGER DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE summerhouses
(
	id          INTEGER auto_increment,
	title       VARCHAR(500),
	address     VARCHAR(500),
	price       INTEGER,
	description VARCHAR(2000),
	capacity    INTEGER,
	date_from   DATE NOT NULL,
	date_to     DATE NOT NULL,
	is_archived TINYINT(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE reservations
(
	id             INTEGER auto_increment,
	date_from      DATE NOT NULL,
	date_to        DATE NOT NULL,
	user_id        INTEGER NOT NULL,
	summerhouse_id INTEGER NOT NULL,
	is_approved    TINYINT(1) NOT NULL,
	is_archived    TINYINT(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users (id),
	FOREIGN KEY (summerhouse_id) REFERENCES summerhouses (id)
);

CREATE TABLE services
(
	id          INTEGER auto_increment,
	title       VARCHAR(500),
	price       INTEGER NOT NULL,
	description VARCHAR(2000),
	is_archived TINYINT(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE summerhouse_services
(
	service_id     INTEGER NOT NULL,
	summerhouse_id INTEGER NOT NULL,
	PRIMARY KEY(service_id, summerhouse_id),
	FOREIGN KEY (service_id) REFERENCES services (id),
	FOREIGN KEY (summerhouse_id) REFERENCES summerhouses (id)
);