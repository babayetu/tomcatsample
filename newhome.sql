-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `betuser`
--

DROP TABLE IF EXISTS `betuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `betuser` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `corp_handle` varchar(20) DEFAULT NULL,
  `deposit_point` int(8) DEFAULT '1000',
  `last_update` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `betuser`
--

LOCK TABLES `betuser` WRITE;
/*!40000 ALTER TABLE `betuser` DISABLE KEYS */;
INSERT INTO `betuser` VALUES (1,'wan jian',1000,'2014-06-15');
/*!40000 ALTER TABLE `betuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit`
--

DROP TABLE IF EXISTS `deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deposit` (
  `user_id` int(4) NOT NULL,
  `money` int(16) NOT NULL DEFAULT '0',
  `unit` char(3) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit`
--

LOCK TABLES `deposit` WRITE;
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mymatch`
--

DROP TABLE IF EXISTS `mymatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mymatch` (
  `match_id` int(16) NOT NULL,
  `host_team` varchar(20) NOT NULL,
  `guest_team` varchar(20) NOT NULL,
  `host_score` int(4) NOT NULL DEFAULT '-1',
  `guest_score` int(4) NOT NULL DEFAULT '-1',
  `match_time` date DEFAULT NULL,
  `match_status` char(20) NOT NULL DEFAULT 'notstart',
  PRIMARY KEY (`match_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mymatch`
--

LOCK TABLES `mymatch` WRITE;
/*!40000 ALTER TABLE `mymatch` DISABLE KEYS */;
INSERT INTO `mymatch` VALUES (1,'brazil','croatia',3,1,'2014-06-13','finished'),(2,'mexico','cameroon',1,0,'2014-06-14','finished'),(3,'spain','holand',1,5,'2014-06-14','finished'),(4,'chile','australia',3,1,'2014-06-14','finished'),(5,'columbia','greece',3,0,'2014-06-15','finished'),(6,'uruguay','costarica',1,3,'2014-06-15','finished'),(7,'england','italy',1,2,'2014-06-15','finished'),(8,'cotedlvoir','japan',2,1,'2014-06-15','finished'),(9,'swiss','ecuador',2,1,'2014-06-16','finished'),(10,'france','honduras',3,0,'2014-06-16','finished'),(11,'argentina','bosniahercegovina',2,1,'2014-06-16','finished'),(12,'germany','portugal',4,0,'2014-06-17','finished'),(13,'iran','nigeria',0,0,'2014-06-17','finished'),(14,'ghana','usa',1,2,'2014-06-17','finished'),(15,'belgium','algeria',2,1,'2014-06-18','finished'),(16,'brazil','mexico',0,0,'2014-06-18','finished'),(17,'russia','korea',1,1,'2014-06-18','finished'),(18,'australia','holand',2,3,'2014-06-19','finished'),(19,'spain','chile',0,2,'2014-06-19','finished'),(20,'cameroon','croatia',0,4,'2014-06-19','finished'),(21,'columbia','cotedlvoir',2,1,'2014-06-20','finished'),(22,'uruguay','england',2,1,'2014-06-20','finished'),(23,'japan','greece',0,0,'2014-06-20','finished'),(24,'italy','costarica',0,1,'2014-06-21','finished'),(25,'swiss','france',2,5,'2014-06-21','finished'),(26,'honduras','ecuador',1,2,'2014-06-21','finished'),(27,'argentina','iran',1,0,'2014-06-22','finished'),(28,'germany','ghana',2,2,'2014-06-22','finished'),(29,'nigeria','bosniahercegovina',3,2,'2014-06-22','finished');
/*!40000 ALTER TABLE `mymatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myorder`
--

DROP TABLE IF EXISTS `myorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `myorder` (
  `order_id` int(16) NOT NULL AUTO_INCREMENT,
  `name` char(20) NOT NULL,
  `money` double(16,2) NOT NULL,
  `rate_id` int(16) NOT NULL,
  `order_time` date DEFAULT NULL,
  `order_status` char(10) NOT NULL DEFAULT 'valid',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myorder`
--

LOCK TABLES `myorder` WRITE;
/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES (8,'jacky',100.00,1,'2014-06-22','missed'),(9,'karl liu',200.00,3,'2014-06-22','bingo'),(10,'ding yingqi',300.00,1,'2014-06-22','missed'),(11,'ding yingqi',100.00,4,'2014-06-22','bingo'),(12,'karl liu',200.00,5,'2014-06-22','missed'),(13,'yao jin',90.00,6,'2014-06-22','missed'),(14,'yao jin',250.00,7,'2014-06-22','missed'),(15,'luo guoping',400.00,8,'2014-06-22','bingo'),(16,'sun austin',90.00,9,'2014-06-22','missed'),(17,'ding yingqi',472.00,10,'2014-06-22','bingo'),(18,'jingjliu',10.00,1,'2014-06-22','bingo'),(19,'yingding',100.00,3,'2014-06-22','valid'),(20,'jingjliu',50.00,6,'2014-06-22','valid');
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myrate`
--

DROP TABLE IF EXISTS `myrate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `myrate` (
  `rate_id` int(16) NOT NULL,
  `match_id` int(16) NOT NULL,
  `match_result` char(10) NOT NULL,
  `rate` double(16,2) NOT NULL DEFAULT '1.00',
  `rate_status` char(10) NOT NULL DEFAULT 'open',
  `rate_time` date DEFAULT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myrate`
--

LOCK TABLES `myrate` WRITE;
/*!40000 ALTER TABLE `myrate` DISABLE KEYS */;
INSERT INTO `myrate` VALUES (1,29,'host_win',4.50,'open','2014-06-22'),(2,29,'deuce',3.60,'open','2014-06-22'),(3,29,'guest_win',1.91,'open','2014-06-22'),(4,27,'host_win',1.50,'open','2014-06-22'),(5,27,'deuce',3.60,'open','2014-06-22'),(6,27,'guest_win',5.00,'open','2014-06-22'),(7,28,'host_win',1.30,'open','2014-06-22'),(8,28,'deuce',5.60,'open','2014-06-22'),(9,28,'guest_win',11.00,'open','2014-06-22'),(10,29,'host_win',2.30,'open','2014-06-22'),(11,29,'deuce',2.60,'open','2014-06-22'),(12,29,'guest_win',3.00,'open','2014-06-22');
/*!40000 ALTER TABLE `myrate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myuser`
--

DROP TABLE IF EXISTS `myuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `myuser` (
  `name` char(20) NOT NULL,
  `passwd` varchar(80) NOT NULL DEFAULT 'pkKner19T1G/kibOr4kfy7Wymbg=',
  `money` double(16,2) NOT NULL DEFAULT '1000.00',
  `role` char(20) NOT NULL DEFAULT 'gambler',
  `register_time` date DEFAULT '2014-06-24',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myuser`
--

LOCK TABLES `myuser` WRITE;
/*!40000 ALTER TABLE `myuser` DISABLE KEYS */;
INSERT INTO `myuser` VALUES ('jingjliu','pkKner19T1G/kibOr4kfy7Wymbg=',545.00,'admin','2014-06-24'),('jyao1','pkKner19T1G/kibOr4kfy7Wymbg=',890.00,'gambler','2014-06-24'),('wshen1','pkKner19T1G/kibOr4kfy7Wymbg=',1310.00,'gambler','2014-06-24'),('yingding','pkKner19T1G/kibOr4kfy7Wymbg=',590.00,'gambler','2014-06-24');
/*!40000 ALTER TABLE `myuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-22 23:28:04
