DROP TABLE IF EXISTS `scenery`;
CREATE TABLE `scenery` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '景色id',
  `name` varchar(25) NOT NULL COMMENT '名称',
  `country` varchar(50) NOT NULL COMMENT '国家',
  `city` varchar(50) NOT NULL COMMENT '城市',
  `area` int(2) DEFAULT NULL COMMENT '面积',
  `addr` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8