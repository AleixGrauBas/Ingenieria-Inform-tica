CREATE TABLE P(
	pnum VARCHAR(2),
	pnombre	VARCHAR(10),
	color VARCHAR(10),
	peso INTEGER,
	ciudad VARCHAR(10),
	CONSTRAINT cp_p PRIMARY KEY ( pnum ),
	CONSTRAINT ri_p_peso CHECK ( peso > 0 )
);
