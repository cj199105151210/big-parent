/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.170
Source Server Version : 50642
Source Host           : 192.168.1.170:3306
Source Database       : user_center

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-03-21 11:47:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `flow_position`
-- ----------------------------
DROP TABLE IF EXISTS `flow_position`;
CREATE TABLE `flow_position` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(40) DEFAULT NULL COMMENT '英文名称',
  `cname` varchar(40) DEFAULT NULL COMMENT '中文名称',
  `short_name` varchar(40) DEFAULT NULL COMMENT '简称',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父机构id',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_tme` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_user_name` varchar(40) DEFAULT NULL COMMENT '创建者名称',
  `extend` varchar(300) DEFAULT NULL COMMENT '扩展字段',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程岗位表';

-- ----------------------------
-- Records of flow_position
-- ----------------------------
INSERT INTO `flow_position` VALUES ('0', 'rootNode', '根节点', '根节点', '000', null, '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('1', 'parentNode1', '校长', '校长', '001', '0', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('10', 'node8', '医学院辅导员', '医学院辅导员', '010', '9', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('11', 'node9', '卫生后勤主任', '卫生后勤主任', '011', '0', '0', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('2', 'node1', '教学主任', '教学主任', '002', '1', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('3', 'node2', '院长', '院长', '003', '1', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('4', 'node3', '测试333', '化学院院长', null, '3', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('5', 'node4', '外语院院长', '外语院院长', '005', '3', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('6', 'parentNode2', '教务处长', '教务处长', '006', '0', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('7', 'node5', '处长助理', '处长助理', '007', '6', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('8', 'node6', '科研主任', '科研主任', '008', '6', '1', '0', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('8a7385980aea4f6fba1552af6e322172', 'test3', '测试3', null, null, '2', null, '1', null, null, null, null, null, null);
INSERT INTO `flow_position` VALUES ('9', 'node7', '辅导员主任', '辅导员主任', '009', '6', '1', '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `organization`
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(40) DEFAULT NULL COMMENT '英文名称',
  `cname` varchar(40) DEFAULT NULL COMMENT '中文名称',
  `short_name` varchar(40) DEFAULT NULL COMMENT '简称',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父机构id',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_user_name` varchar(40) DEFAULT NULL COMMENT '创建者名称',
  `extend` varchar(300) DEFAULT NULL COMMENT '扩展字段',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型（家长，学生，教职工）',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('0', 'rootNode', '根节点', '根节点', '000', null, '1', '0', '2019-03-21 11:14:05', '2019-03-21 11:14:05', null, null, null, null, null);
INSERT INTO `organization` VALUES ('048a70e1c892426b9f6a5c671872db42', null, '办公室6', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('1', 'parentNode1', '校长办公室', '校长办公室', '001', '0', '1', '0', '2019-03-21 11:24:44', '2019-03-21 11:24:44', null, null, null, null, null);
INSERT INTO `organization` VALUES ('10', 'node8', '书法部部长', '书法部部长', '010', '9', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('106d86266cd84758b0252429b8ec02c6', null, '办公室6', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('11', 'node9', '体育部', '体育部', '011', '6', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('2', 'node1', '教学主任办公室', '教学主任办公室', '002', '1', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('2e3a785b10c64396a33644e419a542ee', null, '5555', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('3', 'node2', '院长办公室', '院长办公室', '003', '1', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('3bc46a05966446088f44235f189ec5da', null, '校长秘书处办公室', null, '', '1', '0', '0', '2019-03-19 08:55:13', '2019-03-19 08:55:13', null, null, null, null, null);
INSERT INTO `organization` VALUES ('40ca40aa4be64f318a19817fc3580232', null, '学生学习办公室', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('5', 'node4', '中文学院', '中文学院', '005', '3', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('57ab43b2379e4c5d8847a048f695ecd7', null, '10', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('598c1c40ca724398906e1947879e2da0', null, '学生政治思想办公室', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('6', 'parentNode2', '学生团体', '学生团体', '006', '0', '1', '0', '2019-03-21 11:24:47', '2019-03-21 11:24:47', null, null, null, null, null);
INSERT INTO `organization` VALUES ('6222788b176742b2bad63a2102f4fcee', null, '对外事务办公室', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('6787e84a5f4f477da1ebd0f506a958e9', null, '又来一个', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('7', 'node5', '英语部', '英语部', '007', '6', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('728ad64a14624b2f9a61a2d8b57738b6', null, '办公室2', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('8', 'node6', '部长', '部长', '008', '6', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('812caadcf6134814b429133afddd510f', null, '办公室4', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('8b4b10a157364aaca0454d5a320d0ad3', null, '办公室1', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('9', 'node7', '书法部', '书法部', '009', '6', '1', '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('a0e51eb3d51d4b72ac4d5c319a0b5bff', null, '再来一个', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('b45b417788614a5ebbf9e0036b62d58f', null, '办公室7', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('ba8709c647b74cc7b1c8a513c21f2e52', null, '校园安全办公室', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('c949c02234b5436ea927133f9b77fa48', 'zsb', '招生办', null, '001', '1', null, '1', '2019-03-14 14:11:18', '2019-03-14 14:11:18', null, null, null, null, null);
INSERT INTO `organization` VALUES ('cb5874cc9078460c9cf412267b640afd', null, '办公室5', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('cf47020cb04b460c82932fa008324f37', null, '8', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('d0af72eb46ee48e286f553cc6e679ac7', null, '6', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('d17dad3b6caf489a9b6bf81ac2e3ee5f', null, '办公室8', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('d3033174d4364ca98499e0e37c013f06', 'test2', '测试2', null, null, '2', null, '0', '2019-03-19 08:55:14', '2019-03-19 08:55:14', null, null, null, null, null);
INSERT INTO `organization` VALUES ('d95ede3e1e7645f990cc755bf434a7cc', null, '办公室3', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('da73c93bda1d48f1be6532b2b6fbbf46', null, '9', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('db7075183d8e4a24a9a3e70cf167af97', null, '第四个', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('ddd8c0257dd64f65813b921e06a239a2', null, '来第三个', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('e7861c7624164287b883f543c3fce0d8', null, '鹅鹅鹅', null, '', 'd3033174d4364ca98499e0e37c013f06', null, '0', '2019-03-19 08:55:18', '2019-03-19 08:55:18', null, null, null, null, null);
INSERT INTO `organization` VALUES ('f010b206fc7047d4be7a9a2816a3a293', null, '7', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);
INSERT INTO `organization` VALUES ('f4504dd219a448e1a20a820f515eae44', null, '9', null, '', '3bc46a05966446088f44235f189ec5da', null, '0', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名',
  `card_type` int(1) DEFAULT NULL COMMENT '证件类型(1:身份证，2：港澳台证件，3：国外证件)',
  `id_card` varchar(30) DEFAULT NULL COMMENT '证件号',
  `xb` int(1) DEFAULT NULL COMMENT '性别（1男，0女）',
  `xm` varchar(60) DEFAULT NULL COMMENT '姓名',
  `user_no` varchar(40) DEFAULT NULL COMMENT '学号或者教职工号',
  `type` int(1) DEFAULT NULL COMMENT '用户类型（1学生、2教师、3家长、4企业）',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `mail` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `avator` varchar(100) DEFAULT NULL COMMENT '头像',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号',
  `err_no` int(2) DEFAULT '0' COMMENT '错误次数',
  `third_name` varchar(40) DEFAULT NULL COMMENT '第三方登录名',
  `qq_openid` varchar(100) DEFAULT NULL COMMENT 'qq密钥',
  `wx_openid` varchar(100) DEFAULT NULL COMMENT '微信密钥',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `is_auth` int(1) DEFAULT NULL COMMENT '是否实名（1：是，0：否）',
  `extend` varchar(300) DEFAULT NULL COMMENT '扩展字段',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('04b1c39c08e249f7b06c64bd379e0b7b', 'user6', null, null, null, '赵六', null, null, '$2a$10$QMBw6qqjSn560.ByxuYKDuvzgOLDiuxIF4tJMw7Ehy0WoNwFBPcIO', null, null, null, null, '2', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('07d3ce32b68b4312aa83a632ac299288', '123', '1', '123', '1', '岳不群', null, '3', '$2a$10$eofWon.qMpEVYeb1dwOZpeDnUN.X75jc8fCTIC2gHwe.HQ./H2hdS', '4896', '1485215', null, '25', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('0f1f8172590c47f8b221027478294d45', 'dic655', null, '', null, 'dsijn', null, null, '$2a$10$BLmXqVIHeC1UnEuqtBD9hecUcp3KvyWeH8NmaOQNWWZFMGSE9upkm', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('1001', 'user1', null, null, null, '张三丰', null, null, '$2a$10$hbNvRD7LfRArdSwSzYkZUOjW3dmmGPF/1irETdTHw/uRtAZqbu9oi', '13988887777', null, null, null, '8', null, null, null, '0', null, null, null);
INSERT INTO `user` VALUES ('1002', 'user3', '1', '', '1', '麻子', '', '1', '$2a$10$Ndiyl8XRRey9o6/qzCEVhOjn86dt.fj4dbOxsl7c2UZyJDWEJZ.Pu', '16516563', '16563', null, '123', '1', null, null, null, '0', null, null, null);
INSERT INTO `user` VALUES ('179d0561326e447293179bb56a85eb91', 'dic', null, '', null, 'dsijn', null, null, '$2a$10$n7xsSQuOD.DiKFPVkram/eJH8w0FwwfsILd/plpPHHxQPNu6pe4zC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('19d992944b264cc2aa0b0f30c0665a82', '6125', null, '', null, 'sak', null, null, '$2a$10$QbbuwGM52usmUpIc8CrRzuxpAx4mGbHB67OLoAmjaJX40tWAyclG2', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('1b5fbcf2958740fb98b40213adda439e', 'user4', '2', null, null, '王五', null, null, '$2a$10$Drk0oQEy22bUSGeYWxzjduuoLJ/ej6M91Yu3YKsIZpDsSF0WrQ7tq', '13344445555', null, null, null, null, null, null, null, '0', null, null, null);
INSERT INTO `user` VALUES ('261d128f5ba54fe394ba667ae2b51813', '', null, '', null, '', null, null, '$2a$10$rIB0eDsC4uaV7sJzdYLM0.s/uVwmu382j//YPf.mfUf.RIqm7pHdC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3787febc5aab4eae9db6db75e9fda2df', '1234567890000', '1', '156263325266262615', '1', '麻子2号', null, '3', '$2a$10$MHou8AjRalW64jmKPOwbKeYlho3Mm8czlxN8tnsbUL9KK/Pf4s7Si', '651113323232', '6261262626', null, '14', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3d5ef8f301c941a7b40d7ec69cc2776e', 'dic655', null, '', null, 'dsijn', null, null, '$2a$10$pT8QhoP1/FyAClcMXdX0sOhCBxeXz/mYKtN2/ew/f1Mb7aCPmpIaC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3d8c302d54e348aa8f261bf36668fdf6', '123142', '1', '123', '1', '岳不群12', null, '3', '$2a$10$HRtYwWauJRbmHuX7.cYSz.irb2NfsylhsOBVtOk0djZ/PivjTack2', '4896', '1485215', null, '25', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('50bba4e22d77472690db7934dd719d25', '165513', null, '', null, 'ncuidni', null, null, '$2a$10$6espJC3NQ7beQHK/TFDq4.eCzLMtiZU2HCx9PnvY6PdKPhmnAQ9xa', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('5339f4d2e3704c838e3c481f19941526', '355', null, '', null, '', null, null, '$2a$10$oaKSzqSXdZ5VffQO.HwxgOwR7cBfl4pGhMB4oIRBC2fXWzbhf95Fu', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('5507e7f8fd764823b560feab7401ff3b', 'dic655', null, '', null, 'dsijn', null, null, '$2a$10$.j91KzSYyZfr1wzJ29pbUeSwiT8K0kYvUW87DupsM38FWMte.f1UO', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('594b827296a64516847189e88fdc08d0', '666', null, '', null, '', null, null, '$2a$10$Gs9urzXGryqvnzcLXp95SuHG0qhpOPULBMcOu1VaABqbpXfbEmFmC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('5a8fceb40d2241acacb0c406ee7cda59', '2000120', '1', '1234567', '0', '东方不败', null, '2', '$2a$10$m2dJ/aLhiHsS24HlWbO9FOZHawpFh8H6dCgQTJ0niit06WBgbfmmO', '123456789', '123', null, '1', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('62d5aab7605d48f08c23324abb4c706e', 'dic655', null, '', null, 'dsijn', null, null, '$2a$10$4wiP88XZBfN7gOCUr7D4f.hlubfqGUs9gLFQdsIQZ6OnTRqT0Ok3W', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('727b1f5e57514b308a10c9b7a56d87a1', '122345456', null, '', '0', 'tttttt', null, '1', '$2a$10$njHPAcHeqvvcgE14Wjv7oeMfWhYBQ/gKkrQFXCWs6fpbEoFejGwhe', '', '', null, null, '0', null, null, null, '1', null, null, null);
INSERT INTO `user` VALUES ('798a08e57f3f421eb12847892c8b0f57', '15gu', null, '', '0', '你好1545', null, null, '$2a$10$XX8jIHKiK.Y1eI7Yqm3Pp.yaDdzpyV.IllWqKw8gmEnss8jYb8/aG', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('7a14e5b7ffdb475686de97c6e503d2fa', '355', null, '', null, '', null, null, '$2a$10$pxZH0VGbeaNYMj9OZg7R2u3pDvi.UhANoVAF5dlAa6Lckond82iN.', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('7b59e43268b54376a5f9eb9f25b66884', 'user2', '1', '340321222211221111', null, '汪光银', '2222222222', null, '$2a$10$ytSKq9EH19OfGPP6MhQRheBsOGspOXkUAJtlLltApxlMi7DOthlIK', '13344445555', null, null, null, '13', null, null, null, '1', null, null, null);
INSERT INTO `user` VALUES ('83beb5aae90046baaadf8ece7fcb9a77', '61251626', null, '', null, 'sak', null, null, '$2a$10$pSvduyECCOgKsngwRvcAM.nvUNpBXgizyai6iqnADnUGd/od3L/p2', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('84a526f81cf44c2991d2a472a9e87a9b', '156165', '1', '123', '1', '岳不群123', null, '3', '$2a$10$agjTcKg8HDZZhfm.9WdevuQGirEgBD7iSWarIM1k81rwNIHE.SJdS', '4896', '1485215', null, '25', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('8880b8011aa442c9a2424e38acf19c8d', '156556', null, '', '0', '你好', null, null, '$2a$10$S7LreFsdp8gP.c5Nl/1V1.HjCLugqv63dJ4u4AKYJIfXBfskNPY1S', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('8e57a0eb310846c49d170e00cdc83527', '1655165', null, '', null, '415456165', null, null, '$2a$10$D8sve7B1vz3PjgrKHetYJOoGCa7p3OiiopfKZhPvP0rktyij599Gm', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('9cbbc7a220d84b3a83ed65ec667d657f', '355', null, '', null, '', null, null, '$2a$10$WqMEuodGlVsdOYk2Y7SMfOCLh/x5mFlqz3NeeEGsvYn1zsA1GBYFC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('a5c8762fed9e4dafab62f1b6e493540c', '165685489', null, '', '1', 'bichw', null, '1', '$2a$10$wcTVM3gytsHC7FzBpqM9EeKNOXVAOE9S1TJLcQDdiuXBa1BIBasHC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('a64a744a25a04952a367410c3ecdd1f3', 'dic655', null, '', null, 'dsijn', null, null, '$2a$10$/jx3QEkDfw2.8FkqIgiSoe1hHyfCqacLOPI/PjoQ05SqsBcTOh/bm', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('ab32a84eacd2488d95985c610d591e75', '15651gu', null, '', '0', '你好15', null, null, '$2a$10$ZOBRQdICFvjqoTCwpUySkulO/i0XET2/4IJjVWRo1a3vELf2E7upC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('ac90d21f0dd84a4481e8c100c4b15b19', '1655', null, '', null, '415456', null, null, '$2a$10$lD2BafHga3gkXGMMh0.d5.Vd/8OsUO5tweOZo2XmX68seeuO2KX4q', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('add3a3b4240a47189efa71ab57077607', 'student1', '1', '340321222211221111', null, '小明', null, null, '$2a$10$ChxrDegUZjt.Ts1pd6z3EOsPIGvE8CPhzvn0E8EDnMblVNAllV6OC', '13344445555', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('af60ff909bf0425cbb97bb32634c9a80', '789012', '2', '1526', '1', '任我行', null, '1', '$2a$10$qziJDkNIN4gWvr2OGx.W/OzL9g0g4ytAWpVz5tQMczUi/imZ0MKou', '02', '1651', null, '165', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('b1d27a7258c6494090ffb9e705aab491', '666', null, '', null, '', null, null, '$2a$10$cDqx1bxbpxc7qVFNdid6O.ySS/iYpUnE8nI.oZv9X5k8CbL9qbJbK', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('cd5779db69ab40be8e71e92c67118861', 'dic655', null, '', null, 'dsijn', null, null, '$2a$10$fIJqJvzKC.SudZJW95pwWegrjjH6X/akPiRzShAZhES8oAJMgNkDC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('d0012b723345442ea47f30eaaa66aa0f', '355', null, '', null, '', null, null, '$2a$10$99SNXFeDZelvFd7Drk6A3eeNp0YmiVEBhDt8LAizp9nTiQBWhxEEC', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('d0ecbfee00704bdc9a3c45e25f3c291d', '123456', '2', '123456', '1', '令狐冲', null, '2', '$2a$10$3wTA9NR0cPeXj5mhqiiZHOCfeRcsNcSPRNjhTpJDex75zEWcDxgwq', '123456', '123456', null, '12', '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('d172a57ac7884d14ba12992e3cc04a7b', '61251626', null, '', null, 'sak', null, null, '$2a$10$OO59IQoQcOpWItVC5MlC..sZ9i/Xz9yCZ0Li2d0LWfDFujHKlJfFK', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('da5064fc064f44eba7bcf2be17f30d3c', '888', null, '', null, '14654665', null, null, '$2a$10$oOvT1hobZl/dxupvue.WmuzYWo4K2G8uoE1xDIfyQKzphXqtSshLS', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('db4127e5a3ae4e418ebc6ae875f35a85', '1533556', null, '', null, 'nisdijni', null, null, '$2a$10$K2d5aPquG1JO49GiHKOom./veIgbv4T9HFQxmRWYI2Q3LE0uaZSv6', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('e8a5bd6025c848e0a06b9bafbc7bd7a1', '6526', null, '', null, '羊羊羊', null, null, '$2a$10$bruGQUFR2KhbnHbk5F50xO/sRnTCw5eeDLaSpkSkU4UhClVFnC/N6', '', '', null, null, '0', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('f7603fa0b74343049b297e66345f5243', '355', null, '', null, '', null, null, '$2a$10$f8Au.wKdwjU/ZdCM6JnKC.gMYb25elE1ZVo6vvSvmGLDVcmSGr0gK', '', '', null, null, '0', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_and_flow`
-- ----------------------------
DROP TABLE IF EXISTS `user_and_flow`;
CREATE TABLE `user_and_flow` (
  `user_id` varchar(32) DEFAULT NULL,
  `org_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户流程岗位映射表';

-- ----------------------------
-- Records of user_and_flow
-- ----------------------------
INSERT INTO `user_and_flow` VALUES ('1001', '6', null, null, null);
INSERT INTO `user_and_flow` VALUES ('1002', '4', null, null, null);

-- ----------------------------
-- Table structure for `user_and_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_and_group`;
CREATE TABLE `user_and_group` (
  `user_id` varchar(32) DEFAULT NULL,
  `org_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与用户组';

-- ----------------------------
-- Records of user_and_group
-- ----------------------------
INSERT INTO `user_and_group` VALUES ('1001', '3', '2019-03-13 13:47:22', '2019-03-13 13:47:22', '2019-03-13 13:47:22');
INSERT INTO `user_and_group` VALUES ('1002', '3', '2019-03-13 13:47:28', '2019-03-13 13:47:28', '2019-03-13 13:47:28');

-- ----------------------------
-- Table structure for `user_and_org`
-- ----------------------------
DROP TABLE IF EXISTS `user_and_org`;
CREATE TABLE `user_and_org` (
  `user_id` varchar(32) DEFAULT NULL,
  `org_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_directly` varchar(1) DEFAULT NULL COMMENT '是否直属（1，直属）',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与组织架构表映射表';

-- ----------------------------
-- Records of user_and_org
-- ----------------------------
INSERT INTO `user_and_org` VALUES ('1', '1', '2019-03-13 19:22:13', '2019-03-13 19:22:13', '0', '2019-03-13 19:22:13');
INSERT INTO `user_and_org` VALUES ('1b5fbcf2958740fb98b40213adda439e', '1', null, null, null, null);
INSERT INTO `user_and_org` VALUES ('1002', '2', '2019-03-19 11:44:49', '2019-03-19 11:44:49', '1', '2019-03-14 13:50:42');
INSERT INTO `user_and_org` VALUES ('7b59e43268b54376a5f9eb9f25b66884', '1', null, null, null, null);
INSERT INTO `user_and_org` VALUES ('1', '3', '2019-03-14 13:50:42', '2019-03-14 13:50:42', '0', '2019-03-14 13:50:42');
INSERT INTO `user_and_org` VALUES ('add3a3b4240a47189efa71ab57077607', '4', null, null, null, null);
INSERT INTO `user_and_org` VALUES ('1001', '10', null, null, null, '1002');
INSERT INTO `user_and_org` VALUES ('1001', '1', null, null, null, '1002');
INSERT INTO `user_and_org` VALUES (null, 'e7861c7624164287b883f543c3fce0d8', null, null, null, null);
INSERT INTO `user_and_org` VALUES ('727b1f5e57514b308a10c9b7a56d87a1', 'e7861c7624164287b883f543c3fce0d8', null, null, null, null);
INSERT INTO `user_and_org` VALUES ('3787febc5aab4eae9db6db75e9fda2df', '048a70e1c892426b9f6a5c671872db42', null, null, null, '');
INSERT INTO `user_and_org` VALUES ('3787febc5aab4eae9db6db75e9fda2df', '106d86266cd84758b0252429b8ec02c6', null, null, null, '');
INSERT INTO `user_and_org` VALUES ('3787febc5aab4eae9db6db75e9fda2df', '2e3a785b10c64396a33644e419a542ee', null, null, null, '');
INSERT INTO `user_and_org` VALUES ('3787febc5aab4eae9db6db75e9fda2df', '40ca40aa4be64f318a19817fc3580232', null, null, null, '');
INSERT INTO `user_and_org` VALUES ('3787febc5aab4eae9db6db75e9fda2df', '57ab43b2379e4c5d8847a048f695ecd7', null, null, null, '');

-- ----------------------------
-- Table structure for `user_and_private_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_and_private_group`;
CREATE TABLE `user_and_private_group` (
  `user_id` varchar(32) DEFAULT NULL,
  `org_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与用户私组关系映射表';

-- ----------------------------
-- Records of user_and_private_group
-- ----------------------------
INSERT INTO `user_and_private_group` VALUES ('1002', '4', null, null, null);

-- ----------------------------
-- Table structure for `user_and_user`
-- ----------------------------
DROP TABLE IF EXISTS `user_and_user`;
CREATE TABLE `user_and_user` (
  `user_id` varchar(32) DEFAULT NULL,
  `sun_id` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与用户之间关系表';

-- ----------------------------
-- Records of user_and_user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `cname` varchar(40) DEFAULT NULL COMMENT '中文名称',
  `short_name` varchar(40) DEFAULT NULL COMMENT '简称',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父机构id',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_tme` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_user_name` varchar(40) DEFAULT NULL COMMENT '创建者名称',
  `extend` varchar(300) DEFAULT NULL COMMENT '扩展字段',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组表';

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('0', '根节点', '根节点', '1', null, '1', '0', '2019-03-19 16:13:07', '2019-03-19 16:13:07', null, null, null, '0');
INSERT INTO `user_group` VALUES ('1', '系统组', '系统组', '1-1', '0', '1', '0', '2019-03-19 15:50:15', '2019-03-19 15:50:15', null, null, null, '1');
INSERT INTO `user_group` VALUES ('10', '管理创新', '管理创新', '0010', '8', '1', '0', '2019-03-11 17:01:16', '2019-03-11 17:01:16', null, null, null, '10');
INSERT INTO `user_group` VALUES ('11', 'abcdef', 'ABC', '1-1-1-1', '2', '1', '0', '2019-03-19 16:15:28', '2019-03-19 16:15:28', null, null, null, null);
INSERT INTO `user_group` VALUES ('12', 'CD', 'CD', '122', '2', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group` VALUES ('2', '一卡通', '一卡通', '1-1-1', '1', '1', '0', '2019-03-19 16:15:06', '2019-03-19 16:15:06', null, null, null, '2');
INSERT INTO `user_group` VALUES ('3', '图书馆', '图书馆', '003', '1', '2', '0', '2019-03-19 15:16:09', '2019-03-19 15:16:09', null, null, null, '3');
INSERT INTO `user_group` VALUES ('3abd4ed23cf349f2bde89308a57c7ef2', '校园安全', null, '1-5', '0', null, '0', null, null, null, null, null, null);
INSERT INTO `user_group` VALUES ('4', '科研组', '科研组', '1-2', '0', '1', '0', '2019-03-19 15:50:24', '2019-03-19 15:50:24', null, null, null, '4');
INSERT INTO `user_group` VALUES ('5', '语文', '语文', '1-2-1', '4', '1', '0', '2019-03-19 15:59:31', '2019-03-19 15:59:31', null, null, null, '5');
INSERT INTO `user_group` VALUES ('5b673827f1f34ef497b28bd1732cdd36', '123', null, '1-3', '0', '0', '0', '2019-03-19 15:50:31', '2019-03-19 15:50:31', null, null, null, null);
INSERT INTO `user_group` VALUES ('6', '英语', '英语', '1-2-2', '4', '2', '0', '2019-03-19 15:59:37', '2019-03-19 15:59:37', null, null, null, null);
INSERT INTO `user_group` VALUES ('7', '电磁学', '电磁学', '1-2-3', '4', '3', '0', '2019-03-19 15:59:50', '2019-03-19 15:59:50', null, null, null, '7');
INSERT INTO `user_group` VALUES ('71d3ec89d30d400c8cf6f9bdc95d946c', 'abcd1', null, '', '2', null, '0', '2019-03-19 11:30:39', '2019-03-19 11:30:39', null, null, null, null);
INSERT INTO `user_group` VALUES ('8', '创新组', '创新组', '1-4', '0', '1', '0', '2019-03-19 15:50:37', '2019-03-19 15:50:37', null, null, null, '8');
INSERT INTO `user_group` VALUES ('9', '文化创新', '文化创新', '009', '8', '1', '0', '2019-03-11 17:01:35', '2019-03-11 17:01:35', null, null, null, '9');
INSERT INTO `user_group` VALUES ('96b749c8f88644b9a3976f026f9871bc', '456', null, '1-3-1', '5b673827f1f34ef497b28bd1732cdd36', null, '1', '2019-03-20 08:52:13', '2019-03-20 08:52:13', null, null, null, null);
INSERT INTO `user_group` VALUES ('b01f74cdfd4e482eb62ce3446c1af86b', '张三', null, 'z123', '1', '0', null, '2019-03-19 10:48:48', '2019-03-19 10:48:48', null, null, null, null);
INSERT INTO `user_group` VALUES ('bb505d90d41a46788fe844a7213c11d8', 'abc', null, '', '2', null, '1', '2019-03-19 11:41:41', '2019-03-19 11:41:41', null, null, null, null);
INSERT INTO `user_group` VALUES ('ea784bd84f104dfba2c5999db5ee4c68', '789', null, '1-3-1', '5b673827f1f34ef497b28bd1732cdd36', null, '1', '2019-03-20 14:27:07', '2019-03-20 14:27:07', null, null, null, null);

-- ----------------------------
-- Table structure for `user_group_private`
-- ----------------------------
DROP TABLE IF EXISTS `user_group_private`;
CREATE TABLE `user_group_private` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(40) DEFAULT NULL COMMENT '英文名称',
  `cname` varchar(40) DEFAULT NULL COMMENT '中文名称',
  `short_name` varchar(40) DEFAULT NULL COMMENT '简称',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父机构id',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序号',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（1：是，0：否）',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_tme` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_user_name` varchar(40) DEFAULT NULL COMMENT '创建者名称',
  `extend` varchar(300) DEFAULT NULL COMMENT '扩展字段',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户私组表';

-- ----------------------------
-- Records of user_group_private
-- ----------------------------
INSERT INTO `user_group_private` VALUES ('0', 'rootNode', '根节点', '根节点', '000', null, '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('1', 'parentNode1', '测试前端组', '测试前端组', '001', '0', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('10', 'node8', '方委', '方委', '010', '9', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('11', 'node9', '程杰', '程杰', '011', '0', '0', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('2', 'node1', '杨浩', '杨浩', '002', '1', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('3', 'node2', '查熠伟', '查熠伟', '003', '1', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('4', 'node3', '谭敏', '谭敏', '004', '3', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('5', 'node4', '汪光银', '汪光银', '005', '3', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('6', 'parentNode2', '智慧校园组', '智慧校园组', '006', '0', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('7', 'node5', '方怀正', '方怀正', '007', '6', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('8', 'node6', '梅志祥', '梅志祥', '008', '6', '1', '0', null, null, null, null, null, null);
INSERT INTO `user_group_private` VALUES ('9', 'node7', '马朋朋', '马朋朋', '009', '6', '1', '0', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(60) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `login_type` int(1) DEFAULT NULL COMMENT '登录终端类型（1 ：web，2:IOS，3：android）',
  `opt_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `opt_type` int(1) DEFAULT NULL COMMENT '登录还是登出（1:登录，2:登出）',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ----------------------------
-- Records of user_log
-- ----------------------------
INSERT INTO `user_log` VALUES ('04f69dd755b3413a824eb9bef1d55be4', 'user3', null, '2', '2019-03-20 16:44:24', '1');
INSERT INTO `user_log` VALUES ('0937e30cda0a45048415e154e2b9cebf', 'user2', null, '2', '2019-03-20 14:42:29', '1');
INSERT INTO `user_log` VALUES ('0a723848f6c44228a10a851dbbfc7562', 'user2', null, '2', '2019-03-21 08:27:16', '1');
INSERT INTO `user_log` VALUES ('0a7695b230bc4f9fa923a57944e627c7', 'user2', null, '2', '2019-03-20 10:43:35', '1');
INSERT INTO `user_log` VALUES ('0c2ebd3eaf8e4b959c87ef06a5f05c3b', 'user2', null, '2', '2019-03-20 10:44:11', '1');
INSERT INTO `user_log` VALUES ('0ce486b3f5f24981974456da1277fdaf', 'user2', null, '2', '2019-03-20 15:07:09', '1');
INSERT INTO `user_log` VALUES ('121d5a1959894e8d8415fa716a4460e1', 'user2', null, '2', '2019-03-20 15:19:55', '1');
INSERT INTO `user_log` VALUES ('165028de71554ee6b7c8e0efb2b84f18', 'user2', null, '2', '2019-03-20 15:04:43', '1');
INSERT INTO `user_log` VALUES ('1c641a9315d4468a8f45a87fff95c79a', 'user2', null, '2', '2019-03-20 15:45:39', '1');
INSERT INTO `user_log` VALUES ('248e36d180b444c58769ae93a2f03465', 'user2', null, '2', '2019-03-20 14:39:38', '1');
INSERT INTO `user_log` VALUES ('2892a40ec68e498fb5f5042f23e879c7', 'user2', null, '2', '2019-03-20 15:13:30', '1');
INSERT INTO `user_log` VALUES ('2b618455781442d19750c4813a67aa39', 'user2', null, '2', '2019-03-21 08:53:55', '1');
INSERT INTO `user_log` VALUES ('2c16875a27ea446cac3a6a1c418f278f', 'user2', null, '2', '2019-03-20 16:31:37', '1');
INSERT INTO `user_log` VALUES ('3026cfd48e6848539ab69c248f82e4d9', 'user2', null, '2', '2019-03-21 10:39:24', '1');
INSERT INTO `user_log` VALUES ('32562294936c46b5a0193d46cbc5def1', 'user2', null, '2', '2019-03-20 11:43:18', '1');
INSERT INTO `user_log` VALUES ('347e88b6e2164c6c998e34427598498e', 'user2', null, '2', '2019-03-20 16:19:52', '1');
INSERT INTO `user_log` VALUES ('354b67dfdf434e9a9d6e0dc85417868f', 'user2', null, '2', '2019-03-20 14:47:58', '1');
INSERT INTO `user_log` VALUES ('3a387a56317045ea9f448f0c5d2593b4', 'user2', null, '2', '2019-03-20 16:34:45', '1');
INSERT INTO `user_log` VALUES ('438337b6d8c74d0dbd214a1b9dac81a4', 'user2', null, '2', '2019-03-20 14:45:36', '1');
INSERT INTO `user_log` VALUES ('45ae8ef3642a4584895248a981e00471', 'user2', null, '2', '2019-03-20 17:55:44', '1');
INSERT INTO `user_log` VALUES ('46431ae3052a46f7b120100b874b6dca', 'user4', null, '2', '2019-03-20 16:44:45', '1');
INSERT INTO `user_log` VALUES ('4b9c9ec4de3148b292001be8fed1d390', 'user2', null, '2', '2019-03-20 15:05:38', '1');
INSERT INTO `user_log` VALUES ('4ced6f977aa345d2a11a4ee5d9e3e28a', 'user2', null, '2', '2019-03-20 11:09:35', '1');
INSERT INTO `user_log` VALUES ('505a1855dd614e83b748acd7f51bc291', 'user2', null, '2', '2019-03-20 11:23:14', '1');
INSERT INTO `user_log` VALUES ('50bfcce0c8e24d298deb5e48e3a4be61', 'user2', null, '2', '2019-03-21 09:35:39', '1');
INSERT INTO `user_log` VALUES ('51fb41e9e5af4fe8a81d26d3eac80b07', 'user2', null, '2', '2019-03-20 14:35:24', '1');
INSERT INTO `user_log` VALUES ('55722a6b9af243128e81d0a22cb31bf3', 'user2', null, '2', '2019-03-20 10:57:25', '1');
INSERT INTO `user_log` VALUES ('58d4865dd6f949b48bbb3b0126bbdc75', 'user2', null, '2', '2019-03-20 15:31:11', '1');
INSERT INTO `user_log` VALUES ('5a725941052541c08f03398bee2bf69e', 'user2', null, '2', '2019-03-20 14:38:35', '1');
INSERT INTO `user_log` VALUES ('5fefe2dcd6944cc0949c36144b76b523', 'user2', null, '2', '2019-03-20 15:30:38', '1');
INSERT INTO `user_log` VALUES ('61eaa64cc54b473c8c0051d48ea5ba5f', 'user2', null, '2', '2019-03-20 16:43:04', '1');
INSERT INTO `user_log` VALUES ('64fda4ddd4dc46bca2411d12eb069182', 'user2', null, '2', '2019-03-20 10:57:52', '1');
INSERT INTO `user_log` VALUES ('652afe8428f1411c831ca92199799fb5', 'user2', null, '2', '2019-03-20 14:39:54', '1');
INSERT INTO `user_log` VALUES ('7bb246db005c48b58374e89597026676', 'user2', null, '2', '2019-03-21 09:39:24', '1');
INSERT INTO `user_log` VALUES ('7c834bed606b4f99b572b5ce38d43162', 'user2', null, '2', '2019-03-20 16:35:12', '1');
INSERT INTO `user_log` VALUES ('7da6f2a468cb49d2805a4d63f866ef7f', 'user2', null, '2', '2019-03-20 09:48:13', '1');
INSERT INTO `user_log` VALUES ('8105f4124489429f9b4d7c47c72e29da', 'user2', null, '2', '2019-03-21 09:27:11', '1');
INSERT INTO `user_log` VALUES ('82c69540c810483881a4bc43594e77ef', 'user2', null, '2', '2019-03-20 11:05:37', '1');
INSERT INTO `user_log` VALUES ('859d6e9cc19a4de5a5436f9f180286e9', 'user2', null, '2', '2019-03-20 11:31:22', '1');
INSERT INTO `user_log` VALUES ('8785a45510f34cefafe4987f84c08ae8', 'user2', null, '2', '2019-03-20 10:44:33', '1');
INSERT INTO `user_log` VALUES ('a4aad1304e7245a6885c57f58b086f35', 'user2', null, '2', '2019-03-20 14:57:04', '1');
INSERT INTO `user_log` VALUES ('a8a18bcbb58d4d7cb61ea4c850123769', 'user4', null, '2', '2019-03-20 18:09:39', '1');
INSERT INTO `user_log` VALUES ('a9db85d444d54ea1b257e4565a375267', 'user2', null, '2', '2019-03-20 14:50:31', '1');
INSERT INTO `user_log` VALUES ('aa07ac42cfc646538afd63b9ad69678f', 'user3', null, '2', '2019-03-20 16:43:19', '1');
INSERT INTO `user_log` VALUES ('aaafb82938b142e58b5bc2dd24db1140', 'user3', null, '2', '2019-03-21 11:32:46', '1');
INSERT INTO `user_log` VALUES ('aac50b29707b461a9a4c48bd5afd3d77', 'user2', null, '2', '2019-03-20 15:31:35', '1');
INSERT INTO `user_log` VALUES ('aaf9c5a30af740f686e5a188d8414300', 'user3', null, '2', '2019-03-21 11:29:09', '1');
INSERT INTO `user_log` VALUES ('acf7b0ef8602402aa74db6180535be61', 'user4', null, '2', '2019-03-20 17:20:02', '1');
INSERT INTO `user_log` VALUES ('ae12e60b1ce24bb2a323a83f7dc7ee43', 'fhz', '123456789', '1', '2019-03-14 14:03:08', '2');
INSERT INTO `user_log` VALUES ('afbe1803e43e433a81e04add14a30c1b', 'user2', null, '2', '2019-03-20 15:30:52', '1');
INSERT INTO `user_log` VALUES ('afbf1eccd987442f85a5679e40c71775', '123', null, null, null, null);
INSERT INTO `user_log` VALUES ('b356fff9d83c4d3eadb79b56530cc25f', 'user2', null, '2', '2019-03-20 11:41:57', '1');
INSERT INTO `user_log` VALUES ('b7f0b7af6b5c46a7980d3f81209a41e7', 'user2', null, '2', '2019-03-21 09:24:52', '1');
INSERT INTO `user_log` VALUES ('bced31d0d26c4ee3a5d1e7be1d983a31', 'user2', null, '2', '2019-03-20 15:32:02', '1');
INSERT INTO `user_log` VALUES ('be6b85fbe32240d5988793794e61944d', 'user2', null, '2', '2019-03-20 11:22:13', '1');
INSERT INTO `user_log` VALUES ('c2cc915612f74e5bbc05c6c4876019f5', 'user2', null, '2', '2019-03-20 14:21:33', '1');
INSERT INTO `user_log` VALUES ('c65badf4814547c8a61aa69533bb9e40', 'user2', null, '2', '2019-03-20 14:23:13', '1');
INSERT INTO `user_log` VALUES ('caf63ceda486407c9535d6d20ac17313', 'user3', null, '2', '2019-03-21 11:33:03', '1');
INSERT INTO `user_log` VALUES ('cb2e1cb46a1144418157ee1406a6a444', 'user2', null, '2', '2019-03-20 14:46:15', '1');
INSERT INTO `user_log` VALUES ('cd6fc5d2a4e449e08357cd4c3e269dcd', 'user2', null, '2', '2019-03-20 14:37:58', '1');
INSERT INTO `user_log` VALUES ('cf09d59acff14bdba78fb260f2fd6ef3', 'user2', null, '2', '2019-03-20 11:39:57', '1');
INSERT INTO `user_log` VALUES ('d41f37589c964581be07f56412765a80', 'user2', null, '2', '2019-03-20 14:22:16', '1');
INSERT INTO `user_log` VALUES ('d8e5f3fd355b40d4bc838ca2d691396d', 'user2', null, '2', '2019-03-20 14:22:00', '1');
INSERT INTO `user_log` VALUES ('d9ddb13cb23b4ad4a299e035f5779eaf', 'user2', null, '2', '2019-03-20 17:56:01', '1');
INSERT INTO `user_log` VALUES ('da283505caef465ca2d0d6ef5dc0b189', 'user2', null, '2', '2019-03-20 15:59:30', '1');
INSERT INTO `user_log` VALUES ('dbedc57420e64d7087178bc13a5ec102', 'user2', null, '2', '2019-03-21 08:39:50', '1');
INSERT INTO `user_log` VALUES ('dfd729e58e9f4a62bcdfe8913ee5b532', 'user2', null, '2', '2019-03-20 16:22:49', '1');
INSERT INTO `user_log` VALUES ('e07740bca8c840b28c0d82fffbc28c13', 'user2', null, '2', '2019-03-20 14:37:14', '1');
INSERT INTO `user_log` VALUES ('e08af889d59d4aaf9a30c2e159f59685', 'user3', null, '2', '2019-03-21 11:38:59', '1');
INSERT INTO `user_log` VALUES ('e4493c429e2040ca81f44cb2d31ffea0', 'user2', null, '2', '2019-03-20 16:26:59', '1');
INSERT INTO `user_log` VALUES ('e610ccd2d42c4663bd9748d625d4c80f', 'user2', null, '2', '2019-03-20 10:35:56', '1');
INSERT INTO `user_log` VALUES ('e66006e36f40490a809158c84db129c5', 'user2', null, '2', '2019-03-20 15:14:23', '1');
INSERT INTO `user_log` VALUES ('ea390a70bf80419aa65fba6f726b614a', 'user2', null, '2', '2019-03-20 09:44:22', '1');
INSERT INTO `user_log` VALUES ('edbdbf355f32416fbe915d4a9aaecc70', 'user2', null, '2', '2019-03-20 15:06:55', '1');
INSERT INTO `user_log` VALUES ('ee079756b8014ffbb51c4c08523e725f', 'user2', null, '2', '2019-03-20 15:51:18', '1');
INSERT INTO `user_log` VALUES ('ef0420f043d94142a1a9c84fe6c0c117', 'user2', '13344445555', '1', '2019-03-14 14:09:14', '1');
INSERT INTO `user_log` VALUES ('f002c4a09bad493a9b3fa31dc87b6ddb', 'user2', null, '2', '2019-03-20 10:59:02', '1');
INSERT INTO `user_log` VALUES ('fb568030b67d4758a5c86d9343221958', 'user2', null, '2', '2019-03-20 14:39:33', '1');
INSERT INTO `user_log` VALUES ('fc176a98134943bbb0791f7cffa2b325', 'user2', null, '2', '2019-03-19 19:40:38', '1');
INSERT INTO `user_log` VALUES ('fee76093c3194dd8815f9877aff84949', 'user2', null, '2', '2019-03-20 15:57:13', '1');
