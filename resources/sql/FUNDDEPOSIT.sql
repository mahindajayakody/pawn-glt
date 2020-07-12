DROP TABLE IF EXISTS tblbranchfunddeposit;

CREATE TABLE  tblbranchfunddeposit (
  	DEPOSITID INT(11) NOT NULL auto_increment COMMENT 'Transaction Id',
  	COMID INT(11) default NULL COMMENT 'Company Id',
  	BRANCHID INT(11) default NULL COMMENT 'Branch Id',
  	DEPOSITNO VARCHAR(12) default NULL COMMENT 'Depoait No',
  	DEPOSITDATE DATETIME default NULL COMMENT 'Depoait Date',
  	DEPOSITAMT DECIMAL(15,2) default NULL COMMENT 'Depoait Value',
  	TICKETCOUNT INT(11) default NULL COMMENT 'Total Tiketed Count',
  	TICKETEDAMT DECIMAL(15,2) default NULL COMMENT 'Total Tiketed Amount',
  	RECEIPTCOUNT INT(11) default NULL COMMENT 'Total Receip Count',
  	RECEIPTEDAMT DECIMAL(15,2) default NULL COMMENT 'Total Receipted Amount',
  	APPROVEDBY INT default NULL COMMENT 'Approved By',
  	APPROVEDDATE DATETIME default NULL COMMENT 'Approved date',
  	LASTUPDATE DATETIME default NULL COMMENT 'Last Updated Date',
  	LASTUPTIME MEDIUMTEXT COMMENT 'Last Updated Time',
  	LASTUPUSERID INT(11) default NULL COMMENT 'Last Updated User Id',
  	RECSTATUS VARCHAR(2) default NULL COMMENT 'Record Status',
  	ORIGINALID INT(11) default NULL COMMENT 'Original Record Id',
  	VERSIONID INT(11) default NULL COMMENT 'Version',
  	PRIMARY KEY  (`DEPOSITID`)
); 	