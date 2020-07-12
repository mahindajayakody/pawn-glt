-- Table: tblparameter

-- DROP TABLE IF EXISTS `tblparameter`;

CREATE TABLE `tblparameter` (
  PARAMETERID   INT AUTO_INCREMENT NOT NULL COMMENT 'Parameter Id',
  COMID         INT COMMENT 'Company Id',
  BRANCHID      INT COMMENT 'Branch Id',
  PRODUCTID     INT COMMENT 'Product Id',
  CODE          VARCHAR(3) COMMENT 'Parameter Code',
  DESCRIPTION   VARCHAR(60) COMMENT 'Product Description',
  DATATYPE      VARCHAR(20) COMMENT 'Data Type',
  ISACTIVE      VARCHAR(1) COMMENT 'Is Active',
  LASTUPDATE    DATETIME COMMENT 'Last Updated Date',
  LASTUPTIME    MEDIUMTEXT COMMENT 'Last Updated Time',
  LASTUPUSERID  INT COMMENT 'Last Updated User Id',
  RECSTATUS     VARCHAR(2) COMMENT 'Record Status',
  ORIGINALID    INT COMMENT 'Original Record Id',
  VERSIONID     INT COMMENT 'Version',
  /* Keys */
  PRIMARY KEY (PARAMETERID)
) ENGINE = InnoDB;