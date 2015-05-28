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

USE `copartner`;

TRUNCATE `system_setting`;
INSERT INTO `system_setting` (`id`, `group`,`key`,`value`,`created`,`updated`) VALUES (1,'config','config_smtp_host','smtp.163.com',NOW(),NULL),
(2,'config','config_smtp_port','25',NOW(),NULL),
(3,'config','config_smtp_username','ec_java@163.com',NOW(),NULL),
(4,'config','config_smtp_password','abc123_',NOW(),NULL),
(5,'config','config_smtp_nickname','ec',NOW(),NULL),
(6,'config','config_sms_uid','chenqian1126',NOW(),NULL),
(7,'config','config_sms_key','540ad1747eb187896cd5',NOW(),NULL),
(8,'app_info','ios_version','1.0.1',NOW(),NULL),
(9,'app_info','android_version','1.0.1',NOW(),NULL),
(10,'app_info','ios_downloadURL','http://7xjbd9.com1.z0.glb.clouddn.com/chuangkehui1.0.1.ipa',NOW(),NULL),
(11,'app_info','android_downloadURL','http://7xjbd9.com1.z0.glb.clouddn.com/chuangkehui1.0.1.apk',NOW(),NULL),
(12,'image','file_type_limit','JPG,JPEG,GIF,PNG,BMP',NOW(),NULL),
(13,'image','file_max_size','5MB',NOW(),NULL),
(14,'image','file_min_size','1KB',NOW(),NULL),
(15,'image','file_dimension_max_width','1024',NOW(),NULL),
(16,'image','file_dimension_max_height','1024',NOW(),NULL),
(17,'image','file_dimension_min_width','50',NOW(),NULL),
(18,'image','file_dimension_min_height','50',NOW(),NULL);
    
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
