/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ourschool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-08-07 16:29:15
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

-- https://docs.spring.io/spring-session/docs/2.1.0.M2/reference/html5/?spm=a2c6h.12873639.0.0.577133bcQsjG5Q#httpsession-jdbc
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
) ENGINE=InnoDB AUTO_INCREMENT=99175 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('0', 'kiristina', '22', '\0', '0', '1', '四川省乐山市', '0');
INSERT INTO `student` VALUES ('5', '张飞', '22', '0', '1', '2', '北京', '22');
INSERT INTO `student` VALUES ('99', '张飞', '22', '0', '1', '2', '北京', '22');
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
INSERT INTO `student` VALUES ('16356', '雨涵', '22', '男', '2', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('16444', 'MG-HOTdog!', '22', '男', '2', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('19252', '张三', '22', '男', '2', '1', '江苏省南京市', '1');
INSERT INTO `student` VALUES ('21977', '张三', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('27724', 'lisi', '22', '男', '103', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('30276', 'kiristina', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('33220', '雨涵', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('36922', '雨涵', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('46774', 'lisi', '22', '男', '2', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('51209', 'MG-HOTdog!', '22', '男', '107', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('54999', 'kiristina', '22', '男', '109', '1', '江苏省南京市', '1');
INSERT INTO `student` VALUES ('59893', 'MG-HOTdog!', '22', '男', '2', '1', '四川省乐山市', '1');
INSERT INTO `student` VALUES ('60969', '雨涵', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('64392', '张三', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('65973', 'kiristina', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('76114', '张三', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('80083', '张三', '22', '男', '2', '1', '四川省乐山市', '1');
INSERT INTO `student` VALUES ('83558', 'MG-HOTdog!', '22', '男', '2', '1', '江苏省南京市', '1');
INSERT INTO `student` VALUES ('84128', 'MG-HOTdog!', '22', '男', '2', '1', '广东省广州市', '1');
INSERT INTO `student` VALUES ('84134', '张三', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('85052', 'lisi', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('85578', '张三', '22', '男', '2', '1', '四川省成都市', '1');
INSERT INTO `student` VALUES ('92158', '雨涵', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('95722', 'kiristina', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('99172', '雨涵', '22', '男', '2', '1', '北京市', '1');
INSERT INTO `student` VALUES ('99173', '张飞', '22', '0', '1', '2', '北京', '22');
INSERT INTO `student` VALUES ('99174', '张飞', '22', '0', '1', '2', '北京', '99');

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
