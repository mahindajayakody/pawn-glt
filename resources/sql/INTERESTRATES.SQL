DROP TABLE pawn.tblinterestrates;
 create table pawn.tblinterestrates(
       COMID int,
       INTERESTID int not null PRIMARY KEY AUTO_INCREMENT,
       BRANCHID int,
       PRODUCTID int,
       CODE varchar(3),
       DESCRIPTION varchar(60),
       ISACTIVE smallint unsigned,
       RECSTATUS smallint unsigned,
       LASTUPDATE datetime,
       LASTUPTIME mediumtext,
       LASTUPUSERID int,
       VERSIONID int,
       ORIGINALID int
       
    );

