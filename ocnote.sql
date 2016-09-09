/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.29 : Database - ocnote
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ocnote` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ocnote`;

/*Table structure for table `primcategory` */

DROP TABLE IF EXISTS `primcategory`;

CREATE TABLE `primcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `primcategory_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `primcategory` */

insert  into `primcategory`(`id`,`uid`,`categoryName`,`createTime`) values (23,18,'十分士大夫','2016-09-06'),(24,18,'发士大夫撒旦','2016-09-06'),(25,17,'kakaka','2016-09-06');

/*Table structure for table `secategory` */

DROP TABLE IF EXISTS `secategory`;

CREATE TABLE `secategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `secategory_ibfk_2` (`pid`),
  CONSTRAINT `secategory_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `secategory_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `primcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `secategory` */

insert  into `secategory`(`id`,`categoryName`,`createTime`,`uid`,`pid`) values (12,'发射点发射点','2016-09-06',18,23),(13,'似的发射点发射点','2016-09-06',18,24);

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskName` varchar(255) DEFAULT NULL,
  `taskTest` text,
  `createDate` date DEFAULT NULL,
  `isComplation` tinyint(4) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `pid` (`pid`),
  KEY `sid` (`sid`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `primcategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_ibfk_3` FOREIGN KEY (`sid`) REFERENCES `secategory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `task` */

insert  into `task`(`id`,`taskName`,`taskTest`,`createDate`,`isComplation`,`uid`,`pid`,`sid`) values (23,'是打发十分','似的发射点的说法萨芬','2016-01-01',1,18,23,12),(25,'发士大夫士大夫','dsfsd斗鼠厨房','2016-01-01',1,18,24,13);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`token`,`email`) values (17,'lalal','123456',NULL,'123456@qq.com'),(18,'asdd','b08917ecddc9baf1953a3de32ae4e170',NULL,'long@qq.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
