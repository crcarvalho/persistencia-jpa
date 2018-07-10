CREATE DATABASE  IF NOT EXISTS `db_avaliacao_escola` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_avaliacao_escola`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: db_avaliacao_escola
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) NOT NULL,
  `DT_NASCIMENTO` datetime NOT NULL,
  `RG` varchar(11) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `ENDERECO` varchar(100) NOT NULL,
  `SEXO` varchar(1) NOT NULL,
  `TELEFONE` varchar(10) DEFAULT NULL,
  `CELULAR` varchar(11) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `CPF_UNIQUE` (`CPF`),
  UNIQUE KEY `RG_UNIQUE` (`RG`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso_aluno`
--

DROP TABLE IF EXISTS `curso_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso_aluno` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDALUNO` int(11) NOT NULL,
  `IDCURSO` int(11) NOT NULL,
  `DT_INICIO` datetime NOT NULL,
  `FORMADO` int(11) DEFAULT '0',
  `DT_CONCLUSAO` datetime DEFAULT NULL,
  `NOTA_FINAL` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `FK_ID_ALUNO_idx` (`IDALUNO`),
  KEY `FK_ID_CURSO_idx` (`IDCURSO`),
  CONSTRAINT `FK_ID_ALUNO` FOREIGN KEY (`IDALUNO`) REFERENCES `aluno` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ID_CURSO` FOREIGN KEY (`IDCURSO`) REFERENCES `curso_capacitacao` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKjgbbidkj455v2mx74pvnro828` FOREIGN KEY (`IDALUNO`) REFERENCES `aluno` (`ID`),
  CONSTRAINT `FKpmqlstjh6bwftc80x4s8taxnx` FOREIGN KEY (`IDCURSO`) REFERENCES `curso_capacitacao` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso_capacitacao`
--

DROP TABLE IF EXISTS `curso_capacitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso_capacitacao` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) NOT NULL,
  `DURACAO` int(11) NOT NULL,
  `AREA` varchar(50) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `CUSTO` double NOT NULL,
  `IDESCOLA` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `FK_ID_ESCOLA_idx` (`IDESCOLA`),
  CONSTRAINT `FK_ID_ESCOLA` FOREIGN KEY (`IDESCOLA`) REFERENCES `escola` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKfl39xuo4bne5smnelk9q0bv95` FOREIGN KEY (`IDESCOLA`) REFERENCES `escola` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `escola`
--

DROP TABLE IF EXISTS `escola`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `escola` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RAZAO_SOCIAL` varchar(100) NOT NULL,
  `DT_FUNDACAO` datetime DEFAULT NULL,
  `ENDERECO` varchar(100) DEFAULT NULL,
  `CNPJ` varchar(14) NOT NULL,
  `NOME_FANTASIA` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `CNPJ_UNIQUE` (`CNPJ`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-10 17:22:23
