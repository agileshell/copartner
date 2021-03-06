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
INSERT INTO `location` (`id`,`name`,`parent_id`,`is_listed`,`created`) VALUES 
(1,'北京',0,1,NOW()),
(2,'上海',0,1,NOW()),
(3,'天津',0,1,NOW()),
(4,'重庆',0,1,NOW()),
(5,'辽宁',0,1,NOW()),
(6,'吉林',0,1,NOW()),
(7,'黑龙江',0,1,NOW()),
(8,'河北',0,1,NOW()),
(9,'山西',0,1,NOW()),
(10,'陕西',0,1,NOW()),
(11,'甘肃',0,1,NOW()),
(12,'青海',0,1,NOW()),
(13,'山东',0,1,NOW()),
(14,'安徽',0,1,NOW()),
(15,'江苏',0,1,NOW()),
(16,'浙江',0,1,NOW()),
(17,'河南',0,1,NOW()),
(18,'湖北',0,1,NOW()),
(19,'湖南',0,1,NOW()),
(20,'江西',0,1,NOW()),
(21,'福建',0,1,NOW()),
(22,'云南',0,1,NOW()),
(23,'海南',0,1,NOW()),
(24,'四川',0,1,NOW()),
(25,'贵州',0,1,NOW()),
(26,'广东',0,1,NOW()),
(27,'内蒙古',0,1,NOW()),
(28,'新疆',0,1,NOW()),
(29,'广西',0,1,NOW()),
(30,'西藏',0,1,NOW()),
(31,'宁夏',0,1,NOW()),
(32,'香港',0,1,NOW()),
(33,'澳门',0,1,NOW()),
(34,'台湾',0,1,NOW()),
(35,'北京',1,1,NOW()),
(36,'上海',2,1,NOW()),
(37,'天津',3,1,NOW()),
(38,'重庆',4,1,NOW()),
(39,'沈阳',5,1,NOW()),
(40,'大连',5,1,NOW()),
(41,'鞍山',5,1,NOW()),
(42,'抚顺',5,1,NOW()),
(43,'本溪',5,1,NOW()),
(44,'丹东',5,1,NOW()),
(45,'锦州',5,1,NOW()),
(46,'营口',5,1,NOW()),
(47,'阜新',5,1,NOW()),
(48,'辽阳',5,1,NOW()),
(49,'盘锦',5,1,NOW()),
(50,'铁岭',5,1,NOW()),
(51,'朝阳',5,1,NOW()),
(52,'葫芦岛',5,1,NOW()),
(53,'长春',6,1,NOW()),
(54,'吉林',6,1,NOW()),
(55,'四平',6,1,NOW()),
(56,'辽源',6,1,NOW()),
(57,'通化',6,1,NOW()),
(58,'白山',6,1,NOW()),
(59,'松原',6,1,NOW()),
(60,'白城',6,1,NOW()),
(61,'延边',6,1,NOW()),
(62,'哈尔滨',7,1,NOW()),
(63,'齐齐哈尔',7,1,NOW()),
(64,'鸡西',7,1,NOW()),
(65,'鹤岗',7,1,NOW()),
(66,'双鸭山',7,1,NOW()),
(67,'大庆',7,1,NOW()),
(68,'伊春',7,1,NOW()),
(69,'佳木斯',7,1,NOW()),
(70,'七台河',7,1,NOW()),
(71,'牡丹江',7,1,NOW()),
(72,'黑河',7,1,NOW()),
(73,'绥化',7,1,NOW()),
(74,'大兴安岭',7,1,NOW()),
(75,'石家庄',8,1,NOW()),
(76,'唐山',8,1,NOW()),
(77,'秦皇岛',8,1,NOW()),
(78,'邯郸',8,1,NOW()),
(79,'邢台',8,1,NOW()),
(80,'保定',8,1,NOW()),
(81,'张家口',8,1,NOW()),
(82,'承德',8,1,NOW()),
(83,'沧州',8,1,NOW()),
(84,'廊坊',8,1,NOW()),
(85,'衡水',8,1,NOW()),
(86,'太原',9,1,NOW()),
(87,'大同',9,1,NOW()),
(88,'阳泉',9,1,NOW()),
(89,'长治',9,1,NOW()),
(90,'晋城',9,1,NOW()),
(91,'朔州',9,1,NOW()),
(92,'晋中',9,1,NOW()),
(93,'运城',9,1,NOW()),
(94,'忻州',9,1,NOW()),
(95,'临汾',9,1,NOW()),
(96,'吕梁',9,1,NOW()),
(97,'西安市',10,1,NOW()),
(98,'铜川市',10,1,NOW()),
(99,'宝鸡市',10,1,NOW()),
(100,'咸阳市',10,1,NOW()),
(101,'渭南市',10,1,NOW()),
(102,'延安市',10,1,NOW()),
(103,'汉中市',10,1,NOW()),
(104,'榆林市',10,1,NOW()),
(105,'安康市',10,1,NOW()),
(106,'商洛市',10,1,NOW()),
(107,'兰州',11,1,NOW()),
(108,'嘉峪关',11,1,NOW()),
(109,'金昌',11,1,NOW()),
(110,'白银',11,1,NOW()),
(111,'天水',11,1,NOW()),
(112,'武威',11,1,NOW()),
(113,'张掖',11,1,NOW()),
(114,'平凉',11,1,NOW()),
(115,'酒泉',11,1,NOW()),
(116,'庆阳',11,1,NOW()),
(117,'定西',11,1,NOW()),
(118,'陇南',11,1,NOW()),
(119,'临夏',11,1,NOW()),
(120,'甘南',11,1,NOW()),
(121,'西宁',12,1,NOW()),
(122,'海东',12,1,NOW()),
(123,'海北',12,1,NOW()),
(124,'黄南',12,1,NOW()),
(125,'海南',12,1,NOW()),
(126,'果洛',12,1,NOW()),
(127,'玉树',12,1,NOW()),
(128,'海西',12,1,NOW()),
(129,'济南',13,1,NOW()),
(130,'青岛',13,1,NOW()),
(131,'淄博',13,1,NOW()),
(132,'枣庄',13,1,NOW()),
(133,'东营',13,1,NOW()),
(134,'烟台',13,1,NOW()),
(135,'潍坊',13,1,NOW()),
(136,'济宁',13,1,NOW()),
(137,'泰安',13,1,NOW()),
(138,'威海',13,1,NOW()),
(139,'日照',13,1,NOW()),
(140,'莱芜',13,1,NOW()),
(141,'临沂',13,1,NOW()),
(142,'德州',13,1,NOW()),
(143,'聊城',13,1,NOW()),
(144,'滨州',13,1,NOW()),
(145,'菏泽',13,1,NOW()),
(146,'合肥',14,1,NOW()),
(147,'芜湖',14,1,NOW()),
(148,'蚌埠',14,1,NOW()),
(149,'淮南',14,1,NOW()),
(150,'马鞍山',14,1,NOW()),
(151,'淮北',14,1,NOW()),
(152,'铜陵',14,1,NOW()),
(153,'安庆',14,1,NOW()),
(154,'黄山',14,1,NOW()),
(155,'滁州',14,1,NOW()),
(156,'阜阳',14,1,NOW()),
(157,'宿州',14,1,NOW()),
(158,'巢湖',14,1,NOW()),
(159,'六安',14,1,NOW()),
(160,'亳州',14,1,NOW()),
(161,'池州',14,1,NOW()),
(162,'宣城',14,1,NOW()),
(163,'南京',15,1,NOW()),
(164,'无锡',15,1,NOW()),
(165,'徐州',15,1,NOW()),
(166,'常州',15,1,NOW()),
(167,'苏州',15,1,NOW()),
(168,'南通',15,1,NOW()),
(169,'连云港',15,1,NOW()),
(170,'淮安',15,1,NOW()),
(171,'盐城',15,1,NOW()),
(172,'扬州',15,1,NOW()),
(173,'镇江',15,1,NOW()),
(174,'泰州',15,1,NOW()),
(175,'宿迁',15,1,NOW()),
(176,'杭州',16,1,NOW()),
(177,'宁波',16,1,NOW()),
(178,'温州',16,1,NOW()),
(179,'嘉兴',16,1,NOW()),
(180,'湖州',16,1,NOW()),
(181,'绍兴',16,1,NOW()),
(182,'金华',16,1,NOW()),
(183,'衢州',16,1,NOW()),
(184,'舟山',16,1,NOW()),
(185,'台州',16,1,NOW()),
(186,'丽水',16,1,NOW()),
(187,'郑州',17,1,NOW()),
(188,'开封',17,1,NOW()),
(189,'洛阳',17,1,NOW()),
(190,'平顶山',17,1,NOW()),
(191,'安阳',17,1,NOW()),
(192,'鹤壁',17,1,NOW()),
(193,'新乡',17,1,NOW()),
(194,'焦作',17,1,NOW()),
(195,'濮阳',17,1,NOW()),
(196,'许昌',17,1,NOW()),
(197,'漯河',17,1,NOW()),
(198,'三门峡',17,1,NOW()),
(199,'南阳',17,1,NOW()),
(200,'商丘',17,1,NOW()),
(201,'信阳',17,1,NOW()),
(202,'周口',17,1,NOW()),
(203,'驻马店',17,1,NOW()),
(204,'武汉',18,1,NOW()),
(205,'黄石',18,1,NOW()),
(206,'十堰',18,1,NOW()),
(207,'宜昌',18,1,NOW()),
(208,'襄樊',18,1,NOW()),
(209,'鄂州',18,1,NOW()),
(210,'荆门',18,1,NOW()),
(211,'孝感',18,1,NOW()),
(212,'荆州',18,1,NOW()),
(213,'黄冈',18,1,NOW()),
(214,'咸宁',18,1,NOW()),
(215,'随州',18,1,NOW()),
(216,'恩施',18,1,NOW()),
(217,'长沙',19,1,NOW()),
(218,'株洲',19,1,NOW()),
(219,'湘潭',19,1,NOW()),
(220,'衡阳',19,1,NOW()),
(221,'邵阳',19,1,NOW()),
(222,'岳阳',19,1,NOW()),
(223,'常德',19,1,NOW()),
(224,'张家界',19,1,NOW()),
(225,'益阳',19,1,NOW()),
(226,'郴州',19,1,NOW()),
(227,'永州',19,1,NOW()),
(228,'怀化',19,1,NOW()),
(229,'娄底',19,1,NOW()),
(230,'湘西',19,1,NOW()),
(231,'南昌',20,1,NOW()),
(232,'景德镇',20,1,NOW()),
(233,'萍乡',20,1,NOW()),
(234,'九江',20,1,NOW()),
(235,'新余',20,1,NOW()),
(236,'鹰潭',20,1,NOW()),
(237,'赣州',20,1,NOW()),
(238,'吉安',20,1,NOW()),
(239,'宜春',20,1,NOW()),
(240,'抚州',20,1,NOW()),
(241,'上饶',20,1,NOW()),
(242,'福州',21,1,NOW()),
(243,'厦门',21,1,NOW()),
(244,'莆田',21,1,NOW()),
(245,'三明',21,1,NOW()),
(246,'泉州',21,1,NOW()),
(247,'漳州',21,1,NOW()),
(248,'南平',21,1,NOW()),
(249,'龙岩',21,1,NOW()),
(250,'宁德',21,1,NOW()),
(251,'昆明',22,1,NOW()),
(252,'曲靖',22,1,NOW()),
(253,'玉溪',22,1,NOW()),
(254,'保山',22,1,NOW()),
(255,'昭通',22,1,NOW()),
(256,'丽江',22,1,NOW()),
(257,'思茅',22,1,NOW()),
(258,'临沧',22,1,NOW()),
(259,'楚雄',22,1,NOW()),
(260,'红河',22,1,NOW()),
(261,'文山',22,1,NOW()),
(262,'西双版纳',22,1,NOW()),
(263,'大理',22,1,NOW()),
(264,'德宏',22,1,NOW()),
(265,'怒江',22,1,NOW()),
(266,'迪庆',22,1,NOW()),
(267,'海口',23,1,NOW()),
(268,'三亚',23,1,NOW()),
(269,'成都',24,1,NOW()),
(270,'自贡',24,1,NOW()),
(271,'攀枝花',24,1,NOW()),
(272,'泸州',24,1,NOW()),
(273,'德阳',24,1,NOW()),
(274,'绵阳',24,1,NOW()),
(275,'广元',24,1,NOW()),
(276,'遂宁',24,1,NOW()),
(277,'内江',24,1,NOW()),
(278,'乐山',24,1,NOW()),
(279,'南充',24,1,NOW()),
(280,'眉山',24,1,NOW()),
(281,'宜宾',24,1,NOW()),
(282,'广安',24,1,NOW()),
(283,'达州',24,1,NOW()),
(284,'雅安',24,1,NOW()),
(285,'巴中',24,1,NOW()),
(286,'资阳',24,1,NOW()),
(287,'阿坝',24,1,NOW()),
(288,'甘孜',24,1,NOW()),
(289,'凉山',24,1,NOW()),
(290,'贵阳',25,1,NOW()),
(291,'六盘水',25,1,NOW()),
(292,'遵义',25,1,NOW()),
(293,'安顺',25,1,NOW()),
(294,'铜仁',25,1,NOW()),
(295,'黔西南',25,1,NOW()),
(296,'毕节',25,1,NOW()),
(297,'黔东南',25,1,NOW()),
(298,'黔南',25,1,NOW()),
(299,'广州',26,1,NOW()),
(300,'韶关',26,1,NOW()),
(301,'深圳',26,1,NOW()),
(302,'珠海',26,1,NOW()),
(303,'汕头',26,1,NOW()),
(304,'佛山',26,1,NOW()),
(305,'江门',26,1,NOW()),
(306,'湛江',26,1,NOW()),
(307,'茂名',26,1,NOW()),
(308,'肇庆',26,1,NOW()),
(309,'惠州',26,1,NOW()),
(310,'梅州',26,1,NOW()),
(311,'汕尾',26,1,NOW()),
(312,'河源',26,1,NOW()),
(313,'阳江',26,1,NOW()),
(314,'清远',26,1,NOW()),
(315,'东莞',26,1,NOW()),
(316,'中山',26,1,NOW()),
(317,'潮州',26,1,NOW()),
(318,'揭阳',26,1,NOW()),
(319,'云浮',26,1,NOW()),
(320,'呼和浩特',27,1,NOW()),
(321,'包头',27,1,NOW()),
(322,'乌海',27,1,NOW()),
(323,'赤峰',27,1,NOW()),
(324,'通辽',27,1,NOW()),
(325,'鄂尔多斯',27,1,NOW()),
(326,'呼伦贝尔',27,1,NOW()),
(327,'巴彦淖尔',27,1,NOW()),
(328,'乌兰察布',27,1,NOW()),
(329,'兴安盟',27,1,NOW()),
(330,'锡林郭勒盟',27,1,NOW()),
(331,'阿拉善盟',27,1,NOW()),
(332,'乌鲁木齐',28,1,NOW()),
(333,'克拉玛依',28,1,NOW()),
(334,'吐鲁番',28,1,NOW()),
(335,'哈密',28,1,NOW()),
(336,'昌吉',28,1,NOW()),
(337,'博尔塔拉',28,1,NOW()),
(338,'巴音郭楞',28,1,NOW()),
(339,'阿克苏',28,1,NOW()),
(340,'克州',28,1,NOW()),
(341,'喀什',28,1,NOW()),
(342,'和田',28,1,NOW()),
(343,'伊犁',28,1,NOW()),
(344,'塔城',28,1,NOW()),
(345,'阿勒泰',28,1,NOW()),
(346,'南宁',29,1,NOW()),
(347,'柳州',29,1,NOW()),
(348,'桂林',29,1,NOW()),
(349,'梧州',29,1,NOW()),
(350,'北海',29,1,NOW()),
(351,'防城港',29,1,NOW()),
(352,'钦州',29,1,NOW()),
(353,'贵港',29,1,NOW()),
(354,'玉林',29,1,NOW()),
(355,'百色',29,1,NOW()),
(356,'贺州',29,1,NOW()),
(357,'河池',29,1,NOW()),
(358,'来宾',29,1,NOW()),
(359,'崇左',29,1,NOW()),
(360,'拉萨',30,1,NOW()),
(361,'昌都',30,1,NOW()),
(362,'山南',30,1,NOW()),
(363,'日喀则',30,1,NOW()),
(364,'那曲',30,1,NOW()),
(365,'阿里',30,1,NOW()),
(366,'林芝',30,1,NOW()),
(367,'银川',31,1,NOW()),
(368,'石嘴山',31,1,NOW()),
(369,'吴忠',31,1,NOW()),
(370,'固原',31,1,NOW()),
(371,'中卫',31,1,NOW()),
(372,'香港',32,1,NOW()),
(373,'澳门',33,1,NOW()),
(374,'台湾',34,1,NOW());

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
(11, '其它', 1, NOW()),
(12, '农业', 1, NOW()),
(13, '环保', 1, NOW());

TRUNCATE `startup_role`;
INSERT INTO `startup_role` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '产品', 1, NOW()),
(2, '技术', 1, NOW()),
(3, '运营', 1, NOW()),
(4, '营销', 1, NOW()),
(5, '设计', 1, NOW()),
(6, '管理', 1, NOW()),
(7, '其它', 1, NOW());

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
(3,'config','config_smtp_username','copartner2015@126.com',NOW(),NULL),
(4,'config','config_smtp_password','abc123_',NOW(),NULL),
(5,'config','config_smtp_nickname','创客汇',NOW(),NULL),
(6,'config','config_sms_uid','zing',NOW(),NULL),
(7,'config','config_sms_key','keytobechanged',NOW(),NULL),
(8,'app_info','ios_version','1.0.1',NOW(),NULL),
(9,'app_info','android_version','1.0.1',NOW(),NULL),
(10,'app_info','ios_downloadURL','http://7xjbd9.com1.z0.glb.clouddn.com/chuangkehui1.0.1.ipa',NOW(),NULL),
(11,'app_info','android_downloadURL','http://7xjbd9.com1.z0.glb.clouddn.com/chuangkehui1.0.1.apk',NOW(),NULL),
(12,'image','image_type_limit','JPG,JPEG,GIF,PNG,BMP',NOW(),NULL),
(13,'image','image_max_size','5MB',NOW(),NULL),
(14,'image','image_min_size','1KB',NOW(),NULL),
(15,'image','image_dimension_max_width','1024',NOW(),NULL),
(16,'image','image_dimension_max_height','1024',NOW(),NULL),
(17,'image','image_dimension_min_width','50',NOW(),NULL),
(18,'image','image_dimension_min_height','50',NOW(),NULL),
(19,'vedio','vedio_type_limit','MP4,3GP,WMV,MKV,FLV,SWF',NOW(),NULL),
(20,'vedio','vedio_max_size','50MB',NOW(),NULL),
(21,'vedio','vedio_min_size','10KB',NOW(),NULL);

TRUNCATE `project_phase`;
INSERT INTO `project_phase` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '原型构建阶段', 1, NOW()),
(2, 'Beta测试阶段', 1, NOW()),
(3, '已运营', 1, NOW()),
(4, '已收益', 1, NOW());

TRUNCATE `team_size`;
INSERT INTO `team_size` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '少于10人', 1, NOW()),
(2, '10-49人', 1, NOW()),
(3, '50-99人', 1, NOW()),
(4, '100-499人', 1, NOW()),
(5, '500-999人', 1, NOW()),
(6, '1000人以上', 1, NOW());


TRUNCATE `financing_phase`;
INSERT INTO `financing_phase` (`id`, `name`, `is_listed`, `created`) VALUES
('1', '天使投资', 1, NOW()),
('2', '风险投资', 1, NOW()),
('3', '私募股权投资', 1, NOW()),
('4', '首次公开募股', 1, NOW());

TRUNCATE `question_category`;
INSERT INTO `question_category` (`id`, `name`, `is_listed`, `created`) VALUES
(1, '管理', 1, NOW()),
(2, '投资', 1, NOW()),
(3, '股权', 1, NOW()),
(4, '工商', 1, NOW()),
(5, '财务', 1, NOW()),
(6, '税务', 1, NOW()),
(7, '人资', 1, NOW()),
(8, '营销', 1, NOW()),
(9, '其他', 1, NOW());

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
