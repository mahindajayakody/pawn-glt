DROP TABLE tblreminderparameter;
CREATE TABLE `tblreminderparameter` (
  `REMPARAID` int(11) NOT NULL auto_increment COMMENT 'Reminder Id',
  `COMID` int(11) default NULL COMMENT 'Company Id',
  `PRODUCTID` int(11) default NULL COMMENT 'Product Id',
  `BRANCHID` int(11) default NULL COMMENT 'Branch Id',
  `SCHEMEID` int(11) default NULL COMMENT 'Scheme Id',
  `CODE` varchar(4) default NULL COMMENT 'Reminder Code',
  `DESCRIPTION` varchar(60) default NULL COMMENT 'Description',
  `NOOFDAYS` int(3) default NULL COMMENT 'No of Days After Laps',
  `ISSENDNOMINEE` varchar(1) default NULL COMMENT 'Reminder Code',
  `LASTUPDATE` datetime default NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) default NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) default NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) default NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) default NULL COMMENT 'Version',
  PRIMARY KEY  (`REMPARAID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
