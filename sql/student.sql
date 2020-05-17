/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-05-15 18:19:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stId` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `stName` varchar(50) DEFAULT '' COMMENT '学生姓名',
  `stAge` int(11) COMMENT '学生年龄',
  `stGender` char(2) DEFAULT '' COMMENT '学生性别',
  `classId` int(11) DEFAULT NULL COMMENT '班级Id',
  `hobbyGroupId` int(11) DEFAULT NULL COMMENT '兴趣社Id',
  `stHome` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `cityId` int(11) DEFAULT NULL COMMENT '城市Id',
  PRIMARY KEY (`stId`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('100', '张飞', 23, '0', '103', '1', '河北涿郡张家村', '100');
INSERT INTO `student` VALUES ('101', '关羽', 26, '0', '103', '1', '山西运城关家庄', '105');
INSERT INTO `student` VALUES ('102', '赵云', 21, '0', '106', '2', '山西大同小圆村', '108');
INSERT INTO `student` VALUES ('103', '刘备', 31, '0', '104', '5', '江苏沛县柳家屯', '112');
INSERT INTO `student` VALUES ('104', '孙尚香', 23, '1', '201', '3', '江苏南京下马坊', '117');
INSERT INTO `student` VALUES ('105', '孙策', 33, '0', '109', '2', '湖北武汉大柳树', '109');
INSERT INTO `student` VALUES ('106', '貂蝉', 21, '1', '107', '6', '湖北秭归米花村', '128');
INSERT INTO `student` VALUES ('107', '郭嘉', 21, '0', '107', '3', '山东琅琊郭家村', '103');
INSERT INTO `student` VALUES ('108', '诸葛亮', 23, '0', '102', '3', '河南驻马店卧龙村', '123');
INSERT INTO `student` VALUES ('109', '关苞', 3, '0', '0', '0', '山西运城关家庄', '105');
INSERT INTO `student` VALUES ('110', '廖化', 17, '0', '117', '1', '山西运城小黄山', '105');
