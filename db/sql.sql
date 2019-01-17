-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `uid` bigint(20) NOT NULL,
  `s_user_pwd` varchar(255) DEFAULT NULL,
  `s_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_2c6s0grt8f2ffy73n0whijro1` (`s_user_name`)
);

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('35', 'guest', 'guest');
INSERT INTO `s_user` VALUES ('36', 'admin', 'admin');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `rid` bigint(20) NOT NULL,
  `s_role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
);

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('33', 'admin');
INSERT INTO `s_role` VALUES ('34', 'guest');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `pid` bigint(20) NOT NULL,
  `s_permission_discrible` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
);

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('29', 'guest:read');
INSERT INTO `s_permission` VALUES ('30', 'guest:update');
INSERT INTO `s_permission` VALUES ('31', 'admin:read');
INSERT INTO `s_permission` VALUES ('32', 'admin:update');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `uid` bigint(20) NOT NULL,
  `rid` bigint(20) NOT NULL,
  KEY `FKjdx8gor0f4k4baem2a4gy4kwy` (`rid`),
  KEY `FKeis0oy5su7xylvcxlm28imxd7` (`uid`),
  CONSTRAINT `FKeis0oy5su7xylvcxlm28imxd7` FOREIGN KEY (`uid`) REFERENCES `s_user` (`uid`),
  CONSTRAINT `FKjdx8gor0f4k4baem2a4gy4kwy` FOREIGN KEY (`rid`) REFERENCES `s_role` (`rid`)
);

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('35', '34');
INSERT INTO `s_user_role` VALUES ('36', '33');
INSERT INTO `s_user_role` VALUES ('36', '34');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `rid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  KEY `FK10gviwib6uex9b7an1u4lkf2h` (`pid`),
  KEY `FKid6yx7k005jrex20q0y7a9d80` (`rid`),
  CONSTRAINT `FK10gviwib6uex9b7an1u4lkf2h` FOREIGN KEY (`pid`) REFERENCES `s_permission` (`pid`),
  CONSTRAINT `FKid6yx7k005jrex20q0y7a9d80` FOREIGN KEY (`rid`) REFERENCES `s_role` (`rid`)
);

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('33', '31');
INSERT INTO `s_role_permission` VALUES ('33', '32');
INSERT INTO `s_role_permission` VALUES ('34', '29');
INSERT INTO `s_role_permission` VALUES ('34', '30');