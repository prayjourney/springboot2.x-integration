DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`
(
    `id`   int(11)     NOT NULL AUTO_INCREMENT COMMENT '工人id',
    `name` varchar(25) NOT NULL COMMENT '姓名',
    `age`  int(2)               DEFAULT NULL COMMENT '年龄',
    `sex`  tinyint(1)  NOT NULL DEFAULT '0' COMMENT '性别：0-男，1-女',
    `addr` varchar(100)         DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker`
VALUES ('1', '张三', '22', '0', '北京大兴');
INSERT INTO `worker`
VALUES ('2', '李四', '25', '1', '安徽合肥');