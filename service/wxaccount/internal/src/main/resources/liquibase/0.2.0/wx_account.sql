/*
Navicat MySQL Data Transfer

Source Server         : izhiju.cn
Source Server Version : 50637
Source Host           : izhiju.cn:3306
Source Database       : kapuadb

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-07-27 18:01:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `wx_account`
-- ----------------------------
DROP TABLE IF EXISTS `wx_account`;
CREATE TABLE `wx_account` (
  `id` bigint(21) unsigned NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `wx_appid` varchar(255) NOT NULL,
  `wx_appsecret` varchar(255) NOT NULL,
  `wx_token` varchar(255) NOT NULL,
  `wx_aeskey` varchar(255) NOT NULL,
  `url_name` varchar(100) NOT NULL,
  `created_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `created_by` bigint(21) unsigned DEFAULT NULL,
  `modified_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `modified_by` bigint(21) unsigned DEFAULT NULL,
  `optlock` int(10) DEFAULT NULL,
  `scope_id` bigint(21) unsigned DEFAULT NULL,
  `attributes` text,
  `properties` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_account
-- ----------------------------
INSERT INTO `wx_account` VALUES ('1', 'izhiju', 'wxa5342540ccc844d6', '118cfdb7b0e7023b01e53a02b33069b1', 'zhiju', '111', '1111', '2017-07-27 16:23:27.170', '1', '2017-07-27 16:23:27.170', null, '1', '1', null, null);
INSERT INTO `wx_account` VALUES ('7605584318851331499', null, '222', '22', '222', '222', '222', '2017-07-27 07:09:00.788', '1', '2017-07-27 07:09:00.788', '1', '1', '1', null, null);
