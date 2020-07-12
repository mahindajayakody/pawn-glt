CREATE DATABASE  IF NOT EXISTS `pawn_glt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pawn_glt`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: pawn_glt
-- ------------------------------------------------------
-- Server version	5.5.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblduetype`
--

DROP TABLE IF EXISTS `tblduetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblduetype` (
  `DUETYPEID` int(11) NOT NULL AUTO_INCREMENT,
  `PRDID` char(2) DEFAULT NULL,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `SEQUENCE` smallint(6) DEFAULT NULL,
  `ISINTERNAL` varchar(2) DEFAULT NULL,
  `ISRECEIPT` varchar(2) DEFAULT NULL,
  `ISODCHARGABLE` varchar(2) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `ACCOUNTNO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DUETYPEID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblduetype`
--

LOCK TABLES `tblduetype` WRITE;
/*!40000 ALTER TABLE `tblduetype` DISABLE KEYS */;
INSERT INTO `tblduetype` VALUES (1,'1','CAP','Capital',2,'I','R','Y',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1,'1'),(2,'1','INT','Interest',1,'I','R','Y',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1,'2'),(3,'1','PAY','Payment',1,'I','P','Y',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1,'3'),(4,'1','FNR','Fund Request',3,'I','P','Y',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1,'4'),(5,'1','FDP','Fund Deposit',4,'I','P','Y',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1,'5'),(6,'1','DOC','Document Charges',0,'E','R','N',1,'2012-09-06 00:00:00',NULL,1,0,0,1,1,'0');
/*!40000 ALTER TABLE `tblduetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblaudittrial`
--

DROP TABLE IF EXISTS `tblaudittrial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblaudittrial` (
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `TRANNO` int(40) DEFAULT NULL COMMENT 'Transaction No',
  `TABLENAME` varchar(50) DEFAULT NULL COMMENT 'Table Name',
  `PRIMARYKEY` varchar(50) DEFAULT NULL COMMENT 'Primary Key',
  `AUDITDATA` varchar(2000) DEFAULT NULL COMMENT 'Audit Data',
  `ACTION` varchar(20) DEFAULT NULL COMMENT 'Action Name',
  `TRANDATE` datetime DEFAULT NULL COMMENT 'Transaction  Date'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaudittrial`
--

LOCK TABLES `tblaudittrial` WRITE;
/*!40000 ALTER TABLE `tblaudittrial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblaudittrial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcancelduereceipt`
--

DROP TABLE IF EXISTS `tblcancelduereceipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcancelduereceipt` (
  `DUERCPID` int(11) NOT NULL AUTO_INCREMENT,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `RCPID` int(11) DEFAULT NULL,
  `PAWNERID` int(11) DEFAULT NULL,
  `SETAMOUNT` decimal(18,2) DEFAULT NULL,
  `SETDATE` datetime DEFAULT NULL,
  `DUETYPEID` int(11) DEFAULT NULL,
  `REFNO` int(11) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `ORIGINALID` int(11) DEFAULT NULL,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`DUERCPID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcancelduereceipt`
--

LOCK TABLES `tblcancelduereceipt` WRITE;
/*!40000 ALTER TABLE `tblcancelduereceipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblcancelduereceipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblclosuretype`
--

DROP TABLE IF EXISTS `tblclosuretype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblclosuretype` (
  `CLOSURETYPEID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CLOSURETYPEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblclosuretype`
--

LOCK TABLES `tblclosuretype` WRITE;
/*!40000 ALTER TABLE `tblclosuretype` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblclosuretype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusergroup`
--

DROP TABLE IF EXISTS `tblusergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblusergroup` (
  `USERGROUPID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(4) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `ISADMIN` int(1) DEFAULT '0',
  PRIMARY KEY (`USERGROUPID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusergroup`
--

LOCK TABLES `tblusergroup` WRITE;
/*!40000 ALTER TABLE `tblusergroup` DISABLE KEYS */;
INSERT INTO `tblusergroup` VALUES (1,'0001','Admin',1,'2008-12-07 13:08:13',NULL,1,0,0,NULL,NULL,0),(2,'0002','Branch Back Office',1,'2009-08-31 12:28:48',NULL,12,0,1,NULL,NULL,0),(3,'0003','Head Of Branch',1,'2009-08-31 12:28:48',NULL,12,0,0,NULL,NULL,0),(4,'0004','IT Department',1,'2009-08-31 12:28:48',NULL,12,0,0,NULL,NULL,0),(5,'0005','Internal Audit',1,'2009-08-31 12:28:48',NULL,12,0,0,NULL,NULL,0),(6,'0006','Inactive',1,'2012-01-19 00:00:00',NULL,13,0,1,NULL,NULL,0),(7,'0007','Branch Coordinator',1,'2009-08-31 12:28:48',NULL,12,0,0,NULL,NULL,0),(8,'0008','Finance ',1,'2010-12-11 12:28:48',NULL,13,0,0,NULL,NULL,0),(9,'0009','View',1,'2012-07-02 00:00:00',NULL,1,0,0,NULL,NULL,0);
/*!40000 ALTER TABLE `tblusergroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldueto`
--

DROP TABLE IF EXISTS `tbldueto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbldueto` (
  `DUETOID` int(11) NOT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `DUETYPEID` int(11) DEFAULT NULL,
  `PWNID` int(11) DEFAULT NULL,
  `DUEAMOUNT` decimal(15,2) DEFAULT NULL,
  `PAIDAMOUNT` decimal(15,2) DEFAULT NULL,
  `BALAMOUNT` decimal(15,2) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`DUETOID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldueto`
--

LOCK TABLES `tbldueto` WRITE;
/*!40000 ALTER TABLE `tbldueto` DISABLE KEYS */;
INSERT INTO `tbldueto` VALUES (7,1,7,3,1,100000.00,0.00,100000.00,'2013-05-25 00:00:00',NULL,1,0,1,1,0,0),(8,1,8,3,1,100000.00,0.00,100000.00,'2013-06-03 00:00:00',NULL,1,0,1,1,0,0),(9,1,9,3,1,100000.00,0.00,100000.00,'2013-06-03 00:00:00',NULL,1,0,1,1,0,0),(10,1,10,3,1,100000.00,0.00,100000.00,'2013-05-26 00:00:00',NULL,1,0,1,1,0,0);
/*!40000 ALTER TABLE `tbldueto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbranchfundrequest`
--

DROP TABLE IF EXISTS `tblbranchfundrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblbranchfundrequest` (
  `REQUESTID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Transaction Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `REQUESTNO` varchar(12) DEFAULT NULL COMMENT 'Request No',
  `REQUESTDATE` datetime DEFAULT NULL COMMENT 'Request Date',
  `REQUESTAMT` decimal(10,0) DEFAULT NULL COMMENT 'Request Value',
  `APPROVEDBY` int(11) DEFAULT NULL COMMENT 'Approved By',
  `APPROVEDDATE` datetime DEFAULT NULL COMMENT 'Approved date',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  `EMAIL` varchar(100) DEFAULT NULL,
  `REQBRNID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REQUESTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbranchfundrequest`
--

LOCK TABLES `tblbranchfundrequest` WRITE;
/*!40000 ALTER TABLE `tblbranchfundrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblbranchfundrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbranchfunddeposit`
--

DROP TABLE IF EXISTS `tblbranchfunddeposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblbranchfunddeposit` (
  `DEPOSITID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Transaction Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `DEPOSITNO` varchar(12) DEFAULT NULL COMMENT 'Depoait No',
  `DEPOSITDATE` datetime DEFAULT NULL COMMENT 'Depoait Date',
  `DEPOSITAMT` decimal(15,2) DEFAULT NULL COMMENT 'Depoait Value',
  `TICKETCOUNT` int(11) DEFAULT NULL COMMENT 'Total Tiketed Count',
  `TICKETEDAMT` decimal(15,2) DEFAULT NULL COMMENT 'Total Tiketed Amount',
  `RECEIPTCOUNT` int(11) DEFAULT NULL COMMENT 'Total Receip Count',
  `RECEIPTEDAMT` decimal(15,2) DEFAULT NULL COMMENT 'Total Receipted Amount',
  `APPROVEDBY` int(11) DEFAULT NULL COMMENT 'Approved By',
  `APPROVEDDATE` datetime DEFAULT NULL COMMENT 'Approved date',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  `EMAIL` varchar(100) DEFAULT NULL,
  `REQBRNID` bigint(20) DEFAULT NULL,
  `DEPOBRNID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DEPOSITID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbranchfunddeposit`
--

LOCK TABLES `tblbranchfunddeposit` WRITE;
/*!40000 ALTER TABLE `tblbranchfunddeposit` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblbranchfunddeposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serial_master`
--

DROP TABLE IF EXISTS `serial_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serial_master` (
  `SERIALMASTERID` int(11) NOT NULL,
  `DESCRIPTION` varchar(30) DEFAULT NULL,
  `CODE` char(3) DEFAULT NULL,
  `ISBRANCH` smallint(6) DEFAULT NULL,
  `ISPRD` smallint(6) DEFAULT NULL,
  `ISANNUALLY` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`SERIALMASTERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serial_master`
--

LOCK TABLES `serial_master` WRITE;
/*!40000 ALTER TABLE `serial_master` DISABLE KEYS */;
INSERT INTO `serial_master` VALUES (1,'Ticket Number','TKT',1,1,0),(2,'Receipt','RCP',1,0,1),(3,'Fund Request','FNR',1,1,1),(4,'Fund Deposit','FDP',1,1,0);
/*!40000 ALTER TABLE `serial_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblticketstatus`
--

DROP TABLE IF EXISTS `tblticketstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblticketstatus` (
  `STSID` int(11) NOT NULL AUTO_INCREMENT,
  `STSCODE` char(4) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`STSID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblticketstatus`
--

LOCK TABLES `tblticketstatus` WRITE;
/*!40000 ALTER TABLE `tblticketstatus` DISABLE KEYS */;
INSERT INTO `tblticketstatus` VALUES (1,'0001','ACTIVE',1,'2008-12-07 12:28:48',NULL,1,0,0,NULL,NULL),(2,'0002','PENDING',1,'2008-12-07 12:28:48',NULL,1,0,0,NULL,NULL),(3,'0003','REJECTED',1,'2008-12-07 12:28:48',NULL,1,0,0,NULL,NULL),(4,'0004','LAPS',1,'2008-12-07 12:28:48',NULL,1,0,0,NULL,NULL),(5,'0005','REDEEM',1,'2009-07-28 12:28:48',NULL,3,0,1,NULL,NULL),(6,'0006','STOLEN',1,'2013-05-25 12:28:48',NULL,1,0,0,NULL,NULL),(7,'0007','DEAD',1,'2013-05-25 12:28:48',NULL,1,0,0,NULL,NULL);
/*!40000 ALTER TABLE `tblticketstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblreceipt`
--

DROP TABLE IF EXISTS `tblreceipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblreceipt` (
  `RCPID` int(11) NOT NULL AUTO_INCREMENT,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `RCPNO` varchar(12) DEFAULT NULL,
  `pawnERID` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `RCPAMOUNT` decimal(18,2) DEFAULT NULL,
  `RCPDATE` datetime DEFAULT NULL,
  `RCPTYPE` smallint(5) unsigned DEFAULT NULL,
  `PRINTNO` int(11) DEFAULT NULL,
  `PRINTDATE` datetime DEFAULT NULL,
  `CHEQUENO` varchar(20) DEFAULT NULL,
  `CHEQUEDATE` datetime DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `LASTUPDATE` varchar(45) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `ORIGINALID` int(10) unsigned DEFAULT NULL,
  `LASTUPUSERID` varchar(25) DEFAULT NULL,
  `RPCENTUSER` varchar(20) DEFAULT NULL,
  `CANCELUSERID` int(11) DEFAULT NULL,
  `CANCELDATE` datetime DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`RCPID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblreceipt`
--

LOCK TABLES `tblreceipt` WRITE;
/*!40000 ALTER TABLE `tblreceipt` DISABLE KEYS */;
INSERT INTO `tblreceipt` VALUES (1,1,1,1,10,'HED13R000001',1,'',2000.00,'2013-06-29 00:00:00',1,1,'2013-06-29 00:00:00',NULL,NULL,1,'2013-06-29 00:00:00',0,NULL,0,'1','DEMO',NULL,NULL,0),(2,1,1,1,10,'HED13R000002',1,'',1000.00,'2013-06-29 00:00:00',1,2,'2013-06-29 00:00:00',NULL,NULL,1,'2013-06-29 00:00:00',0,NULL,0,'1','DEMO',NULL,NULL,0),(3,1,1,1,10,'HED13R000003',1,'',1000.00,'2013-06-29 00:00:00',1,1,'2013-06-29 00:00:00',NULL,NULL,1,'2013-06-29 00:00:00',0,NULL,0,'1','DEMO',NULL,NULL,0),(4,1,1,1,10,'HED13R000004',1,'',1000.00,'2013-06-29 00:00:00',1,1,'2013-06-29 00:00:00',NULL,NULL,1,'2013-06-29 00:00:00',0,NULL,0,'1','DEMO',NULL,NULL,0),(5,1,1,1,10,'HED13R000005',1,'',1000.00,'2013-06-29 00:00:00',1,1,'2013-06-29 00:00:00',NULL,NULL,1,'2013-06-29 00:00:00',0,NULL,0,'1','DEMO',NULL,NULL,0);
/*!40000 ALTER TABLE `tblreceipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serial`
--

DROP TABLE IF EXISTS `serial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serial` (
  `SERIALID` int(11) NOT NULL,
  `COMID` int(11) DEFAULT NULL,
  `FINAYEAR` int(11) DEFAULT NULL,
  `FINABEGDATE` datetime DEFAULT NULL,
  `FINAENDDATE` datetime DEFAULT NULL,
  `CODE` char(3) DEFAULT NULL,
  `RUNGNO` bigint(20) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SERIALID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serial`
--

LOCK TABLES `serial` WRITE;
/*!40000 ALTER TABLE `serial` DISABLE KEYS */;
INSERT INTO `serial` VALUES (0,1,2013,'2011-01-01 00:00:00','2015-12-31 00:00:00','RCP',5,1,1,6),(1,1,2013,'2010-01-01 00:00:00','2015-12-31 00:00:00','TKT',4,1,1,394),(3,1,2013,'2010-01-01 12:28:48','2015-12-31 12:28:48','FNR',0,1,1,18),(4,1,2013,'2010-01-01 00:00:00','2015-12-31 00:00:00','FDP',0,1,1,2);
/*!40000 ALTER TABLE `serial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblparameter`
--

DROP TABLE IF EXISTS `tblparameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblparameter` (
  `PARAMETERID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Parameter Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `CODE` varchar(3) DEFAULT NULL COMMENT 'Parameter Code',
  `DESCRIPTION` varchar(60) DEFAULT NULL COMMENT 'Product Description',
  `DATATYPE` varchar(20) DEFAULT NULL COMMENT 'Data Type',
  `ISACTIVE` varchar(1) DEFAULT NULL COMMENT 'Is Active',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`PARAMETERID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblparameter`
--

LOCK TABLES `tblparameter` WRITE;
/*!40000 ALTER TABLE `tblparameter` DISABLE KEYS */;
INSERT INTO `tblparameter` VALUES (1,1,1,1,'001','Minimum Interest Days','Decimal','A','2008-12-07 12:28:48',NULL,1,'1',0,0),(2,1,1,1,'002','Maximum Advance for  Customer','Decimal','A','2012-11-08 00:00:00',NULL,1,'1',0,2),(3,1,1,1,'003','Maximum Advance for Non Customer','Decimal','A','2012-11-08 00:00:00',NULL,1,'1',0,2),(4,1,1,1,'004','Minimum Interest','Decimal','I','2010-12-11 12:28:48',NULL,1,'1',0,1),(5,1,1,1,'005','Secondary Interest Days','Decimal','A','2012-11-08 00:00:00',NULL,1,'1',0,1);
/*!40000 ALTER TABLE `tblparameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsystemprogram`
--

DROP TABLE IF EXISTS `tblsystemprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblsystemprogram` (
  `PRGID` smallint(6) NOT NULL DEFAULT '0',
  `PRDCODE` char(2) DEFAULT NULL,
  `PARENTID` smallint(6) DEFAULT NULL,
  `NODENAME` varchar(30) DEFAULT NULL,
  `URLPATH` varchar(30) DEFAULT NULL,
  `ACCESS` varchar(15) DEFAULT '1:2:3',
  PRIMARY KEY (`PRGID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsystemprogram`
--

LOCK TABLES `tblsystemprogram` WRITE;
/*!40000 ALTER TABLE `tblsystemprogram` DISABLE KEYS */;
INSERT INTO `tblsystemprogram` VALUES (0,'',-1,'pawning','','1'),(1,'PW',0,'SysConfig','','1'),(5,'PW',0,'Customer Care','','1'),(10,'PW',0,'Ticket Manager','','1'),(15,'PW',0,'Settlement','','1'),(20,'PW',0,'Redimtion','','1'),(21,'PW',0,'Fund Management','','1'),(25,'PW',0,'Reminders','','1'),(30,'PW',0,'Auction','','1'),(35,'PW',0,'Information','','1'),(40,'PW',0,'Reports','','1'),(45,'PW',0,'Sys Admin','','1'),(1000,'PW',1,'Ticket Status','ticketStatusService.do','1:2:3'),(1001,'PW',1,'Location','locationService.do','1:2:3'),(1002,'PW',1,'Cartage Master','cartageMarsterService.do','1:2:3'),(1003,'PW',1,'Cartage','cartageService.do','1:2:3'),(1004,'PW',1,'Due Types','dueTypeService.do','1:2:3'),(1005,'PW',1,'Client Types','pawnerTypeService.do','1:2:3'),(1006,'PW',1,'Parameters','parameterService.do','1:2:3'),(1007,'PW',1,'Parameter Values','parameterValueService.do','1:2:3'),(1010,'PW',1,'Closure Type','closureTypeService.do','1:2:3'),(1011,'PW',1,'User Company','companyService.do','1:2:3'),(1012,'PW',1,'User Branch','branchService.do','1:2'),(1013,'PW',1,'Article Model','articleModelService.do','1:2:3'),(1014,'PW',1,'Article','articleService.do','1:2:3'),(1015,'PW',1,'Auction Location','auctionLocationService.do','1:2:3'),(1016,'PW',1,'Product','productService.do','1:2:3'),(1017,'PW',1,'Account','accountService.do','1:2'),(1018,'PW',1,'Interest Rates','interestRatesService.do','1:2'),(1019,'PW',1,'Schemes','schemeService.do','1:2'),(2000,'PW',5,'Client Main','pawnerService.do','1:2'),(2001,'PW',5,'Ticket Marster','ticketService.do','1'),(2002,'PW',5,'Print Ticket','printTicketService.do','1'),(3000,'PW',10,'Authorize Ticket','authorizeTicketService.do','5'),(3001,'PW',10,'Safe Item','safeItemService.do','1'),(3002,'PW',10,'Other Expences','otherExpencesService.do','1'),(3003,'PW',10,'Status Change','changeStatusService.do','1:2:3'),(4000,'PW',15,'Receipt','receiptService.do','1'),(4001,'PW',15,'Receipt Cancelation','cancelreceiptService.do','1'),(4002,'PW',15,'Payment','paymentService.do','1'),(4003,'PW',15,'Payment Cancelation','paymentCancelationService.do','1'),(4005,'PW',15,'Receipt Re-Print','receiptRePrintService.do','1'),(5000,'PW',20,'Ticket Redeem','ticketRedeemService.do','1'),(5001,'PW',20,'Renew','renewService.do','1'),(5500,'PW',21,'Fund Deposit','fundDepositService.do','1:5'),(5600,'PW',21,'Fund Request','fundRequestService.do','1:2:3:5'),(5999,'PW',25,'Reminder Parameter','reminderParaService.do','1:2:3'),(6000,'PW',25,'Reminder Printing','reminderPrintService.do','1'),(6001,'PW',25,'Reminder Re-Printing','reminderRePrintService.do','1'),(7000,'PW',30,'Initiate Auction','initiateAuctionService.do','1'),(7001,'PW',30,'Auction Mark','auctionMarkService.do','1'),(7002,'PW',30,'Total Expences','auctionExpencesService.do','1'),(7003,'PW',30,'Expences Ticket Wise','allocateExpencesService.do','1'),(7004,'PW',30,'Expenece Details','expenceDetailsService.do','1'),(8000,'PW',35,'Info Console','infoconsoleService.do','1'),(9000,'PW',40,'Daily pawning Statement','reportService.do','1'),(9001,'PW',40,'Daily Payment Report','dailypaymentreportService.do','1'),(9002,'PW',40,'Trial Balance Outstanding','trialbaloutstandingService.do','1'),(9003,'PW',40,'Daily Cash Balance Report','fundDepositReportService.do','1'),(9004,'PW',40,'Staff pawning ','Staffpawnning.do','1'),(9005,'PW',40,'pawn Article Sumary','pawnArticleSummary.do','1'),(9006,'PW',40,'pawning Transaction By Size','pawningTransaction.do','1'),(9007,'PW',40,'Summary Of Interest','summaryOfInterest.do','1'),(9008,'PW',40,'Business Transaction','businessTransaction.do','1'),(9009,'PW',40,'Ledger Advance','ledgerAdvance.do','1'),(9010,'PW',40,'Ledger Interest','ledgerInterest.do','1'),(10000,'PW',45,'Day End','dayEndService.do','1'),(10001,'PW',45,'System Users','officerService.do','1:2'),(10002,'PW',45,'User Group','userGroupService.do','1:2'),(10003,'PW',45,'Program Access','programAccessService.do','1'),(10004,'PW',45,'Change Password','changePasswordService.do','1'),(10005,'PW',45,'Back-up Database','backUpDataBaseService.do','1'),(10006,'PW',45,'System Audit','systemAuditService.do','1');
/*!40000 ALTER TABLE `tblsystemprogram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblticketredeem`
--

DROP TABLE IF EXISTS `tblticketredeem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblticketredeem` (
  `REDEMID` int(11) NOT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `NEWTICKETID` int(11) DEFAULT NULL,
  `TOTPAID` decimal(15,2) DEFAULT NULL,
  `REDEMTYP` int(11) DEFAULT NULL,
  `REDUPUSERID` int(11) DEFAULT NULL,
  `REDDATE` datetime DEFAULT NULL,
  `APPUPUSERID` int(11) DEFAULT NULL,
  `APPDATE` datetime DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  PRIMARY KEY (`REDEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblticketredeem`
--

LOCK TABLES `tblticketredeem` WRITE;
/*!40000 ALTER TABLE `tblticketredeem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblticketredeem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpawnertype`
--

DROP TABLE IF EXISTS `tblpawnertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblpawnertype` (
  `PWTID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PWTID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpawnertype`
--

LOCK TABLES `tblpawnertype` WRITE;
/*!40000 ALTER TABLE `tblpawnertype` DISABLE KEYS */;
INSERT INTO `tblpawnertype` VALUES (1,'OFF','Officer',1,'2008-11-26 03:56:34',NULL,1,0,0,NULL,NULL),(2,'PWN','pawner',1,'2008-11-26 03:56:34',NULL,1,0,0,NULL,NULL);
/*!40000 ALTER TABLE `tblpawnertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblaccbranch`
--

DROP TABLE IF EXISTS `tblaccbranch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblaccbranch` (
  `ACCBRNCHID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `BRANCHID` int(10) unsigned NOT NULL DEFAULT '0',
  `OFFICERID` int(10) unsigned NOT NULL DEFAULT '0',
  `BRNCHNAME` varchar(45) NOT NULL DEFAULT '',
  `VERSIONID` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`ACCBRNCHID`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblaccbranch`
--

LOCK TABLES `tblaccbranch` WRITE;
/*!40000 ALTER TABLE `tblaccbranch` DISABLE KEYS */;
INSERT INTO `tblaccbranch` VALUES (282,1,1,'HEAD OFFICE',0);
/*!40000 ALTER TABLE `tblaccbranch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblauctionmaster`
--

DROP TABLE IF EXISTS `tblauctionmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblauctionmaster` (
  `AUCTIONID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Auction Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `AUCTIONEEID` int(11) DEFAULT NULL COMMENT 'Auctionee Id',
  `AUCTIONDATE` datetime DEFAULT NULL COMMENT 'Auction Date',
  `LOCATIONID` int(11) DEFAULT NULL COMMENT 'Loacation Id',
  `CODE` varchar(4) DEFAULT NULL COMMENT 'Reminder Code',
  `DESCRIPTION` varchar(60) DEFAULT NULL COMMENT 'Description',
  `USERID` int(11) DEFAULT NULL COMMENT 'Resposible Person',
  `NOOFTICKET` int(11) DEFAULT NULL COMMENT 'No of Ticket',
  `STATUS` varchar(1) DEFAULT NULL COMMENT 'Status',
  `TOTCAP` decimal(15,2) DEFAULT NULL COMMENT 'Total Capital',
  `TOTINT` decimal(15,2) DEFAULT NULL COMMENT 'Total Interest',
  `TOTEXPNS` decimal(15,2) DEFAULT NULL COMMENT 'Total Expences',
  `TOTTAX` decimal(15,2) DEFAULT NULL COMMENT 'Tax',
  `PROFIT` decimal(15,2) DEFAULT NULL COMMENT 'Profit',
  `TOTEARNED` decimal(15,2) DEFAULT NULL COMMENT 'Total Earned',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`AUCTIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblauctionmaster`
--

LOCK TABLES `tblauctionmaster` WRITE;
/*!40000 ALTER TABLE `tblauctionmaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblauctionmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcartage`
--

DROP TABLE IF EXISTS `tblcartage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcartage` (
  `CARTAGEID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Cartage Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `MCARTAGEID` int(11) DEFAULT NULL COMMENT 'Master Cartage Id',
  `CODE` varchar(3) DEFAULT NULL COMMENT 'Account Code',
  `DESCRIPTION` varchar(60) DEFAULT NULL COMMENT 'Description',
  `MAKVALUE` decimal(10,2) DEFAULT NULL,
  `DISBSEPECR` decimal(10,2) DEFAULT NULL,
  `DISBSEVALE` decimal(10,2) DEFAULT NULL,
  `DISPLAYVALUE` varchar(60) DEFAULT NULL COMMENT 'Display Value',
  `ISACTIVE` varchar(1) DEFAULT NULL COMMENT 'Is Active',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`CARTAGEID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcartage`
--

LOCK TABLES `tblcartage` WRITE;
/*!40000 ALTER TABLE `tblcartage` DISABLE KEYS */;
INSERT INTO `tblcartage` VALUES (1,0,1,1,'20K','20 Karatage',40000.00,0.00,40000.00,'40000.00','A','2012-09-07 00:00:00',NULL,13,'1',0,9),(2,0,1,1,'22K','22 Karatage',46000.00,0.00,46000.00,'46000.00','A','2012-09-07 00:00:00',NULL,13,'1',0,8),(3,0,1,1,'24K','24 Karatage',52000.00,0.00,52000.00,'52000.00','A','2012-09-07 00:00:00',NULL,13,'1',0,8),(15,0,1,5,'20K','20 Karatage',40000.00,0.00,40000.00,'40000.00','A','2012-05-14 00:00:00',NULL,13,'1',0,1),(16,0,1,5,'22K','22 Karatage',46000.00,0.00,46000.00,'46000.00','A','2012-05-14 00:00:00',NULL,13,'1',0,1),(17,0,1,5,'24K','24 Karatage',52000.00,0.00,52000.00,'52000.00','A','2012-05-14 00:00:00',NULL,13,'1',0,1),(18,0,1,1,'21K','21 Karatage',42000.00,0.00,42000.00,'42000.00','A','2012-09-07 00:00:00',NULL,13,'1',0,2),(19,0,1,5,'21K','21 Karatage',42000.00,0.00,42000.00,'42000.00','A','2012-05-14 00:00:00',NULL,13,'1',0,1),(20,0,1,6,'22K','22 Karatage',76000.00,0.00,76000.00,'76000.00','A','2012-08-20 00:00:00',NULL,13,'1',0,1),(21,0,1,6,'18K','18K Karatage',76000.00,0.00,76000.00,'76000.00','A','2012-08-20 00:00:00',NULL,13,'1',0,0);
/*!40000 ALTER TABLE `tblcartage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblauctionlocation`
--

DROP TABLE IF EXISTS `tblauctionlocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblauctionlocation` (
  `ALOCATIONID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ALOCATIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblauctionlocation`
--

LOCK TABLES `tblauctionlocation` WRITE;
/*!40000 ALTER TABLE `tblauctionlocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblauctionlocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblduereceipt`
--

DROP TABLE IF EXISTS `tblduereceipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblduereceipt` (
  `DUERCPID` int(11) NOT NULL AUTO_INCREMENT,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `RCPID` int(11) DEFAULT NULL,
  `PAWNERID` int(11) DEFAULT NULL,
  `SETAMOUNT` decimal(15,2) DEFAULT NULL,
  `SETDATE` datetime DEFAULT NULL,
  `DUETYPEID` int(11) DEFAULT NULL,
  `REFNO` int(11) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `ORIGINALID` int(11) DEFAULT NULL,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`DUERCPID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblduereceipt`
--

LOCK TABLES `tblduereceipt` WRITE;
/*!40000 ALTER TABLE `tblduereceipt` DISABLE KEYS */;
INSERT INTO `tblduereceipt` VALUES (1,1,1,1,10,1,1,1452.04,'2013-06-29 00:00:00',2,0,'2013-06-29 00:00:00',0,NULL,0,1,0),(2,1,1,1,10,1,1,547.96,'2013-06-29 00:00:00',1,0,'2013-06-29 00:00:00',0,NULL,0,1,0),(3,1,1,1,10,2,1,1000.00,'2013-06-29 00:00:00',1,0,'2013-06-29 00:00:00',0,NULL,0,1,0),(4,1,1,1,10,3,1,1000.00,'2013-06-29 00:00:00',1,0,'2013-06-29 00:00:00',0,NULL,0,1,0),(5,1,1,1,10,4,1,1000.00,'2013-06-29 00:00:00',1,0,'2013-06-29 00:00:00',0,NULL,0,1,0),(6,1,1,1,10,5,1,1000.00,'2013-06-29 00:00:00',1,0,'2013-06-29 00:00:00',0,NULL,0,1,0);
/*!40000 ALTER TABLE `tblduereceipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblcartagemarster`
--

DROP TABLE IF EXISTS `tblcartagemarster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblcartagemarster` (
  `MCARTAGEID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MCARTAGEID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblcartagemarster`
--

LOCK TABLES `tblcartagemarster` WRITE;
/*!40000 ALTER TABLE `tblcartagemarster` DISABLE KEYS */;
INSERT INTO `tblcartagemarster` VALUES (1,'001','Rate Of Advance',1,'2011-11-08 00:00:00',NULL,5,0,6,0,NULL),(5,'002','Rate of Advance 2',1,'2011-12-14 00:00:00',NULL,13,0,0,0,NULL),(6,'003','Special Offer',1,'2012-06-05 00:00:00',NULL,13,0,0,0,NULL);
/*!40000 ALTER TABLE `tblcartagemarster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblauctioexpences`
--

DROP TABLE IF EXISTS `tblauctioexpences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblauctioexpences` (
  `AUCTIONDETID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Auction Detail Id',
  `AUCTIONID` int(11) DEFAULT NULL COMMENT 'Auction Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `DUETYPEID` int(11) DEFAULT NULL COMMENT 'Due Type Id',
  `AMOUNT` mediumtext COMMENT 'Amount',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` datetime DEFAULT NULL COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`AUCTIONDETID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblauctioexpences`
--

LOCK TABLES `tblauctioexpences` WRITE;
/*!40000 ALTER TABLE `tblauctioexpences` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblauctioexpences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblprgaccess`
--

DROP TABLE IF EXISTS `tblprgaccess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblprgaccess` (
  `PRGACCESSID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCESS` varchar(10) DEFAULT NULL,
  `PRGID` int(11) DEFAULT NULL,
  `USERGROUPID` int(11) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRGACCESSID`)
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblprgaccess`
--

LOCK TABLES `tblprgaccess` WRITE;
/*!40000 ALTER TABLE `tblprgaccess` DISABLE KEYS */;
INSERT INTO `tblprgaccess` VALUES (937,'1',1,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(938,'1',5,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(939,'1',10,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(940,'1',15,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(941,'1',20,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(942,'1',21,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(943,'1',25,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(944,'1',30,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(945,'1',35,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(946,'1',40,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(947,'1',45,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(948,'1:2:3',1000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(949,'1:2:3',1001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(950,'1:2:3',1002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(951,'1:2:3',1003,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(952,'1:2:3',1004,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(953,'1:2:3',1005,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(954,'1:2:3',1006,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(955,'1:2:3',1007,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(956,'1:2:3',1010,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(957,'1:2:3',1011,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(958,'1:2',1012,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(959,'1:2:3',1013,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(960,'1:2:3',1014,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(961,'1:2:3',1015,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(962,'1:2:3',1016,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(963,'1:2',1017,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(964,'1:2',1018,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(965,'1:2',1019,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(966,'1:2',2000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(967,'1',2001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(968,'1',2002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(969,'1',3001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(970,'1',3002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(971,'2',3003,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(972,'1',4000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(973,'1',4001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(974,'1',4002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(975,'1',4003,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(976,'1',4005,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(977,'1',5000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(978,'1',5001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(979,'1:5',5500,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(980,'1:2:3:5',5600,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(981,'1:2:3',5999,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(982,'1',6000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(983,'1',6001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(984,'1',7000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(985,'1',7001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(986,'1',7002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(987,'1',7003,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(988,'1',7004,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(989,'1',8000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(990,'1',9000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(991,'1',9001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(992,'1',9002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(993,'1',9003,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(994,'1',9004,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(995,'1',9005,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(996,'1',9006,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(997,'1',9007,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(998,'1',9008,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(999,'1',9009,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1000,'1',9010,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1001,'1',10000,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1002,'1:2',10001,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1003,'1:2',10002,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1004,'1',10003,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1005,'1',10004,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1006,'1',10005,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1007,'1',10006,1,'2013-05-25 00:00:00',NULL,1,2,0,0),(1008,'5',3000,1,'2013-05-25 00:00:00',NULL,1,1,0,0);
/*!40000 ALTER TABLE `tblprgaccess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_gen`
--

DROP TABLE IF EXISTS `id_gen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen` (
  `ID_NAME` varchar(50) NOT NULL,
  `ID_VALUE` int(11) DEFAULT '0',
  PRIMARY KEY (`ID_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_gen`
--

LOCK TABLES `id_gen` WRITE;
/*!40000 ALTER TABLE `id_gen` DISABLE KEYS */;
INSERT INTO `id_gen` VALUES ('AccessBranch',540),('Article',52),('ArticleModel',23),('AuctionLocation',1),('BaseSystem',77),('Branch',1),('CancelDueReceipt',0),('Cartage',22),('CartageMarster',7),('Company',2),('DailyInterest',201),('DueFrom',21),('DueReceipt',7),('DueTo',11),('DueType',7),('GlAccount',1),('InterestRates',112),('InterestSlab',216),('Ledger',21),('Location',2),('Mappawner',5),('Officer',1),('Parameter',26),('ParameterValue',8),('pawner',1),('pawnerType',1),('Product',2),('ProgramAccess',1009),('Receipt',6),('Reminder',0),('ReminderPara',6),('Schemes',2),('SystemDate',1),('tblbranchfunddeposit',0),('tblbranchfundrequest',0),('Ticket',11),('TicketArticle',11),('TicketRedeem',0),('TicketStatus',6),('UserGroup',10),('UserLog',96);
/*!40000 ALTER TABLE `id_gen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblmappawner`
--

DROP TABLE IF EXISTS `tblmappawner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblmappawner` (
  `MAPPAWID` int(11) NOT NULL,
  `PWNID` int(11) DEFAULT NULL,
  `PAWTYPEID` int(11) DEFAULT NULL,
  `CODE` char(3) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `TEPAWID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MAPPAWID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblmappawner`
--

LOCK TABLES `tblmappawner` WRITE;
/*!40000 ALTER TABLE `tblmappawner` DISABLE KEYS */;
INSERT INTO `tblmappawner` VALUES (2,1,1,NULL,0,'2010-11-22 12:28:48',NULL,1,0,0,1,1,2),(3,1,1,NULL,1,'2012-11-08 00:00:00',NULL,1,0,0,1,1,1),(4,1,2,NULL,1,'2012-11-08 00:00:00',NULL,1,0,0,1,1,1);
/*!40000 ALTER TABLE `tblmappawner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblscheme`
--

DROP TABLE IF EXISTS `tblscheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblscheme` (
  `COMID` int(11) DEFAULT NULL,
  `SCHEMEID` int(11) NOT NULL AUTO_INCREMENT,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `INTERESTID` int(11) DEFAULT NULL,
  `INTERESTCODE` char(3) DEFAULT NULL,
  `MCARTAGEID` int(11) DEFAULT NULL,
  `CODE` varchar(3) DEFAULT NULL,
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  `ISACTIVE` smallint(5) unsigned DEFAULT NULL,
  `PERIOD` smallint(6) DEFAULT NULL,
  `RECSTATUS` smallint(5) unsigned DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SCHEMEID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblscheme`
--

LOCK TABLES `tblscheme` WRITE;
/*!40000 ALTER TABLE `tblscheme` DISABLE KEYS */;
INSERT INTO `tblscheme` VALUES (1,1,1,1,111,'001',6,'001','Scheme one',1,12,1,'2012-11-08 00:00:00',NULL,1,0,0);
/*!40000 ALTER TABLE `tblscheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbldailyinterest`
--

DROP TABLE IF EXISTS `tbldailyinterest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbldailyinterest` (
  `INTRESTID` int(11) NOT NULL AUTO_INCREMENT,
  `BRANCHID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `TKTID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `INTRATE` mediumtext,
  `INTAMOUNT` mediumtext,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`INTRESTID`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbldailyinterest`
--

LOCK TABLES `tbldailyinterest` WRITE;
/*!40000 ALTER TABLE `tbldailyinterest` DISABLE KEYS */;
INSERT INTO `tbldailyinterest` VALUES (40,1,1,7,'2013-05-26','0','0',0,0,'2013-05-25 00:00:00',NULL,1,1),(41,1,1,8,'2013-05-26','0','0',0,0,'2013-05-25 00:00:00',NULL,1,1),(42,1,1,9,'2013-05-26','0','0',0,0,'2013-05-25 00:00:00',NULL,1,1),(43,1,1,10,'2013-05-26','0','273.97',0,0,'2013-05-26 00:00:00',NULL,1,1),(44,1,1,7,'2013-05-27','0','0',0,0,'2013-05-26 00:00:00',NULL,1,1),(45,1,1,8,'2013-05-27','0','0',0,0,'2013-05-26 00:00:00',NULL,1,1),(46,1,1,9,'2013-05-27','0','0',0,0,'2013-05-26 00:00:00',NULL,1,1),(47,1,1,10,'2013-05-27','0','0',0,0,'2013-05-26 00:00:00',NULL,1,1),(48,1,1,7,'2013-05-28','0','0',0,0,'2013-05-27 00:00:00',NULL,1,1),(49,1,1,8,'2013-05-28','0','0',0,0,'2013-05-27 00:00:00',NULL,1,1),(50,1,1,9,'2013-05-28','0','0',0,0,'2013-05-27 00:00:00',NULL,1,1),(51,1,1,10,'2013-05-28','0','0',0,0,'2013-05-27 00:00:00',NULL,1,1),(52,1,1,7,'2013-05-29','0','0',0,0,'2013-05-28 00:00:00',NULL,1,1),(53,1,1,8,'2013-05-29','0','0',0,0,'2013-05-28 00:00:00',NULL,1,1),(54,1,1,9,'2013-05-29','0','0',0,0,'2013-05-28 00:00:00',NULL,1,1),(55,1,1,10,'2013-05-29','0','0',0,0,'2013-05-28 00:00:00',NULL,1,1),(56,1,1,7,'2013-05-30','0','0',0,0,'2013-05-29 00:00:00',NULL,1,1),(57,1,1,8,'2013-05-30','0','0',0,0,'2013-05-29 00:00:00',NULL,1,1),(58,1,1,9,'2013-05-30','0','0',0,0,'2013-05-29 00:00:00',NULL,1,1),(59,1,1,10,'2013-05-30','0','0',0,0,'2013-05-29 00:00:00',NULL,1,1),(60,1,1,7,'2013-05-31','0','0',0,0,'2013-05-30 00:00:00',NULL,1,1),(61,1,1,8,'2013-05-31','0','0',0,0,'2013-05-30 00:00:00',NULL,1,1),(62,1,1,9,'2013-05-31','0','0',0,0,'2013-05-30 00:00:00',NULL,1,1),(63,1,1,10,'2013-05-31','0','0',0,0,'2013-05-30 00:00:00',NULL,1,1),(64,1,1,7,'2013-06-01','0','0',0,0,'2013-05-31 00:00:00',NULL,1,1),(65,1,1,8,'2013-06-01','0','0',0,0,'2013-05-31 00:00:00',NULL,1,1),(66,1,1,9,'2013-06-01','0','0',0,0,'2013-05-31 00:00:00',NULL,1,1),(67,1,1,10,'2013-06-01','0','0',0,0,'2013-05-31 00:00:00',NULL,1,1),(68,1,1,7,'2013-06-02','0','0',0,0,'2013-06-01 00:00:00',NULL,1,1),(69,1,1,8,'2013-06-02','0','0',0,0,'2013-06-01 00:00:00',NULL,1,1),(70,1,1,9,'2013-06-02','0','0',0,0,'2013-06-01 00:00:00',NULL,1,1),(71,1,1,10,'2013-06-02','0','0',0,0,'2013-06-01 00:00:00',NULL,1,1),(72,1,1,7,'2013-06-03','0','0',0,0,'2013-06-02 00:00:00',NULL,1,1),(73,1,1,8,'2013-06-03','0','0',0,0,'2013-06-02 00:00:00',NULL,1,1),(74,1,1,9,'2013-06-03','0','0',0,0,'2013-06-02 00:00:00',NULL,1,1),(75,1,1,10,'2013-06-03','0','0',0,0,'2013-06-02 00:00:00',NULL,1,1),(76,1,1,7,'2013-06-04','0','0',0,0,'2013-06-03 00:00:00',NULL,1,1),(77,1,1,8,'2013-06-04','0','0',0,0,'2013-06-03 00:00:00',NULL,1,1),(78,1,1,9,'2013-06-04','0','0',0,0,'2013-06-03 00:00:00',NULL,1,1),(79,1,1,10,'2013-06-04','0','0',0,0,'2013-06-03 00:00:00',NULL,1,1),(80,1,1,7,'2013-06-05','0','0',0,0,'2013-06-04 00:00:00',NULL,1,1),(81,1,1,8,'2013-06-05','0','0',0,0,'2013-06-04 00:00:00',NULL,1,1),(82,1,1,9,'2013-06-05','0','0',0,0,'2013-06-04 00:00:00',NULL,1,1),(83,1,1,10,'2013-06-05','0','54.79',0,0,'2013-06-04 00:00:00',NULL,1,1),(99,1,1,7,'2013-06-06','0','0',0,0,'2013-06-05 00:00:00',NULL,1,1),(100,1,1,8,'2013-06-06','0','0',0,0,'2013-06-05 00:00:00',NULL,1,1),(101,1,1,9,'2013-06-06','0','0',0,0,'2013-06-05 00:00:00',NULL,1,1),(102,1,1,10,'2013-06-06','0','1232.88',0,0,'2013-06-05 00:00:00',NULL,1,1),(103,1,1,7,'2013-06-07','0','0',0,0,'2013-06-06 00:00:00',NULL,1,1),(104,1,1,8,'2013-06-07','0','0',0,0,'2013-06-06 00:00:00',NULL,1,1),(105,1,1,9,'2013-06-07','0','0',0,0,'2013-06-06 00:00:00',NULL,1,1),(106,1,1,10,'2013-06-07','0','0',0,0,'2013-06-06 00:00:00',NULL,1,1),(107,1,1,7,'2013-06-08','0','0',0,0,'2013-06-07 00:00:00',NULL,1,1),(108,1,1,8,'2013-06-08','0','0',0,0,'2013-06-07 00:00:00',NULL,1,1),(109,1,1,9,'2013-06-08','0','0',0,0,'2013-06-07 00:00:00',NULL,1,1),(110,1,1,10,'2013-06-08','0','0',0,0,'2013-06-07 00:00:00',NULL,1,1),(111,1,1,7,'2013-06-09','0','0',0,0,'2013-06-08 00:00:00',NULL,1,1),(112,1,1,8,'2013-06-09','0','0',0,0,'2013-06-08 00:00:00',NULL,1,1),(113,1,1,9,'2013-06-09','0','0',0,0,'2013-06-08 00:00:00',NULL,1,1),(114,1,1,10,'2013-06-09','0','0',0,0,'2013-06-08 00:00:00',NULL,1,1),(115,1,1,7,'2013-06-10','0','0',0,0,'2013-06-09 00:00:00',NULL,1,1),(116,1,1,8,'2013-06-10','0','0',0,0,'2013-06-09 00:00:00',NULL,1,1),(117,1,1,9,'2013-06-10','0','0',0,0,'2013-06-09 00:00:00',NULL,1,1),(118,1,1,10,'2013-06-10','0','0',0,0,'2013-06-09 00:00:00',NULL,1,1),(119,1,1,7,'2013-06-11','0','0',0,0,'2013-06-10 00:00:00',NULL,1,1),(120,1,1,8,'2013-06-11','0','0',0,0,'2013-06-10 00:00:00',NULL,1,1),(121,1,1,9,'2013-06-11','0','0',0,0,'2013-06-10 00:00:00',NULL,1,1),(122,1,1,10,'2013-06-11','0','0',0,0,'2013-06-10 00:00:00',NULL,1,1),(123,1,1,7,'2013-06-12','0','0',0,0,'2013-06-11 00:00:00',NULL,1,1),(124,1,1,8,'2013-06-12','0','0',0,0,'2013-06-11 00:00:00',NULL,1,1),(125,1,1,9,'2013-06-12','0','0',0,0,'2013-06-11 00:00:00',NULL,1,1),(126,1,1,10,'2013-06-12','0','0',0,0,'2013-06-11 00:00:00',NULL,1,1),(127,1,1,7,'2013-06-13','0','0',0,0,'2013-06-12 00:00:00',NULL,1,1),(128,1,1,8,'2013-06-13','0','0',0,0,'2013-06-12 00:00:00',NULL,1,1),(129,1,1,9,'2013-06-13','0','0',0,0,'2013-06-12 00:00:00',NULL,1,1),(130,1,1,10,'2013-06-13','0','0',0,0,'2013-06-12 00:00:00',NULL,1,1),(131,1,1,7,'2013-06-14','0','0',0,0,'2013-06-13 00:00:00',NULL,1,1),(132,1,1,8,'2013-06-14','0','0',0,0,'2013-06-13 00:00:00',NULL,1,1),(133,1,1,9,'2013-06-14','0','0',0,0,'2013-06-13 00:00:00',NULL,1,1),(134,1,1,10,'2013-06-14','0','0',0,0,'2013-06-13 00:00:00',NULL,1,1),(135,1,1,7,'2013-06-15','0','0',0,0,'2013-06-14 00:00:00',NULL,1,1),(136,1,1,8,'2013-06-15','0','0',0,0,'2013-06-14 00:00:00',NULL,1,1),(137,1,1,9,'2013-06-15','0','0',0,0,'2013-06-14 00:00:00',NULL,1,1),(138,1,1,10,'2013-06-15','0','0',0,0,'2013-06-14 00:00:00',NULL,1,1),(139,1,1,7,'2013-06-16','0','0',0,0,'2013-06-15 00:00:00',NULL,1,1),(140,1,1,8,'2013-06-16','0','0',0,0,'2013-06-15 00:00:00',NULL,1,1),(141,1,1,9,'2013-06-16','0','0',0,0,'2013-06-15 00:00:00',NULL,1,1),(142,1,1,10,'2013-06-16','0','0',0,0,'2013-06-15 00:00:00',NULL,1,1),(143,1,1,7,'2013-06-17','0','0',0,0,'2013-06-16 00:00:00',NULL,1,1),(144,1,1,8,'2013-06-17','0','0',0,0,'2013-06-16 00:00:00',NULL,1,1),(145,1,1,9,'2013-06-17','0','0',0,0,'2013-06-16 00:00:00',NULL,1,1),(146,1,1,10,'2013-06-17','0','0',0,0,'2013-06-16 00:00:00',NULL,1,1),(147,1,1,7,'2013-06-18','0','0',0,0,'2013-06-17 00:00:00',NULL,1,1),(148,1,1,8,'2013-06-18','0','0',0,0,'2013-06-17 00:00:00',NULL,1,1),(149,1,1,9,'2013-06-18','0','0',0,0,'2013-06-17 00:00:00',NULL,1,1),(150,1,1,10,'2013-06-18','0','0',0,0,'2013-06-17 00:00:00',NULL,1,1),(151,1,1,7,'2013-06-19','0','0',0,0,'2013-06-18 00:00:00',NULL,1,1),(152,1,1,8,'2013-06-19','0','0',0,0,'2013-06-18 00:00:00',NULL,1,1),(153,1,1,9,'2013-06-19','0','0',0,0,'2013-06-18 00:00:00',NULL,1,1),(154,1,1,10,'2013-06-19','0','0',0,0,'2013-06-18 00:00:00',NULL,1,1),(155,1,1,7,'2013-06-20','0','0',0,0,'2013-06-19 00:00:00',NULL,1,1),(156,1,1,8,'2013-06-20','0','0',0,0,'2013-06-19 00:00:00',NULL,1,1),(157,1,1,9,'2013-06-20','0','0',0,0,'2013-06-19 00:00:00',NULL,1,1),(158,1,1,10,'2013-06-20','0','0',0,0,'2013-06-19 00:00:00',NULL,1,1),(159,1,1,7,'2013-06-21','0','0',0,0,'2013-06-20 00:00:00',NULL,1,1),(160,1,1,8,'2013-06-21','0','0',0,0,'2013-06-20 00:00:00',NULL,1,1),(161,1,1,9,'2013-06-21','0','0',0,0,'2013-06-20 00:00:00',NULL,1,1),(162,1,1,10,'2013-06-21','0','0',0,0,'2013-06-20 00:00:00',NULL,1,1),(163,1,1,7,'2013-06-22','0','0',0,0,'2013-06-21 00:00:00',NULL,1,1),(164,1,1,8,'2013-06-22','0','0',0,0,'2013-06-21 00:00:00',NULL,1,1),(165,1,1,9,'2013-06-22','0','0',0,0,'2013-06-21 00:00:00',NULL,1,1),(166,1,1,10,'2013-06-22','0','0',0,0,'2013-06-21 00:00:00',NULL,1,1),(167,1,1,7,'2013-06-23','0','0',0,0,'2013-06-22 00:00:00',NULL,1,1),(168,1,1,8,'2013-06-23','0','0',0,0,'2013-06-22 00:00:00',NULL,1,1),(169,1,1,9,'2013-06-23','0','0',0,0,'2013-06-22 00:00:00',NULL,1,1),(170,1,1,10,'2013-06-23','0','0',0,0,'2013-06-22 00:00:00',NULL,1,1),(171,1,1,7,'2013-06-24','0','0',0,0,'2013-06-23 00:00:00',NULL,1,1),(172,1,1,8,'2013-06-24','0','0',0,0,'2013-06-23 00:00:00',NULL,1,1),(173,1,1,9,'2013-06-24','0','0',0,0,'2013-06-23 00:00:00',NULL,1,1),(174,1,1,10,'2013-06-24','0','0',0,0,'2013-06-23 00:00:00',NULL,1,1),(175,1,1,7,'2013-06-25','0','0',0,0,'2013-06-24 00:00:00',NULL,1,1),(176,1,1,8,'2013-06-25','0','0',0,0,'2013-06-24 00:00:00',NULL,1,1),(177,1,1,9,'2013-06-25','0','0',0,0,'2013-06-24 00:00:00',NULL,1,1),(178,1,1,10,'2013-06-25','0','0',0,0,'2013-06-24 00:00:00',NULL,1,1),(185,1,1,7,'2013-06-26','0','0',0,0,'2013-06-25 00:00:00',NULL,1,1),(186,1,1,8,'2013-06-26','0','0',0,0,'2013-06-25 00:00:00',NULL,1,1),(187,1,1,9,'2013-06-26','0','0',0,0,'2013-06-25 00:00:00',NULL,1,1),(188,1,1,10,'2013-06-26','0','54.79',0,0,'2013-06-25 00:00:00',NULL,1,1),(189,1,1,7,'2013-06-27','0','0',0,0,'2013-06-26 00:00:00',NULL,1,1),(190,1,1,8,'2013-06-27','0','0',0,0,'2013-06-26 00:00:00',NULL,1,1),(191,1,1,9,'2013-06-27','0','0',0,0,'2013-06-26 00:00:00',NULL,1,1),(192,1,1,10,'2013-06-27','0','54.79',0,0,'2013-06-26 00:00:00',NULL,1,1),(193,1,1,7,'2013-06-28','0','0',0,0,'2013-06-27 00:00:00',NULL,1,1),(194,1,1,8,'2013-06-28','0','0',0,0,'2013-06-27 00:00:00',NULL,1,1),(195,1,1,9,'2013-06-28','0','0',0,0,'2013-06-27 00:00:00',NULL,1,1),(196,1,1,10,'2013-06-28','0','54.79',0,0,'2013-06-27 00:00:00',NULL,1,1),(197,1,1,7,'2013-06-29','0','0',0,0,'2013-06-28 00:00:00',NULL,1,1),(198,1,1,8,'2013-06-29','0','0',0,0,'2013-06-28 00:00:00',NULL,1,1),(199,1,1,9,'2013-06-29','0','0',0,0,'2013-06-28 00:00:00',NULL,1,1),(200,1,1,10,'2013-06-29','0','54.79',0,0,'2013-06-28 00:00:00',NULL,1,1);
/*!40000 ALTER TABLE `tbldailyinterest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbleventlog`
--

DROP TABLE IF EXISTS `tbleventlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbleventlog` (
  `EVENTLOGID` int(11) NOT NULL,
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `TRANNO` varchar(40) DEFAULT NULL COMMENT 'Transaction No',
  `PRGNAME` varchar(50) DEFAULT NULL COMMENT 'Table Name',
  `EVENTID` int(11) DEFAULT NULL COMMENT 'Table Name',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Transaction  Date',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Id',
  `RECSTATUS` int(11) DEFAULT NULL COMMENT 'Record Status',
  `VERSIONID` int(11) DEFAULT NULL,
  PRIMARY KEY (`EVENTLOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbleventlog`
--

LOCK TABLES `tbleventlog` WRITE;
/*!40000 ALTER TABLE `tbleventlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbleventlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload`
--

DROP TABLE IF EXISTS `upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upload` (
  `amount` decimal(10,2) NOT NULL,
  `ticketid` int(11) NOT NULL,
  PRIMARY KEY (`ticketid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='upload';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload`
--

LOCK TABLES `upload` WRITE;
/*!40000 ALTER TABLE `upload` DISABLE KEYS */;
INSERT INTO `upload` VALUES (-93.54,287),(-98.56,289),(-98.56,290),(-113.60,294),(-19.84,295),(-96.05,297),(-56.80,298),(-15.35,307),(-135.72,315),(-19.00,320),(-184.50,333),(-226.80,339),(-77.52,349),(-172.50,353),(-16.41,355),(-472.62,364),(-16.72,367),(-15.73,371),(-52.38,373),(-82.15,378),(-125.30,385),(-12.20,387),(-193.15,391),(-18.88,397),(-80.07,398),(-31.06,400),(-23.95,406),(-96.45,408),(-12.20,410),(-12.20,417),(-96.45,419),(-34.40,425),(-36.49,426),(-176.77,431),(-18.88,432),(-23.95,452),(-102.69,460),(-92.54,465),(-12.20,466),(-56.54,469),(-20.60,470),(-108.92,472),(-34.78,475),(-22.08,477),(-12.70,478),(-43.95,481),(-39.70,484),(-19.48,493),(-108.58,494),(-227.81,497),(-18.01,500),(-13.00,503),(-48.39,506),(-38.36,512),(-13.20,517),(-36.97,522),(-28.24,524),(-21.04,525),(-53.13,531),(-51.34,533),(-39.53,540),(-72.90,543),(-238.72,548),(-65.20,551),(-10.24,553),(-15.40,555),(-96.77,556),(-11.52,557),(-66.10,566),(-295.96,569),(-78.80,575),(-94.72,578),(-48.93,580),(-69.48,586),(-77.04,587),(-192.32,588),(-13.36,595),(-47.36,596),(-29.67,597),(-44.51,602),(-266.22,607),(-53.35,612),(-28.00,617),(-53.35,621),(-44.51,627),(-78.96,649),(-11.17,652),(-298.75,657),(-39.16,658),(-155.36,667),(-51.79,669),(-33.45,671),(-318.01,672),(-147.81,683),(-185.04,684),(-28.75,685),(-32.66,688),(-39.34,694),(-24.62,695),(-16.87,699),(-122.45,703),(-109.56,704),(-108.40,705),(-226.98,708),(-90.74,709),(-18.34,711),(-46.30,713),(-76.57,716),(-38.78,718),(-25.27,720),(-156.93,722),(-46.32,729),(-18.00,732),(-17.85,735),(-16.39,736),(-17.27,737),(-17.27,738),(-59.12,742),(-39.10,744),(-10.24,745),(-37.46,746),(-23.71,747),(-22.24,748),(-36.00,749),(-98.49,750),(-39.34,751),(-66.44,754),(-18.15,755),(-24.59,760),(-19.32,761),(-44.49,764),(-206.63,773),(-17.27,775),(-49.25,781),(-26.49,782),(-44.67,783),(-16.39,785),(-18.00,787),(-10.24,789),(-11.86,790);
/*!40000 ALTER TABLE `upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbllocation`
--

DROP TABLE IF EXISTS `tbllocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbllocation` (
  `LOCATIONID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`LOCATIONID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbllocation`
--

LOCK TABLES `tbllocation` WRITE;
/*!40000 ALTER TABLE `tbllocation` DISABLE KEYS */;
INSERT INTO `tbllocation` VALUES (1,'001','HEADOFFICE',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1);
/*!40000 ALTER TABLE `tbllocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblbranch`
--

DROP TABLE IF EXISTS `tblbranch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblbranch` (
  `BRANCHID` int(11) NOT NULL AUTO_INCREMENT,
  `COMID` int(11) DEFAULT NULL,
  `CODE` char(3) DEFAULT NULL,
  `ADDLINE1` varchar(40) DEFAULT NULL,
  `ADDLINE2` varchar(40) DEFAULT NULL,
  `ADDLINE3` varchar(40) DEFAULT NULL,
  `ADDLINE4` varchar(40) DEFAULT NULL,
  `TPNO` varchar(15) DEFAULT NULL,
  `FAXNO` varchar(15) DEFAULT NULL,
  `TAXNO` varchar(20) DEFAULT NULL,
  `ISDEFAULT` smallint(6) DEFAULT NULL,
  `RCPACC` varchar(20) DEFAULT NULL,
  `PMTACC` varchar(20) DEFAULT NULL,
  `DATEINSTALL` datetime DEFAULT NULL,
  `FUNDAVAILABLE` decimal(10,0) DEFAULT NULL,
  `FUNDLIMIT` decimal(10,0) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `SYSDATE` int(11) DEFAULT NULL,
  `BRANCHNAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BRANCHID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbranch`
--

LOCK TABLES `tblbranch` WRITE;
/*!40000 ALTER TABLE `tblbranch` DISABLE KEYS */;
INSERT INTO `tblbranch` VALUES (1,1,'HED','No -21, ','Nawam Mawatha','','Colombo 02','0112300191','0112300190','134000228',1,'012','02356','2009-10-08 00:00:00',258009,1000000,1,'2012-10-26 00:00:00',NULL,13,0,672,1,'HEAD OFFICE','');
/*!40000 ALTER TABLE `tblbranch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblreminders`
--

DROP TABLE IF EXISTS `tblreminders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblreminders` (
  `REMINDERID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Reminder Id',
  `REMPARAID` int(11) NOT NULL COMMENT 'Reminder Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `SCHEMEID` int(11) DEFAULT NULL COMMENT 'Scheme Id',
  `REMPARACODE` varchar(4) DEFAULT NULL COMMENT 'Reminder Para Code',
  `ISPRINTED` int(11) DEFAULT NULL COMMENT 'Is Printed',
  `TICKETID` int(11) DEFAULT NULL COMMENT 'Ticket Id',
  `DATEGENERATED` datetime NOT NULL COMMENT 'Date Generated',
  `DATEPRINTED` datetime DEFAULT NULL COMMENT 'Date Printed',
  `CAPOUT` decimal(10,0) DEFAULT NULL COMMENT 'Capital Outstanding',
  `INTOUT` decimal(10,0) DEFAULT NULL COMMENT 'Interest Outstanding',
  `OTHOUT` decimal(10,0) DEFAULT NULL COMMENT 'Other Outstanding',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`REMINDERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblreminders`
--

LOCK TABLES `tblreminders` WRITE;
/*!40000 ALTER TABLE `tblreminders` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblreminders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblsystemdate`
--

DROP TABLE IF EXISTS `tblsystemdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblsystemdate` (
  `SYSDATEID` int(11) NOT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRVDATE` datetime DEFAULT NULL,
  `CURDATE` datetime DEFAULT NULL,
  `NXTDATE` datetime DEFAULT NULL,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  PRIMARY KEY (`SYSDATEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblsystemdate`
--

LOCK TABLES `tblsystemdate` WRITE;
/*!40000 ALTER TABLE `tblsystemdate` DISABLE KEYS */;
INSERT INTO `tblsystemdate` VALUES (1,1,1,'2013-06-28 00:00:00','2013-06-29 00:00:00','2013-06-30 00:00:00',1,1200);
/*!40000 ALTER TABLE `tblsystemdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblinterestrates`
--

DROP TABLE IF EXISTS `tblinterestrates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblinterestrates` (
  `COMID` int(11) DEFAULT NULL,
  `INTERESTID` int(11) NOT NULL AUTO_INCREMENT,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `CODE` varchar(3) DEFAULT NULL,
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  `ISACTIVE` smallint(5) unsigned DEFAULT NULL,
  `RECSTATUS` smallint(5) unsigned DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INTERESTID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblinterestrates`
--

LOCK TABLES `tblinterestrates` WRITE;
/*!40000 ALTER TABLE `tblinterestrates` DISABLE KEYS */;
INSERT INTO `tblinterestrates` VALUES (1,111,1,1,'001','INTEREST ONE',1,1,'2012-11-08 00:00:00',NULL,1,0,0);
/*!40000 ALTER TABLE `tblinterestrates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblticket`
--

DROP TABLE IF EXISTS `tblticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblticket` (
  `TICKETID` int(11) NOT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TKTNO` varchar(14) DEFAULT NULL,
  `SERNO` varchar(7) DEFAULT NULL,
  `SCHEMEID` int(11) DEFAULT NULL,
  `CONDATE` datetime DEFAULT NULL,
  `EXPRDATE` datetime DEFAULT NULL,
  `CLODATE` datetime DEFAULT NULL,
  `INTSLABID` int(11) DEFAULT NULL,
  `PERIOD` int(11) DEFAULT NULL,
  `LOCATIONID` int(11) DEFAULT NULL,
  `NOOFITEM` int(11) DEFAULT NULL,
  `NETWEIGHT` decimal(15,2) DEFAULT NULL,
  `GROSSWEIGHT` decimal(15,2) DEFAULT NULL,
  `ASSVALUE` decimal(15,2) DEFAULT NULL,
  `MKTVALUE` decimal(15,2) DEFAULT NULL,
  `pawnADV` decimal(15,2) DEFAULT NULL,
  `SYSRASDVALUE` decimal(15,2) DEFAULT NULL,
  `REMARKS` varchar(100) DEFAULT NULL,
  `TOTINT` decimal(15,2) DEFAULT NULL,
  `TOTCAP` decimal(15,2) DEFAULT NULL,
  `TOTINTPAID` decimal(15,2) DEFAULT NULL,
  `TOTCAPPAID` decimal(15,2) DEFAULT NULL,
  `STSID` int(11) DEFAULT NULL,
  `ISAUCTIONED` smallint(6) DEFAULT NULL,
  `TAXAMT1` decimal(15,2) DEFAULT NULL,
  `TAXAMT2` decimal(15,2) DEFAULT NULL,
  `USRID` int(11) DEFAULT NULL,
  `RTKTNO` varchar(14) DEFAULT NULL,
  `PWNID` int(11) DEFAULT NULL,
  `NOOFPRINT` smallint(6) DEFAULT NULL,
  `INTMETHOD` smallint(6) DEFAULT NULL,
  `ISSCHEDULE` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `APPROVEBY` int(11) DEFAULT '0',
  `MINDAYS` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`TICKETID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblticket`
--

LOCK TABLES `tblticket` WRITE;
/*!40000 ALTER TABLE `tblticket` DISABLE KEYS */;
INSERT INTO `tblticket` VALUES (7,1,1,1,'HEDPW13000001','000001',1,'2013-05-25 00:00:00','2014-05-25 00:00:00',NULL,111,12,0,1,12.00,12.00,100000.00,114000.00,100000.00,114000.00,'',19178.08,100000.00,0.00,0.00,1,0,0.00,0.00,1,'0',1,0,0,0,'2013-05-25 00:00:00',1,1,0,0,0,10),(8,1,1,1,'HEDPW13000002','000002',1,'2013-06-03 00:00:00','2014-05-25 00:00:00',NULL,111,12,0,1,12.00,12.00,100000.00,114000.00,100000.00,114000.00,'',19178.08,100000.00,0.00,0.00,1,0,0.00,0.00,1,'0',1,0,0,0,'2013-06-03 00:00:00',1,1,0,0,0,10),(9,1,1,1,'HEDPW13000003','000003',1,'2013-06-03 00:00:00','2014-05-25 00:00:00',NULL,111,12,0,1,12.00,12.00,100000.00,114000.00,100000.00,114000.00,'',19178.08,100000.00,0.00,0.00,1,0,0.00,0.00,1,'0',1,0,0,0,'2013-06-03 00:00:00',1,1,0,0,0,10),(10,1,1,1,'HEDPW13000004','000004',1,'2013-05-26 00:00:00','2014-05-26 00:00:00',NULL,111,12,0,1,12.00,12.00,100000.00,114000.00,100000.00,114000.00,'Test printing abcdefghijklmnoqrstuvwxyz1234567890',19178.08,100000.00,0.00,0.00,1,0,0.00,0.00,1,'0',1,0,0,0,'2013-05-26 00:00:00',1,1,0,1,1,10);
/*!40000 ALTER TABLE `tblticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblauctionticket`
--

DROP TABLE IF EXISTS `tblauctionticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblauctionticket` (
  `AUCTIONTKTID` int(11) NOT NULL AUTO_INCREMENT,
  `TKTID` int(11) DEFAULT NULL,
  `AUCTIONID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `ASSIGNDATE` date DEFAULT NULL,
  `SOLDDATE` date DEFAULT NULL,
  `MININT` decimal(15,2) DEFAULT NULL,
  `MINCAP` decimal(15,2) DEFAULT NULL,
  `SOLDAMT` decimal(15,2) DEFAULT NULL,
  `AUCTIONEXP` decimal(15,2) DEFAULT NULL,
  `CAPSET` decimal(15,2) DEFAULT NULL,
  `INTSET` decimal(15,2) DEFAULT NULL,
  `ISPAIDTOCLIENT` int(11) DEFAULT NULL,
  `PAIDAMT` decimal(15,2) DEFAULT NULL,
  `EXCESS` decimal(15,2) DEFAULT NULL,
  `ISALLECATED` int(11) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` decimal(15,2) DEFAULT NULL,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`AUCTIONTKTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblauctionticket`
--

LOCK TABLES `tblauctionticket` WRITE;
/*!40000 ALTER TABLE `tblauctionticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblauctionticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblparametervalue`
--

DROP TABLE IF EXISTS `tblparametervalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblparametervalue` (
  `PARAMETERVALUEID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Parameter Value Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `PARAMETERID` int(11) DEFAULT NULL COMMENT 'Parameter Id',
  `PARAVALUE` varchar(20) DEFAULT NULL COMMENT 'Parameter Value',
  `EFFDATE` datetime DEFAULT NULL COMMENT 'Effective Date',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`PARAMETERVALUEID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblparametervalue`
--

LOCK TABLES `tblparametervalue` WRITE;
/*!40000 ALTER TABLE `tblparametervalue` DISABLE KEYS */;
INSERT INTO `tblparametervalue` VALUES (1,1,3,1,1,'10','2013-05-01 00:00:00','2011-03-31 00:00:00',NULL,1,'1',0,2),(2,1,2,1,2,'250000','2009-07-28 00:00:00','2009-07-28 12:28:48',NULL,3,'1',0,2),(3,1,2,1,3,'250000','2009-07-28 00:00:00','2009-07-28 12:28:48',NULL,3,'1',0,1),(5,1,1,1,4,'250','2010-02-24 00:00:00','2010-11-24 12:28:48',NULL,1,'1',0,1),(7,1,1,1,5,'30','2013-05-01 00:00:00','2012-11-08 00:00:00',NULL,1,'1',0,0);
/*!40000 ALTER TABLE `tblparametervalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblproduct`
--

DROP TABLE IF EXISTS `tblproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblproduct` (
  `PRODUCTID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `SCHEMEID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRODUCTID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblproduct`
--

LOCK TABLES `tblproduct` WRITE;
/*!40000 ALTER TABLE `tblproduct` DISABLE KEYS */;
INSERT INTO `tblproduct` VALUES (1,'PW','pawning',1,'2008-12-07 12:28:48',NULL,1,0,0,1,1,1);
/*!40000 ALTER TABLE `tblproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbluserlog`
--

DROP TABLE IF EXISTS `tbluserlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbluserlog` (
  `USERLOGID` int(11) NOT NULL,
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `TRANNO` varchar(40) DEFAULT NULL COMMENT 'Transaction No',
  `STATUS` int(1) DEFAULT NULL COMMENT 'Status',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Transaction  Date',
  `LASTUPUSERID` int(1) DEFAULT NULL COMMENT ' User Id',
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`USERLOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluserlog`
--

LOCK TABLES `tbluserlog` WRITE;
/*!40000 ALTER TABLE `tbluserlog` DISABLE KEYS */;
INSERT INTO `tbluserlog` VALUES (1,1,1,'05094E34A33A7421B8BD61BEC9980BFD',0,NULL,1,0,0,0),(2,1,1,'2E0E732FADDA50E9E0568FA414920E95',1,NULL,1,0,0,0),(3,1,1,'2E0E732FADDA50E9E0568FA414920E95',0,NULL,1,0,0,0),(4,1,1,'CA376FF0C8114957AC282F1EE52EEA62',1,NULL,1,0,0,0),(5,1,1,'CA376FF0C8114957AC282F1EE52EEA62',0,NULL,1,0,0,0),(6,1,1,'68164D5BCBAB0A182B8433D3338D8061',1,NULL,1,0,0,0),(7,1,1,'87B2E8F06E83C6751D68DD4432EFE8E9',1,NULL,1,0,0,0),(8,1,1,'87B2E8F06E83C6751D68DD4432EFE8E9',0,NULL,1,0,0,0),(9,1,1,'6E1B6DFC7024F9BBD5DC732AC0755ABF',1,NULL,1,0,0,0),(10,1,1,'6E1B6DFC7024F9BBD5DC732AC0755ABF',0,NULL,1,0,0,0),(11,1,1,'A4EEEA57B8B521CDBBEE443A5F6456B4',1,NULL,1,0,0,0),(12,1,1,'A4EEEA57B8B521CDBBEE443A5F6456B4',0,NULL,1,0,0,0),(13,1,1,'484E8D7671F99A3C2F634BDE11DB1EC5',1,NULL,1,0,0,0),(14,1,1,'484E8D7671F99A3C2F634BDE11DB1EC5',0,NULL,1,0,0,0),(15,1,1,'AEB3F1D9C039D93EFD6537AC37E7B063',1,NULL,1,0,0,0),(16,1,1,'AEB3F1D9C039D93EFD6537AC37E7B063',0,NULL,1,0,0,0),(17,1,1,'181AD01BBB69CC48D069283B3FA4059C',1,NULL,1,0,0,0),(18,1,1,'181AD01BBB69CC48D069283B3FA4059C',0,NULL,1,0,0,0),(19,1,1,'3976B636CAD2660FA756078135EE0779',1,NULL,1,0,0,0),(20,1,1,'3976B636CAD2660FA756078135EE0779',0,NULL,1,0,0,0),(21,1,1,'E9624D0AA650C033EE171D907EA9C40E',1,NULL,1,0,0,0),(22,1,1,'E9624D0AA650C033EE171D907EA9C40E',0,NULL,1,0,0,0),(23,1,1,'75AE4F0BEE4A272F1FB8EC31A57CCE59',1,NULL,1,0,0,0),(24,1,1,'75AE4F0BEE4A272F1FB8EC31A57CCE59',0,NULL,1,0,0,0),(25,1,1,'61242E005AD15CE4A9F653C84E6DF00A',1,NULL,1,0,0,0),(26,1,1,'450AAB4E93EDBCD227BF6A59B79F7A72',1,NULL,1,0,0,0),(27,1,1,'450AAB4E93EDBCD227BF6A59B79F7A72',0,NULL,1,0,0,0),(28,1,1,'51D9017D903D22D5B654B60F3FF1CAB5',1,NULL,1,0,0,0),(29,1,1,'94F703387DE93AEF466998F86F525F4F',1,NULL,1,0,0,0),(30,1,1,'D98FB494BC2EF04DD87BF4C5B91DB717',1,NULL,1,0,0,0),(31,1,1,'57D8E7B3AD2905D2C569159C3AD813CB',1,NULL,1,0,0,0),(32,1,1,'92B86552B57B6333458B6A072EAD8B04',1,NULL,1,0,0,0),(33,1,1,'FD845009B382873BBFBEC6E37E6E6D86',1,NULL,1,0,0,0),(34,1,1,'FD845009B382873BBFBEC6E37E6E6D86',0,NULL,1,0,0,0),(35,1,1,'94309BAD8624EDF01ED6128776C901E6',1,NULL,1,0,0,0),(36,1,1,'2C21C7AEDFEE6628CAC9DF79DFD283B8',1,NULL,1,0,0,0),(37,1,1,'575E67E3D81113E7BECFA682495C4491',1,NULL,1,0,0,0),(38,1,1,'575E67E3D81113E7BECFA682495C4491',0,NULL,1,0,0,0),(39,1,1,'5EB10293F0EEFD53CAACA273C73A9497',1,NULL,1,0,0,0),(40,1,1,'5EB10293F0EEFD53CAACA273C73A9497',0,NULL,1,0,0,0),(41,1,1,'83E6DD3D533F1E8DD486FA707C094AD8',1,NULL,1,0,0,0),(42,1,1,'83E6DD3D533F1E8DD486FA707C094AD8',0,NULL,1,0,0,0),(43,1,1,'3DC61DC9FCA2CFAB2D5B2E5DAFCF96C7',1,NULL,1,0,0,0),(44,1,1,'6812B9060B62C9E99C67AD82FA9A1900',1,NULL,1,0,0,0),(45,1,1,'AF5E56A77280A6484C127B08FE023F35',1,NULL,1,0,0,0),(46,1,1,'AF5E56A77280A6484C127B08FE023F35',0,NULL,1,0,0,0),(47,1,1,'FE4C1ABB3C3AE9035F496AA4E5C14045',1,NULL,1,0,0,0),(48,1,1,'FE4C1ABB3C3AE9035F496AA4E5C14045',0,NULL,1,0,0,0),(49,1,1,'F0660325DCBCB3F288987D186F264C3A',1,NULL,1,0,0,0),(50,1,1,'EF39DC63C4130969D6C0FF8AABC619FC',1,NULL,1,0,0,0),(51,1,1,'AE1AF9DD63B315682C741A0FD76EBB7E',1,NULL,1,0,0,0),(52,1,1,'AE1AF9DD63B315682C741A0FD76EBB7E',0,NULL,1,0,0,0),(53,1,1,'D6ED5A69E8341377294B545ECABFF573',1,NULL,1,0,0,0),(54,1,1,'D6ED5A69E8341377294B545ECABFF573',0,NULL,1,0,0,0),(55,1,1,'04FF0642FC6F97D1A2DA01E6C9AB9C9C',1,NULL,1,0,0,0),(56,1,1,'04FF0642FC6F97D1A2DA01E6C9AB9C9C',0,NULL,1,0,0,0),(57,1,1,'D031CC93576590809EC482465AFF15F8',1,NULL,1,0,0,0),(58,1,1,'D031CC93576590809EC482465AFF15F8',0,NULL,1,0,0,0),(59,1,1,'70A5D8C96DF2AE2E9FAF2A1BB287D557',1,NULL,1,0,0,0),(60,1,1,'70A5D8C96DF2AE2E9FAF2A1BB287D557',0,NULL,1,0,0,0),(61,1,1,'1572D42654BE4222D15A46472AA2FC55',1,NULL,1,0,0,0),(62,1,1,'1572D42654BE4222D15A46472AA2FC55',0,NULL,1,0,0,0),(63,1,1,'496BB3553516E5A9296BAD1EF7ECBA53',1,NULL,1,0,0,0),(64,1,1,'496BB3553516E5A9296BAD1EF7ECBA53',0,NULL,1,0,0,0),(65,1,1,'D7AE6CD07EBEEAE1E3D415D43F50520B',1,NULL,1,0,0,0),(66,1,1,'531D8D39C3299B9E4B93C79F2FF2B955',1,NULL,1,0,0,0),(67,1,1,'B7084E45D22459F6B2486BAFEFF6DB9A',1,NULL,1,0,0,0),(68,1,1,'194F6B6C7EED6907E223ACB105ADFB4B',1,NULL,1,0,0,0),(69,1,1,'780FF7E87D216D4670AD85E9014F01C6',1,NULL,1,0,0,0),(70,1,1,'780FF7E87D216D4670AD85E9014F01C6',0,NULL,1,0,0,0),(71,1,1,'968A384EFD4EE48694EF06CD621E554A',1,NULL,1,0,0,0),(72,1,1,'2AB4626836FECBF6D458A62428429575',1,NULL,1,0,0,0),(73,1,1,'2AB4626836FECBF6D458A62428429575',0,NULL,1,0,0,0),(74,1,1,'D59F054060B6A28B5675EBD7FEC072CE',1,NULL,1,0,0,0),(75,1,1,'BCDEEC138C000BF5E62CF8468A43C975',1,NULL,1,0,0,0),(76,1,1,'0D3DDA5576BAF0B6ADE1CF3DC093C5B9',1,NULL,1,0,0,0),(77,1,1,'5D60E808E73A57A28D5F8ACD5960CEF6',1,NULL,1,0,0,0),(78,1,1,'5D60E808E73A57A28D5F8ACD5960CEF6',0,NULL,1,0,0,0),(79,1,1,'046D2A14659FF699DEC5BB0C0BD20CC6',1,NULL,1,0,0,0),(80,1,1,'3343C63650528E4DCDEC8C102EAF1666',1,NULL,1,0,0,0),(81,1,1,'0AB7CEBBD59867CD1327A89A072A0209',1,NULL,1,0,0,0),(82,1,1,'8048CC0CC2779655E25E88A18362F3CB',1,NULL,1,0,0,0),(83,1,1,'4C8A65ED6F2C4465F8E974166B5E51FB',1,NULL,1,0,0,0),(84,1,1,'4C8A65ED6F2C4465F8E974166B5E51FB',0,NULL,1,0,0,0),(85,1,1,'7CE60262DDB6E68D504761AACDCE69AD',1,NULL,1,0,0,0),(86,1,1,'94597A8269B8C1D8495110730B304F2C',1,NULL,1,0,0,0),(87,1,1,'94597A8269B8C1D8495110730B304F2C',0,NULL,1,0,0,0),(88,1,1,'193CD80DF5AD2548216FF814B0EF8BB0',1,NULL,1,0,0,0),(89,1,1,'193CD80DF5AD2548216FF814B0EF8BB0',0,NULL,1,0,0,0),(90,1,1,'FFC689D2557AE0806A77816F73F6D163',1,NULL,1,0,0,0),(91,1,1,'FFC689D2557AE0806A77816F73F6D163',0,NULL,1,0,0,0),(92,1,1,'8490F0FABEACD7A0FA636FAF39A98042',1,NULL,1,0,0,0),(93,1,1,'C7F6A7A45D3857A6A120656FA356EB21',1,NULL,1,0,0,0),(94,1,1,'EB6C57387BD1EDB67B6A59122B29BA00',1,NULL,1,0,0,0),(95,1,1,'EB6C57387BD1EDB67B6A59122B29BA00',0,NULL,1,0,0,0);
/*!40000 ALTER TABLE `tbluserlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblglaccount`
--

DROP TABLE IF EXISTS `tblglaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblglaccount` (
  `ACCOUNTID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Account Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `ACCNO` varchar(10) DEFAULT NULL COMMENT 'Account Number',
  `DRCR` varchar(2) DEFAULT NULL COMMENT 'Standard Type',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  `DESCRIPTION` varchar(60) DEFAULT NULL,
  `CODE` char(3) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblglaccount`
--

LOCK TABLES `tblglaccount` WRITE;
/*!40000 ALTER TABLE `tblglaccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblglaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblledger`
--

DROP TABLE IF EXISTS `tblledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblledger` (
  `LEDGERID` int(11) NOT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `DUETYPEID` int(11) DEFAULT NULL,
  `CRAMOUNT` decimal(15,2) DEFAULT NULL,
  `DRAMOUNT` decimal(15,2) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  `TKTID` int(11) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`LEDGERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblledger`
--

LOCK TABLES `tblledger` WRITE;
/*!40000 ALTER TABLE `tblledger` DISABLE KEYS */;
INSERT INTO `tblledger` VALUES (1,1,3,0.00,100000.00,'2013-05-25 00:00:00',NULL,1,0,1,1,0,1,0,'2013-05-25'),(2,1,3,0.00,200000.00,'2013-06-03 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-03'),(3,1,3,0.00,100000.00,'2013-05-25 00:00:00',NULL,1,0,1,1,0,1,0,'2013-05-25'),(4,1,2,0.00,54.79,'2013-06-03 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-03'),(5,1,3,0.00,200000.00,'2013-06-03 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-03'),(6,1,3,0.00,100000.00,'2013-05-25 00:00:00',NULL,1,0,1,1,0,1,0,'2013-05-25'),(7,0,2,0.00,273.97,'2013-05-26 00:00:00',NULL,1,0,1,1,0,1,0,NULL),(8,1,3,0.00,100000.00,'2013-05-26 00:00:00',NULL,1,0,1,1,0,1,0,'2013-05-26'),(9,1,3,0.00,200000.00,'2013-06-03 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-03'),(10,1,2,0.00,54.79,'2013-06-04 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-04'),(11,1,2,0.00,54.79,'2013-06-25 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-25'),(12,1,2,0.00,54.79,'2013-06-26 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-26'),(13,1,2,0.00,54.79,'2013-06-27 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-27'),(14,1,2,0.00,54.79,'2013-06-28 00:00:00',NULL,1,0,1,1,0,1,0,'2013-06-28'),(15,1,2,0.00,1452.04,'2013-06-29 00:00:00',NULL,1,0,1,0,0,1,0,'2013-06-29'),(16,1,1,0.00,547.96,'2013-06-29 00:00:00',NULL,1,0,1,0,0,1,0,'2013-06-29'),(17,1,1,0.00,1000.00,'2013-06-29 00:00:00',NULL,1,0,1,0,0,1,0,'2013-06-29'),(18,1,1,0.00,1000.00,'2013-06-29 00:00:00',NULL,1,0,1,0,0,1,0,'2013-06-29'),(19,1,1,0.00,1000.00,'2013-06-29 00:00:00',NULL,1,0,1,0,0,1,0,'2013-06-29'),(20,1,1,0.00,1000.00,'2013-06-29 00:00:00',NULL,1,0,1,0,0,1,0,'2013-06-29');
/*!40000 ALTER TABLE `tblledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblduefrom`
--

DROP TABLE IF EXISTS `tblduefrom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblduefrom` (
  `DUEFROMID` int(11) NOT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `REFNO` int(11) DEFAULT NULL,
  `DUETYPEID` int(11) DEFAULT NULL,
  `DUEAMOUNT` decimal(15,2) DEFAULT NULL,
  `PAIDAMOUNT` decimal(15,2) DEFAULT NULL,
  `BALAMOUNT` decimal(15,2) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`DUEFROMID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblduefrom`
--

LOCK TABLES `tblduefrom` WRITE;
/*!40000 ALTER TABLE `tblduefrom` DISABLE KEYS */;
INSERT INTO `tblduefrom` VALUES (19,1,10,NULL,1,100000.00,4547.96,95452.04,'2013-05-26 00:00:00',NULL,1,5,1,1,0,0),(20,1,10,NULL,2,1452.04,1452.04,0.00,'2013-05-26 00:00:00',NULL,1,7,1,1,0,0);
/*!40000 ALTER TABLE `tblduefrom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblarticle`
--

DROP TABLE IF EXISTS `tblarticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblarticle` (
  `ARTICALID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `ARTICALMODELID` int(11) DEFAULT NULL,
  `ISACTIVE` smallint(6) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ARTICALID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblarticle`
--

LOCK TABLES `tblarticle` WRITE;
/*!40000 ALTER TABLE `tblarticle` DISABLE KEYS */;
INSERT INTO `tblarticle` VALUES (1,'001','Plan Ring',1,1,0,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(2,'002','Ring With Stone',1,1,0,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(3,'001','Chain',1,2,0,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(4,'002','Tube Chain',1,2,0,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(5,'001','Pendant',1,3,0,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(6,'001','Plain Bangal',1,4,0,1,'2012-05-29 00:00:00',NULL,5,0,1,1,1),(7,'003','Tube Ring',1,1,0,1,'2008-12-13 12:28:48',NULL,3,0,0,1,2),(8,'002','BACELET',1,0,0,1,'2008-12-18 12:28:48',NULL,4,0,0,1,2),(9,'001','Thella',1,5,0,1,'2008-12-18 12:28:48',NULL,3,0,0,1,2),(10,'001','Normal Bracelet',1,6,0,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(11,'001','Plain Gold Coin',1,7,0,1,'2010-01-25 12:28:48',NULL,6,0,0,1,2),(12,'002','Gold Coin With Pendent',1,7,0,1,'2010-01-25 12:28:48',NULL,6,0,0,1,2),(13,'001','Normal Earings',1,8,0,1,'2010-03-01 12:28:48',NULL,6,0,0,1,2),(14,'003','Chain with Stones',1,2,0,1,'2011-12-19 00:00:00',NULL,5,0,1,1,1),(15,'004','Nawarathne Ring',1,1,0,1,'2011-12-19 00:00:00',NULL,5,0,1,1,1),(16,'002','Bangal with stone ',1,4,0,1,'2011-12-19 00:00:00',NULL,5,0,1,1,1),(17,'001','Necklace',1,12,0,1,'2011-09-09 00:00:00',NULL,13,0,1,1,4),(18,'002','Necklace with stones ',1,12,0,1,'2011-09-09 00:00:00',NULL,13,0,0,1,4),(19,'001','Thodu with stones',1,9,0,1,'2011-12-19 00:00:00',NULL,5,0,1,1,1),(20,'001','Plain gold biscuit',1,13,0,1,'2011-12-19 00:00:00',NULL,5,0,1,1,1),(21,'002','Gold biscuit with with pendant',1,13,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(22,'002','plain thodu',1,9,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(23,'001','Plain Gypsi',1,10,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(24,'001','Plain Ankett',1,11,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(25,'002','Anklette with stones',1,11,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(26,'001','chain with pendent (plain)',1,14,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(27,'002','chain with pendent (Stones)',1,14,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(28,'001','Plain Gents Ring',1,15,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(29,'002','Gents Ring with stones',1,15,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(30,'003','Gents ring ( Nawarathna)',1,15,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(31,'001','plain Ladies Ring',1,16,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(32,'002','Ladies Ring with stones',1,16,0,1,'2011-12-19 00:00:00',NULL,5,0,1,1,1),(33,'003','Ladies Ring ( Nawarathna)',1,16,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(34,'001','Plain jimiki',1,17,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(35,'002','Jimiki with stones',1,17,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(36,'001','Plain Tussels',1,18,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(37,'002','Tussels with Stones',1,18,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(38,'001','Plain Panchauda',1,19,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(39,'002','Panchauda with Stones',1,19,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(40,'001','Plain Sawadi',1,20,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(41,'002','Sawadi with Stones',1,20,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(42,'002','Earings with Tussel',1,8,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(43,'003','Earings with Stones',1,8,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(44,'003','Screw Bangal',1,4,0,1,'2011-12-19 00:00:00',NULL,5,0,0,1,1),(45,'001','Gold pieces',1,21,0,1,'2011-12-28 00:00:00',NULL,5,0,0,1,1),(46,'002','Gold Key Tag',1,21,0,1,'2011-12-28 00:00:00',NULL,5,0,0,1,1),(47,'002','Thalikodi',1,5,0,1,'2012-01-03 00:00:00',NULL,5,0,0,1,1),(48,'003','Mani',1,21,0,1,'2012-01-24 00:00:00',NULL,5,0,0,1,1),(49,'001','Mattle',1,22,0,1,'2012-06-08 00:00:00',NULL,5,0,0,1,1),(50,'004','Tube Bangal',1,4,0,1,'2012-08-13 00:00:00',NULL,5,0,0,1,1),(51,'004','Gold Watch',1,21,0,1,'2012-08-20 00:00:00',NULL,5,0,0,1,1);
/*!40000 ALTER TABLE `tblarticle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblofficer`
--

DROP TABLE IF EXISTS `tblofficer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblofficer` (
  `OFFICERID` int(11) NOT NULL,
  `DEFBRANCH` int(11) DEFAULT NULL,
  `PWNID` int(11) DEFAULT NULL,
  `USERNAME` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `USERGROUP` int(11) DEFAULT NULL,
  `ISACTIVE` smallint(6) DEFAULT NULL,
  `VALIEDPD` int(11) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  PRIMARY KEY (`OFFICERID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblofficer`
--

LOCK TABLES `tblofficer` WRITE;
/*!40000 ALTER TABLE `tblofficer` DISABLE KEYS */;
INSERT INTO `tblofficer` VALUES (1,1,1,'DEMO','0DPiKuNIrrVmD8IUCuw1hQxNqZc=',1,1,NULL,1,'2012-06-20 00:00:00',NULL,1,0,9,1);
/*!40000 ALTER TABLE `tblofficer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblinterestslabs`
--

DROP TABLE IF EXISTS `tblinterestslabs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblinterestslabs` (
  `INTSLABID` int(11) NOT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `CODE` int(11) DEFAULT NULL,
  `SLABNO` int(11) DEFAULT NULL,
  `NODAYSFROM` int(11) DEFAULT NULL,
  `NODAYSTO` int(11) DEFAULT NULL,
  `RATE` decimal(5,2) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INTSLABID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblinterestslabs`
--

LOCK TABLES `tblinterestslabs` WRITE;
/*!40000 ALTER TABLE `tblinterestslabs` DISABLE KEYS */;
INSERT INTO `tblinterestslabs` VALUES (213,1,111,1,1,10,10.00,1,'2012-11-08 00:00:00',NULL,1,0,0,1,1),(214,1,111,2,11,30,15.00,1,'2012-11-08 00:00:00',NULL,1,0,0,1,1),(215,1,111,3,31,999,20.00,1,'2012-11-08 00:00:00',NULL,1,0,0,1,1);
/*!40000 ALTER TABLE `tblinterestslabs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblticketarticle`
--

DROP TABLE IF EXISTS `tblticketarticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblticketarticle` (
  `TICKETARTID` int(11) NOT NULL,
  `ARTICALID` int(11) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `TICKETID` int(11) DEFAULT NULL,
  `ARTICALMODELID` int(11) DEFAULT NULL,
  `MARVALUE` decimal(15,2) DEFAULT NULL,
  `ASSVALUE` decimal(15,2) DEFAULT NULL,
  `NETWEIGHT` decimal(15,2) DEFAULT NULL,
  `GROSSWEIGHT` decimal(15,2) DEFAULT NULL,
  `REMARKS` varchar(100) DEFAULT NULL,
  `ARTVALUE` decimal(15,2) DEFAULT NULL,
  `ISACTIVE` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  `ARTDESCRIPTION` varchar(50) DEFAULT NULL,
  `NOOFARTICLE` int(11) DEFAULT NULL,
  `CARTAGEID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `RECSTATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`TICKETARTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblticketarticle`
--

LOCK TABLES `tblticketarticle` WRITE;
/*!40000 ALTER TABLE `tblticketarticle` DISABLE KEYS */;
INSERT INTO `tblticketarticle` VALUES (7,1,1,7,1,114000.00,100000.00,12.00,12.00,NULL,0.00,1,'2013-05-25 00:00:00',NULL,1,0,1,1,'Plan Ring',1,20,0,0),(8,2,1,8,2,114000.00,100000.00,12.00,12.00,NULL,0.00,1,'2013-06-03 00:00:00',NULL,1,0,1,1,'Chain',1,20,0,0),(9,1,1,9,1,114000.00,100000.00,12.00,12.00,NULL,0.00,1,'2013-06-03 00:00:00',NULL,1,0,1,1,'Plan Ring',1,20,0,0),(10,1,1,10,1,114000.00,100000.00,12.00,12.00,NULL,0.00,1,'2013-05-26 00:00:00',NULL,1,0,1,1,'Plan Ring',1,20,0,0);
/*!40000 ALTER TABLE `tblticketarticle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblreminderparameter`
--

DROP TABLE IF EXISTS `tblreminderparameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblreminderparameter` (
  `REMPARAID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Reminder Id',
  `COMID` int(11) DEFAULT NULL COMMENT 'Company Id',
  `PRODUCTID` int(11) DEFAULT NULL COMMENT 'Product Id',
  `BRANCHID` int(11) DEFAULT NULL COMMENT 'Branch Id',
  `SCHEMEID` int(11) DEFAULT NULL COMMENT 'Scheme Id',
  `CODE` varchar(4) DEFAULT NULL COMMENT 'Reminder Code',
  `DESCRIPTION` varchar(60) DEFAULT NULL COMMENT 'Description',
  `NOOFDAYS` int(3) DEFAULT NULL COMMENT 'No of Days After Laps',
  `ISSENDNOMINEE` varchar(1) DEFAULT NULL COMMENT 'Reminder Code',
  `LASTUPDATE` datetime DEFAULT NULL COMMENT 'Last Updated Date',
  `LASTUPTIME` mediumtext COMMENT 'Last Updated Time',
  `LASTUPUSERID` int(11) DEFAULT NULL COMMENT 'Last Updated User Id',
  `RECSTATUS` varchar(2) DEFAULT NULL COMMENT 'Record Status',
  `ORIGINALID` int(11) DEFAULT NULL COMMENT 'Original Record Id',
  `VERSIONID` int(11) DEFAULT NULL COMMENT 'Version',
  PRIMARY KEY (`REMPARAID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblreminderparameter`
--

LOCK TABLES `tblreminderparameter` WRITE;
/*!40000 ALTER TABLE `tblreminderparameter` DISABLE KEYS */;
INSERT INTO `tblreminderparameter` VALUES (1,1,1,1,2823,'REM1','First Reminder',30,'N','2010-12-11 12:28:48',NULL,1,'1',0,0),(2,1,1,1,2823,'REM2','Second Reminder',60,'N','2010-12-11 12:28:48',NULL,1,'1',0,0),(3,1,1,1,2823,'REM3','Third Reminder',90,'N','2010-12-11 12:28:48',NULL,1,'1',0,0);
/*!40000 ALTER TABLE `tblreminderparameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblarticlemodel`
--

DROP TABLE IF EXISTS `tblarticlemodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblarticlemodel` (
  `ARTICALMODELID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `PRODUCTID` int(11) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `BRANCHID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ARTICALMODELID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblarticlemodel`
--

LOCK TABLES `tblarticlemodel` WRITE;
/*!40000 ALTER TABLE `tblarticlemodel` DISABLE KEYS */;
INSERT INTO `tblarticlemodel` VALUES (1,'001','Ring',1,1,'2010-11-24 12:28:48',NULL,2,0,1,1,1),(2,'002','Chain',1,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(3,'003','Pendant',1,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(4,'004','Bangal',1,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(5,'005','Thalikodi',1,1,'2011-12-15 00:00:00',NULL,5,0,1,1,1),(6,'006','Bracelet',1,1,'2008-12-07 12:28:48',NULL,1,0,0,1,1),(7,'007','Gold Coin',1,1,'2010-01-25 12:28:48',NULL,6,0,0,1,2),(8,'008','Earings',1,1,'2010-03-01 12:28:48',NULL,6,0,0,1,2),(9,'009','Thodu',1,1,'2011-12-19 00:00:00',NULL,5,0,2,1,1),(10,'010','Gypsi',1,1,'2011-12-15 00:00:00',NULL,5,0,2,1,1),(11,'011','Anklett',1,1,'2011-12-15 00:00:00',NULL,5,0,1,1,1),(12,'012','Necklace',1,1,'2011-09-09 00:00:00',NULL,13,0,1,1,4),(13,'013','Gold Biscuit',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(14,'014','Chain with Pendent',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(15,'015','Gents Ring',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(16,'016','Ladies Ring',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(17,'017','Jimiki',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(18,'018','Tussels',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(19,'019','Panchauda',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(20,'020','Sawadi',1,1,'2011-12-15 00:00:00',NULL,5,0,0,1,1),(21,'021','Multiple Items',1,1,'2011-12-28 00:00:00',NULL,5,0,0,1,1),(22,'022','Mattle',1,1,'2012-06-08 00:00:00',NULL,5,0,1,1,1);
/*!40000 ALTER TABLE `tblarticlemodel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblusercompany`
--

DROP TABLE IF EXISTS `tblusercompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblusercompany` (
  `COMID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` char(3) DEFAULT NULL,
  `COMPNAME` varchar(50) DEFAULT NULL,
  `ADDLINE1` varchar(40) DEFAULT NULL,
  `ADDLINE2` varchar(40) DEFAULT NULL,
  `ADDLINE3` varchar(40) DEFAULT NULL,
  `ADDLINE4` varchar(40) DEFAULT NULL,
  `TPNO` varchar(15) DEFAULT NULL,
  `FAXNO` varchar(15) DEFAULT NULL,
  `TAXNO` varchar(20) DEFAULT NULL,
  `DATEINSTALL` datetime DEFAULT NULL,
  `FBIGDATE` datetime DEFAULT NULL,
  `FENDDATE` datetime DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `AUTHMODE` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`COMID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblusercompany`
--

LOCK TABLES `tblusercompany` WRITE;
/*!40000 ALTER TABLE `tblusercompany` DISABLE KEYS */;
INSERT INTO `tblusercompany` VALUES (1,'GLT','Global Trust Pvt Ltd','# 40/12 A','Park Road','Colombo 05','','0112300191','0112300190','134000228','2011-01-12 00:00:00','0008-05-30 00:00:00','0021-05-30 00:00:00',1,'2011-03-24 12:28:48',NULL,1,0,7,0);
/*!40000 ALTER TABLE `tblusercompany` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpawner`
--

DROP TABLE IF EXISTS `tblpawner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblpawner` (
  `PWNID` int(11) NOT NULL AUTO_INCREMENT,
  `CORI` char(1) DEFAULT NULL,
  `CLTITLE` varchar(10) DEFAULT NULL,
  `INITIALS` varchar(20) DEFAULT NULL,
  `INITIALSFULL` varchar(150) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `BRDATE` datetime DEFAULT NULL,
  `NATIONAL` varchar(15) DEFAULT NULL,
  `MARISTS` smallint(6) DEFAULT NULL,
  `IDNO` varchar(20) DEFAULT NULL,
  `PPNO` varchar(20) DEFAULT NULL,
  `DRVLNO` varchar(20) DEFAULT NULL,
  `HOMETPNO` varchar(15) DEFAULT NULL,
  `OFFICETPNO` varchar(15) DEFAULT NULL,
  `MOBILENO` varchar(15) DEFAULT NULL,
  `FAXNO` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `MAILADDLINE1` varchar(60) DEFAULT NULL,
  `MAILADDLINE2` varchar(60) DEFAULT NULL,
  `MAILADDLINE3` varchar(60) DEFAULT NULL,
  `MAILADDLINE4` varchar(60) DEFAULT NULL,
  `INTDTE` datetime DEFAULT NULL,
  `CLSTATUS` smallint(6) DEFAULT NULL,
  `RECSTATUS` smallint(6) DEFAULT NULL,
  `LASTUPDATE` datetime DEFAULT NULL,
  `LASTUPTIME` mediumtext,
  `LASTUPUSERID` int(11) DEFAULT NULL,
  `ORIGINALID` int(11) DEFAULT NULL,
  `VERSIONID` int(11) DEFAULT NULL,
  `COMID` int(11) DEFAULT NULL,
  `SEX` smallint(6) DEFAULT NULL,
  `pawnCODE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`PWNID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpawner`
--

LOCK TABLES `tblpawner` WRITE;
/*!40000 ALTER TABLE `tblpawner` DISABLE KEYS */;
INSERT INTO `tblpawner` VALUES (1,'I','Mr','J.M.M.M','Mahinda Madhujith Jayakody','Jayakody',NULL,NULL,2,'773563527V','','','','','','','','734/27/A','DawatagahaWatta','','Wattala',NULL,1,1,'2012-11-08 00:00:00',NULL,1,0,3,0,1,'00000001');
/*!40000 ALTER TABLE `tblpawner` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-06  6:55:42
