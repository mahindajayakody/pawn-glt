DROP TABLE tblSystemProgram;

CREATE TABLE tblSystemProgram(
	PRGID SMALLINT,
	PRDCODE CHAR(2),
	PARENTID SMALLINT,
	NODENAME VARCHAR(30),
	URLPATH VARCHAR(30)
);

ALTER TABLE tblSystemProgram ADD COLUMN ACCESS VARCHAR(15) DEFAULT '1:2:3';