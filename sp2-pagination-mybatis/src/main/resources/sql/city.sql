/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : myschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-04-06 21:55:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `ctId` int(11) NOT NULL AUTO_INCREMENT,
  `ctName` varchar(50) NOT NULL,
  `ctProvince` varchar(50) NOT NULL,
  PRIMARY KEY (`ctId`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('100', '成都', '四川');
INSERT INTO `city` VALUES ('101', '重庆', '重庆');
INSERT INTO `city` VALUES ('102', '天水', '甘肃');
INSERT INTO `city` VALUES ('103', '绵阳', '四川');
INSERT INTO `city` VALUES ('104', '西安', '陕西');
INSERT INTO `city` VALUES ('105', '青岛', '山东');
INSERT INTO `city` VALUES ('106', '苏州', '江苏');
INSERT INTO `city` VALUES ('107', '上海', '上海');
INSERT INTO `city` VALUES ('108', '南京', '江苏');
INSERT INTO `city` VALUES ('109', '广州', '广东');
INSERT INTO `city` VALUES ('110', '深圳', '广东');
INSERT INTO `city` VALUES ('111', '北京', '北京');
INSERT INTO `city` VALUES ('112', '河源', '广东');
INSERT INTO `city` VALUES ('113', '肇庆', '广东');
INSERT INTO `city` VALUES ('114', '汕头', '广东');
INSERT INTO `city` VALUES ('115', '潮州', '广东');
INSERT INTO `city` VALUES ('116', '汕尾', '广东');
INSERT INTO `city` VALUES ('117', '佛山', '广东');
INSERT INTO `city` VALUES ('118', '东莞', '广东');
INSERT INTO `city` VALUES ('119', 'xxx', '广东');
