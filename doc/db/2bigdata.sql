/*
 Navicat Premium Data Transfer

 Source Server         : 本地环境_localhost
 Source Server Type    : MySQL
 Source Server Version : 50636
 Source Host           : localhost:3306
 Source Schema         : bigdata

 Target Server Type    : MySQL
 Target Server Version : 50636
 File Encoding         : 65001

 Date: 27/02/2019 17:16:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for job_execution_log
-- ----------------------------
DROP TABLE IF EXISTS `job_execution_log`;
CREATE TABLE `job_execution_log`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hostname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sharding_item` int(11) NOT NULL,
  `execution_source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `failure_cause` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_success` int(11) NOT NULL,
  `start_time` timestamp(0) NULL DEFAULT NULL,
  `complete_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for job_status_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `job_status_trace_log`;
CREATE TABLE `job_status_trace_log`  (
  `id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `original_task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `slave_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `execution_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sharding_item` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `creation_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `TASK_ID_STATE_INDEX`(`task_id`, `state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务轨迹表' ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_common_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_parameter`;
CREATE TABLE `sys_common_parameter`  (
  `public_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统id',
  `public_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公共参数名称',
  `public_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公共参数地址值',
  `public_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注信息',
  `status` char(1) NULL DEFAULT 1 COMMENT '状态（1有效；2无效；）',
  `delete_flag` char(1) NULL DEFAULT 1 COMMENT '删除状态（1：正常 2：已删除）',
  `validate_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公共参数编码',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `public_type` char(1) NULL DEFAULT 0 COMMENT '模块类型',
  PRIMARY KEY (`public_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 327329 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公共参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_common_parameter
-- ----------------------------
INSERT INTO `sys_common_parameter` VALUES (18808, '生成全量索引的吞吐量', 'SOLR_INDEX_NUM', '10000', 1, 1, NULL, '2018-11-13 10:35:12', '2018-11-13 10:35:12', 1);
INSERT INTO `sys_common_parameter` VALUES (22211, '上传文件方式', 'UPLOAD_TYPE', 'UDS', 1, 1, '^(UDS|LOCAL)$', '2018-11-13 10:35:12', '2018-11-13 10:39:12', 1);
INSERT INTO `sys_common_parameter` VALUES (188099, '上次创建原文索引的时间', 'SOLR_CREATE_INDEX_TIME_EFILE', '2013-12-24 17:15:39', 1, 1, '1', '2018-11-13 10:35:12', '2018-11-13 10:35:12', 1);
INSERT INTO `sys_common_parameter` VALUES (327163, '高级检索中的快捷检索字段', 'ADVSEARCH_QUICKFIELDS', 'FLH,FLMC,cwrq,jgdm,mj', 1, 1, NULL, '2018-11-13 10:35:12', '2018-11-13 10:35:12', 1);
INSERT INTO `sys_common_parameter` VALUES (327326, '上传文件限制大小', 'FILE_SIZE_LIMIT', '2048MB', 1, 1, NULL, '2018-11-13 10:35:12', '2018-11-13 10:39:12', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父ID',
  `tenant_id` int(11) NULL DEFAULT NULL COMMENT '租户身份证',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '经济学院', NULL, '2018-01-22 19:00:23', '2019-02-25 09:25:20', '0', 0, 1);
INSERT INTO `sys_dept` VALUES (2, '机械工程学院', NULL, '2018-01-22 19:00:38', '2019-02-25 09:23:27', '0', 0, 1);
INSERT INTO `sys_dept` VALUES (3, '教育机构', NULL, '2018-01-22 19:00:44', '2019-02-25 09:26:21', '0', 1, 1);
INSERT INTO `sys_dept` VALUES (4, '经济学系', NULL, '2018-01-22 19:00:52', '2019-02-25 09:26:30', '0', 3, 1);
INSERT INTO `sys_dept` VALUES (5, '职工部', NULL, '2018-01-22 19:00:57', '2019-02-25 09:27:07', '0', 4, 1);
INSERT INTO `sys_dept` VALUES (6, '潍院农信', NULL, '2018-01-22 19:01:06', '2019-01-09 10:58:18', '1', 5, 1);
INSERT INTO `sys_dept` VALUES (7, '教育机构', NULL, '2018-01-22 19:01:57', '2019-02-25 09:23:41', '0', 2, 1);
INSERT INTO `sys_dept` VALUES (8, '工业工程系', NULL, '2018-01-22 19:02:03', '2019-02-25 09:24:07', '0', 7, 1);
INSERT INTO `sys_dept` VALUES (9, '高新沙县', NULL, '2018-01-22 19:02:14', '2018-09-13 01:46:44', '1', 8, 1);
INSERT INTO `sys_dept` VALUES (10, '租户2', NULL, '2018-11-18 13:27:11', '2019-02-25 09:06:06', '1', 0, 2);
INSERT INTO `sys_dept` VALUES (11, '职工部', NULL, '2018-12-10 21:19:26', '2019-02-25 09:24:21', '0', 8, 1);

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation`  (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE,
  INDEX `idx1`(`ancestor`) USING BTREE,
  INDEX `idx2`(`descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '部门关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES (1, 1);
INSERT INTO `sys_dept_relation` VALUES (1, 3);
INSERT INTO `sys_dept_relation` VALUES (1, 4);
INSERT INTO `sys_dept_relation` VALUES (1, 5);
INSERT INTO `sys_dept_relation` VALUES (2, 2);
INSERT INTO `sys_dept_relation` VALUES (2, 7);
INSERT INTO `sys_dept_relation` VALUES (2, 8);
INSERT INTO `sys_dept_relation` VALUES (2, 11);
INSERT INTO `sys_dept_relation` VALUES (3, 3);
INSERT INTO `sys_dept_relation` VALUES (3, 4);
INSERT INTO `sys_dept_relation` VALUES (3, 5);
INSERT INTO `sys_dept_relation` VALUES (4, 4);
INSERT INTO `sys_dept_relation` VALUES (4, 5);
INSERT INTO `sys_dept_relation` VALUES (5, 5);
INSERT INTO `sys_dept_relation` VALUES (7, 7);
INSERT INTO `sys_dept_relation` VALUES (7, 8);
INSERT INTO `sys_dept_relation` VALUES (7, 11);
INSERT INTO `sys_dept_relation` VALUES (8, 8);
INSERT INTO `sys_dept_relation` VALUES (8, 11);
INSERT INTO `sys_dept_relation` VALUES (11, 11);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `sort` int(10) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`label`) USING BTREE,
  INDEX `sys_dict_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '9', '异常', 'log_type', '日志异常', 1, '2018-07-09 06:16:14', '2018-11-24 07:25:11', '日志异常', '0', 1);
INSERT INTO `sys_dict` VALUES (2, '0', '正常', 'log_type', '正常', 0, '2018-07-09 06:15:40', '2018-11-24 07:25:14', '正常', '0', 0);
INSERT INTO `sys_dict` VALUES (3, 'WX', '微信', 'social_type', '微信登录', 0, '2018-08-16 14:01:45', '2018-11-24 07:25:16', '微信登录', '0', 0);
INSERT INTO `sys_dict` VALUES (4, 'QQ', 'QQ', 'social_type', 'QQ登录', 1, '2018-07-09 06:15:40', '2018-11-24 07:25:18', 'QQ登录', '0', 0);
INSERT INTO `sys_dict` VALUES (5, '0', '正常', 'log_type', '日志类型', 0, '2018-09-30 02:33:53', '2018-11-24 07:25:20', '日志正常', '0', 1);
INSERT INTO `sys_dict` VALUES (6, '0', '未提交', 'leave_status', '请假状态', 0, '2018-09-30 02:34:45', '2019-02-25 14:31:25', '请假状态', '0', 1);
INSERT INTO `sys_dict` VALUES (7, '1', '审批中', 'leave_status', '请假状态', 1, '2018-09-30 02:35:16', '2019-02-25 14:31:27', '请假状态', '0', 1);
INSERT INTO `sys_dict` VALUES (8, '2', '完成', 'leave_status', '请假状态', 2, '2018-09-30 02:35:58', '2019-02-25 14:31:28', '请假状态', '0', 1);
INSERT INTO `sys_dict` VALUES (9, '9', '驳回', 'leave_status', '请假状态', 9, '2018-09-30 02:36:31', '2019-02-25 14:31:30', '请假状态', '0', 1);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作提交的数据',
  `time` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '执行时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `exception` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `tenant_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_log_create_by`(`create_by`) USING BTREE,
  INDEX `sys_log_request_uri`(`request_uri`) USING BTREE,
  INDEX `sys_log_type`(`type`) USING BTREE,
  INDEX `sys_log_create_date`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端URL',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序值',
  `keep_alive` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1000, '权限管理', NULL, '/upms', -1, 'icon-quanxianguanli', 'Layout', 0, '0', '0', '2018-09-28 08:29:53', '2018-09-28 08:53:01', '0');
INSERT INTO `sys_menu` VALUES (1100, '用户管理', NULL, 'user', 1000, 'icon-yonghuguanli', 'views/admin/user/index', 1, '0', '0', '2017-11-02 22:24:37', '2018-09-28 09:00:41', '0');
INSERT INTO `sys_menu` VALUES (1101, '用户新增', 'sys_user_add', NULL, 1100, NULL, NULL, NULL, '0', '1', '2017-11-08 09:52:09', '2018-09-28 09:06:34', '0');
INSERT INTO `sys_menu` VALUES (1102, '用户修改', 'sys_user_edit', NULL, 1100, NULL, NULL, NULL, '0', '1', '2017-11-08 09:52:48', '2018-09-28 09:06:37', '0');
INSERT INTO `sys_menu` VALUES (1103, '用户删除', 'sys_user_del', NULL, 1100, NULL, NULL, NULL, '0', '1', '2017-11-08 09:54:01', '2018-09-28 09:06:42', '0');
INSERT INTO `sys_menu` VALUES (1200, '菜单管理', NULL, 'menu', 1000, 'icon-caidanguanli', 'views/admin/menu/index', 2, '0', '0', '2017-11-08 09:57:27', '2018-09-28 09:00:45', '0');
INSERT INTO `sys_menu` VALUES (1201, '菜单新增', 'sys_menu_add', NULL, 1200, NULL, NULL, NULL, '0', '1', '2017-11-08 10:15:53', '2018-09-28 09:07:16', '0');
INSERT INTO `sys_menu` VALUES (1202, '菜单修改', 'sys_menu_edit', NULL, 1200, NULL, NULL, NULL, '0', '1', '2017-11-08 10:16:23', '2018-09-28 09:07:18', '0');
INSERT INTO `sys_menu` VALUES (1203, '菜单删除', 'sys_menu_del', NULL, 1200, NULL, NULL, NULL, '0', '1', '2017-11-08 10:16:43', '2018-09-28 09:07:22', '0');
INSERT INTO `sys_menu` VALUES (1300, '角色管理', NULL, 'role', 1000, 'icon-jiaoseguanli', 'views/admin/role/index', 3, '0', '0', '2017-11-08 10:13:37', '2018-09-28 09:00:48', '0');
INSERT INTO `sys_menu` VALUES (1301, '角色新增', 'sys_role_add', NULL, 1300, NULL, NULL, NULL, '0', '1', '2017-11-08 10:14:18', '2018-09-28 09:07:46', '0');
INSERT INTO `sys_menu` VALUES (1302, '角色修改', 'sys_role_edit', NULL, 1300, NULL, NULL, NULL, '0', '1', '2017-11-08 10:14:41', '2018-09-28 09:07:49', '0');
INSERT INTO `sys_menu` VALUES (1303, '角色删除', 'sys_role_del', NULL, 1300, NULL, NULL, NULL, '0', '1', '2017-11-08 10:14:59', '2018-09-28 09:07:53', '0');
INSERT INTO `sys_menu` VALUES (1304, '分配权限', 'sys_role_perm', NULL, 1300, NULL, NULL, NULL, '0', '1', '2018-04-20 07:22:55', '2018-09-28 09:13:23', '0');
INSERT INTO `sys_menu` VALUES (1400, '部门管理', NULL, 'dept', 1000, 'icon-web-icon-', 'views/admin/dept/index', 4, '0', '0', '2018-01-20 13:17:19', '2018-12-09 16:35:12', '0');
INSERT INTO `sys_menu` VALUES (1401, '部门新增', 'sys_dept_add', NULL, 1400, NULL, NULL, NULL, '0', '1', '2018-01-20 14:56:16', '2018-09-28 09:08:13', '0');
INSERT INTO `sys_menu` VALUES (1402, '部门修改', 'sys_dept_edit', NULL, 1400, NULL, NULL, NULL, '0', '1', '2018-01-20 14:56:59', '2018-09-28 09:08:16', '0');
INSERT INTO `sys_menu` VALUES (1403, '部门删除', 'sys_dept_del', NULL, 1400, NULL, NULL, NULL, '0', '1', '2018-01-20 14:57:28', '2018-09-28 09:08:18', '0');
INSERT INTO `sys_menu` VALUES (2000, '系统管理', NULL, '/admin', -1, 'icon-xitongguanli', 'Layout', 1, '0', '0', '2017-11-07 20:56:00', '2018-09-28 08:53:18', '0');
INSERT INTO `sys_menu` VALUES (2100, '日志管理', NULL, 'log', 2000, 'icon-rizhiguanli', 'views/admin/log/index', 5, '0', '0', '2017-11-20 14:06:22', '2018-09-28 09:01:52', '0');
INSERT INTO `sys_menu` VALUES (2101, '日志删除', 'sys_log_del', NULL, 2100, NULL, NULL, NULL, '0', '1', '2017-11-20 20:37:37', '2018-09-28 09:08:44', '0');
INSERT INTO `sys_menu` VALUES (2200, '字典管理', NULL, 'dict', 2000, 'icon-navicon-zdgl', 'views/admin/dict/index', 6, '0', '0', '2017-11-29 11:30:52', '2018-09-28 09:01:47', '0');
INSERT INTO `sys_menu` VALUES (2201, '字典删除', 'sys_dict_del', NULL, 2200, NULL, NULL, NULL, '0', '1', '2017-11-29 11:30:11', '2018-09-28 09:09:10', '0');
INSERT INTO `sys_menu` VALUES (2202, '字典新增', 'sys_dict_add', NULL, 2200, NULL, NULL, NULL, '0', '1', '2018-05-11 22:34:55', '2018-09-28 09:09:12', '0');
INSERT INTO `sys_menu` VALUES (2203, '字典修改', 'sys_dict_edit', NULL, 2200, NULL, NULL, NULL, '0', '1', '2018-05-11 22:36:03', '2018-09-28 09:09:16', '0');
INSERT INTO `sys_menu` VALUES (2300, '代码生成', '', 'gen', 2000, 'icon-weibiaoti46', 'views/gen/index', 8, '0', '0', '2018-01-20 13:17:19', '2018-11-24 05:21:01', '0');
INSERT INTO `sys_menu` VALUES (2400, '终端管理', '', 'client', 2000, 'icon-shouji', 'views/admin/client/index', 9, '0', '0', '2018-01-20 13:17:19', '2018-09-28 09:01:43', '0');
INSERT INTO `sys_menu` VALUES (2401, '客户端新增', 'sys_client_add', NULL, 2400, '1', NULL, NULL, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:10:25', '0');
INSERT INTO `sys_menu` VALUES (2402, '客户端修改', 'sys_client_edit', NULL, 2400, NULL, NULL, NULL, '0', '1', '2018-05-15 21:37:06', '2018-09-28 09:10:27', '0');
INSERT INTO `sys_menu` VALUES (2403, '客户端删除', 'sys_client_del', NULL, 2400, NULL, NULL, NULL, '0', '1', '2018-05-15 21:39:16', '2018-09-28 09:10:30', '0');
INSERT INTO `sys_menu` VALUES (2500, '密钥管理', '', 'social', 2000, 'icon-miyue', 'views/admin/social/index', 10, '0', '0', '2018-01-20 13:17:19', '2018-09-28 09:01:41', '0');
INSERT INTO `sys_menu` VALUES (2501, '密钥新增', 'generator_syssocialdetails_add', NULL, 2500, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:11:02', '0');
INSERT INTO `sys_menu` VALUES (2502, '密钥修改', 'generator_syssocialdetails_edit', NULL, 2500, '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:11:04', '0');
INSERT INTO `sys_menu` VALUES (2503, '密钥删除', 'generator_syssocialdetails_del', NULL, 2500, '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:11:06', '0');
INSERT INTO `sys_menu` VALUES (2600, '令牌管理', NULL, 'token', 2000, 'icon-denglvlingpai', 'views/admin/token/index', 11, '0', '0', '2018-09-04 05:58:41', '2018-09-28 09:01:38', '0');
INSERT INTO `sys_menu` VALUES (2601, '令牌删除', 'sys_token_del', NULL, 2600, NULL, NULL, 1, '0', '1', '2018-09-04 05:59:50', '2018-09-28 09:11:24', '0');
INSERT INTO `sys_menu` VALUES (2700, '动态路由', NULL, 'route', 2000, 'icon-luyou', 'views/admin/route/index', 12, '0', '0', '2018-09-04 05:58:41', '2018-09-28 09:01:38', '0');
INSERT INTO `sys_menu` VALUES (3000, '系统监控', NULL, '/daemon', -1, 'icon-msnui-supervise', 'Layout', 2, '0', '0', '2018-07-27 01:13:21', '2018-09-28 08:53:24', '0');
INSERT INTO `sys_menu` VALUES (3100, '服务监控', NULL, 'http://127.0.0.1:5001', 3000, 'icon-server', NULL, 0, '0', '0', '2018-06-26 10:50:32', '2018-12-11 17:17:07', '0');
INSERT INTO `sys_menu` VALUES (3200, '接口文档', NULL, 'http://bigdata-gateway:2999/swagger-ui.html', 3000, 'icon-wendang', NULL, 1, '0', '0', '2018-06-26 10:50:32', '2018-09-28 09:05:16', '0');
INSERT INTO `sys_menu` VALUES (3300, '事务监控', NULL, 'tx', 3000, 'icon-gtsquanjushiwufuwuGTS', 'views/tx/index', 5, '0', '0', '2018-08-19 11:02:39', '2018-11-23 14:25:08', '0');
INSERT INTO `sys_menu` VALUES (3400, '在线事务', NULL, 'model', 3000, 'icon-online', 'views/tx/model', 6, '0', '0', '2018-08-19 11:32:04', '2018-11-23 14:25:14', '0');
INSERT INTO `sys_menu` VALUES (3500, '任务监控', NULL, 'http://127.0.0.1:8899', 3000, 'icon-msnui-supervise', NULL, 7, '0', '0', '2018-06-26 10:50:32', '2018-09-28 09:03:33', '0');
INSERT INTO `sys_menu` VALUES (3600, '任务轨迹', '', 'status-trace-log', 3000, 'icon-guiji', 'views/daemon/status-trace-log/index', 8, '0', '0', '2018-01-20 13:17:19', '2018-11-24 06:34:52', '0');
INSERT INTO `sys_menu` VALUES (3601, '删除任务轨迹', 'daemon_status_trace_log_del', NULL, 3600, '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2018-11-24 06:37:31', '0');
INSERT INTO `sys_menu` VALUES (3700, '调用拓扑', NULL, 'http://127.0.0.1:8081', 3000, 'icon-line', NULL, 10, '0', '0', '2018-01-25 11:08:52', '2018-09-28 09:04:29', '0');
INSERT INTO `sys_menu` VALUES (3800, '缓存状态', NULL, 'http://127.0.0.1:8585', 3000, 'icon-qingchuhuancun', NULL, 12, '0', '0', '2018-01-23 10:56:11', '2018-09-28 09:13:56', '0');
INSERT INTO `sys_menu` VALUES (3900, '任务日志', '', 'execution-log', 3000, 'icon-guiji', 'views/daemon/execution-log/index', 8, '0', '0', '2018-01-20 13:17:19', '2018-11-24 06:34:52', '0');
INSERT INTO `sys_menu` VALUES (3901, '删除日志', 'daemon_execution_log_del', NULL, 3900, '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2018-11-24 06:39:03', '0');
INSERT INTO `sys_menu` VALUES (4000, '协同管理', NULL, '/activti', -1, 'icon-kuaisugongzuoliu_o', 'Layout', 3, '0', '0', '2018-09-26 01:38:13', '2018-09-28 08:58:24', '0');
INSERT INTO `sys_menu` VALUES (4100, '模型管理', NULL, 'activiti', 4000, 'icon-weibiaoti13', 'views/activiti/index', 1, '0', '0', '2018-09-26 01:39:07', '2018-12-09 16:35:16', '0');
INSERT INTO `sys_menu` VALUES (4101, '模型管理', 'act_model_manage', NULL, 4100, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:12:07', '0');
INSERT INTO `sys_menu` VALUES (4200, '流程管理', NULL, 'process', 4000, 'icon-liucheng', 'views/activiti/process', 2, '0', '0', '2018-09-26 06:41:05', '2018-11-23 14:25:33', '0');
INSERT INTO `sys_menu` VALUES (4201, '流程管理', 'act_process_manage', NULL, 4200, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:12:07', '0');
INSERT INTO `sys_menu` VALUES (4300, '请假管理', '', 'leavebill', 4000, 'icon-qingjia', 'views/activiti/leave', 3, '0', '0', '2018-01-20 13:17:19', '2018-12-09 16:35:20', '0');
INSERT INTO `sys_menu` VALUES (4301, '请假新增', 'act_leavebill_add', NULL, 4300, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:12:07', '0');
INSERT INTO `sys_menu` VALUES (4302, '请假修改', 'act_leavebill_edit', NULL, 4300, '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:12:09', '0');
INSERT INTO `sys_menu` VALUES (4303, '请假删除', 'act_leavebill_del', NULL, 4300, '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:12:14', '0');
INSERT INTO `sys_menu` VALUES (4400, '待办任务', NULL, 'task', 4000, 'icon-renwu', 'views/activiti/task', 4, '0', '0', '2018-09-27 09:52:31', '2018-12-09 16:35:26', '0');
INSERT INTO `sys_menu` VALUES (4401, '流程管理', 'act_task_manage', NULL, 4400, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:12:07', '0');
INSERT INTO `sys_menu` VALUES (5000, '一级菜单', NULL, '/crud', -1, 'icon-caidanguanli', '', 4, '0', '0', '2018-08-28 01:50:22', '2018-09-28 08:58:20', '0');
INSERT INTO `sys_menu` VALUES (5001, '一级菜单', NULL, 'index', 5000, 'icon-caidanguanli', 'views/crud/index', 1, '0', '0', '2018-08-28 01:50:48', '2018-11-21 17:48:19', '1');
INSERT INTO `sys_menu` VALUES (5002, '二级菜单', NULL, 'crud', 5001, 'icon-caidanguanli', 'views/crud/index', 1, '0', '0', '2018-08-28 01:51:23', '2018-11-21 17:47:40', '1');
INSERT INTO `sys_menu` VALUES (5003, '二级菜单', NULL, '', 5000, 'icon-caidanguanli', '', 1, '0', '0', '2018-11-21 17:49:18', '2018-11-21 17:53:25', '0');
INSERT INTO `sys_menu` VALUES (5004, '二级菜单', NULL, 'index', 5003, 'icon-caidanguanli', 'views/crud/index', 1, '0', '0', '2018-11-21 17:53:51', '2018-12-20 14:26:53', '1');
INSERT INTO `sys_menu` VALUES (6000, '系统官网', NULL, '', -1, 'icon-guanwang', '', 5, '0', '0', '2018-09-27 02:26:36', '2019-02-27 17:14:26', '1');
INSERT INTO `sys_menu` VALUES (9999, '系统官网', NULL, '', -1, 'icon-guanwangfangwen', NULL, 9, '0', '0', '2019-01-17 17:05:19', '2019-01-17 17:29:06', '0');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源编号',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '钥匙',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '域',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权模式',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT '令牌时效',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT '刷新时效',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展信息',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自动放行',
  `tenant_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '终端信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('app', NULL, 'app', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', 1);
INSERT INTO `sys_oauth_client_details` VALUES ('bos', NULL, 'bos', 'server', 'password,refresh_token,authorization_code', 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login', NULL, NULL, NULL, NULL, 'true', 1);
INSERT INTO `sys_oauth_client_details` VALUES ('daemon', NULL, 'daemon', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', 1);
INSERT INTO `sys_oauth_client_details` VALUES ('gen', NULL, 'gen', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', 1);
INSERT INTO `sys_oauth_client_details` VALUES ('test', NULL, 'test', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `ds_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '2' COMMENT '数据权限类型',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '数据权限范围',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_idx1_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '系统角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'ROLE_ADMIN', '管理员', '0', '2', '2017-10-29 15:45:51', '2018-12-26 14:09:11', '0', 1);
INSERT INTO `sys_role` VALUES (2, 'ROLE_CQQ', 'ROLE_CQQ', 'ROLE_CQQ', '2', NULL, '2018-11-11 19:42:26', '2018-12-26 14:09:07', '0', 2);
INSERT INTO `sys_role` VALUES (3, '普通用户', '普通用户', '普通用户', '1', '11', '2019-01-25 11:05:11', NULL, '0', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1000);
INSERT INTO `sys_role_menu` VALUES (1, 1100);
INSERT INTO `sys_role_menu` VALUES (1, 1101);
INSERT INTO `sys_role_menu` VALUES (1, 1102);
INSERT INTO `sys_role_menu` VALUES (1, 1103);
INSERT INTO `sys_role_menu` VALUES (1, 1200);
INSERT INTO `sys_role_menu` VALUES (1, 1201);
INSERT INTO `sys_role_menu` VALUES (1, 1202);
INSERT INTO `sys_role_menu` VALUES (1, 1203);
INSERT INTO `sys_role_menu` VALUES (1, 1300);
INSERT INTO `sys_role_menu` VALUES (1, 1301);
INSERT INTO `sys_role_menu` VALUES (1, 1302);
INSERT INTO `sys_role_menu` VALUES (1, 1303);
INSERT INTO `sys_role_menu` VALUES (1, 1304);
INSERT INTO `sys_role_menu` VALUES (1, 1400);
INSERT INTO `sys_role_menu` VALUES (1, 1401);
INSERT INTO `sys_role_menu` VALUES (1, 1402);
INSERT INTO `sys_role_menu` VALUES (1, 1403);
INSERT INTO `sys_role_menu` VALUES (1, 2000);
INSERT INTO `sys_role_menu` VALUES (1, 2100);
INSERT INTO `sys_role_menu` VALUES (1, 2101);
INSERT INTO `sys_role_menu` VALUES (1, 2200);
INSERT INTO `sys_role_menu` VALUES (1, 2201);
INSERT INTO `sys_role_menu` VALUES (1, 2202);
INSERT INTO `sys_role_menu` VALUES (1, 2203);
INSERT INTO `sys_role_menu` VALUES (1, 2300);
INSERT INTO `sys_role_menu` VALUES (1, 2400);
INSERT INTO `sys_role_menu` VALUES (1, 2401);
INSERT INTO `sys_role_menu` VALUES (1, 2402);
INSERT INTO `sys_role_menu` VALUES (1, 2403);
INSERT INTO `sys_role_menu` VALUES (1, 2500);
INSERT INTO `sys_role_menu` VALUES (1, 2501);
INSERT INTO `sys_role_menu` VALUES (1, 2502);
INSERT INTO `sys_role_menu` VALUES (1, 2503);
INSERT INTO `sys_role_menu` VALUES (1, 2600);
INSERT INTO `sys_role_menu` VALUES (1, 2601);
INSERT INTO `sys_role_menu` VALUES (1, 2700);
INSERT INTO `sys_role_menu` VALUES (1, 3000);
INSERT INTO `sys_role_menu` VALUES (1, 3100);
INSERT INTO `sys_role_menu` VALUES (1, 3200);
INSERT INTO `sys_role_menu` VALUES (1, 3300);
INSERT INTO `sys_role_menu` VALUES (1, 3400);
INSERT INTO `sys_role_menu` VALUES (1, 3500);
INSERT INTO `sys_role_menu` VALUES (1, 3600);
INSERT INTO `sys_role_menu` VALUES (1, 3601);
INSERT INTO `sys_role_menu` VALUES (1, 3700);
INSERT INTO `sys_role_menu` VALUES (1, 3800);
INSERT INTO `sys_role_menu` VALUES (1, 3900);
INSERT INTO `sys_role_menu` VALUES (1, 3901);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1100);
INSERT INTO `sys_role_menu` VALUES (2, 1101);
INSERT INTO `sys_role_menu` VALUES (2, 1102);
INSERT INTO `sys_role_menu` VALUES (2, 1103);
INSERT INTO `sys_role_menu` VALUES (2, 1200);
INSERT INTO `sys_role_menu` VALUES (2, 1201);
INSERT INTO `sys_role_menu` VALUES (2, 1202);
INSERT INTO `sys_role_menu` VALUES (2, 1203);
INSERT INTO `sys_role_menu` VALUES (2, 1300);
INSERT INTO `sys_role_menu` VALUES (2, 1301);
INSERT INTO `sys_role_menu` VALUES (2, 1302);
INSERT INTO `sys_role_menu` VALUES (2, 1303);
INSERT INTO `sys_role_menu` VALUES (2, 1304);
INSERT INTO `sys_role_menu` VALUES (2, 1400);
INSERT INTO `sys_role_menu` VALUES (2, 1401);
INSERT INTO `sys_role_menu` VALUES (2, 1402);
INSERT INTO `sys_role_menu` VALUES (2, 1403);
INSERT INTO `sys_role_menu` VALUES (3, 2000);
INSERT INTO `sys_role_menu` VALUES (3, 2100);
INSERT INTO `sys_role_menu` VALUES (3, 2101);
INSERT INTO `sys_role_menu` VALUES (3, 2200);
INSERT INTO `sys_role_menu` VALUES (3, 2201);
INSERT INTO `sys_role_menu` VALUES (3, 2202);
INSERT INTO `sys_role_menu` VALUES (3, 2203);
INSERT INTO `sys_role_menu` VALUES (3, 2300);
INSERT INTO `sys_role_menu` VALUES (3, 2400);
INSERT INTO `sys_role_menu` VALUES (3, 2401);
INSERT INTO `sys_role_menu` VALUES (3, 2402);
INSERT INTO `sys_role_menu` VALUES (3, 2403);
INSERT INTO `sys_role_menu` VALUES (3, 2500);
INSERT INTO `sys_role_menu` VALUES (3, 2501);
INSERT INTO `sys_role_menu` VALUES (3, 2502);
INSERT INTO `sys_role_menu` VALUES (3, 2503);
INSERT INTO `sys_role_menu` VALUES (3, 2600);
INSERT INTO `sys_role_menu` VALUES (3, 2601);
INSERT INTO `sys_role_menu` VALUES (3, 2700);

-- ----------------------------
-- Table structure for sys_route_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_route_conf`;
CREATE TABLE `sys_route_conf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `route_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `route_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '路由ID',
  `predicates` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '断言',
  `filters` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '过滤器',
  `uri` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order` int(2) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '路由配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_route_conf
-- ----------------------------
INSERT INTO `sys_route_conf` VALUES (1, '工作流管理模块', 'bigdata-activiti', '[{\"args\": {\"_genkey_0\": \"/act/**\"}, \"name\": \"Path\"}]', '[]', 'lb://bigdata-activiti', 0, '2019-01-17 16:42:50', '2019-02-27 02:08:44', '0');
INSERT INTO `sys_route_conf` VALUES (2, '认证中心', 'bigdata-auth', '[{\"args\": {\"_genkey_0\": \"/auth/**\"}, \"name\": \"Path\"}]', '[{\"args\": {}, \"name\": \"ValidateCodeGatewayFilter\"}, {\"args\": {}, \"name\": \"PasswordDecoderFilter\"}]', 'lb://bigdata-auth', 0, '2019-01-17 16:42:50', '2019-02-27 02:08:46', '0');
INSERT INTO `sys_route_conf` VALUES (3, '代码生成模块', 'bigdata-codegen', '[{\"args\": {\"_genkey_0\": \"/gen/**\"}, \"name\": \"Path\"}]', '[]', 'lb://bigdata-codegen', 0, '2019-01-17 16:42:50', '2019-02-27 02:08:50', '0');
INSERT INTO `sys_route_conf` VALUES (4, '定时任务模块', 'bigdata-daemon', '[{\"args\": {\"_genkey_0\": \"/daemon/**\"}, \"name\": \"Path\"}]', '[]', 'lb://bigdata-daemon', 0, '2019-01-17 16:42:50', '2019-02-27 02:08:52', '0');
INSERT INTO `sys_route_conf` VALUES (5, '分布式事务模块', 'bigdata-tx-manager', '[{\"args\": {\"_genkey_0\": \"/tx/**\"}, \"name\": \"Path\"}]', '[]', 'lb://bigdata-tx-manager', 0, '2019-01-17 16:42:50', '2019-02-27 02:08:55', '0');
INSERT INTO `sys_route_conf` VALUES (6, '通用权限模块', 'bigdata-upms-biz', '[{\"args\": {\"_genkey_0\": \"/admin/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"20\", \"redis-rate-limiter.replenishRate\": \"10\"}, \"name\": \"RequestRateLimiter\"}, {\"args\": {\"name\": \"default\", \"fallbackUri\": \"forward:/fallback\"}, \"name\": \"Hystrix\"}]', 'lb://bigdata-upms-biz', 0, '2019-01-17 16:42:50', '2019-02-27 02:08:59', '0');
INSERT INTO `sys_route_conf` VALUES (15, '维修基金信息', 'information', '[{\"args\": {\"_genkey_0\": \"/info/**\"}, \"name\": \"Path\"}]', '[]', 'lb://information', 0, '2019-01-17 16:42:50', '2019-04-19 11:03:22', '0');
INSERT INTO `sys_route_conf` VALUES (16, '维修基金事务', 'affairs', '[{\"args\": {\"_genkey_0\": \"/affairs/**\"}, \"name\": \"Path\"}]', '[]', 'lb://affairs', 0, '2019-01-17 16:42:50', '2019-04-19 11:03:26', '0');
INSERT INTO `sys_route_conf` VALUES (17, '维修基金支付', 'pay', '[{\"args\": {\"_genkey_0\": \"/pay/**\"}, \"name\": \"Path\"}]', '[]', 'lb://pay', 0, '2019-01-17 16:42:50', '2019-04-19 11:03:30', '0');

-- ----------------------------
-- Table structure for sys_social_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_social_details`;
CREATE TABLE `sys_social_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'appid',
  `app_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'app_secret',
  `redirect_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统社交登录账号表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_social_details
-- ----------------------------
INSERT INTO `sys_social_details` VALUES (1, 'WX', '微信互联参数', 'wxd1678d3f83b1d83a', '6ddb043f94da5d2172926abe8533504f', 'daoweicloud.com', '2018-08-16 14:24:25', '2018-12-04 11:50:57', '0', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '简介',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `lock_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '0-正常，9-锁定',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '0-正常，1-删除',
  `wx_openid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信openid',
  `qq_openid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'QQ openid',
  `tenant_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属租户',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user_wx_openid`(`wx_openid`) USING BTREE,
  INDEX `user_qq_openid`(`qq_openid`) USING BTREE,
  INDEX `user_idx1_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$443LgOOR.gtWAKSC6UJG8.bAnnltd/7fksgcBDn93.l6pbs99EnXu', NULL, '18046030333', 'admin-9192b2af7526499a8c4b0b8e4b3f3a3f.png', 1, '2018-04-20 07:15:18', '2019-02-24 10:41:10', '0', '0', 'o_0FT0uyg_H1vVy2H0JpSwlVGhWQ', NULL, 1);
-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (4, 1);
INSERT INTO `sys_user_role` VALUES (5, 3);

SET FOREIGN_KEY_CHECKS = 1;
