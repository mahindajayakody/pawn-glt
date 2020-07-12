-- Table: tblGlAccount

DROP TABLE IF EXISTS `tblGlAccount`;

CREATE TABLE `tblGlAccount` (
  ACCOUNTID     INT AUTO_INCREMENT NOT NULL COMMENT 'Account Id',
  COMID         INT COMMENT 'Company Id',
  BRANCHID      INT COMMENT 'Branch Id',
  PRODUCTID     INT COMMENT 'Product Id',
  ACCNO         VARCHAR(10) COMMENT 'Account Number',
  DRCR          VARCHAR(2) COMMENT 'Standard Type',
  LASTUPDATE    DATETIME COMMENT 'Last Updated Date',
  LASTUPTIME    MEDIUMTEXT COMMENT 'Last Updated Time',
  LASTUPUSERID  INT COMMENT 'Last Updated User Id',
  RECSTATUS     VARCHAR(2) COMMENT 'Record Status',
  ORIGINALID    INT COMMENT 'Original Record Id',
  VERSIONID     INT COMMENT 'Version',
  DESCRIPTION VARCHAR(60),
  CODE CHAR(3),
  /* Keys */
  PRIMARY KEY (ACCOUNTID)
) ENGINE = InnoDB;