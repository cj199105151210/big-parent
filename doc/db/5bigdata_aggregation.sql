/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : bigdata_aggregation

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 08/04/2019 09:55:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aggr_data
-- ----------------------------
DROP TABLE IF EXISTS `aggr_data`;
CREATE TABLE `aggr_data`  (
  `data_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `subject_id` int(8) NOT NULL COMMENT '对应栏目表主键',
  `url` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '链接',
  `describe_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`data_id`) USING BTREE,
  INDEX `data_id`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aggr_gateway
-- ----------------------------
DROP TABLE IF EXISTS `aggr_gateway`;
CREATE TABLE `aggr_gateway`  (
  `gateway_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '编码',
  `type` varchar(4) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '类型',
  `identity` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份标识_数据字典：106',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`gateway_id`) USING BTREE,
  INDEX `gateway_id`(`gateway_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aggr_gateway
-- ----------------------------
INSERT INTO `aggr_gateway` VALUES (2, 'banshidating', '12', NULL, 'lifei', '2019-03-29 15:44:46');
INSERT INTO `aggr_gateway` VALUES (4, 'shouye', '12', NULL, 'lifei', '2019-03-29 15:44:46');
INSERT INTO `aggr_gateway` VALUES (5, 'wode', '12', NULL, 'lifei', '2019-03-29 15:44:46');

-- ----------------------------
-- Table structure for aggr_hyperlink
-- ----------------------------
DROP TABLE IF EXISTS `aggr_hyperlink`;
CREATE TABLE `aggr_hyperlink`  (
  `hyperlink_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `subject_id` int(8) NOT NULL COMMENT '对应栏目表主键',
  `url` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '链接',
  `describe_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `open_mode` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '打开方式_数据字典：104',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`hyperlink_id`) USING BTREE,
  INDEX `hyperlink_id`(`hyperlink_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aggr_hyperlink
-- ----------------------------
INSERT INTO `aggr_hyperlink` VALUES (3, 74, 'http://www.baidu.com', '喜讯：海南省成功开具出三类财政电子票据', '11', 'admin', '2019-04-01 18:16:59');
INSERT INTO `aggr_hyperlink` VALUES (4, 75, 'http://www.baidu.com', '我司举办“拥抱春天 砥砺前行”活动', '12', 'admin', '2019-04-01 18:17:40');
INSERT INTO `aggr_hyperlink` VALUES (6, 73, 'http://www.baidu.com', '关于我院2018年度奖学金公告', '11', 'admin', '2019-04-02 09:40:19');

-- ----------------------------
-- Table structure for aggr_lightapp
-- ----------------------------
DROP TABLE IF EXISTS `aggr_lightapp`;
CREATE TABLE `aggr_lightapp`  (
  `lightapp_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '编码',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `photo_url` varchar(1024) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '图片地址',
  `url` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '链接',
  `service_category` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '服务类别_数据字典：105',
  `processing_time` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '办理时间',
  `processing_place` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办理地点',
  `service_dpt` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '服务部门',
  `service_mode` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '服务方式',
  `service_topice` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务专题',
  `contacts` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '联系电话',
  `describe_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `status` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '状态',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`lightapp_id`) USING BTREE,
  INDEX `lightapp_id`(`lightapp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aggr_lightapp
-- ----------------------------
INSERT INTO `aggr_lightapp` VALUES (1, '', 'lightApp', '1553656066845.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-03-12 16:38:15');
INSERT INTO `aggr_lightapp` VALUES (2, '', 'zhifubao', '8f171c942c60552f27beedb99d2f64bb.jpg', 'www.zhifubao', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-03-22 09:40:06');
INSERT INTO `aggr_lightapp` VALUES (3, 'lightapp1', '办事', 'icon-service.png', 'ServiceList/login', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (4, 'lightapp2', '进度', 'icon-progress.png', 'ItemProgressList/login', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (5, 'lightapp3', '审批', 'icon-approve.png', 'ItemApproveList/login', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (6, 'lightapp4', '评价', 'icon-evaluate.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (7, 'lightapp5', '工资查询', 'service-query.png', 'WageEnquiry/login', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (8, 'lightapp6', '一周会表', 'service-meeting.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (9, 'lightapp7', '报销申请', 'service-payment.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (10, 'lightapp8', '中层领导请假', 'service-mid-leader-leave.png', 'LeaderLeave/login?formId=80d2114e744741c3bc8c2c11ae31a426', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (11, 'lightapp9', '接待用餐', 'service-reception-dinner.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (12, 'lightapp10', '合同会签', 'service-sign-contract.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (13, 'lightapp11', '用车申请', 'service-use-car.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (14, 'lightapp12', '用章申请', 'service-use-seal.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (15, 'lightapp13', '生活缴费', 'service-living-payment.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (16, 'lightapp14', '报修申请', 'service-repairs.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (17, 'lightapp15', '物资申购', 'service-buy-goods.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (18, 'lightapp16', '学校简介', 'logo-campus-profile.png', 'CampusProfile', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (19, 'lightapp17', '学校地图', 'logo-campus-map.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (20, 'lightapp18', '学校年历', 'logo-campus-calendar.png', 'SinglepageCalendar', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (21, 'lightapp19', '学校黄页', 'logo-campus-yellow-page.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (22, 'lightapp20', '身份认证', 'identity-auth.png', 'IdentityAuth/login', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (23, 'lightapp21', '我的档案', 'my-archives.png', 'MyArchives/login', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (24, 'lightapp22', '我的消息', 'my-message.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (25, 'lightapp23', '我的证件', 'my-certificate.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (26, 'lightapp24', '我的钱包', 'my-wallet.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (27, 'lightapp25', '我的信用分', 'my-credit-score.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (28, 'lightapp26', '客服服务', 'customer-service.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');
INSERT INTO `aggr_lightapp` VALUES (29, 'lightapp27', '常见问题', 'faq.png', 'http://www.baidu.com', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 'lifei', '2019-04-01 14:52:17');

-- ----------------------------
-- Table structure for aggr_slideshow
-- ----------------------------
DROP TABLE IF EXISTS `aggr_slideshow`;
CREATE TABLE `aggr_slideshow`  (
  `slideshow_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `subject_id` int(8) NOT NULL COMMENT '对应栏目表主键',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `photo_url` varchar(1024) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '图片地址',
  `url` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '链接',
  `sort` int(8) NOT NULL COMMENT '排序',
  `describe_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `open_mode` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '打开方式_数据字典：104',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`slideshow_id`) USING BTREE,
  INDEX `slideshow_id`(`slideshow_id`) USING BTREE,
  INDEX `subject_id`(`subject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aggr_slideshow
-- ----------------------------
INSERT INTO `aggr_slideshow` VALUES (50, 60, '轮播图1', '1554107754155.png', 'http://www.baidu.com', 1, NULL, '11', 'admin', '2019-04-02 10:19:14');
INSERT INTO `aggr_slideshow` VALUES (51, 60, '轮播图2', '1554171503297.png', 'http://www.baidu.com', 2, NULL, '11', 'admin', '2019-04-02 10:19:14');
INSERT INTO `aggr_slideshow` VALUES (52, 60, '轮播图3', '1554171534836.png', 'http://www.baidu.com', 3, NULL, '11', 'admin', '2019-04-02 10:19:14');
INSERT INTO `aggr_slideshow` VALUES (61, 81, '轮播图1', '1554176078431.png', 'http://www.baidu.com', 1, NULL, '11', 'admin', '2019-04-02 17:03:57');
INSERT INTO `aggr_slideshow` VALUES (62, 81, '轮播图2', '1554176101533.png', 'http://www.baidu.com', 2, NULL, '11', 'admin', '2019-04-02 17:03:57');
INSERT INTO `aggr_slideshow` VALUES (63, 81, '轮播图3', '1554176130049.png', 'http://www.baidu.com', 3, NULL, '11', 'admin', '2019-04-02 17:03:57');
INSERT INTO `aggr_slideshow` VALUES (64, 81, '轮播图4', '1554195819129.png', 'http://www.baidu.com', 4, NULL, '11', 'admin', '2019-04-02 17:03:57');
INSERT INTO `aggr_slideshow` VALUES (65, 86, '掌上办事大厅', '1554184152777.png', 'http://www.baidu.com', 1, NULL, '11', 'admin', '2019-04-02 17:31:01');
INSERT INTO `aggr_slideshow` VALUES (66, 86, '互动平台', '1554197368470.png', 'InteractivePlatform', 2, NULL, '11', 'admin', '2019-04-02 17:31:01');
INSERT INTO `aggr_slideshow` VALUES (67, 86, '平安校园', '1554197398442.png', 'http://www.baidu.com', 3, NULL, '11', 'admin', '2019-04-02 17:31:01');
INSERT INTO `aggr_slideshow` VALUES (68, 86, '信用校园', '1554197425342.png', 'http://www.baidu.com', 4, NULL, '11', 'admin', '2019-04-02 17:31:01');
INSERT INTO `aggr_slideshow` VALUES (69, 86, '信息查询', '1554197448281.png', 'SearchPage', 5, NULL, '11', 'admin', '2019-04-02 17:31:01');

-- ----------------------------
-- Table structure for aggr_subject
-- ----------------------------
DROP TABLE IF EXISTS `aggr_subject`;
CREATE TABLE `aggr_subject`  (
  `subject_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `subject_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `gateway_id` int(8) NOT NULL COMMENT '门户信息表主键',
  `lightapp_id` int(8) NULL DEFAULT NULL COMMENT '对应轻应用信息表主键',
  `type_flag` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '类型标识_数据字典：100',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sort` int(8) NOT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '状态',
  `show_type` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '显示性质_数据字典：101',
  `operation_type` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '操作类型_数据字典：102',
  `parent_subject_id` int(8) NULL DEFAULT NULL COMMENT '对应父结点在当前表的主键',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`subject_id`) USING BTREE,
  INDEX `subject_id`(`subject_id`) USING BTREE,
  INDEX `gateway_id`(`gateway_id`) USING BTREE,
  INDEX `widget_id`(`lightapp_id`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `parent_subject_id`(`parent_subject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aggr_subject
-- ----------------------------
INSERT INTO `aggr_subject` VALUES (42, 'banshidating', 2, NULL, '16', '办事大厅', 1, '1', '11', '11', 0, 'lifei', '2019-03-14 09:50:56');
INSERT INTO `aggr_subject` VALUES (50, 'banshi', 2, NULL, '11', '办事', 1, '1', '11', '11', 42, 'admin', '2019-04-01 16:12:36');
INSERT INTO `aggr_subject` VALUES (52, 'zhanneitongzhi', 2, NULL, '11', '站内通知', 2, '1', '11', '11', 42, 'admin', '2019-04-02 16:48:54');
INSERT INTO `aggr_subject` VALUES (53, 'lunbotuzhuanqu', 2, NULL, '11', '轮播图专区', 3, '1', '11', '11', 42, 'admin', '2019-04-01 16:14:48');
INSERT INTO `aggr_subject` VALUES (54, 'banshizhuanqu', 2, NULL, '11', '办事专区', 4, '1', '11', '12', 42, 'admin', '2019-04-01 16:41:29');
INSERT INTO `aggr_subject` VALUES (56, '1-1', 2, 3, '12', '办事', 1, '1', '11', '11', 50, 'admin', '2019-04-01 16:18:37');
INSERT INTO `aggr_subject` VALUES (57, '1-2', 2, 4, '12', '进度', 2, '1', '11', '11', 50, 'admin', '2019-04-01 16:33:03');
INSERT INTO `aggr_subject` VALUES (58, '1-3', 2, 5, '12', '审批', 3, '1', '11', '11', 50, 'admin', '2019-04-01 16:34:43');
INSERT INTO `aggr_subject` VALUES (59, '1-4', 2, 6, '12', '评价', 4, '1', '11', '11', 50, 'admin', '2019-04-01 16:35:03');
INSERT INTO `aggr_subject` VALUES (60, '3-1', 2, NULL, '13', '轮播图', 1, '1', '11', '11', 53, 'admin', '2019-04-02 10:19:14');
INSERT INTO `aggr_subject` VALUES (61, '4-1', 2, 7, '12', '工资查询', 1, '1', '11', '11', 54, 'admin', '2019-04-01 16:36:45');
INSERT INTO `aggr_subject` VALUES (62, '4-2', 2, 8, '12', '一周会表', 2, '1', '11', '11', 54, 'admin', '2019-04-01 16:37:10');
INSERT INTO `aggr_subject` VALUES (63, '4-3', 2, 9, '12', '报销申请', 3, '1', '11', '11', 54, 'admin', '2019-04-01 16:37:33');
INSERT INTO `aggr_subject` VALUES (64, '4-4', 2, 10, '12', '中层领导请假', 4, '1', '11', '11', 54, 'admin', '2019-04-01 16:37:50');
INSERT INTO `aggr_subject` VALUES (65, '4-5', 2, 11, '12', '接待用餐', 5, '1', '11', '11', 54, 'admin', '2019-04-01 16:38:07');
INSERT INTO `aggr_subject` VALUES (66, '4-6', 2, 12, '12', '合同会签', 6, '1', '11', '11', 54, 'admin', '2019-04-01 16:38:25');
INSERT INTO `aggr_subject` VALUES (67, '4-7', 2, 13, '12', '用车申请', 7, '1', '11', '11', 54, 'admin', '2019-04-01 16:38:43');
INSERT INTO `aggr_subject` VALUES (68, '4-8', 2, 14, '12', '用章申请', 8, '1', '11', '11', 54, 'admin', '2019-04-01 16:39:07');
INSERT INTO `aggr_subject` VALUES (69, '4-9', 2, 15, '12', '生活缴费', 9, '1', '11', '11', 54, 'admin', '2019-04-01 16:39:31');
INSERT INTO `aggr_subject` VALUES (70, '4-10', 2, 16, '12', '报修申请', 10, '1', '11', '11', 54, 'admin', '2019-04-01 16:39:50');
INSERT INTO `aggr_subject` VALUES (71, '4-11', 2, 17, '12', '物资申购', 11, '1', '11', '11', 54, 'admin', '2019-04-01 16:40:10');
INSERT INTO `aggr_subject` VALUES (73, '2-1', 2, NULL, '15', '关于我院2018年度奖学金公告', 1, '1', '11', '11', 52, 'admin', '2019-04-02 09:40:19');
INSERT INTO `aggr_subject` VALUES (74, '2-2', 2, NULL, '15', '喜讯：海南省成功开具出三类财政电子票据', 2, '1', '11', '11', 52, 'admin', '2019-04-01 18:16:59');
INSERT INTO `aggr_subject` VALUES (75, '2-3', 2, NULL, '15', '我司举办“拥抱春天 砥砺前行”活动', 3, '1', '11', '11', 52, 'admin', '2019-04-01 18:17:40');
INSERT INTO `aggr_subject` VALUES (76, 'shouye', 4, NULL, '16', '首页', 2, '1', '11', '11', 0, 'lifei', '2019-03-14 09:50:56');
INSERT INTO `aggr_subject` VALUES (77, 'shouyelunbotu', 4, NULL, '11', '首页轮播图', 1, '1', '11', '11', 76, 'admin', '2019-04-02 11:32:20');
INSERT INTO `aggr_subject` VALUES (78, 'xuexiaogaikuang', 4, NULL, '11', '学校概况', 2, '1', '11', '11', 76, 'admin', '2019-04-02 11:32:42');
INSERT INTO `aggr_subject` VALUES (79, 'xuexiaozhuanti', 4, NULL, '11', '学校专题', 3, '1', '11', '11', 76, 'admin', '2019-04-02 11:33:01');
INSERT INTO `aggr_subject` VALUES (80, 'xuexiaoxinwen', 4, NULL, '11', '学校新闻', 4, '1', '11', '11', 76, 'admin', '2019-04-02 11:33:28');
INSERT INTO `aggr_subject` VALUES (81, '', 4, NULL, '13', '轮播图', 1, '1', '11', '11', 77, 'admin', '2019-04-02 17:03:57');
INSERT INTO `aggr_subject` VALUES (82, NULL, 4, 18, '12', '学校简介', 1, '1', '11', '11', 78, 'admin', '2019-04-02 13:42:40');
INSERT INTO `aggr_subject` VALUES (83, NULL, 4, 19, '12', '学校地图', 2, '1', '11', '11', 78, 'admin', '2019-04-02 13:43:03');
INSERT INTO `aggr_subject` VALUES (84, NULL, 4, 20, '12', '学校年历', 3, '1', '11', '11', 78, 'admin', '2019-04-02 13:43:24');
INSERT INTO `aggr_subject` VALUES (85, NULL, 4, 21, '12', '学校黄页', 4, '1', '11', '11', 78, 'admin', '2019-04-02 13:43:47');
INSERT INTO `aggr_subject` VALUES (86, NULL, 4, NULL, '13', '掌上办事大厅', 1, '1', '11', '11', 79, 'admin', '2019-04-02 17:31:01');
INSERT INTO `aggr_subject` VALUES (91, 'wode', 5, NULL, '16', '我的', 3, '1', '11', '11', 0, 'lifei', '2019-03-14 09:50:56');
INSERT INTO `aggr_subject` VALUES (92, 'wodelanmu1', 5, NULL, '11', '我的栏目1', 1, '1', '11', '11', 91, 'admin', '2019-04-02 14:56:00');
INSERT INTO `aggr_subject` VALUES (93, 'wodelanmu2', 5, NULL, '11', '我的栏目2', 2, '1', '11', '11', 91, 'admin', '2019-04-02 14:56:19');
INSERT INTO `aggr_subject` VALUES (94, 'wodelanmu3', 5, NULL, '11', '我的栏目3', 3, '1', '11', '11', 91, 'admin', '2019-04-02 14:56:35');
INSERT INTO `aggr_subject` VALUES (95, NULL, 5, 22, '12', '身份认证', 1, '1', '11', '11', 92, 'admin', '2019-04-02 15:05:12');
INSERT INTO `aggr_subject` VALUES (96, NULL, 5, 23, '12', '我的档案', 2, '1', '11', '11', 92, 'admin', '2019-04-02 15:05:30');
INSERT INTO `aggr_subject` VALUES (97, NULL, 5, 24, '12', '我的消息', 3, '1', '11', '11', 92, 'admin', '2019-04-02 15:05:49');
INSERT INTO `aggr_subject` VALUES (98, NULL, 5, 25, '12', '我的证件', 1, '1', '11', '11', 93, 'admin', '2019-04-02 15:09:07');
INSERT INTO `aggr_subject` VALUES (99, NULL, 5, 26, '12', '我的钱包', 2, '1', '11', '11', 93, 'admin', '2019-04-02 15:06:37');
INSERT INTO `aggr_subject` VALUES (100, NULL, 5, 27, '12', '我的信用分', 3, '1', '11', '11', 93, 'admin', '2019-04-02 15:06:55');
INSERT INTO `aggr_subject` VALUES (101, NULL, 5, 28, '12', '客服服务', 1, '1', '11', '11', 94, 'admin', '2019-04-02 15:07:15');
INSERT INTO `aggr_subject` VALUES (102, NULL, 5, 29, '12', '常见问题', 2, '1', '11', '11', 94, 'admin', '2019-04-02 15:07:33');
INSERT INTO `aggr_subject` VALUES (103, NULL, 2, 1, '12', '测试删除', 11, '1', '11', '11', 42, 'admin', '2019-04-04 14:17:18');

-- ----------------------------
-- Table structure for aggr_subject_rel
-- ----------------------------
DROP TABLE IF EXISTS `aggr_subject_rel`;
CREATE TABLE `aggr_subject_rel`  (
  `subject_rel_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `subject_id` int(8) NULL DEFAULT NULL COMMENT '对应栏目表主键',
  `lightapp_id` int(8) NULL DEFAULT NULL COMMENT '对应轻应用表主键',
  `parent_subject_id` int(8) NULL DEFAULT NULL COMMENT '对应父结点在栏目表的主键',
  `gateway_code` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '门户信息表编码',
  `user_id` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '用户ID',
  `show_status` varchar(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '显示状态（1：新增，0：移除）',
  `sort` int(8) NULL DEFAULT NULL COMMENT '排序',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`subject_rel_id`) USING BTREE,
  INDEX `subject_rel_id`(`subject_rel_id`) USING BTREE,
  INDEX `subject_id`(`subject_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aggr_widget_rel
-- ----------------------------
DROP TABLE IF EXISTS `aggr_widget_rel`;
CREATE TABLE `aggr_widget_rel`  (
  `widget_rel_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `subject_id` int(8) NOT NULL COMMENT '对应栏目表主键',
  `type` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '控件类型_0：非轻应用控件，1：轻应用控件',
  `group_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组名',
  `group_id` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '用户组ID',
  `create_user` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`widget_rel_id`) USING BTREE,
  INDEX `widget_rel_id`(`widget_rel_id`) USING BTREE,
  INDEX `subject_id`(`subject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aggr_widget_rel
-- ----------------------------
INSERT INTO `aggr_widget_rel` VALUES (1, 1, '1', NULL, '1-1-1', 'lifei', '2019-04-04 14:10:46');
INSERT INTO `aggr_widget_rel` VALUES (2, 1, '1', NULL, '1-1-2', 'lifei', '2019-04-04 14:11:00');

SET FOREIGN_KEY_CHECKS = 1;



########################   数据字典     #######################################
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (10, '11', '栏目', 'type-flag', '栏目', 1, '2019-03-28 10:29:50', '2019-03-28 10:29:50', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (11, '12', '轻应用', 'type-flag', '轻应用', 2, '2019-03-28 10:30:15', '2019-03-28 10:30:15', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (12, '13', '轮播图', 'type-flag', '轮播图', 3, '2019-03-28 10:35:10', '2019-03-28 10:35:10', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (13, '14', '数据', 'type-flag', '数据', 4, '2019-03-28 10:35:30', '2019-03-28 10:35:30', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (14, '15', '超链接', 'type-flag', '超链接', 5, '2019-03-28 10:35:53', '2019-03-28 10:35:53', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (15, '16', '门户', 'type-flag', '门户', 6, '2019-03-28 10:36:11', '2019-03-28 10:36:11', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (16, '11', '固定', 'show-type', '固定', 1, '2019-03-28 10:36:50', '2019-03-28 10:36:50', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (17, '12', '可移动', 'show-type', '可移动', 2, '2019-03-28 10:37:06', '2019-03-28 10:37:06', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (18, '11', '固定', 'operation-type', '固定', 1, '2019-03-28 10:38:02', '2019-03-28 10:38:02', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (19, '12', '维护', 'operation-type', '维护', 2, '2019-03-28 10:38:18', '2019-03-28 10:38:18', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (20, '13', '可查询', 'operation-type', '可查询', 3, '2019-03-28 10:38:32', '2019-03-28 10:38:32', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (21, '11', '显示', 'show-status', '显示', 1, '2019-03-28 10:39:50', '2019-03-28 10:39:50', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (22, '12', '隐藏', 'show-status', '隐藏', 2, '2019-03-28 10:40:13', '2019-03-28 10:40:13', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (23, '11', '新页面', 'open-mode', '新页面', 1, '2019-03-28 10:41:54', '2019-03-28 10:41:54', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (24, '12', '新窗口', 'open-mode', '新窗口', 2, '2019-03-28 10:42:19', '2019-03-28 10:42:19', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (25, '13', '原窗口', 'open-mode', '原窗口', 3, '2019-03-28 10:42:37', '2019-03-28 10:42:37', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (26, '11', '线上', 'service-type', '线上', 1, '2019-03-28 10:43:27', '2019-03-28 10:43:27', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (27, '12', '线下', 'service-type', '线下', 2, '2019-03-28 10:43:43', '2019-03-28 10:43:43', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (28, '11', '学生', 'identity-type', '学生', 1, '2019-03-28 10:44:34', '2019-03-28 10:44:34', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (29, '12', '教师', 'identity-type', '教师', 2, '2019-03-28 10:44:51', '2019-03-28 10:44:51', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (30, '13', '家长', 'identity-type', '家长', 3, '2019-03-28 10:45:12', '2019-03-28 10:45:12', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (31, '14', '企业', 'identity-type', '企业', 4, '2019-03-28 10:45:24', '2019-03-28 10:45:24', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (32, '11', 'PC', 'gateway-type', 'PC', 1, '2019-03-29 15:01:53', '2019-03-29 15:01:53', '', '0', 0);
INSERT INTO `bigdata`.`sys_dict`(`id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`, `tenant_id`) VALUES (33, '12', 'APP', 'gateway-type', 'APP', 2, '2019-03-29 15:02:07', '2019-03-29 15:02:07', '', '0', 0);

########################   转发配置     #######################################
INSERT INTO `bigdata`.`sys_route_conf`(`id`, `route_name`, `route_id`, `predicates`, `filters`, `uri`, `order`, `create_time`, `update_time`, `del_flag`) VALUES (7, '聚合平台', 'bigdata-aggregation', '[{\"args\": {\"_genkey_0\": \"/aggr/**\"}, \"name\": \"Path\"}]', '[{\"args\": {\"key-resolver\": \"#{@remoteAddrKeyResolver}\", \"redis-rate-limiter.burstCapacity\": \"20\", \"redis-rate-limiter.replenishRate\": \"10\"}, \"name\": \"RequestRateLimiter\"}, {\"args\": {\"name\": \"default\", \"fallbackUri\": \"forward:/fallback\"}, \"name\": \"Hystrix\"}]', 'lb://bigdata-aggregation', 0, '2019-03-11 16:08:04', '2019-03-12 12:02:43', '0');
