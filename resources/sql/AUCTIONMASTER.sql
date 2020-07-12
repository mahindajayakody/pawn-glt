DROP TABLE `pawn`.`tblauctionmaster`;

    create table `pawn`.`tblauctionmaster`(
        `AUCTIONID` int PRIMARY KEY auto_increment comment 'Auction Id',
       `COMID` int comment 'Company Id',
       `PRODUCTID` int comment 'Product Id',
       `BRANCHID` int comment 'Branch Id',
       `AUCTIONEEID` int comment 'Auctionee Id',
       `AUCTIONDATE` datetime comment 'Auction Date',
       `LOCATIONID` int comment 'Loacation Id',
       `CODE` varchar(4) comment 'Reminder Code',
       `DESCRIPTION` varchar(60) comment 'Description',
       `USERID` int comment 'Resposible Person',
       `NOOFTICKET` int comment 'No of Ticket',
       `STATUS` varchar(1) comment 'Status',
       `TOTCAP` DECIMAL(15,2) comment 'Total Capital',
       `TOTINT` DECIMAL(15,2)comment 'Total Interest',
       `TOTEXPNS` DECIMAL(15,2) comment 'Total Expences',
       `TOTTAX` DECIMAL(15,2) comment 'Tax',
       `PROFIT` DECIMAL(15,2) comment 'Profit',
       `TOTEARNED` DECIMAL(15,2) comment 'Total Earned',
       `LASTUPDATE` datetime comment 'Last Updated Date',
       `LASTUPTIME` mediumtext comment 'Last Updated Time',
       `LASTUPUSERID` int comment 'Last Updated User Id',
       `RECSTATUS` varchar(2) comment 'Record Status',
       `ORIGINALID` int comment 'Original Record Id',
       `VERSIONID` int comment 'Version'
    );


 