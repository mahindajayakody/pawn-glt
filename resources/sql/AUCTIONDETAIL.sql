DROP TABLE tblauctioexpences;

    create table `pawn`.`tblauctioexpences`(
       `AUCTIONDETID` int PRIMARY KEY auto_increment comment 'Auction Detail Id',
       `AUCTIONID` int comment 'Auction Id',
       `COMID` int comment 'Company Id',
       `PRODUCTID` int comment 'Product Id',
       `BRANCHID` int comment 'Branch Id',
       `DUETYPEID` int comment 'Due Type Id',
       `AMOUNT` long comment 'Amount',
       `LASTUPDATE` datetime comment 'Last Updated Date',
       `LASTUPTIME` datetime comment 'Last Updated Time',
       `LASTUPUSERID` int comment 'Last Updated User Id',
       `RECSTATUS` varchar(2) comment 'Record Status',
       `ORIGINALID` int comment 'Original Record Id',
       `VERSIONID` int comment 'Version'
    );

 