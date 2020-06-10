/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : xuandui_ai

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-06-09 11:02:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for search_location
-- ----------------------------
DROP TABLE IF EXISTS `search_location`;
CREATE TABLE `search_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '搜索的位置',
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `info` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
