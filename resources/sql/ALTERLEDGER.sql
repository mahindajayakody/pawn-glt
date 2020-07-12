ALTER TABLE `tblledger` ADD COLUMN `TKTID` INT(11) NULL  AFTER `RECSTATUS` , ADD COLUMN `DATE` DATE NULL  AFTER `TKTID` ;
update `tblledger` set date = lastupdate ;