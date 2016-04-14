/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50629
 Source Host           : localhost
 Source Database       : spotrs_data_center

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : utf-8

 Date: 03/23/2016 17:35:58 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_hgaccount`
-- ----------------------------
DROP TABLE IF EXISTS `t_hgaccount`;
CREATE TABLE `t_hgaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hg_username` varchar(64) NOT NULL,
  `hg_password` varchar(64) NOT NULL,
  `status` varchar(3) DEFAULT '1' COMMENT '状态 1 － 可用， 2 － 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_hgurl`
-- ----------------------------
DROP TABLE IF EXISTS `t_hgurl`;
CREATE TABLE `t_hgurl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hg_url` varchar(128) NOT NULL,
  `status` varchar(3) DEFAULT '1' COMMENT '状态： 1 － 可用， 2 － 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_host`
-- ----------------------------
DROP TABLE IF EXISTS `t_host`;
CREATE TABLE `t_host` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(50) NOT NULL COMMENT 'ip加端口，如：127.0.0.1:8080',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1=可用，2=不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_send_result`
-- ----------------------------
DROP TABLE IF EXISTS `t_send_result`;
CREATE TABLE `t_send_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostId` int(11) NOT NULL COMMENT 't_host的id',
  `host` varchar(50) NOT NULL COMMENT 't_host的host',
  `sports_events_id` int(11) NOT NULL COMMENT 't_lottery的id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，1=成功，2=不成功',
  `result` varchar(200) DEFAULT NULL COMMENT '发送结果',
  `createTime` datetime NOT NULL COMMENT '添加时间',
  `failedNum` int(11) NOT NULL DEFAULT '0' COMMENT '发送失败次数，第一次失败后5秒再发送一次，以后每次多加10秒，如果失败次数超过5次，就不再发送',
  PRIMARY KEY (`id`),
  UNIQUE KEY `host_lottery_type_index` (`hostId`,`sports_events_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_sports_events`
-- ----------------------------
DROP TABLE IF EXISTS `t_sports_events`;
CREATE TABLE `t_sports_events` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gid` varchar(10) DEFAULT NULL,
  `league` varchar(500) DEFAULT NULL,
  `league_tw` varchar(100) DEFAULT NULL,
  `league_en` varchar(100) DEFAULT NULL,
  `gnum_h` varchar(10) DEFAULT NULL,
  `gnum_c` varchar(10) DEFAULT NULL,
  `team_h` varchar(100) DEFAULT NULL,
  `team_h_tw` varchar(100) DEFAULT NULL,
  `team_h_en` varchar(100) DEFAULT NULL,
  `team_c` varchar(100) DEFAULT NULL,
  `team_c_tw` varchar(100) DEFAULT NULL,
  `team_c_en` varchar(100) DEFAULT NULL,
  `hgid` varchar(100) DEFAULT NULL,
  `play` varchar(100) DEFAULT NULL,
  `runTime` varchar(255) DEFAULT NULL,
  `beginTime` datetime DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `payTime` datetime DEFAULT NULL,
  `ballcode` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='从其他网站读入基础信息(将来重命名为foot_league_info)';

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(10) NOT NULL COMMENT '密码的盐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'admin', 'fc6b211a17d25afbb4f99a619aa3a683', 'dtc96npamp');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
