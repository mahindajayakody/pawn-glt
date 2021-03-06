DROP TABLE tblauctionticket;

CREATE TABLE tblauctionticket(
	AUCTIONTKTID INT PRIMARY KEY AUTO_INCREMENT,
	TKTID INT,
	AUCTIONID INT,
	PRODUCTID INT,
	ASSIGNDATE DATE,
	SOLDDATE DATE,
	MININT DECIMAL(15,2),
	MINCAP DECIMAL(15,2),
	SOLDAMT DECIMAL(15,2),
	AUCTIONEXP DECIMAL(15,2),
	CAPSET DECIMAL(15,2),
	INTSET DECIMAL(15,2),
	ISPAIDTOCLIENT INT,
	PAIDAMT DECIMAL(15,2),
	EXCESS DECIMAL(15,2),
	ISALLECATED INT,
	RECSTATUS SMALLINT,
	LASTUPDATE DATETIME,
	LASTUPTIME DECIMAL(15,2),
	LASTUPUSERID INT,
	ORIGINALID INT,
	VERSIONID INT,
	COMID INT,
	BRANCHID INT
);