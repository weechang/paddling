/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : paddling

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-08-07 17:49:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for paddling_admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `paddling_admin_resource`;
CREATE TABLE `paddling_admin_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `resource_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `resource_code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `resource_type` tinyint(4) DEFAULT NULL,
  `resource_icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `resource_status` tinyint(4) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `modified_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `logic` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of paddling_admin_resource
-- ----------------------------
INSERT INTO `paddling_admin_resource` VALUES ('1', '0', '基础管理', 'paddling-admin', '/paddling/admin', '1', '', '1', '1', '', '2019-07-31 16:26:38', null, '2019-08-01 13:50:51', null, '0');
INSERT INTO `paddling_admin_resource` VALUES ('2', '0', '资源名称1', 'code1', '/usr', '2', null, '1', '1', null, '2019-08-01 11:08:31', null, '2019-08-01 11:08:32', null, '1');
INSERT INTO `paddling_admin_resource` VALUES ('3', '1', '资源管理', 'resource', '/resource', '1', null, '1', '1', null, '2019-08-01 13:51:37', null, '2019-08-01 13:51:37', null, '0');
INSERT INTO `paddling_admin_resource` VALUES ('4', '1', '角色管理', 'role', '/role', '1', '', '1', '1', '', '2019-08-01 13:52:49', null, '2019-08-01 13:53:01', null, '0');
INSERT INTO `paddling_admin_resource` VALUES ('5', '1', '用户管理', 'user', '/user', '1', null, '1', '1', null, '2019-08-01 13:53:26', null, '2019-08-01 13:53:26', null, '0');

-- ----------------------------
-- Table structure for paddling_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `paddling_admin_role`;
CREATE TABLE `paddling_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `logic` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of paddling_admin_role
-- ----------------------------
INSERT INTO `paddling_admin_role` VALUES ('1', '管理员', '', '2019-08-01 14:31:27', null, '2019-08-01 15:07:51', null, '0');
INSERT INTO `paddling_admin_role` VALUES ('2', '000', null, '2019-08-01 15:10:39', null, '2019-08-01 15:10:39', null, '1');

-- ----------------------------
-- Table structure for paddling_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `paddling_admin_user`;
CREATE TABLE `paddling_admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `tried_times` int(10) DEFAULT NULL,
  `locked_time` datetime DEFAULT NULL,
  `user_status` tinyint(4) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `logic` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of paddling_admin_user
-- ----------------------------
INSERT INTO `paddling_admin_user` VALUES ('1', 'admin', '262f585c5c113704854da8f5b07dd85a82b86c159af723502044ae3a46ada287', '', '管理员', '', 'xx@paddling.com', '13102023435', null, null, '1', '2019-08-01 17:28:02', null, '2019-08-02 10:24:30', null, '0');
INSERT INTO `paddling_admin_user` VALUES ('3', 'test', null, null, 'nick', null, 'ddd', '123', null, null, '1', '2019-08-02 10:46:42', null, '2019-08-02 10:46:42', null, '0');
