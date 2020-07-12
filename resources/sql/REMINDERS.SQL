DROP TABLE `tblreminders`;
CREATE TABLE `tblreminders` (
	REMINDERID int(11) NOT NULL auto_increment COMMENT 'Reminder Id',
	REMPARAID int(11) NOT NULL COMMENT 'Reminder Id',
  	COMID int(11) default NULL COMMENT 'Company Id',
  	PRODUCTID int(11) default NULL COMMENT 'Product Id',
  	BRANCHID int(11) default NULL COMMENT 'Branch Id',
  	SCHEMEID int(11) default NULL COMMENT 'Scheme Id',
  	REMPARACODE varchar(4) default NULL COMMENT 'Reminder Para Code',
	ISPRINTED varchar(1) default NULL COMMENT 'Is Printed',
  	TICKETID int(11) default NULL COMMENT 'Ticket Id',
  	DATEGENERATED datetime  NOT NULL COMMENT 'Date Generated',
  	DATEPRINTED datetime  NULL COMMENT 'Date Printed',
	CAPOUT Decimal default NULL COMMENT 'Capital Outstanding',
	INTOUT Decimal default NULL COMMENT 'Interest Outstanding',
	OTHOUT Decimal default NULL COMMENT 'Other Outstanding',
  	LASTUPDATE datetime default NULL COMMENT 'Last Updated Date',
  	LASTUPTIME mediumtext COMMENT 'Last Updated Time',
  	LASTUPUSERID int(11) default NULL COMMENT 'Last Updated User Id',
  	RECSTATUS varchar(2) default NULL COMMENT 'Record Status',
  	ORIGINALID int(11) default NULL COMMENT 'Original Record Id',
  	VERSIONID int(11) default NULL COMMENT 'Version',
  PRIMARY KEY  (`REMINDERID`)
) ;