/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-10-12 16:33:52
*/

# 大文件上传的两个表， file_md5快速上传，存储md5，检测md5是否存在，file_upload_status存储上传状态
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_md5
-- ----------------------------
DROP TABLE IF EXISTS `file_md5`;
CREATE TABLE `file_md5` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileMd5` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_upload_status
-- ----------------------------
DROP TABLE IF EXISTS `file_upload_status`;
CREATE TABLE `file_upload_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileMd5` varchar(255) DEFAULT NULL,
  `uploadStatus` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
