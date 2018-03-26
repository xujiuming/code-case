/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.16.193
 Source Server Type    : MariaDB
 Source Server Version : 100300
 Source Host           : 192.168.16.193
 Source Database       : quartz-admin

 Target Server Type    : MariaDB
 Target Server Version : 100300
 File Encoding         : utf-8

 Date: 08/16/2017 21:03:18 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE `quartz-admin`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_bin;

-- ----------------------------
--  Table structure for `alarm_contacts`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_contacts`;
CREATE TABLE `alarm_contacts` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(50) NOT NULL
  COMMENT '名称',
  `email`       VARCHAR(50) NOT NULL
  COMMENT '邮箱',
  `status`      TINYINT(4)  NOT NULL
  COMMENT '状态',
  `create_time` TIMESTAMP   NULL     DEFAULT NULL
  COMMENT '创建时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `deleted_job`
-- ----------------------------
DROP TABLE IF EXISTS `deleted_job`;
CREATE TABLE `deleted_job` (
  `id`                 INT(11)     NOT NULL AUTO_INCREMENT,
  `cluster_name`       VARCHAR(255)         DEFAULT NULL
  COMMENT '集群名',
  `instance_name`      VARCHAR(50) NOT NULL
  COMMENT '集群名',
  `job_name`           VARCHAR(50) NOT NULL
  COMMENT '任务名',
  `job_desc`           VARCHAR(100)         DEFAULT NULL
  COMMENT '任务描述',
  `trigger_type`       VARCHAR(20) NOT NULL
  COMMENT '触发器类型',
  `trigger_expression` VARCHAR(50) NOT NULL
  COMMENT '触发器表达式',
  `time`               TIMESTAMP   NULL     DEFAULT current_timestamp()
  COMMENT '删除时间',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `dispatch_log`
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_log`;
CREATE TABLE `dispatch_log` (
  `id`                         INT(9)           NOT NULL  AUTO_INCREMENT,
  `cluster_name`               VARCHAR(255)
                               COLLATE utf8_bin           DEFAULT NULL
  COMMENT '集群名',
  `instance_name`              VARCHAR(255)
                               COLLATE utf8_bin NOT NULL,
  `begin_time`                 BIGINT(13)       NOT NULL,
  `end_time`                   BIGINT(13)                 DEFAULT NULL,
  `job_name`                   VARCHAR(255)
                               COLLATE utf8_bin NOT NULL,
  `status`                     INT(1)           NOT NULL,
  `execute_duration`           INT(9)                     DEFAULT NULL,
  `exception`                  TEXT COLLATE utf8_bin      DEFAULT NULL,
  `exception_notice_status`    TINYINT(11)                DEFAULT 1
  COMMENT '异常通知状态',
  `slow_execute_notice_status` TINYINT(4)                 DEFAULT 1
  COMMENT '慢执行通知状态',
  PRIMARY KEY (`id`),
  KEY `idx_cluster_name` (`cluster_name`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 206
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- ----------------------------
--  Table structure for `job_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `job_monitor`;
CREATE TABLE `job_monitor` (
  `id`                 INT(11)   NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `cluster_name`       VARCHAR(255)       DEFAULT NULL
  COMMENT '集群名',
  `instance_name`      VARCHAR(50)        DEFAULT NULL
  COMMENT '实例名',
  `job_name`           VARCHAR(50)        DEFAULT NULL
  COMMENT '任务名',
  `alarm_contacts_ids` VARCHAR(500)       DEFAULT NULL
  COMMENT '报警人id集合',
  `max_execute_time`   INT(11)            DEFAULT NULL
  COMMENT '最大执行时间',
  `create_time`        TIMESTAMP NULL     DEFAULT NULL
  COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_clu_inst_job` (`cluster_name`, `instance_name`, `job_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 47
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `quartz_cluster`
-- ----------------------------
DROP TABLE IF EXISTS `quartz_cluster`;
CREATE TABLE `quartz_cluster` (
  `id`               INT(11)      NOT NULL AUTO_INCREMENT,
  `name`             VARCHAR(50)  NOT NULL
  COMMENT '名称',
  `instance_name`    VARCHAR(50)  NOT NULL
  COMMENT '实例名',
  `datasource`       VARCHAR(200) NOT NULL
  COMMENT '数据源',
  `memo`             VARCHAR(200)          DEFAULT NULL
  COMMENT '备注',
  `status`           TINYINT(20)           DEFAULT NULL
  COMMENT '集群状态 0 正常 1 停用',
  `remote_node_host` VARCHAR(100)          DEFAULT NULL
  COMMENT '远程节点地址',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 70
  DEFAULT CHARSET = utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          INT(11) NOT NULL   AUTO_INCREMENT,
  `username`    VARCHAR(100)
                CHARACTER SET utf8 DEFAULT NULL,
  `login_name`  VARCHAR(100)
                COLLATE utf8_bin   DEFAULT NULL,
  `password`    VARCHAR(100)
                COLLATE utf8_bin   DEFAULT NULL,
  `create_time` DATETIME           DEFAULT NULL,
  `status`      INT(11)            DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 10025
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;

#passwd112233

INSERT INTO `user` VALUES ('2', 'admin', '管理员', '1a88a2e5e3cec1f4d4fba1479c198fc3', '2017-07-13 14:40:25', '1');
