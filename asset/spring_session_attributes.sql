/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-07 16:29:07
*/

-- https://docs.spring.io/spring-session/docs/2.1.0.M2/reference/html5/?spm=a2c6h.12873639.0.0.577133bcQsjG5Q#httpsession-jdbc
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for spring_session_attributes
-- ----------------------------
DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes` (
  `session_primary_id` char(36) NOT NULL,
  `attribute_name` varchar(200) NOT NULL,
  `attribute_bytes` blob NOT NULL,
  PRIMARY KEY (`session_primary_id`,`attribute_name`),
  CONSTRAINT `spring_session_attributes_fk` FOREIGN KEY (`session_primary_id`) REFERENCES `spring_session` (`primary_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spring_session_attributes
-- ----------------------------
