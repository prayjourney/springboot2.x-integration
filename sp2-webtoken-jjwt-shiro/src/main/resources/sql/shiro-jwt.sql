/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-02-01 19:19:18
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`         varchar(50)  NOT NULL COMMENT '权限名称',
    `display_name` varchar(255) NOT NULL COMMENT '显示名称',
    `create_time`  datetime     NOT NULL COMMENT '建立时间',
    `update_time`  timestamp    NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority`
VALUES ('1', 'addUser', '新增用户', '2021-02-01 09:50:54', '2021-02-01 09:50:54');
INSERT INTO `authority`
VALUES ('2', 'deleteUser', '删除用户', '2021-02-01 09:51:06', '2021-02-01 09:51:06');
INSERT INTO `authority`
VALUES ('3', 'updateUser', '更新用户', '2021-02-01 09:51:21', '2021-02-01 09:51:21');
INSERT INTO `authority`
VALUES ('4', 'readUser', '查看用户', '2021-02-01 09:52:07', '2021-02-01 09:52:07');
INSERT INTO `authority`
VALUES ('5', 'addMessage', '新增消息', '2021-02-01 09:53:06', '2021-02-01 09:53:06');
INSERT INTO `authority`
VALUES ('6', 'deleteMessage', '删除消息', '2021-02-01 09:53:18', '2021-02-01 09:53:18');
INSERT INTO `authority`
VALUES ('7', 'updateMessage', '更新消息', '2021-02-01 09:53:30', '2021-02-01 09:53:30');
INSERT INTO `authority`
VALUES ('8', 'readMessage', '查看消息', '2021-02-01 09:53:56', '2021-02-01 17:54:39');

-- ----------------------------
-- Table structure for op_user
-- ----------------------------
DROP TABLE IF EXISTS `op_user`;
CREATE TABLE `op_user`
(
    `id`                  int(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `username`            varchar(50)  NOT NULL COMMENT '用户名',
    `password`            varchar(255) NOT NULL COMMENT '密码',
    `salt`                varchar(255) NOT NULL COMMENT '盐',
    `email`               varchar(255) NOT NULL DEFAULT '' COMMENT 'Email',
    `real_name`           varchar(50)  NOT NULL DEFAULT '' COMMENT '真实姓名',
    `tel`                 varchar(50)  NOT NULL DEFAULT '' COMMENT '联系电话',
    `account_disabled`    bit(1)       NOT NULL DEFAULT b'0' COMMENT '账号失效',
    `account_expired`     bit(1)       NOT NULL DEFAULT b'0' COMMENT '账号过期',
    `account_locked`      bit(1)       NOT NULL DEFAULT b'0' COMMENT '账号冻结',
    `credentials_expired` bit(1)       NOT NULL DEFAULT b'0' COMMENT '密码过期',
    `create_time`         datetime     NOT NULL COMMENT '建立时间',
    `update_time`         timestamp    NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of op_user
-- ----------------------------
INSERT INTO `op_user`
VALUES ('1', 'admin', '49977e4d084c00ce902690d0363691aef3032955b257205c3ba638a61089209b',
        'bad8579d-a4b6-4eda-819b-82a5cbb0384e', 'admin@qq.com', 'admin', '18888889999', '\0', '\0', '\0', '\0',
        '2021-02-01 08:47:26', '2021-02-01 08:47:26');
INSERT INTO `op_user`
VALUES ('2', 'zhangsan', '80c9e093863b4ba526a722ef0c942a60577c7f0af59ccac6af86fead9d02138e',
        'f9eb640c-dd8a-4b4f-a9c7-6015aad7459e', 'zhangsan@qq.com', '张三', '18888886666', '\0', '\0', '\0', '\0',
        '2021-02-01 08:47:58', '2021-02-01 08:47:58');
INSERT INTO `op_user`
VALUES ('3', 'xiaoming', '8c72315247ce4a3335ea666d385535a2557ad5025f0d595da17f6b10403faadf',
        '55b11cce-5502-452f-bf2b-aea973e8764c', 'xiaoming999@163.com', '小明', '17388991221', '\0', '\0', '\0', '\0',
        '2021-02-01 08:48:36', '2021-02-01 08:48:36');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name`         varchar(50)  NOT NULL COMMENT '角色名称',
    `display_name` varchar(255) NOT NULL COMMENT '显示名称',
    `create_time`  datetime     NOT NULL COMMENT '建立时间',
    `update_time`  timestamp    NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES ('1', 'admin', '管理员', '2021-02-01 09:49:21', '2021-02-01 09:49:21');
INSERT INTO `role`
VALUES ('2', 'user', '普通用户', '2021-02-01 09:49:46', '2021-02-01 09:49:46');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`
(
    `role_id`      int(11) NOT NULL COMMENT '角色ID',
    `authority_id` int(11) NOT NULL COMMENT '权限ID',
    PRIMARY KEY (`role_id`, `authority_id`),
    KEY `role_id` (`role_id`),
    KEY `authority_id` (`authority_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority`
VALUES ('1', '1');
INSERT INTO `role_authority`
VALUES ('1', '2');
INSERT INTO `role_authority`
VALUES ('1', '3');
INSERT INTO `role_authority`
VALUES ('1', '4');
INSERT INTO `role_authority`
VALUES ('1', '5');
INSERT INTO `role_authority`
VALUES ('1', '6');
INSERT INTO `role_authority`
VALUES ('1', '7');
INSERT INTO `role_authority`
VALUES ('1', '8');
INSERT INTO `role_authority`
VALUES ('2', '5');
INSERT INTO `role_authority`
VALUES ('2', '7');
INSERT INTO `role_authority`
VALUES ('2', '8');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `user_id` int(11) NOT NULL COMMENT 'OP用户ID',
    `role_id` int(11) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `user_id` (`user_id`),
    KEY `role_id` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role`
VALUES ('1', '1');
INSERT INTO `user_role`
VALUES ('2', '2');
INSERT INTO `user_role`
VALUES ('3', '2');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(100) NOT NULL COMMENT '题目',
    `userId`      int(11)      NOT NULL COMMENT '用户id',
    `content`     varchar(255) NOT NULL COMMENT '内容',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
