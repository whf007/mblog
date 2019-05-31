CREATE database IF not EXISTS db_chat;
use db_chat;

DROP TABLE IF EXISTS `chat_group`;
CREATE TABLE `chat_group` (
  `user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `chat_group_id` bigint(20)  NOT NULL AUTO_INCREMENT  COMMENT '聊天组id',
  `chat_group_name` VARCHAR(64) DEFAULT NULL COMMENT '聊天组名称',
  `CREATE_TIME` datetime DEFAULT NULL  COMMENT '建立时间',
  `UPDATE_TIME` datetime  DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`chat_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='聊天创建信息表';

DROP TABLE IF EXISTS `chat_group_user`;
CREATE TABLE `chat_group_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `chat_group_id` bigint(20) NOT NULL COMMENT '聊天组id',
  `role` varchar(8) DEFAULT NULL COMMENT '权限',
	`operation_id` bigint(20) NOT NULL COMMENT '操作用户ID',
  `operation_name` bigint(32) NOT NULL COMMENT '操作用户ID',
  `CREATE_TIME` datetime NOT NULL  COMMENT '建立时间',
  `UPDATE_TIME` datetime  DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天会员权限表';

DROP TABLE IF EXISTS `chat_user_record`;
CREATE TABLE `chat_user_record` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `info` varchar(256) DEFAULT NULL COMMENT '聊天信息',
  `chat_group_id`bigint(20) DEFAULT NULL COMMENT '聊天组id',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `UPDATE_TIME` datetime  DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员聊天记录';

DROP TABLE IF EXISTS `tmp_chat_group_user`;
CREATE TABLE `tmp_chat_group_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `chat_group_id` bigint(20) NOT NULL COMMENT '聊天组id',
  `CREATE_TIME` datetime NOT NULL  COMMENT '建立时间',
  `UPDATE_TIME` datetime  DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='在线聊天会员表';