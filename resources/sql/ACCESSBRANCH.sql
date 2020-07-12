
    create table `pawn`.`tblaccbranch`(
        `ACCBRNCHID` int unsigned not null auto_increment,
       `BRANCHID` int unsigned default '0' not null,
       `OFFICERID` int unsigned default '0' not null,
       `BRNCHNAME` varchar(45) default '' not null,
       `VERSIONID` int unsigned default '0' not null,
        primary key (`ACCBRNCHID`)
    );

