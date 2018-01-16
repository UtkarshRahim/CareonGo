-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pro
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` float DEFAULT NULL,
  `TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `STATUS` varchar(20) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,28,'2018-01-12 16:42:09','Delivered',1),(2,28,'2018-01-12 16:43:21','Delivered',1),(3,28,'2018-01-12 16:44:01','Delivered',1),(4,392,'2018-01-12 16:44:48','Delivered',1),(5,380,'2018-01-12 16:46:01','Delivered',1),(6,84,'2018-01-12 16:46:42','Delivered',1),(7,84,'2018-01-12 16:47:08','Delivered',1),(8,84,'2018-01-12 16:50:55','Delivered',1),(9,84,'2018-01-12 16:51:15','Delivered',1),(10,84,'2018-01-12 16:51:16','Delivered',1),(11,84,'2018-01-12 16:51:16','Delivered',1),(12,84,'2018-01-12 16:51:17','Delivered',1),(13,84,'2018-01-12 16:51:17','Delivered',1),(14,84,'2018-01-12 16:51:17','Delivered',1),(15,84,'2018-01-12 16:51:18','Delivered',1),(16,84,'2018-01-12 16:51:18','Delivered',1),(17,84,'2018-01-12 16:51:18','Delivered',1),(18,84,'2018-01-12 16:51:19','Delivered',1),(19,84,'2018-01-12 16:51:19','Delivered',1),(20,84,'2018-01-12 16:51:19','Delivered',1),(21,84,'2018-01-12 16:51:20','Delivered',1),(22,50,'2018-01-12 16:59:23','Delivered',1),(23,60,'2018-01-12 16:59:50','Delivered',1),(24,90,'2018-01-12 17:01:57','Delivered',1),(25,90,'2018-01-12 17:02:30','Delivered',1),(26,144,'2018-01-12 17:13:12','Delivered',1),(27,150,'2018-01-12 17:16:38','Delivered',1),(28,200,'2018-01-12 17:17:33','Delivered',1),(29,20,'2018-01-12 18:01:07','Delivered',1),(30,24,'2018-01-12 18:02:40','Delivered',1),(31,4,'2018-01-15 12:48:10','Delivered',1),(32,4,'2018-01-15 12:50:35','Delivered',1),(33,8,'2018-01-15 12:50:43','Delivered',1),(34,4,'2018-01-15 12:51:36','Delivered',1),(35,34,'2018-01-15 12:53:13','Delivered',1),(36,100,'2018-01-15 12:54:08','Delivered',1),(37,100,'2018-01-15 12:57:33','Delivered',1),(38,100,'2018-01-15 12:58:13','Delivered',1),(39,2,'2018-01-15 13:01:41','Delivered',1),(40,2,'2018-01-15 13:01:48','Delivered',1),(41,2,'2018-01-15 13:03:05','Delivered',1),(42,2,'2018-01-15 13:03:12','Delivered',1),(43,2,'2018-01-15 13:03:18','Delivered',1),(44,2,'2018-01-15 13:03:37','Delivered',1),(45,2,'2018-01-15 13:04:05','Delivered',1),(46,2,'2018-01-15 13:05:22','Delivered',1),(47,2,'2018-01-15 13:11:25','Delivered',1),(48,2,'2018-01-15 13:21:17','Delivered',1),(49,2,'2018-01-15 13:21:22','Delivered',1),(50,2,'2018-01-15 13:21:28','Delivered',1),(51,2,'2018-01-15 13:25:57','Delivered',1),(52,50,'2018-01-15 13:26:11','Delivered',1),(53,50,'2018-01-15 13:47:48','Delivered',1),(54,50,'2018-01-15 13:52:37','Delivered',1),(55,50,'2018-01-15 13:52:42','Delivered',1),(56,50,'2018-01-15 13:52:46','Delivered',1),(57,50,'2018-01-15 14:01:59','Delivered',1),(58,50,'2018-01-15 14:02:05','Delivered',1),(59,50,'2018-01-15 14:02:35','Delivered',1),(60,50,'2018-01-15 14:10:26','Delivered',1),(61,50,'2018-01-15 14:10:48','Delivered',1),(62,100,'2018-01-15 14:13:00','Delivered',1),(63,0,'2018-01-15 14:14:26','Delivered',1),(64,0,'2018-01-15 14:14:36','Delivered',1),(65,0,'2018-01-15 14:14:46','Delivered',1),(66,0,'2018-01-15 14:14:59','Delivered',1),(67,2,'2018-01-15 14:33:02','Delivered',1),(68,2,'2018-01-15 14:33:26','Delivered',1),(69,2,'2018-01-15 14:33:34','Delivered',1),(70,2,'2018-01-15 14:33:39','Delivered',1),(71,2,'2018-01-15 14:33:53','Delivered',1),(72,2,'2018-01-15 14:33:57','Delivered',1),(73,2,'2018-01-15 14:34:04','Delivered',1),(74,0,'2018-01-15 14:53:57','Delivered',1),(75,0,'2018-01-15 15:00:55','Delivered',1),(76,0,'2018-01-15 15:01:54','Delivered',1),(77,0,'2018-01-15 15:08:35','Delivered',1),(78,0,'2018-01-15 15:10:48','Delivered',1),(79,2,'2018-01-15 15:11:43','Delivered',1),(80,2,'2018-01-15 15:11:48','Delivered',1),(81,2,'2018-01-15 15:12:02','Delivered',1),(82,2,'2018-01-15 15:12:30','Delivered',1),(83,2,'2018-01-15 15:12:45','Delivered',1),(84,0,'2018-01-15 15:12:52','Delivered',1),(85,0,'2018-01-15 15:13:03','Delivered',1),(86,0,'2018-01-15 15:13:45','Delivered',1),(87,2,'2018-01-15 15:13:55','Delivered',1),(88,2,'2018-01-15 15:14:14','Delivered',1),(89,84,'2018-01-15 15:17:01','Delivered',1),(90,2,'2018-01-15 15:18:20','Delivered',1),(91,6,'2018-01-15 15:18:24','Delivered',1),(92,66,'2018-01-15 15:18:29','Delivered',1),(93,2,'2018-01-15 15:46:26','SHIPPED',1),(94,94,'2018-01-15 21:41:35','SHIPPED',2),(95,2,'2018-01-16 11:29:56',NULL,NULL),(96,2,'2018-01-16 11:30:14',NULL,NULL),(97,2,'2018-01-16 11:30:18',NULL,NULL),(98,2,'2018-01-16 11:34:03','PLACED',2),(99,2,'2018-01-16 11:34:14','SHIPPED',2),(100,30,'2018-01-16 13:19:46','PLACED',1),(101,30,'2018-01-16 13:19:52','PLACED',1),(102,30,'2018-01-16 13:20:27','SHIPPED',1),(103,2,'2018-01-16 13:58:13','SHIPPED',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-16 18:40:22
