/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-15 23:22:16
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for littlestudent
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`    int(11) NOT NULL AUTO_INCREMENT,
    `name`  varchar(50)  DEFAULT NULL,
    `age`   int(11)      DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of littlestudent
-- ----------------------------
INSERT INTO `student`
VALUES ('1', '张三', '12', '171823@qq.com');
INSERT INTO `student`
VALUES ('2', '李四', '13', 'eioruioq@qq.com');
INSERT INTO `student`
VALUES ('3', '小红', '15', '123456rrr@126.com');
INSERT INTO `student`
VALUES ('4', 'Amy', '9', 'amy@hotmail.com');
