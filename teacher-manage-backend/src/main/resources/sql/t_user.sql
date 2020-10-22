/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : bootvue

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-05 00:57:34
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `name`      varchar(50)   DEFAULT NULL,
    `age`       int(3)        DEFAULT NULL,
    `salary`    double(10, 2) DEFAULT NULL,
    `phoneCode` varchar(11)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user`
VALUES ('1', '张三', '22', '3200.00', '17788991234');
INSERT INTO `t_user`
VALUES ('2', '李四', '23', '3200.00', '15988991233');
INSERT INTO `t_user`
VALUES ('3', '小泽玛利亚', '32', '13200.00', '14588991234');
INSERT INTO `t_user`
VALUES ('4', '天海翼', '29', '15200.00', '17788565234');
INSERT INTO `t_user`
VALUES ('5', '北条麻妃', '31', '22200.00', '17334491234');
INSERT INTO `t_user`
VALUES ('6', '波多野结衣', '28', '33200.00', '16768991234');
INSERT INTO `t_user`
VALUES ('7', '樱井莉亚', '40', '62500.00', '15188996634');
INSERT INTO `t_user`
VALUES ('8', '西野翔', '33', '45000.00', '15188996633');
INSERT INTO `t_user`
VALUES ('9', '雨宫琴音', '32', '33500.00', '17812227899');
