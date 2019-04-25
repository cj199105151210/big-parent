/*
 Navicat Premium Data Transfer

 Source Server         : 本地环境_localhost
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : localhost:3306
 Source Schema         : bigdata_codegen

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 01/04/2019 23:20:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_datasource_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource_conf`;
CREATE TABLE `sys_datasource_conf`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_date` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_datasource_conf
-- ----------------------------
INSERT INTO `sys_datasource_conf` VALUES (1, 'bigdata_job', 'jdbc:mysql://bigdata-mysql:3306/bigdata_job?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8', 'root', 'bsxc!@#123', '2019-04-01 22:04:49', '2019-04-01 22:38:16', '0', 1);
INSERT INTO `sys_datasource_conf` VALUES (2, 'bigdata', 'jdbc:mysql://bigdata-mysql:3306/bigdata?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true', 'root', 'bsxc!@#123', '2019-04-01 22:04:49', '2019-04-01 22:40:20', '0', 1);
INSERT INTO `sys_datasource_conf` VALUES (3, 'bigdata_aggregation', 'jdbc:mysql://bigdata-mysql:3306/bigdata_cas?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8', 'root', 'bsxc!@#123', '2019-04-01 22:44:17', NULL, '0', 1);

SET FOREIGN_KEY_CHECKS = 1;
