-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dev_titulacion
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `tb_categorias`
--

DROP TABLE IF EXISTS `tb_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_categorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `estado_categoria` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categorias`
--

LOCK TABLES `tb_categorias` WRITE;
/*!40000 ALTER TABLE `tb_categorias` DISABLE KEYS */;
INSERT INTO `tb_categorias` VALUES (1,'LICORES','2020-05-20 04:03:41',NULL,_binary ''),(2,'GASEOSAS','2020-05-20 04:03:46',NULL,_binary '');
/*!40000 ALTER TABLE `tb_categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_productos`
--

DROP TABLE IF EXISTS `tb_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `descripcion_producto` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `presentacion_producto` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `stock_producto` int DEFAULT NULL,
  `precio_producto` decimal(13,2) DEFAULT NULL,
  `foto_producto` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'assets/images/productos/no_image.png',
  `date_created` datetime(6) DEFAULT NULL,
  `date_updated` datetime(6) DEFAULT NULL,
  `estado_producto` bit(1) DEFAULT b'1',
  `id_categoria` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRODUCTO_CATEGORIA_idx` (`id_categoria`),
  CONSTRAINT `FK_PRODUCTO_CATEGORIA` FOREIGN KEY (`id_categoria`) REFERENCES `tb_categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_productos`
--

LOCK TABLES `tb_productos` WRITE;
/*!40000 ALTER TABLE `tb_productos` DISABLE KEYS */;
INSERT INTO `tb_productos` VALUES (1,'INKA KOLA','SABOR ORIGINAL','BOTELLA 1.5 LT',50,6.10,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(2,'COCA COLA','SABOR ORIGINAL','BOTELLA 1.5 LT',50,6.10,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(3,'SPRITE','LIMA LIMON','BOTELLA 3 LT',50,6.00,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(4,'FANTA','SABOR NARANJA','BOTELLA 400 ML',50,2.20,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(5,'FANTA','SABOR NARANJA','BOTELLA 2.25 LT',50,5.00,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(6,'FANTA','SABOR KOLA INGLESA','BOTELLA 2.25 LT',50,5.00,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(7,'PEPSI','','BOTELLA 3 LT',50,6.50,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(8,'PEPSI','','TWO PACK BOTELLA 3 LT C/U',50,12.50,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(9,'GUARANÀ','','BOTELLA 3 LT',50,5.50,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(10,'COCA COLA + INKA KOLA','','TWO PACK BOTELLA 3 LT C/U',50,18.90,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',1),(11,'JÄGERMEISTER','','BOTELLA 700 ML',50,56.90,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(12,'RON CARTAVIO','SELECTO BLANCO','BOTELLA 750 ML',50,24.90,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(13,'VODKA ABSOLUT','','BOTELLA 1 LT',50,63.90,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(14,'WHISKY JOHNNIE WALKER','WHITE WALKER ','BOTELLA 750 ML',50,110.90,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(15,'ESPUMANTE TABERNERO',' MUSCAT ALEJANDRÌA','BOTELLA 750 ML',50,32.50,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(16,'CERVEZA CUZQUEÑA','NEGRA DARK LAGER','SIX PACK LATA 355 ML C/U',50,24.00,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(17,'CERVEZA CRISTAL','','SIX PACK LATA 355 ML C/U',50,19.20,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2),(18,'CERVEZA CORONITA','','SIX PACK BOTELLA 200 ML C/U',50,17.90,'/assets/images/productos/no-image.png','2020-05-19 20:41:05.000000',NULL,_binary '',2);
/*!40000 ALTER TABLE `tb_productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20  4:04:31
