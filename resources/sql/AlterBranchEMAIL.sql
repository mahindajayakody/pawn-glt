ALTER TABLE `pawn`.`tblbranch` ADD COLUMN `EMAIL` VARCHAR(100) AFTER `BRANCHNAME`;
ALTER TABLE `pawn`.`tblbranchfundrequest` ADD COLUMN `EMAIL` VARCHAR(100) AFTER `VERSIONID`;
ALTER TABLE `pawn`.`tblbranchfunddeposit` ADD COLUMN `EMAIL` VARCHAR(100) AFTER `VERSIONID`;

