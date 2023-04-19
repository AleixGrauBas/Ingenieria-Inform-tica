CREATE TABLE S (
	snum VARCHAR(2),
	snombre VARCHAR(10),
	estado INTEGER,
	ciudad VARCHAR(10),
	CONSTRAINT cp_s PRIMARY KEY ( snum ),
	CONSTRAINT calt_s UNIQUE ( snombre ),
	CONSTRAINT ri_s_estado CHECK ( estado > 0 )
);
