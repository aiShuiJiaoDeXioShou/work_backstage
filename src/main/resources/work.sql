/*
 Navicat Premium Data Transfer

 Source Server         : yt
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : work

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 12/08/2022 03:31:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_chapter
-- ----------------------------
DROP TABLE IF EXISTS `book_chapter`;
CREATE TABLE `book_chapter`
(
    `id`            int(0)                                                       NOT NULL COMMENT '章节ID',
    `create_time`   datetime(0)                                                  NULL     DEFAULT NULL COMMENT '创造时间',
    `update_time`   datetime(0)                                                  NULL     DEFAULT NULL COMMENT '更新时间',
    `book_id`       int(0)                                                       NULL     DEFAULT NULL COMMENT '书籍id',
    `create_user`   int(0)                                                       NOT NULL COMMENT '创建者的用户id',
    `update_user`   int(0)                                                       NOT NULL COMMENT '更新者的用户id',
    `chapter_title` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT '' COMMENT '章节的标题，max25',
    `content`       json                                                         NULL COMMENT '内容',
    `num`           bigint(0)                                                    NULL     DEFAULT 0 COMMENT '章节的字数',
    `state`         int(0)                                                       NOT NULL DEFAULT 0 COMMENT '章节的状态，是否为上架0代表没有上架，1代表上架，-1代表被屏蔽了',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_chapter
-- ----------------------------

-- ----------------------------
-- Table structure for book_collect
-- ----------------------------
DROP TABLE IF EXISTS `book_collect`;
CREATE TABLE `book_collect`
(
    `id`          int(0)      NOT NULL COMMENT '用户id',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创造时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `deleted`     int(0)      NULL DEFAULT 0 COMMENT '逻辑删除字段',
    `user_id`     int(0)      NOT NULL COMMENT '收藏的用户id',
    `book_id`     int(0)      NULL DEFAULT NULL COMMENT '收藏的书籍信息id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_collect
-- ----------------------------
INSERT INTO `book_collect`
VALUES (960389120, '2022-08-10 03:21:34', '2022-08-10 03:21:34', 1, 1633525760, 1511915520);
INSERT INTO `book_collect`
VALUES (964280320, '2022-08-10 03:21:29', '2022-08-10 03:21:29', 1, 1633525760, 1511915520);

-- ----------------------------
-- Table structure for chapter_comment
-- ----------------------------
DROP TABLE IF EXISTS `chapter_comment`;
CREATE TABLE `chapter_comment`
(
    `id`                    int(0)      NOT NULL COMMENT '章节评论id',
    `create_user`           int(0)      NOT NULL COMMENT '创建者的用户id',
    `create_time`           datetime(0) NOT NULL COMMENT '评论的创建时间',
    `chapter_id`            int(0)      NULL DEFAULT NULL COMMENT '章节id',
    `chapter_paragraph_num` int(0)      NULL DEFAULT NULL COMMENT '被评论章节的段落编号',
    `content`               json        NULL COMMENT '评论内容',
    `be_id`                 int(0)      NULL DEFAULT 0 COMMENT '被评论对象的id，默认为零的情况下，则是顶楼',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter_comment
-- ----------------------------

-- ----------------------------
-- Table structure for monthly_ticket
-- ----------------------------
DROP TABLE IF EXISTS `monthly_ticket`;
CREATE TABLE `monthly_ticket`
(
    `id`          int(0)      NOT NULL COMMENT '用户id',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创造时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `deleted`     int(0)      NULL DEFAULT 0 COMMENT '逻辑删除字段',
    `user_id`     int(0)      NOT NULL COMMENT '投入月票的用户id',
    `book_id`     int(0)      NULL DEFAULT NULL COMMENT '被投入月票的书籍信息id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monthly_ticket
-- ----------------------------

-- ----------------------------
-- Table structure for ua_authority
-- ----------------------------
DROP TABLE IF EXISTS `ua_authority`;
CREATE TABLE `ua_authority`
(
    `id`             int(0)                                                        NOT NULL COMMENT '权限id',
    `create_time`    datetime(0)                                                   NOT NULL COMMENT '创造时间',
    `update_time`    datetime(0)                                                   NOT NULL COMMENT '更新时间',
    `create_user`    int(0)                                                        NOT NULL COMMENT '创建者的用户id',
    `update_user`    int(0)                                                        NOT NULL COMMENT '更新者的用户id',
    `authority_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '权限名称限制25个字符',
    `authority_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限详情，他的应用场景',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ua_authority
-- ----------------------------

-- ----------------------------
-- Table structure for ua_role
-- ----------------------------
DROP TABLE IF EXISTS `ua_role`;
CREATE TABLE `ua_role`
(
    `id`          int(0)                                                        NOT NULL COMMENT '角色id',
    `create_time` datetime(0)                                                   NOT NULL COMMENT '创造时间',
    `update_time` datetime(0)                                                   NOT NULL COMMENT '更新时间',
    `create_user` int(0)                                                        NOT NULL COMMENT '创建者的用户id',
    `update_user` int(0)                                                        NOT NULL COMMENT '更新者的用户id',
    `role_name`   varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '角色名称限制25个字符',
    `role_info`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色详情，他的应用场景',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ua_role
-- ----------------------------

-- ----------------------------
-- Table structure for ua_role_for_authority
-- ----------------------------
DROP TABLE IF EXISTS `ua_role_for_authority`;
CREATE TABLE `ua_role_for_authority`
(
    `id`           int(0)      NOT NULL COMMENT '角色to权限id',
    `create_time`  datetime(0) NOT NULL COMMENT '创造时间',
    `update_time`  datetime(0) NOT NULL COMMENT '更新时间',
    `role_id`      int(0)      NOT NULL COMMENT '角色id',
    `authority_id` int(0)      NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ua_role_for_authority
-- ----------------------------

-- ----------------------------
-- Table structure for ua_user_for_role
-- ----------------------------
DROP TABLE IF EXISTS `ua_user_for_role`;
CREATE TABLE `ua_user_for_role`
(
    `id`          int(0)      NOT NULL COMMENT '用户for role',
    `create_time` datetime(0) NOT NULL COMMENT '创造时间',
    `update_time` datetime(0) NOT NULL COMMENT '更新时间',
    `user_id`     int(0)      NOT NULL COMMENT '用户id',
    `role_id`     int(0)      NOT NULL COMMENT '角色id',
    `expiration`  datetime(0) NOT NULL COMMENT '过期时间，如果为空就说明没有过期时间，为永久',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ua_user_for_role
-- ----------------------------

-- ----------------------------
-- Table structure for user_consume
-- ----------------------------
DROP TABLE IF EXISTS `user_consume`;
CREATE TABLE `user_consume`
(
    `id`          int(0)         NOT NULL COMMENT '用户id',
    `create_time` datetime(0)    NULL     DEFAULT NULL COMMENT '创造时间',
    `update_time` datetime(0)    NULL     DEFAULT NULL COMMENT '更新时间',
    `deleted`     int(0)         NULL     DEFAULT 0 COMMENT '逻辑删除字段',
    `user_id`     int(0)         NOT NULL COMMENT '收藏的用户id',
    `book_id`     int(0)         NOT NULL COMMENT '收藏的书籍信息id',
    `version`     int(0)         NOT NULL DEFAULT 0 COMMENT '效费版本',
    `money`       decimal(10, 2) NULL     DEFAULT NULL COMMENT '消费金额',
    `type`        int(0)         NULL     DEFAULT NULL COMMENT '消费类型',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_consume
-- ----------------------------

-- ----------------------------
-- Table structure for work_book
-- ----------------------------
DROP TABLE IF EXISTS `work_book`;
CREATE TABLE `work_book`
(
    `id`             int(0)                                                        NOT NULL DEFAULT 0 COMMENT '书籍id',
    `create_time`    datetime(0)                                                   NULL     DEFAULT NULL COMMENT '创造时间',
    `update_time`    datetime(0)                                                   NULL     DEFAULT NULL COMMENT '更新时间',
    `deleted`        int(0)                                                        NULL     DEFAULT 0 COMMENT '逻辑删除字段',
    `name`           varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '书籍名称',
    `autor_id`       int(0)                                                        NOT NULL COMMENT '作者id',
    `price_combo_id` int(0)                                                        NULL     DEFAULT 0 COMMENT '书籍套餐id',
    `book_price`     decimal(10, 2)                                                NOT NULL COMMENT '指定书籍价格，有它在price_combo_id不起效',
    `book_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '书籍的封面',
    `book_introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '书籍的简介',
    `book_category`  varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL     DEFAULT NULL COMMENT '书籍类别',
    `clicks`         int(0)                                                        NULL     DEFAULT 0 COMMENT '被点击量',
    `state`          int(0)                                                        NULL     DEFAULT NULL COMMENT '书籍状态0表示未通过审查,1表示已通过审查,2表示已签约,3表示已上架,-1表示已封杀',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_book
-- ----------------------------
INSERT INTO `work_book`
VALUES (1511915520, '2022-08-10 00:15:35', '2022-08-10 00:15:35', 0, '完美世界5', 1, 1, 0.50, NULL, '大荒中的少年称霸世界', '玄幻', 4,
        NULL);
INSERT INTO `work_book`
VALUES (1514946560, '2022-08-10 00:15:31', '2022-08-10 00:15:31', 0, '完美世界4', 1, 1, 0.50, NULL, '大荒中的少年称霸世界', '玄幻', 12,
        NULL);
INSERT INTO `work_book`
VALUES (1517707264, '2022-08-10 00:15:27', '2022-08-10 00:15:27', 0, '完美世界3', 1, 1, 0.50, NULL, '大荒中的少年称霸世界', '玄幻',
        2000, NULL);
INSERT INTO `work_book`
VALUES (1521573888, '2022-08-10 00:15:23', '2022-08-10 00:15:23', 0, '完美世界2', 1, 1, 0.50, NULL, '大荒中的少年称霸世界', '玄幻', 154,
        NULL);
INSERT INTO `work_book`
VALUES (1946763264, '2022-08-10 00:06:44', '2022-08-10 00:11:16', 0, '完美世界', 1, 1, 0.50, NULL, '大荒中的少年称霸世界', '玄幻', 14,
        NULL);

-- ----------------------------
-- Table structure for work_user
-- ----------------------------
DROP TABLE IF EXISTS `work_user`;
CREATE TABLE `work_user`
(
    `id`              int(0)                                                       NOT NULL COMMENT '用户id',
    `name`            varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名是唯一的，最大值为12位字符',
    `password`        varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码最大为12位字符',
    `email`           varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
    `phone`           varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
    `create_time`     datetime(0)                                                  NULL DEFAULT NULL COMMENT '创造时间',
    `update_time`     datetime(0)                                                  NULL DEFAULT NULL COMMENT '更新时间',
    `account_balance` double                                                       NULL DEFAULT 0 COMMENT '账户余额',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `phone` (`phone`) USING BTREE,
    UNIQUE INDEX `email` (`email`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_user
-- ----------------------------
INSERT INTO `work_user`
VALUES (1633525760, 'yangteng', 'yt1234', '2832294398@qq.com', '19151950915', '2022-08-09 00:55:00',
        '2022-08-09 00:55:00', 0);

SET FOREIGN_KEY_CHECKS = 1;
