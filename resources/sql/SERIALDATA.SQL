DELETE FROM `pawn`.`serial_master`;
DELETE FROM `pawn`.`serial`;

INSERT INTO `pawn`.`serial_master`(SERIALMASTERID,DESCRIPTION,CODE,ISBRANCH,ISPRD,ISANNUALLY)
VALUES(1,'Ticket Number','TKT',1,1,0);

INSERT INTO `pawn`.`serial`(SERIALID,COMID,FINAYEAR,FINABEGDATE,FINAENDDATE,CODE,RUNGNO,BRANCHID,PRODUCTID,VERSIONID)
VALUES(1,1,2008,'2008-01-01','2008-12-31','TKT',1,1,1,0);


INSERT INTO `pawn`.`serial_master`(SERIALMASTERID,DESCRIPTION,CODE,ISBRANCH,ISPRD,ISANNUALLY)
VALUES(2,'Receipt','RCP',1,0,1);

INSERT INTO `pawn`.`serial`(SERIALID,COMID,FINAYEAR,FINABEGDATE,FINAENDDATE,CODE,RUNGNO,BRANCHID,PRODUCTID,VERSIONID)
VALUES(3,1,2008,'2008-01-01','2008-12-31','RCP',1,2,0,0);

INSERT INTO `pawn`.`serial`(SERIALID,COMID,FINAYEAR,FINABEGDATE,FINAENDDATE,CODE,RUNGNO,BRANCHID,PRODUCTID,VERSIONID)
VALUES(4,1,2008,'2008-01-01','2008-12-31','RCP',1,1,0,0);
 