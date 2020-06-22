/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-06-22 18:05:07
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
  `stAge` char(5) DEFAULT '' COMMENT '学生年龄',
  `stGender` char(2) DEFAULT '' COMMENT '学生性别',
  `classId` int(11) DEFAULT NULL COMMENT '班级Id',
  `hobbyGroupId` int(11) DEFAULT NULL COMMENT '兴趣社Id',
  `stHome` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `cityId` int(11) DEFAULT NULL COMMENT '城市Id',
  PRIMARY KEY (`stId`)
) ENGINE=InnoDB AUTO_INCREMENT=99173 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('0', 'kiristina', '22', '\0', '0', '1', '四川省乐山市', '0');
INSERT INTO `student` VALUES ('100', '张飞', '23', '0', '103', '1', '河北涿郡张家村', '100');
INSERT INTO `student` VALUES ('101', '关羽', '26', '0', '103', '1', '山西运城关家庄', '105');
INSERT INTO `student` VALUES ('102', '赵云', '21', '0', '106', '2', '山西大同小圆村', '108');
INSERT INTO `student` VALUES ('103', '刘备', '31', '0', '104', '5', '江苏沛县柳家屯', '112');
INSERT INTO `student` VALUES ('104', '孙尚香', '23', '1', '201', '3', '江苏南京下马坊', '117');
INSERT INTO `student` VALUES ('105', '孙策', '33', '0', '109', '2', '湖北武汉大柳树', '109');
INSERT INTO `student` VALUES ('106', '貂蝉', '21', '1', '107', '6', '湖北秭归米花村', '128');
INSERT INTO `student` VALUES ('107', '郭嘉', '21', '0', '107', '3', '山东琅琊郭家村', '103');
INSERT INTO `student` VALUES ('108', '诸葛亮', '23', '0', '102', '3', '河南驻马店卧龙村', '123');
INSERT INTO `student` VALUES ('109', '关苞', '3', '0', '0', '0', '山西运城关家庄', '105');
INSERT INTO `student` VALUES ('110', '廖化', '17', '0', '117', '1', '山西运城小黄山', '105');
INSERT INTO `student` VALUES ('1660', 'kiristina', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('1753', 'lisi', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('2608', '张三', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('6957', 'MG-HOTdog!', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('7085', 'MG-HOTdog!', '22', '男', '2', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('13886', '雨涵', '22', '男', '2', '1', '四川省成都市', '1');
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
  `roles` varchar(100) NOT NULL COMMENT '角色信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1262050682745253890 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '李四', '18', 'mg123@qq.com', '2020-05-17 23:37:48', '2020-05-17 17:43:25', '2', '0', '1', 'user:add', '');
INSERT INTO `user` VALUES ('2', '张自忠', '20', 'helloworld@gmail.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '1', 'user:read', '');
INSERT INTO `user` VALUES ('3', '李四111', '28', 'mg123@qq.com', '2020-05-17 23:37:48', '2020-05-17 18:00:40', '4', '0', '1', 'user:update', '');
INSERT INTO `user` VALUES ('4', 'Amy', '21', 'test4@baomidou.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '1', '1', 'role:admin');
INSERT INTO `user` VALUES ('5', 'MG', '24', 'test5@baomidou.com', '2020-05-17 23:37:48', '2020-05-17 23:37:48', '1', '0', '1', 'user:money', '');
