CREATE DATABASE  IF NOT EXISTS `flights` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `flights`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flights
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airline` (
  `name` varchar(5) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES ('BAAIR'),('BAIR'),('BHAIR'),('BLU'),('BLUE'),('CRO'),('DELTA'),('EMIRA'),('GSSF'),('JAT'),('KLM'),('NORTH'),('WIZZ');
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airport` (
  `name` varchar(3) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES ('ATL','Atlanta'),('chi','Chicago'),('det','Detroit'),('fra','Frankfurt'),('lon','London'),('MOS','Mostar'),('RIM','Rome'),('SAR','Sarajevo'),('SKO','Skoplje'),('ZAG','Zagreb');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight` (
  `id` int(11) NOT NULL,
  `flight_name` varchar(45) NOT NULL,
  `origin` varchar(45) NOT NULL,
  `destination` varchar(45) NOT NULL,
  `airport` varchar(3) DEFAULT NULL,
  `airline` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `airport_idx` (`airport`),
  KEY `airline_idx` (`airline`),
  CONSTRAINT `airline` FOREIGN KEY (`airline`) REFERENCES `airline` (`name`),
  CONSTRAINT `airport` FOREIGN KEY (`airport`) REFERENCES `airport` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (1,'Flight1','Detroit','Chicago','DET','JAT'),(2,'flight2','Detroit','Chicago','det','jat'),(3,'bahama','chicago','zagreb','chi','jat'),(4,'flight4','Atlanta','Frankfurt','atl','blu'),(5,'flight5','Rim','Skoplje','SAR','JAT'),(6,'flight6','SAR','Sarajevo','SAR','JAT'),(7,'flight7','Detroit','chicago','det','jat'),(8,'FLIGHT8','Mostar','Rim','MOS','CRO'),(9,'FLIGHT9','Rome','Sarajevo','RIM','KLM'),(10,'FLIGHT10','Rome','Sarajevo','RIM','DELTA'),(11,'FLIGHT11','Atlanta','Frankfurt','ATL','DELTA');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seatID` int(11) NOT NULL AUTO_INCREMENT,
  `rowLetter` varchar(1) NOT NULL,
  `seat_number` int(11) NOT NULL,
  `available` tinyint(1) NOT NULL,
  `flightID` int(11) NOT NULL,
  PRIMARY KEY (`seatID`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (27,'A',1,1,1),(28,'A',2,1,1),(29,'A',3,1,1),(30,'B',1,1,1),(31,'B',2,1,1),(32,'B',3,1,1),(33,'C',1,0,1),(34,'C',2,1,1),(35,'C',3,1,1),(36,'D',1,1,1),(37,'D',2,1,1),(38,'D',3,1,1),(39,'E',1,1,1),(40,'E',2,1,1),(41,'E',3,1,1),(42,'F',1,1,1),(43,'F',2,1,1),(44,'F',3,1,1),(46,'A',1,1,3),(47,'A',2,1,3),(48,'A',3,1,3),(49,'A',4,1,3),(50,'B',1,1,3),(51,'B',2,1,3),(52,'B',3,1,3),(53,'B',4,1,3),(54,'C',1,1,3),(55,'C',2,0,3),(56,'C',3,1,3),(57,'C',4,1,3),(58,'D',1,1,3),(59,'D',2,1,3),(60,'D',3,1,3),(61,'D',4,1,3),(62,'E',1,1,3),(63,'E',2,0,3),(64,'E',3,0,3),(65,'E',4,1,3),(66,'F',1,1,3),(67,'F',2,1,3),(68,'F',3,1,3),(69,'F',4,1,3),(70,'A',1,1,5),(71,'A',2,1,5),(72,'A',3,1,5),(73,'A',4,1,5),(74,'B',1,1,5),(75,'B',2,1,5),(76,'B',3,1,5),(77,'B',4,0,5),(78,'C',1,1,5),(79,'C',2,1,5),(80,'C',3,1,5),(81,'C',4,1,5),(82,'D',1,1,5),(83,'D',2,1,5),(84,'D',3,1,5),(85,'D',4,1,5),(86,'E',1,1,5),(87,'E',2,1,5),(88,'E',3,0,5),(89,'E',4,1,5),(90,'F',1,1,5),(91,'F',2,0,5),(92,'F',3,1,5),(93,'F',4,1,5),(94,'A',1,1,6),(95,'A',2,1,6),(96,'A',3,1,6),(97,'A',4,1,6),(98,'B',1,1,6),(99,'B',2,1,6),(100,'B',3,1,6),(101,'B',4,1,6),(102,'C',1,1,6),(103,'C',2,1,6),(104,'C',3,1,6),(105,'C',4,1,6),(106,'D',1,1,6),(107,'D',2,1,6),(108,'D',3,1,6),(109,'D',4,1,6),(110,'E',1,1,6),(111,'E',2,1,6),(112,'E',3,1,6),(113,'E',4,1,6),(114,'F',1,1,6),(115,'F',2,1,6),(116,'F',3,1,6),(117,'F',4,1,6),(118,'A',1,1,7),(119,'A',2,1,7),(120,'A',3,1,7),(121,'A',4,1,7),(122,'B',1,1,7),(123,'B',2,0,7),(124,'B',3,0,7),(125,'B',4,1,7),(126,'C',1,1,7),(127,'C',2,1,7),(128,'C',3,1,7),(129,'C',4,1,7),(130,'D',1,1,7),(131,'D',2,1,7),(132,'D',3,1,7),(133,'D',4,1,7),(134,'E',1,1,7),(135,'E',2,1,7),(136,'E',3,1,7),(137,'E',4,1,7),(138,'F',1,1,7),(139,'F',2,1,7),(140,'F',3,1,7),(141,'F',4,0,7),(142,'A',1,1,4),(143,'A',2,1,4),(144,'A',3,1,4),(145,'B',1,1,4),(146,'B',2,1,4),(147,'B',3,1,4),(148,'C',1,1,4),(149,'C',2,1,4),(150,'C',3,1,4),(151,'D',1,1,4),(152,'D',2,1,4),(153,'D',3,1,4),(154,'E',1,1,4),(155,'E',2,1,4),(156,'E',3,1,4),(157,'F',1,1,4),(158,'F',2,1,4),(159,'F',3,1,4),(160,'A',1,1,8),(161,'A',2,1,8),(162,'A',3,1,8),(163,'B',1,1,8),(164,'B',2,1,8),(165,'B',3,1,8),(166,'C',1,1,8),(167,'C',2,1,8),(168,'C',3,1,8),(169,'D',1,1,8),(170,'D',2,1,8),(171,'D',3,1,8),(172,'E',1,1,8),(173,'E',2,1,8),(174,'E',3,1,8),(175,'F',1,1,8),(176,'F',2,1,8),(177,'F',3,0,8),(178,'A',1,1,9),(179,'A',2,1,9),(180,'A',3,1,9),(181,'A',4,1,9),(182,'B',1,1,9),(183,'B',2,1,9),(184,'B',3,1,9),(185,'B',4,1,9),(186,'C',1,1,9),(187,'C',2,1,9),(188,'C',3,1,9),(189,'C',4,1,9),(190,'D',1,0,9),(191,'D',2,0,9),(192,'D',3,1,9),(193,'D',4,1,9),(194,'E',1,1,9),(195,'E',2,1,9),(196,'E',3,1,9),(197,'E',4,1,9),(198,'F',1,1,9),(199,'F',2,1,9),(200,'F',3,1,9),(201,'F',4,0,9),(202,'A',1,1,10),(203,'A',2,1,10),(204,'A',3,1,10),(205,'B',1,1,10),(206,'B',2,1,10),(207,'B',3,1,10),(208,'C',1,1,10),(209,'C',2,1,10),(210,'C',3,1,10),(211,'D',1,1,10),(212,'D',2,1,10),(213,'D',3,0,10),(214,'E',1,0,10),(215,'E',2,0,10),(216,'E',3,0,10),(217,'F',1,0,10),(218,'F',2,1,10),(219,'F',3,1,10),(220,'A',1,1,11),(221,'A',2,1,11),(222,'A',3,1,11),(223,'A',4,1,11),(224,'B',1,1,11),(225,'B',2,1,11),(226,'B',3,1,11),(227,'B',4,1,11),(228,'C',1,1,11),(229,'C',2,1,11),(230,'C',3,1,11),(231,'C',4,1,11),(232,'D',1,1,11),(233,'D',2,1,11),(234,'D',3,1,11),(235,'D',4,1,11),(236,'E',1,1,11),(237,'E',2,1,11),(238,'E',3,1,11),(239,'E',4,1,11),(240,'F',1,1,11),(241,'F',2,1,11),(242,'F',3,1,11),(243,'F',4,1,11);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-01 23:21:23
