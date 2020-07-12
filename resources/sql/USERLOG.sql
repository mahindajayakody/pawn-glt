DROP TABLE IF EXISTS `tbluserlog`;
CREATE TABLE `tbluserlog` (
  `USERLOGID` INT PRIMARY KEY ,
  `COMID` int(11) default NULL COMMENT 'Company Id',
  `BRANCHID` int(11) default NULL COMMENT 'Branch Id',
  `TRANNO` varchar(40) default NULL COMMENT 'Transaction No',
  `STATUS` int(1) default NULL COMMENT 'Status',
  `LASTUPDATE` datetime default NULL COMMENT 'Transaction  Date',
  `LASTUPUSERID` int(1) default NULL COMMENT ' User Id',
  `ORIGINALID` INT,
  `VERSIONID` INT,
  `RECSTATUS` int  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
