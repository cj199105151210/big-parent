-- ----------------------------
-- Table structure for sys_dict
-- 新添加定时模块路由、定时模块菜单
-- ----------------------------

-- ----------------------------
-- Table structure for 定时模块路由
-- ----------------------------
INSERT INTO `bigdata`.`sys_route_conf`(`id`, `route_name`, `route_id`, `predicates`, `filters`, `uri`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (14, '定时模块', 'bigdata-daemon-quartz', '[{\"args\": {\"_genkey_0\": \"/job/**\"}, \"name\": \"Path\"}]', '[]', 'lb://bigdata-daemon-quartz', 0, '2019-01-17 16:42:50', '2019-04-12 10:04:56', '0');
-- ----------------------------
-- Table structure for 定时模块菜单
-- ----------------------------
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2800, 'Quartz管理', '', 'job-manage', 2000, 'icon-guanwangfangwen', 'views/daemon/job-manage/index', 8, '0', '0', '2018-01-20 13:17:19', '2019-03-25 13:52:14', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2810, '任务新增', 'job_sys_job_add', NULL, 2800, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2019-03-25 11:50:23', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2820, '任务修改', 'job_sys_job_edit', NULL, 2800, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2019-03-25 11:50:26', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2830, '任务删除', 'job_sys_job_del', NULL, 2800, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2019-03-25 11:50:30', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2840, '任务暂停', 'job_sys_job_shutdown_job', NULL, 2800, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2019-03-25 11:50:18', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2850, '任务开始', 'job_sys_job_start_job', NULL, 2800, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2019-03-25 11:50:35', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (2860, '任务刷新', 'job_sys_job_refresh_job', NULL, 2800, '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2019-03-25 11:50:39', '0');
INSERT INTO `bigdata`.`sys_menu`(`menu_id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES (3620, 'Quartz日志', '', 'joblog', 3000, 'icon-gtsquanjushiwufuwuGTS', 'views/daemon/job-log/index', 8, '0', '0', '2018-01-20 13:17:19', '2019-03-25 11:49:36', '0');

-- ----------------------------
-- Table structure for 参数配置
-- ----------------------------
DROP TABLE IF EXISTS `sys_common_parameter`;
CREATE TABLE `sys_common_parameter`  (
  `public_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统id',
  `public_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公共参数名称',
  `public_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公共参数地址值',
  `public_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态（1有效；2无效；）',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '删除状态（1：正常 2：已删除）',
  `validate_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公共参数编码',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `public_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '模块类型',
  PRIMARY KEY (`public_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 327327 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公共参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_common_parameter
-- ----------------------------
INSERT INTO `sys_common_parameter` VALUES (18808, '生成全量索引的吞吐量', 'SOLR_INDEX_NUM', '10000', '1', '0', NULL, '2018-11-13 10:35:12', '2019-04-11 18:05:34', '1');
INSERT INTO `sys_common_parameter` VALUES (22211, '上传文件方式', 'UPLOAD_TYPE', 'UDS', '1', '0', '^(UDS|LOCAL)$', '2018-11-13 10:35:12', '2019-04-11 18:05:36', '1');
INSERT INTO `sys_common_parameter` VALUES (188099, '上次创建原文索引的时间', 'SOLR_CREATE_INDEX_TIME_EFILE', '2013-12-24 17:15:39', '1', '0', '1', '2018-11-13 10:35:12', '2019-04-11 18:05:37', '1');
INSERT INTO `sys_common_parameter` VALUES (327163, '高级检索中的快捷检索字段', 'ADVSEARCH_QUICKFIELDS', 'FLH,FLMC,cwrq,jgdm,mj', '1', '0', NULL, '2018-11-13 10:35:12', '2019-04-11 18:05:38', '1');
INSERT INTO `sys_common_parameter` VALUES (327326, '上传文件限制大小', 'FILE_SIZE_LIMIT', '2048MB', '1', '0', NULL, '2018-11-13 10:35:12', '2019-04-11 18:05:40', '1');

SET FOREIGN_KEY_CHECKS = 1;



-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';
-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES ('1', 'log_type', '日志类型', '2019-03-19 11:06:44', '2019-03-19 11:06:44', '异常、正常', '0', '1');
COMMIT;
-- ----------------------------
--  Table structure for `sys_dict_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_id` int(11) NOT NULL,
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(10) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典项';

-- ----------------------------
--  Records of `sys_dict_item`
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', '1', '9', '异常', 'log_type', '日志异常', '1', '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '0', '1'), ('2', '1', '0', '正常', 'log_type', '日志正常', '0', '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '0', '1');


-- ----------------------------
-- Table structure for sys_user 添加字段
-- ----------------------------

alter table sys_oauth_client_details add portal_name varchar(255);
-- ----------------------------
-- Table structure for sys_user 字段长度修正
-- ----------------------------
alter table sys_user modify column del_flag varchar(1) ;
-- ----------------------------
-- Table structure for sys_menu 域名解决跨域问题
-- ----------------------------
UPDATE `bigdata`.`sys_menu` SET `name` = '接口文档', `permission` = NULL, `path` = 'http://bigdata-gateway:2999/swagger-ui.html', `parent_id` = 3000, `icon` = 'icon-wendang', `component` = NULL, `sort` = 1, `keep_alive` = '0', `type` = '0', `create_time` = '2019-03-26 10:50:32', `update_time` = '2019-03-28 09:05:16', `del_flag` = '0' WHERE `menu_id` = 3200;
commit;


DROP TABLE IF EXISTS `sys_common_parameter`;
CREATE TABLE `sys_common_parameter`  (
  `public_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统id',
  `public_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公共参数名称',
  `public_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公共参数地址值',
  `public_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注信息',
  `status` decimal(4, 0) NULL DEFAULT 1 COMMENT '状态（1有效；2无效；）',
  `delete_flag` decimal(4, 0) NULL DEFAULT 1 COMMENT '删除状态（1：正常 2：已删除）',
  `validate_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公共参数编码',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `public_type` smallint(1) NULL DEFAULT 0 COMMENT '模块类型',
  PRIMARY KEY (`public_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 327327 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公共参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_common_parameter
-- ----------------------------
INSERT INTO `sys_common_parameter` VALUES (18808, '生成全量索引的吞吐量', 'SOLR_INDEX_NUM', '10000', 1, 1, NULL, '2018-11-13 10:35:12', '2018-11-13 10:35:12', 1);
INSERT INTO `sys_common_parameter` VALUES (22211, '上传文件方式', 'UPLOAD_TYPE', 'UDS', 1, 1, '^(UDS|LOCAL)$', '2018-11-13 10:35:12', '2018-11-13 10:39:12', 1);
INSERT INTO `sys_common_parameter` VALUES (188099, '上次创建原文索引的时间', 'SOLR_CREATE_INDEX_TIME_EFILE', '2013-12-24 17:15:39', 1, 1, '1', '2018-11-13 10:35:12', '2018-11-13 10:35:12', 1);
INSERT INTO `sys_common_parameter` VALUES (327163, '高级检索中的快捷检索字段', 'ADVSEARCH_QUICKFIELDS', 'FLH,FLMC,cwrq,jgdm,mj', 1, 1, NULL, '2018-11-13 10:35:12', '2018-11-13 10:35:12', 1);
INSERT INTO `sys_common_parameter` VALUES (327326, '上传文件限制大小', 'FILE_SIZE_LIMIT', '2048MB', 1, 1, NULL, '2018-11-13 10:35:12', '2018-11-13 10:39:12', 1);


-- ----------------------------
-- upms运维菜单id设置成自增，无需手输
-- ----------------------------
ALTER TABLE `bigdata`.`sys_menu` MODIFY COLUMN `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID' FIRST;