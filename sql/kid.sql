/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-09 13:12:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kid
-- ----------------------------
DROP TABLE IF EXISTS `kid`;
CREATE TABLE `kid` (
  `id` varchar(20) NOT NULL DEFAULT '',
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kid
-- ----------------------------
INSERT INTO `kid` VALUES ('111', '张三', '123');
INSERT INTO `kid` VALUES ('112', '李四', '124');
INSERT INTO `kid` VALUES ('113', '小红', '125');
