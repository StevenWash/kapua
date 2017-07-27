/*
Navicat MySQL Data Transfer

Source Server         : www.izhiju.cn
Source Server Version : 50636
Source Host           : www.izhiju.cn:3306
Source Database       : kapuadb

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-07-20 16:15:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_info`
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id`  bigint(21) unsigned NOT NULL,
  `packagename` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `comment` text,
  `types` varchar(45) DEFAULT NULL,
  `image` text,
  `created_on` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `created_by` bigint(21) unsigned NOT NULL,
  `modified_on` timestamp(3) NOT NULL DEFAULT '0000-00-00 00:00:00.000',
  `modified_by` bigint(21) unsigned NOT NULL,
  `optlock` int(10) unsigned DEFAULT NULL,
  `scope_id` bigint(21) unsigned DEFAULT NULL,
  `attributes` text,
  `properties` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_info
-- ----------------------------
INSERT INTO `app_info` VALUES ('1', 'com.example.goliath', 'wifi智能排插', '更新说明', 'sys', null, '2017-07-04 17:57:19.405', '0', '2017-07-04 17:57:10.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('2', 'ota.system.M101.CF101', '爱奇艺固件', '升级测试，非法的测试包，非法测试包', 'sys-man', null, '2017-07-04 17:57:21.649', '0', '2017-07-04 17:57:14.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('3', 'net.bestidear.donglelauncher', '盒子shell', '更新说明', null, null, '2017-07-04 17:57:24.144', '0', '2017-07-04 17:57:16.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('4', 'net.bestidear.wifispeaker.client', '音箱控制客户端', '增加文字搜索', 'apk', null, '2017-07-04 17:57:26.463', '0', '2017-07-04 17:57:19.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('5', 'net.bestidear.wifispeaker.device', '音箱服务端', '更新说明', null, null, '2017-07-04 17:57:28.594', '0', '2017-07-04 17:57:21.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('6', 'ota.system.S805.K6-SCCHINAMOBILEV1', '爱奇异', '更新说明', 'sys-man', null, '2017-07-04 17:57:31.428', '0', '2017-07-04 17:57:23.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('7', 'ota.system.iqiyi.inc', '爱奇异', null, null, null, '2017-07-04 17:57:34.351', '0', '2017-07-04 17:57:26.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('8', 'ota.system.M101.PF8096', '爱奇艺固件', '升级测试，非法的测试包，非法测试包', 'sys-man', null, '2017-07-04 17:57:36.485', '0', '2017-07-04 17:57:29.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('9', 'net.bestidear.dlna.cling', 'dlna', 'dlna', null, null, '2017-07-04 17:57:39.533', '0', '2017-07-04 17:57:31.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('10', 'ota.system.S805.K6-SCCHINAMOBILEV1', '爱奇异', '更新说明', 'sys-man', null, '2017-07-04 17:57:42.005', '0', '2017-07-04 17:57:34.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('11', 'ota.system.M101.Q10', '爱奇艺增量升级包', 'http://www.yekertech.com/ota/20151117/all_ota_20151117.zipaa.zip|||http://www.yekertech.com/ota/20151117/all_ota_20151117.zipab.zip|||http://www.yekertech.com/ota/20151117/all_ota_20151117.zipac.zip|||http://www.yekertech.com/ota/20151117/all_ota_20151117.zipad.zip|||http://www.yekertech.com/ota/20151117/all_ota_20151117.zipae.zip|||http://www.yekertech.com/ota/20151117/all_ota_20151117.zipaf.zip|||http://www.yekertech.com/ota/20151117/all_ota_20151117.zipag.zip', 'sys-force', null, '2017-07-04 17:57:44.848', '0', '2017-07-04 17:57:37.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('12', 'ota.system.M101.Q10V2', '爱奇艺增量升级包', '升级测试，非法的测试包，非法测试包', 'sys-force', null, '2017-07-04 17:57:46.996', '0', '2017-07-04 17:57:39.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('13', 'ota.system.S805.K6-TEMPOINT', 'Tempoint', '发布说明:', 'sys-force', null, '2017-07-04 17:57:50.157', '0', '2017-07-04 17:57:41.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('14', 'com.yekertech.im.client.android', '推聊', 'tt', null, null, '2017-07-04 17:57:52.797', '0', '2017-07-04 17:57:45.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('15', 'ota.system.TVBAR.GG', '推聊', '发布说明:', null, null, '2017-07-04 17:57:55.056', '0', '2017-07-04 17:57:47.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('16', 'com.yekertech.alibaba.openIMUIDemo', 'im', 'im', null, null, '2017-07-04 17:57:58.136', '0', '2017-07-04 17:57:50.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('17', 'net.bestidear.K6LauncherFuLiang', 'Luancher', '修改了一些bug', null, null, '2017-07-04 17:58:00.467', '0', '2017-07-04 17:57:53.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('18', 'ota.system.YKIOT.FT-CXW', '油烟机', '发布说明:', null, null, '2017-07-04 17:58:03.151', '0', '2017-07-04 17:57:55.000', '0', null, null, null, null);
INSERT INTO `app_info` VALUES ('19', 'ota.system.YKIOT.FT-ZTD', '消毒柜', '发布说明:', null, null, '2017-07-04 17:58:06.514', '0', '2017-07-04 17:57:58.000', '0', null, null, null, null);
