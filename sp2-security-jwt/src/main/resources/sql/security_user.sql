/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-02-25 00:09:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for security_user
-- ----------------------------
DROP TABLE IF EXISTS `security_user`;
CREATE TABLE `security_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `roles` varchar(255) NOT NULL DEFAULT '' COMMENT '角色',
  `authorities` varchar(255) NOT NULL DEFAULT '' COMMENT '权限',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户可用',
  `account_non_expired` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户没有过期',
  `credentials_non_expired` tinyint(1) NOT NULL DEFAULT 1 COMMENT '凭证(密码)没有失效',
  `account_non_locked` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账户没有被锁定',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of security_user
-- ----------------------------
INSERT INTO `security_user` VALUES ('3', 'admin', '$2a$10$jSgSsYEjwe2Ibs8Q/nD4TuV7VhERSj0VD2/eNAmM.UG7c2BCWIqxm', 'ROLE_admin', 'admin,user,insert,delete,update,select', '1', '1', '1', '1', '2021-02-25 00:08:47', '2021-02-25 00:08:47');
INSERT INTO `security_user` VALUES ('4', 'zhangsan', '$2a$10$XOqhZcOnNXXash1.IxPZMO14C8FOkoI4MI.mbPqW/YjnofUfSbNt6', 'ROLE_vip', 'user,select', '1', '1', '1', '1', '2021-02-25 00:09:15', '2021-02-25 00:09:15');
