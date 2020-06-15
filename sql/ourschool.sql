/*
Navicat MySQL Data Transfer

Source Server         : mariadb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-06-16 01:43:16
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of search_location
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stId` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `stName` varchar(50) DEFAULT '' COMMENT '学生姓名',
  `stAge` int(11) DEFAULT NULL COMMENT '学生年龄',
  `stGender` char(2) DEFAULT '' COMMENT '学生性别',
  `classId` int(11) DEFAULT NULL COMMENT '班级Id',
  `hobbyGroupId` int(11) DEFAULT NULL COMMENT '兴趣社Id',
  `stHome` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `cityId` int(11) DEFAULT NULL COMMENT '城市Id',
  PRIMARY KEY (`stId`)
) ENGINE=InnoDB AUTO_INCREMENT=97619 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('100', '张飞', '26', '1', '103', '1', '河北涿郡张家村', '100');
INSERT INTO `student` VALUES ('101', '关羽', '29', '1', '103', '1', '山西运城关家庄', '105');
INSERT INTO `student` VALUES ('102', '赵云', '24', '1', '106', '2', '山西大同小圆村', '108');
INSERT INTO `student` VALUES ('103', '刘备', '34', '1', '104', '5', '江苏沛县柳家屯', '112');
INSERT INTO `student` VALUES ('104', '孙尚香', '22', '1', '201', '3', '江苏南京下马坊', '117');
INSERT INTO `student` VALUES ('105', '孙策', '32', '1', '109', '2', '湖北武汉大柳树', '109');
INSERT INTO `student` VALUES ('106', '貂蝉', '20', '1', '107', '6', '湖北秭归米花村', '128');
INSERT INTO `student` VALUES ('107', '郭嘉', '20', '1', '107', '3', '山东琅琊郭家村', '103');
INSERT INTO `student` VALUES ('108', '诸葛亮', '22', '1', '102', '3', '河南驻马店卧龙村', '123');
INSERT INTO `student` VALUES ('109', '关苞', '6', '1', '0', '0', '山西运城关家庄', '105');
INSERT INTO `student` VALUES ('110', '廖化', '22', '1', '117', '1', '山西运城小黄山', '105');
INSERT INTO `student` VALUES ('1332', '雨涵', '23', '1', '2', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('2390', 'kiristina', '22', '男', '2', '1', '江苏省南京市', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  `deleted` int(11) DEFAULT 0 COMMENT '逻辑删除',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `perms` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1262050682745253890 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '李四', '18', 'mg123@qq.com', '2020-05-17 23:37:48', '2020-05-17 17:43:25', '2', '0', '', 'user:add');
INSERT INTO `user` VALUES ('2', '张自忠', '20', 'helloworld@gmail.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '', 'user:read');
INSERT INTO `user` VALUES ('3', '李四111', '28', 'mg123@qq.com', '2020-05-17 23:37:48', '2020-05-17 18:00:40', '4', '0', '', 'user:update');
INSERT INTO `user` VALUES ('4', 'Sandy', '21', 'test4@baomidou.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '', '');
INSERT INTO `user` VALUES ('5', 'Billie', '24', 'test5@baomidou.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '', '');
INSERT INTO `user` VALUES ('1262037913186697217', '张三', '3', '2246890834578@qq.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '', '');
INSERT INTO `user` VALUES ('1262037913186697218', '张三', '3', '2246890834578@qq.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '', '');
INSERT INTO `user` VALUES ('1262041503661940738', '张三', '3', '2246890834578@qq.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '1', '', '');
INSERT INTO `user` VALUES ('1262043073220792322', '张三', '3', '2246890834578@qq.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '1', '', '');
INSERT INTO `user` VALUES ('1262050682745253889', 'Kings !', '3', '2246890834578@qq.com', '2020-05-17 16:01:45', '2020-05-17 16:01:45', '1', '0', '', '');
