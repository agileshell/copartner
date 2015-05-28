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

TRUNCATE `location`;
INSERT INTO `location` (`id`, `parent_id`, `name`, `pinyin`, `is_listed`, `created`) VALUES
(1, 0, '北京', 'beijing', 1, NOW()),
(2, 0, '上海', 'shanghai', 1, NOW()),
(3, 0, '天津', 'tianjin', 1, NOW()),
(4, 0, '重庆', 'chongqing', 1, NOW()),
(5, 0, '辽宁', 'liaoning', 1, NOW()),
(6, 0, '吉林', 'jilin', 1, NOW()),
(7, 0, '黑龙江', 'heilongjiang', 1, NOW()),
(8, 0, '河北', 'hebei', 1, NOW()),
(9, 0, '山西', 'shanxi', 1, NOW()),
(10, 0, '陕西', 'shanxi', 1, NOW()),
(11, 0, '甘肃', 'gansu', 1, NOW()),
(12, 0, '青海', 'qinghai', 1, NOW()),
(13, 0, '山东', 'shandong', 1, NOW()),
(14, 0, '安徽', 'anhui', 1, NOW()),
(15, 0, '江苏', 'jiangsu', 1, NOW()),
(16, 0, '浙江', 'zhejiang', 1, NOW()),
(17, 0, '河南', 'henan', 1, NOW()),
(18, 0, '湖北', 'hubei', 1, NOW()),
(19, 0, '湖南', 'hunan', 1, NOW()),
(20, 0, '江西', 'jiangxi', 1, NOW()),
(21, 0, '福建', 'fujian', 1, NOW()),
(22, 0, '云南', 'yunnan', 1, NOW()),
(23, 0, '海南', 'hannan', 1, NOW()),
(24, 0, '四川', 'sichuan', 1, NOW()),
(25, 0, '贵州', 'guizhou', 1, NOW()),
(26, 0, '广东', 'guangdong', 1, NOW()),
(27, 0, '内蒙古', 'neimenggu', 1, NOW()),
(28, 0, '新疆', 'xinjiang', 1, NOW()),
(29, 0, '广西', 'guangxi', 1, NOW()),
(30, 0, '西藏', 'xizhang', 1, NOW()),
(31, 0, '宁夏', 'ningxia', 1, NOW()),
(32, 0, '香港', 'xianggang', 1, NOW()),
(33, 0, '澳门', 'aomen', 1, NOW()),
(34, 0, '台湾', 'taiwan', 1, NOW()),
(35, 1, '北京', 'beijing', 1, NOW()),
(36, 2, '上海', 'shanghai', 1, NOW()),
(37, 3, '天津', 'tianjin', 1, NOW()),
(38, 4, '重庆', 'chongqing', 1, NOW());

TRUNCATE `industry_domain`;
INSERT INTO `industry_domain` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '互联网', 1, NOW()),
(2, '移动互联网', 1, NOW()),
(3, '电子商务', 1, NOW()),
(4, '社交', 1, NOW()),
(5, '游戏', 1, NOW()),
(6, '云计算', 1, NOW()),
(7, 'IT服务', 1, NOW()),
(8, '软件/工具', 1, NOW()),
(9, '软硬件', 1, NOW()),
(10, '数码电子', 1, NOW()),
(11, '其它', 1, NOW());

TRUNCATE `startup_role`;
INSERT INTO `startup_role` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '产品', 1, NOW()),
(2, '技术', 1, NOW()),
(3, '运营', 1, NOW()),
(4, '营销', 1, NOW()),
(5, '设计', 1, NOW()),
(6, '其它', 1, NOW());

TRUNCATE `startup_status`;
INSERT INTO `startup_status` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '有创业意愿', 1, NOW()),
(2, '有创业想法', 1, NOW()),
(3, '全职创业中', 1, NOW()),
(4, '兼职创业中', 1, NOW()),
(5, '投资人', 1, NOW()),
(6, '媒体人', 1, NOW());

TRUNCATE `system_setting`;
INSERT INTO `system_setting` (`id`, `group`,`key`,`value`,`created`,`updated`) VALUES
(1,'config','config_smtp_host','smtp.163.com',NOW(),NULL),
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
