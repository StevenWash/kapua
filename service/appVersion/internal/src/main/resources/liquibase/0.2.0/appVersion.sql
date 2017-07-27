/*
Navicat MySQL Data Transfer

Source Server         : izhiju.cn
Source Server Version : 50637
Source Host           : izhiju.cn:3306
Source Database       : kapuadb

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-07-27 11:07:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app_version`
-- ----------------------------
DROP TABLE IF EXISTS `app_version`;
CREATE TABLE `app_version` (
  `id` bigint(21) unsigned NOT NULL,
  `packagename` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `url` text,
  `size` int(11) DEFAULT NULL,
  `md5` varchar(45) DEFAULT NULL,
  `types` varchar(45) DEFAULT NULL,
  `revision` varchar(45) DEFAULT NULL,
  `forversion` varchar(45) DEFAULT NULL,
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
-- Records of app_version
-- ----------------------------
INSERT INTO `app_version` VALUES ('1', 'ota.system.iqiyi.inc', '2', 'v1.1.2', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20150821/incremental_ota_update.zip http://cdn.data.video.iqiyi.com/cdn/oxygen/20150821/incremental_ota_update.zip http://cdn.data.video.iqiyi.com/cdn/oxygen/20150821/incremental_ota_update.zip', '123', null, 'all', '6', null, '2017-07-18 09:14:59.288', '0', '2017-07-05 14:36:01.000', '0', '1', '1', null, null);
INSERT INTO `app_version` VALUES ('2', 'ota.system.M101.PF8096', '3', 'V1.0.1.20150811', 'http://www.yekertech.com/ota/incremental_ota_update.zip.aa|||http://www.yekertech.com/ota/incremental_ota_update.zip.ab|||http://www.yekertech.com/ota/incremental_ota_update.zip.ac', '1123412341', '12341234', 'all', '7', 'V1.0.1.20150811', '2017-07-05 14:36:12.151', '0', '2017-07-05 14:36:04.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('3', 'ota.system.M101.PF8096', '3', 'V1.0.1.20150818', 'http://www.yekertech.com/ota/incremental_ota_20150818.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20150810', '2017-07-05 14:36:14.808', '0', '2017-07-05 14:36:06.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('4', 'ota.system.M101.PF8096', '3', 'V1.0.1.20151021', 'http://www.yekertech.com/ota/20151021/inc_for_20151019.zipaa|||http://www.yekertech.com/ota/20151021/inc_for_20151019.zipab|||http://www.yekertech.com/ota/20151021/inc_for_20151019.zipac', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151019', '2017-07-05 14:36:18.105', '0', '2017-07-05 14:36:09.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('5', 'net.bestidear.dlna.cling', '1', '2.0', 'https://raw.githubusercontent.com/miricy/release/master/temp/DLNA_Cling_Play.apk', '1268534', null, 'all', null, '1.0', '2017-07-05 14:36:21.053', '0', '2017-07-05 14:36:13.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('6', 'ota.system.S805.K6-SCCHINAMOBILEV1', '2', 'V1.0.1.20140429', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipaa.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151120/all_ota_20151117.zipab.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipac.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipad.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipae.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipaf.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipag.zip', '123', 'asdfasdfasdf', 'all', '6', '', '2017-07-05 14:36:23.636', '0', '2017-07-05 14:36:15.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('7', 'ota.system.S805.K6-SCCHINAMOBILEV1', '2', 'V1.0.1.20140430', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipaa.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151120/all_ota_20151117.zipab.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipac.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipad.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipae.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipaf.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipag.zip', '123', 'asdfasdfasdf', '.patch', '6', 'V1.0.1.20140429', '2017-07-05 14:36:26.373', '0', '2017-07-05 14:36:18.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('8', 'ota.system.M101.Q10', 'system', 'V1.0.1.20151117', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipaa.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151120/all_ota_20151117.zipab.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipac.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipad.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipae.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipaf.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20151119/all_ota_20151117.zipag.zip', '1123412341', '1123412341', 'all', '8', 'V1.0.1.20151111', '2017-07-05 14:36:30.299', '0', '2017-07-05 14:36:22.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('9', 'ota.system.M101.Q10', '3', 'V1.0.1.20151207', 'http://www.yekertech.com/ota/20151207/inc_for_20151124.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151124', '2017-07-05 14:36:32.775', '0', '2017-07-05 14:36:25.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('10', 'ota.system.M101.Q10', '3', 'V1.0.1.20151124', 'http://www.yekertech.com/ota/20151124/inc_ota_20151124t20151117.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151117', '2017-07-05 14:36:36.794', '0', '2017-07-05 14:36:27.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('11', 'ota.system.M101.Q10', '3', 'V1.0.1.20151209', 'http://www.yekertech.com/ota/20151209/inc_ota_20151209t20151207.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151207', '2017-07-07 09:32:56.201', '0', '2017-07-07 09:32:47.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('12', 'ota.system.M101.Q10', '3', 'V1.0.1.20151212', 'http://www.yekertech.com/ota/20151212/update_sys_20151212f20151209.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151209', '2017-07-07 09:32:58.668', '0', '2017-07-07 09:32:50.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('13', 'ota.system.M101.Q10', '3', 'V1.0.1.20151215', 'http://www.yekertech.com/ota/20151215/update_sys_20151215f20151212.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151212', '2017-07-07 09:33:00.801', '0', '2017-07-07 09:32:52.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('14', 'ota.system.M101.Q10', '3', 'V1.0.1.20151225', 'http://www.yekertech.com/ota/20151225/inc_ota_20151225t20151215.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151215', '2017-07-07 09:33:03.047', '0', '2017-07-07 09:32:54.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('15', 'ota.system.M101.Q10', '3', 'V1.0.1.20160830', 'http://usyeker.oss-us-west-1.aliyuncs.com/ota/update_sys_ykiot-ota-20160902.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20160725', '2017-07-07 09:33:06.028', '0', '2017-07-07 09:32:57.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('16', 'ota.system.M101.Q10V2', '3', 'V1.0.1.20160114', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20160115/update_sys_patch_6181_20160114f20151215.aa.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20160115/update_sys_patch_6181_20160114f20151215.ab.zip', '1123412341', '1123412341', 'all', '8', 'V1.0.1.20151215', '2017-07-07 09:33:08.431', '0', '2017-07-07 09:32:59.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('17', 'ota.system.M101.Q10V2', '3', 'V1.0.1.20160114', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20160115/update_sys_patch_6181_20160114f20151215.aa.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20160115/update_sys_patch_6181_20160114f20151215.ab.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20151215', '2017-07-07 09:33:11.709', '0', '2017-07-07 09:33:02.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('18', 'ota.system.M101.Q10V2', '3', 'V1.0.1.20160202', 'http://cdn.data.video.iqiyi.com/cdn/oxygen/20160115/update_sys_patch_6181_20160114f20151215.aa.zip|||http://cdn.data.video.iqiyi.com/cdn/oxygen/20160115/update_sys_patch_6181_20160114f20151215.ab.zip', '1123412341', '1123412341', '.patch', '8', 'V1.0.1.20160114', '2017-07-07 09:33:14.379', '0', '2017-07-07 09:33:05.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('19', 'ota.system.S805.K6-TEMPOINT', '3', 'V1.1.1.20160308', 'http://www.yekertech.com/ota/tempo_inc_ota20160308f0220.zip', '12345', null, 'all', '9', 'V1.1.1.20160220', '2017-07-07 09:33:16.513', '0', '2017-07-07 09:33:08.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('20', 'ota.system.S805.K6-TEMPOINT', '3', 'V1.1.1.20160308', 'http://www.yekertech.com/ota/tempo_inc_ota20160308f0220.zip', '12345', null, '.patch', '9', 'V1.1.1.20160220', '2017-07-07 09:33:19.224', '0', '2017-07-07 09:33:10.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('21', 'com.yekertech.im.client.android', '3', '1.0.0', 'http://res.yekertech.com/apk/yekerim.apk', '1111', '1111', 'all', '1', null, '2017-07-07 09:33:21.716', '0', '2017-07-07 09:33:13.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('22', 'ota.system.TVBAR.GG', '3', 'V1.0.0.20160524', 'http://res.yekertech.com/apk/yekerim.apk', '1111', '1111', 'all', '1', null, '2017-07-07 09:33:25.439', '0', '2017-07-07 09:33:17.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('23', 'com.yekertech.alibaba.openIMUIDemo', '2', 'v1.0.0', 'https://www.yekertech.com/svn/release/apk/yeker/LoginActivity.apk', '1234', '12341', 'all', '1', null, '2017-07-07 09:33:27.669', '0', '2017-07-07 09:33:19.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('24', 'net.bestidear.K6LauncherFuLiang', '2', '1.0.0', 'http://www.yekertech.com/svn/release/apk/yeker/K6LauncherFuLiang_signed.apk', '111', '1111', 'all', '1', null, '2017-07-07 09:33:33.231', '0', '2017-07-07 09:33:21.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('25', 'ota.system.YKIOT.FT-CXW', '3', 'V1.1.1.20161104', 'http://res.yekertech.com/ota/update_sys_ykiot-cxw20161110.zip', '1111', '11111', 'all', '1', null, '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('26', 'ota.system.YKIOT.FT-ZTD', '3', 'V1.1.1.20161027', 'http://res.yekertech.com/ota/update_sys_ztd_dq20-ota-20161026.zip', '1111', '11111', 'all', '1', null, '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('27', 'com.example.goliath~', '24', '1.0.0.24', 'https://raw.githubusercontent.com/miricy/release/master/switch/Goliath.apk', '12345', 'aaaaaaaa', 'all', '2', null, '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('28', 'ota.system.M101.CF101', '3', 'V1.0.1.20150811', 'http://www.yekertech.com/ota/incremental_ota_update.zip.aa|||http://www.yekertech.com/ota/incremental_ota_update.zip.ab|||http://www.yekertech.com/ota/incremental_ota_update.zip.ac', '1123412341', '12341234', 'all', '7', 'V1.0.1.20150811', '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('29', 'net.bestidear.donglelauncher', '1', '1.0.0', 'https://raw.githubusercontent.com/miricy/release/master/temp/ContralDongle.apk', '12345', null, 'all', null, null, '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('30', 'net.bestidear.wifispeaker.client', '43', '2.5.1', 'https://raw.githubusercontent.com/miricy/release/master/net-speaker/BoxController.apk', '1995903', 'asdfasdfasdfasdf', 'all', null, null, '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('31', 'net.bestidear.wifispeaker.device', '1', '1.0.3', 'http://', '12345', null, 'all', null, null, '2017-07-05 14:34:00.811', '0', '0000-00-00 00:00:00.000', '0', null, null, null, null);
INSERT INTO `app_version` VALUES ('32', 'ota.system.YKDPF.GADMEI-Y10V1', null, 'V1.0.1.20170724', 'https://github.com/miricy/release/raw/master/ota/YKDPF/update_sys_astar_d7-ota-20170724.zip.001|||https://github.com/miricy/release/raw/master/ota/YKDPF/update_sys_astar_d7-ota-20170724.zip.002|||https://github.com/miricy/release/raw/master/ota/YKDPF/update_sys_astar_d7-ota-20170724.zip.003|||https://github.com/miricy/release/raw/master/ota/YKDPF/update_sys_astar_d7-ota-20170724.zip.004|||https://github.com/miricy/release/raw/master/ota/YKDPF/update_sys_astar_d7-ota-20170724.zip.005', null, null, 'all', null, null, '2017-07-27 10:48:46.266', '1', '2017-07-27 10:48:46.266', null, null, null, null, null);
