DROP TABLE IF EXISTS `tbleventlog`;
CREATE TABLE `tbleventlog` (
  `EVENTLOGID` int PRIMARY KEY,
  `COMID` int(11) default NULL COMMENT 'Company Id',
  `BRANCHID` int(11) default NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) default NULL COMMENT 'Product Id',
  `TRANNO` varchar(40)  default NULL COMMENT 'Transaction No',
  `PRGNAME` varchar(50) default NULL COMMENT 'Table Name',
  `EVENTID`int default NULL COMMENT 'Table Name',
  `LASTUPDATE` datetime default NULL COMMENT 'Transaction  Date',
  `LASTUPUSERID` int(11) default NULL COMMENT 'Last Updated User Id',
  `ORIGINALID` int default NULL COMMENT 'Original Id',
  `RECSTATUS`  int default NULL COMMENT 'Record Status',
  `VERSIONID` int
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
