  DROP TABLE tblcartage;
  
  create table tblcartage(
       CARTAGEID INT PRIMARY KEY auto_increment comment 'Cartage Id',
       COMID INT comment 'Company Id',
       PRODUCTID INT comment 'Product Id',
       MCARTAGEID INT comment 'Master Cartage Id',
       CODE VARCHAR(3) comment 'Account Code',
       DESCRIPTION VARCHAR(60) comment 'Description',
       MAKVALUE DECIMAL(10,2) comment 'Marcket Value',
       DISBSEPECR DECIMAL(10,2) comment 'Disbusement Percentage',
       DISBSEVALE DECIMAL(10,2) comment 'Disbusement Value',
       DISPLAYVALUE VARCHAR(60) comment 'Display Value',
       ISACTIVE VARCHAR(1) comment 'Is Active',
       LASTUPDATE DATETIME comment 'Last Updated Date',
       LASTUPTIME MEDIUMTEXT comment 'Last Updated Time',
       LASTUPUSERID INT comment 'Last Updated User Id',
       RECSTATUS VARCHAR(2) comment 'Record Status',
       ORIGINALID INT comment 'Original Record Id',
       VERSIONID INT comment 'Version'
  )

/*
ALTER TABLE tblcartage MODIFY COLUMN MAKVALUE DECIMAL(10,2); 
ALTER TABLE tblcartage MODIFY COLUMN DISBSEPECR DECIMAL(10,2);
ALTER TABLE tblcartage MODIFY COLUMN DISBSEVALE DECIMAL(10,2);
*/  