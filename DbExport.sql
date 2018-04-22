-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: stores_group
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `chain`
--

DROP TABLE IF EXISTS `chain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chain` (
  `idchain` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idchain`),
  UNIQUE KEY `idchain_UNIQUE` (`idchain`)
) ENGINE=InnoDB AUTO_INCREMENT=10061 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chain`
--

LOCK TABLES `chain` WRITE;
/*!40000 ALTER TABLE `chain` DISABLE KEYS */;
INSERT INTO `chain` VALUES (1,'Fox'),(10000,'Golf'),(10001,' Golf'),(10002,' Fox'),(10003,' Fox'),(10004,' Fox'),(10005,' Fox'),(10006,' Fox'),(10007,' Fox'),(10008,' Fox'),(10009,' Fox'),(10010,' Fox'),(10011,' Fox'),(10012,' Fox'),(10013,' Fox'),(10014,' Golf'),(10015,' Fox'),(10016,' Fox'),(10017,'Golf'),(10018,'Azrieli'),(10019,'Golf'),(10020,'Golf'),(10021,'Golf'),(10022,'Golf'),(10023,'Golf'),(10024,'Golf'),(10025,'aaa'),(10026,'tal'),(10027,'ddd'),(10028,'Golf'),(10029,'TTT'),(10030,'1'),(10031,'The'),(10032,'The'),(10033,'Tal'),(10034,'VVVVVV'),(10035,'McDonalds'),(10036,'Test'),(10037,'tesst'),(10038,'test1524370938352'),(10047,'Test Chain'),(10049,'Test Chain'),(10051,'Test Chain'),(10059,'test1524372412800');
/*!40000 ALTER TABLE `chain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'TLV'),(2,'Haifa');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(75) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `isManager` tinyint(4) DEFAULT NULL,
  `chainID` int(11) DEFAULT NULL,
  `storeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `chain_idx` (`chainID`),
  KEY `store_idx` (`storeID`),
  CONSTRAINT `chain` FOREIGN KEY (`chainID`) REFERENCES `chain` (`idchain`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `store` FOREIGN KEY (`storeID`) REFERENCES `stores` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39397783 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Tal','Buchshreiber','The Man',NULL,NULL,NULL,NULL),(2,'Stav','Kalo','Hertzel',NULL,NULL,NULL,NULL),(3,'t','b','ttt',NULL,NULL,NULL,NULL),(4,' Tal','Buch','Buch',NULL,NULL,10001,NULL),(123456,'Tal','BBB','B',NULL,NULL,10027,NULL),(12345678,'Tal','B','B',NULL,NULL,1,NULL),(39397700,'Tal','Buch','Buch',NULL,NULL,10001,NULL),(39397718,'Tal','Buch','Buch',NULL,NULL,10001,NULL),(39397767,'Tal','Buch','Buch',NULL,NULL,10001,NULL),(39397771,'T','B',NULL,NULL,1,10034,NULL),(39397772,'Tal','Hertzel',NULL,NULL,0,NULL,20),(39397773,'Tal','Ben','Sason',NULL,1,10037,NULL),(39397774,'Tal','M','S',NULL,0,NULL,25);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mallgroups`
--

DROP TABLE IF EXISTS `mallgroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mallgroups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mallgroups`
--

LOCK TABLES `mallgroups` WRITE;
/*!40000 ALTER TABLE `mallgroups` DISABLE KEYS */;
INSERT INTO `mallgroups` VALUES (1,'Azrieli Group');
/*!40000 ALTER TABLE `mallgroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingmalls`
--

DROP TABLE IF EXISTS `shoppingmalls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingmalls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  `StreesAddress` varchar(45) DEFAULT NULL,
  `mallGroupId` int(11) DEFAULT NULL,
  `shoppingmallscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cityId_idx` (`cityId`),
  KEY `mallGroupId_idx` (`mallGroupId`),
  CONSTRAINT `city` FOREIGN KEY (`cityId`) REFERENCES `cities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mallGroupId` FOREIGN KEY (`mallGroupId`) REFERENCES `mallgroups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingmalls`
--

LOCK TABLES `shoppingmalls` WRITE;
/*!40000 ALTER TABLE `shoppingmalls` DISABLE KEYS */;
INSERT INTO `shoppingmalls` VALUES (1,'Azrieli',NULL,'Hagana',1,NULL),(2,'Arim',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `shoppingmalls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(50) NOT NULL,
  `chain` int(11) DEFAULT NULL,
  `cityId` int(11) DEFAULT NULL,
  `StreetAddress` varchar(45) DEFAULT NULL,
  `mallId` int(11) DEFAULT NULL,
  `storeNumberInMall` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idchain_idx` (`chain`),
  KEY `cityId_idx` (`cityId`),
  KEY `mallId_idx` (`mallId`),
  CONSTRAINT `chainId` FOREIGN KEY (`chain`) REFERENCES `chain` (`idchain`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cityId` FOREIGN KEY (`cityId`) REFERENCES `cities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mallId` FOREIGN KEY (`mallId`) REFERENCES `shoppingmalls` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (3,' Fox kids',10001,1,NULL,1,NULL),(4,'Fox kids',10001,NULL,NULL,1,NULL),(5,'Fox kids',10001,NULL,NULL,1,NULL),(8,'Fox kids',10001,NULL,NULL,NULL,NULL),(10,'Fox kids',10001,NULL,NULL,NULL,NULL),(12,'Fox kids',10001,NULL,NULL,NULL,NULL),(14,'Fox kids',10001,NULL,NULL,NULL,NULL),(16,'Fox kids',10001,NULL,NULL,NULL,NULL),(17,'qqq',10019,NULL,NULL,1,NULL),(18,'Mango',10026,NULL,NULL,1,NULL),(20,'Fox kids',10001,NULL,NULL,NULL,NULL),(21,'Castro',10034,NULL,NULL,1,NULL),(23,'Avazi',10035,NULL,NULL,NULL,NULL),(24,'Milswanka',10036,NULL,NULL,NULL,NULL),(25,'teststore',10033,NULL,NULL,NULL,NULL),(30,'Test Store',1,NULL,NULL,NULL,NULL),(32,'Test Store',1,NULL,NULL,NULL,NULL),(34,'Test Store',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-22 11:15:15
