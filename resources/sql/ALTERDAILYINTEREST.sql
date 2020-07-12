ALTER TABLE `tbldailyinterest` ADD COLUMN `DATE` DATE NULL  AFTER `TKTID` ;

update `tbldailyinterest` set date = lastupdate 