-- https://docs.spring.io/spring-session/docs/2.1.0.M2/reference/html5/?spm=a2c6h.12873639.0.0.577133bcQsjG5Q#httpsession-jdbc

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


DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes` (
  `session_primary_id` char(36) NOT NULL,
  `attribute_name` varchar(200) NOT NULL,
  `attribute_bytes` blob NOT NULL,
  PRIMARY KEY (`session_primary_id`,`attribute_name`),
  CONSTRAINT `spring_session_attributes_fk` FOREIGN KEY (`session_primary_id`) REFERENCES `spring_session` (`primary_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;