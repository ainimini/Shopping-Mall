/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : oa

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 18/04/2020 15:30:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acl_user
-- ----------------------------
DROP TABLE IF EXISTS `acl_user`;
CREATE TABLE `acl_user`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` tinyint(2) UNSIGNED NULL DEFAULT NULL COMMENT '性别 1 女，2 男',
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `job_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '工号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户签名',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工照片',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of acl_user
-- ----------------------------
INSERT INTO `acl_user` VALUES ('1251106399707013122', 'string', '$2a$10$AujFnAajViMmc8xl200PoePfLnKa0Ul9wHVyotlc2tnnge3g8Iaby', 1, 'string', '', 'string', '2020-04-17', 'string', 'string', NULL, 'string', 'string', 0, 0, '2020-04-17 19:13:05', '2020-04-17 19:13:05');
INSERT INTO `acl_user` VALUES ('1251110160735178753', 'string', '$2a$10$Kky62knINNsHMHUipZHY2.q8D9BPRL2YALjeK3POgZQW2iSgjAaaC', 1, 'string', '', 'string', '2020-04-17', 'string', 'string', 'string', 'string', 'str1ing', 0, 0, '2020-04-17 19:28:01', '2020-04-17 19:28:01');
INSERT INTO `acl_user` VALUES ('1251110492869586946', 'string', '$2a$10$AyKTb45C6XfltjpV1jho4.zYKj8JkopC1BiMKVn/sHR.a1qedhmXS', 1, 'string', '', 'string', '2020-04-17', 'string', 'string', 'string', 'string', 'str11ing', 0, 0, '2020-04-17 19:29:21', '2020-04-17 19:29:21');
INSERT INTO `acl_user` VALUES ('1251193787036790785', 'string', '$2a$10$WaedfGekcVJR1xpKsMu3QOxPV0MrsvxtmoSo8ZxnA9CG25dsbibzy', 2, 'string', '', 'string', '2020-04-17', 'string', 'string', 'string', 'string', '13253604579', 0, 0, '2020-04-18 01:00:20', '2020-04-18 01:00:20');
INSERT INTO `acl_user` VALUES ('1251196049964445698', 'string', '$2a$10$2nBU9ChMbSRXk90FowiAeOvV.iL7b8aVuH.6kPzenHetzOlTgM2/e', 2, '021093194', '', 'string', '2020-04-17', 'string', 'string', 'string', 'string', '13253604571', 0, 0, '2020-04-18 01:09:19', '2020-04-18 01:09:19');
INSERT INTO `acl_user` VALUES ('1251407826249687041', 'string', '$2a$10$QFFD/7EfxnJsMcgdXJQw1OauQMeIdAv4bNIt59WfaPwxrgASmv3VG', 1, '213123131321', '38552084041', 'string', '2020-04-18', 'string', 'string', NULL, 'string', '2131231', 0, 0, '2020-04-18 15:10:50', '2020-04-18 15:10:50');
INSERT INTO `acl_user` VALUES ('1251410331389972482', 'string', '$2a$10$h6eGq8Fz6FEto5CZ1xgOUeWx5kSGbQEFUcHJ9CwbGd.Wn4Rl6nPqu', 1, '213123131321', '55832420021', 'string', '2020-04-18', 'string', 'string', NULL, 'string', '131231', 0, 0, '2020-04-18 15:20:48', '2020-04-18 15:20:48');
INSERT INTO `acl_user` VALUES ('string', '111', '$2a$10$xwipMI1SxEyR0yUe00/zsuV3mk9B9a0sElNcyx8kc2uTgmgM6LeEm', 2, 'string', '', 'string', '2020-04-17', 'string', 'string', 'string', 'string', '111', 0, 0, '2020-04-17 19:20:50', '2020-04-17 19:20:50');

SET FOREIGN_KEY_CHECKS = 1;
