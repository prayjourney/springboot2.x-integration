DROP TABLE IF EXISTS `scenery`;
CREATE TABLE `scenery`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT COMMENT '景色id',
    `name`    varchar(25) NOT NULL COMMENT '名称',
    `country` varchar(50) NOT NULL COMMENT '国家',
    `city`    varchar(50) NOT NULL COMMENT '城市',
    `area`    int(2)       DEFAULT NULL COMMENT '面积',
    `addr`    varchar(100) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of scenery
-- ----------------------------
INSERT INTO `scenery`
VALUES ('1', '八达岭长城', '中国', '北京', '22', '北京八达岭');
INSERT INTO `scenery`
VALUES ('2', '颐和园', '中国', '北京', '35', '北京海淀区颐和园');
INSERT INTO `scenery`
VALUES ('3', '富士山', '日本', '东京', '272', '东京富士山');