DROP TABLE IF EXISTS `tblaudittrial`;
CREATE TABLE `tblaudittrial` (
  `COMID` int(11) default NULL COMMENT 'Company Id',
  `BRANCHID` int(11) default NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) default NULL COMMENT 'Product Id',
  `TRANNO` int(40) default NULL COMMENT 'Transaction No',
  `TABLENAME` varchar(50) default NULL COMMENT 'Table Name',
  `PRIMARYKEY`varchar(50) default NULL COMMENT 'Primary Key',
  `AUDITDATA` varchar(2000) default NULL COMMENT 'Audit Data',
  `ACTION` varchar(20) default NULL COMMENT 'Action Name',
  `TRANDATE` datetime default NULL COMMENT 'Transaction  Date'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
