/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-07 16:29:01
*/

-- https://docs.spring.io/spring-session/docs/2.1.0.M2/reference/html5/?spm=a2c6h.12873639.0.0.577133bcQsjG5Q#httpsession-jdbc
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for spring_session
-- ----------------------------
DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session` (
  `primary_id` char(36) NOT NULL,
  `session_id` char(36) NOT NULL,
  `creation_time` bigint(20) NOT NULL,
  `last_access_time` bigint(20) NOT NULL,
  `max_inactive_interval` int(11) NOT NULL,
  `expiry_time` bigint(20) NOT NULL,
  `principal_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`primary_id`),
  UNIQUE KEY `spring_session_ix1` (`session_id`),
  KEY `spring_session_ix2` (`expiry_time`),
  KEY `spring_session_ix3` (`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spring_session
-- ----------------------------
