ALTER TABLE `pawn`.`tblauctionticket` ADD COLUMN `UPSETVALUE` DECIMAL(15,2) NULL DEFAULT 0  AFTER `BRANCHID` ;

INSERT INTO `pawn`.`tblsystemprogram` (`PRGID`, `PRDCODE`, `PARENTID`, `NODENAME`, `URLPATH`, `ACCESS`) VALUES (9011, 'PW', 40, 'Auction Tickets', 'auctionTicket.do', '1');