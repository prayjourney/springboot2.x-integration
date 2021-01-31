-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `display_name` varchar(255) NOT NULL COMMENT '显示名称',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for op_user
-- ----------------------------
DROP TABLE IF EXISTS `op_user`;
CREATE TABLE `op_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) NOT NULL COMMENT '盐',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT 'Email',
  `real_name` varchar(50) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `tel` varchar(50) NOT NULL DEFAULT '' COMMENT '联系电话',
  `account_disabled` bit(1) NOT NULL DEFAULT b'0' COMMENT '账号失效',
  `account_expired` bit(1) NOT NULL DEFAULT b'0' COMMENT '账号过期',
  `account_locked` bit(1) NOT NULL DEFAULT b'0' COMMENT '账号冻结',
  `credentials_expired` bit(1) NOT NULL DEFAULT b'0' COMMENT '密码过期',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `display_name` varchar(255) NOT NULL COMMENT '显示名称',
  `create_time` datetime NOT NULL COMMENT '建立时间',
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `authority_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `role_id` (`role_id`),
  KEY `authority_id` (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL COMMENT 'OP用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

