/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-15 23:22:07
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `name`  varchar(50)  DEFAULT NULL,
    `age`   int(11)      DEFAULT NULL,
    `grade` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher`
VALUES ('1', '张大锤', '26', '本科');
INSERT INTO `teacher`
VALUES ('2', '杭晓兰', '23', '专科');
INSERT INTO `teacher`
VALUES ('3', '昂熊二', '22', '研究生');
INSERT INTO `teacher`
VALUES ('4', '王杰瑞', '25', '研究生');
