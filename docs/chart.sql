CREATE database IF not EXISTS db_chat;
use db_chat;
DROP TABLE IF EXISTS `chat_user_record`;
CREATE TABLE `chat_user_record` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `info` varchar(256) DEFAULT NULL COMMENT '聊天信息',
  `chat_group_id` varchar(256) DEFAULT NULL COMMENT '聊天组id',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `UPDATE_TIME` datetime  DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员聊天记录';