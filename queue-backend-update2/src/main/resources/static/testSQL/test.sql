/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.23 : Database - queue
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`queue` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `queue`;

/*Table structure for table `queue_customer` */

DROP TABLE IF EXISTS `queue_customer`;

CREATE TABLE `queue_customer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '顾客自身的Id',
  `customer_level` int unsigned NOT NULL DEFAULT '3' COMMENT '顾客的等级1-Supreme,2-Gold,3-Common',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '顾客名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100021 DEFAULT CHARSET=utf8;

/*Data for the table `queue_customer` */

insert  into `queue_customer`(`id`,`customer_level`,`name`,`create_time`,`update_time`) values (100001,1,'Anna','2000-03-25 16:46:00','2000-03-25 16:46:00'),(100002,1,'Bobby','2000-03-25 16:46:21','2000-03-25 16:46:21'),(100003,1,'Curry','2000-03-25 16:49:53','2000-03-25 16:49:53'),(100004,2,'David','2000-03-25 16:50:19','2000-03-25 16:50:19'),(100005,2,'Ella','2000-03-25 16:50:29','2000-03-25 16:50:29'),(100006,2,'Fred','2000-03-25 16:52:15','2000-03-25 16:52:15'),(100007,2,'Greenwood','2000-03-25 16:52:26','2000-03-25 16:52:26'),(100008,2,'Henry','2000-03-25 16:52:39','2000-03-25 16:52:39'),(100009,3,'Lingard','2000-03-25 16:52:45','2000-03-25 16:52:45'),(100010,3,'Jackson','2000-03-25 16:52:54','2000-03-25 16:52:54'),(100011,3,'Kety','2000-03-25 16:53:18','2000-03-25 16:53:18'),(100012,3,'Michael','2000-03-25 16:53:27','2000-03-25 16:53:27'),(100013,3,'Nobel','2000-03-25 16:53:35','2000-03-25 16:53:35'),(100014,3,'Potter','2000-03-25 16:53:56','2000-03-25 16:53:56'),(100015,3,'Ronaldo','2000-03-25 16:54:07','2000-03-25 16:54:07'),(100016,3,'Sterling','2000-03-25 16:54:44','2000-03-25 16:54:44'),(100017,3,'Torres','2000-03-25 16:54:51','2000-03-25 16:54:51'),(100018,3,'Villa','2000-03-25 16:55:02','2000-03-25 16:55:02'),(100019,3,'Zlatan','2000-03-25 16:55:09','2000-03-25 16:55:09'),(100020,1,'Ivan','2021-03-25 16:46:00','2021-11-20 21:08:00');

/*Table structure for table `service_window` */

DROP TABLE IF EXISTS `service_window`;

CREATE TABLE `service_window` (
  `status` int unsigned DEFAULT '1' COMMENT '窗口状态：1：不可用；2：可用',
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '服务窗口的id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务窗口的名称',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL COMMENT '服务对应的图片url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `service_window` */

insert  into `service_window`(`status`,`id`,`name`,`create_time`,`update_time`,`img_url`) values (1,1,'Service01','2021-11-09 22:58:18','2021-11-09 22:58:21',NULL),(1,2,'Service02','2021-11-09 22:58:23','2021-11-09 22:58:25',NULL),(1,3,'Service03','2021-11-09 22:58:26','2021-11-09 22:58:28',NULL);

/*Table structure for table `user_customer` */

DROP TABLE IF EXISTS `user_customer`;

CREATE TABLE `user_customer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '客户的id，与queue_customer的id外键',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_customer_ibfk_1` FOREIGN KEY (`id`) REFERENCES `queue_customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=100021 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_customer` */

insert  into `user_customer`(`id`,`username`,`password`) values (100001,'anna','123456'),(100002,'bobby','123456'),(100003,'curry','123456'),(100004,'david','123456'),(100005,'ella','123456'),(100006,'fred','123456'),(100007,'greenwood','123456'),(100008,'henry','123456'),(100009,'lingard','123456'),(100010,'jackson','123456'),(100011,'kety','123456'),(100012,'michael','123456'),(100013,'nobel','123456'),(100014,'potter','123456'),(100015,'ronaldo','123456'),(100016,'sterling','123456'),(100017,'torres','123456'),(100018,'villa','123456'),(100019,'zlatan','123456'),(100020,'ivan','123456');

/*Table structure for table `user_service` */

DROP TABLE IF EXISTS `user_service`;

CREATE TABLE `user_service` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_service_ibfk_1` FOREIGN KEY (`id`) REFERENCES `service_window` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_service` */

insert  into `user_service`(`id`,`username`,`password`) values (1,'service01','123456'),(2,'service02','123456'),(3,'service03','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
