/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.13 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `triple` */

DROP TABLE IF EXISTS `triple`;

CREATE TABLE `triple` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `arg1` varchar(255) DEFAULT NULL,
  `rel` varchar(255) DEFAULT NULL,
  `arg2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `triple` */

insert  into `triple`(`id`,`arg1`,`rel`,`arg2`) values (1,'Stanford University','is located in','California . California'),(2,'California . California','is the most populous state in','the United States'),(3,'Stanford University','is','a great university'),(4,'Stanford University','was founded by','Leland Stanford');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
