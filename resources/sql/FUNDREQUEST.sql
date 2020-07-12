DROP TABLE IF EXISTS tblbranchfundrequest;

CREATE TABLE  tblbranchfundrequest (
  	REQUESTID INT(11) NOT NULL auto_increment COMMENT 'Transaction Id',
  	COMID INT(11) default NULL COMMENT 'Company Id',
  	BRANCHID INT(11) default NULL COMMENT 'Branch Id',
  	REQUESTNO VARCHAR(12) default NULL COMMENT 'Request No',
  	REQUESTDATE DATETIME default NULL COMMENT 'Request Date',
  	REQUESTAMT DECIMAL(10,0) default NULL COMMENT 'Request Value',
  	APPROVEDBY INT default NULL COMMENT 'Approved By',
  	APPROVEDDATE DATETIME default NULL COMMENT 'Approved date',
  	LASTUPDATE DATETIME default NULL COMMENT 'Last Updated Date',
  	LASTUPTIME MEDIUMTEXT COMMENT 'Last Updated Time',
  	LASTUPUSERID INT(11) default NULL COMMENT 'Last Updated User Id',
  	RECSTATUS VARCHAR(2) default NULL COMMENT 'Record Status',
  	ORIGINALID INT(11) default NULL COMMENT 'Original Record Id',
  	VERSIONID INT(11) default NULL COMMENT 'Version',
  	PRIMARY KEY  (`REQUESTID`)
) 	