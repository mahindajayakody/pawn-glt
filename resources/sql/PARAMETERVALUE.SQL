-- Table: tblParameterValue

DROP TABLE IF EXISTS `tblParameterValue`;

CREATE TABLE `tblParameterValue` (
  PARAMETERVALUEID   INT AUTO_INCREMENT NOT NULL COMMENT 'Parameter Value Id',
  COMID         INT COMMENT 'Company Id',
  BRANCHID      INT COMMENT 'Branch Id',
  PRODUCTID     INT COMMENT 'Product Id',
  PARAMETERID   INT COMMENT 'Parameter Id',
  PARAVALUE     VARCHAR(20) COMMENT 'Parameter Value',
  EFFDATE       DATETIME COMMENT 'Effective Date',
  LASTUPDATE    DATETIME COMMENT 'Last Updated Date',
  LASTUPTIME    MEDIUMTEXT COMMENT 'Last Updated Time',
  LASTUPUSERID  INT COMMENT 'Last Updated User Id',
  RECSTATUS     VARCHAR(2) COMMENT 'Record Status',
  ORIGINALID    INT COMMENT 'Original Record Id',
  VERSIONID     INT COMMENT 'Version',
  /* Keys */
  PRIMARY KEY (PARAMETERVALUEID)
) ENGINE = InnoDB;