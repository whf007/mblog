package com.mtons.mblog.convert;/**
 * Created by Administrator on 2019/5/17.
 */

import com.mtons.mblog.entity.ChatRecord;
import com.mtons.mblog.entity.GroupRole;
import com.mtons.mblog.entity.GroupUser;
import com.mtons.mblog.enums.GroupRoleEnum;
import com.mtons.mblog.pojo.ChatGroup;
import com.mtons.mblog.pojo.ChatGroupUser;
import com.mtons.mblog.pojo.ChatUserRecord;

import java.util.Date;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/5/17.
 **/
public class ConvertChat {
    public static ChatGroup groupUserTochatGroup(GroupUser groupUser){
        ChatGroup group = new ChatGroup();
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());
        group.setUserId(groupUser.getUserId());
        group.setUserName(groupUser.getUserName());
        return group;
    }
    public static ChatGroupUser groupRole(GroupUser groupUser){
        ChatGroupUser chatGroupUser = new ChatGroupUser();
        chatGroupUser.setCreateTime(new Date());
        chatGroupUser.setUpdateTime(new Date());
        chatGroupUser.setUserId(groupUser.getUserId());
        chatGroupUser.setUserName(groupUser.getUserName());
        chatGroupUser.setRole(GroupRoleEnum.admin.getCode());
        chatGroupUser.setCreateTime(new Date());
        chatGroupUser.setUpdateTime(new Date());
        chatGroupUser.setOperationId(groupUser.getUserId());
        chatGroupUser.setOperationName(groupUser.getUserName());
        return chatGroupUser;
    }
    public static ChatGroupUser groupRoleToChatGroup(GroupRole groupRole){
        ChatGroupUser chatGroupUser = new ChatGroupUser();
        chatGroupUser.setCreateTime(new Date());
        chatGroupUser.setUpdateTime(new Date());
        chatGroupUser.setUserId(groupRole.getUserId());
        chatGroupUser.setUserName(groupRole.getUserName());
        chatGroupUser.setRole(GroupRoleEnum.admin.getCode());
        chatGroupUser.setCreateTime(new Date());
        chatGroupUser.setUpdateTime(new Date());
        chatGroupUser.setOperationId(groupRole.getOperationId());
        chatGroupUser.setOperationName(groupRole.getOperationName());
        return chatGroupUser;
    }
    public static ChatUserRecord  ChatRecordToChatUserRecord(ChatRecord chatRecord){
        ChatUserRecord record = new ChatUserRecord();
        record.setChatGroupId(chatRecord.getChatGroupId());
        record.setCreateTime(new Date());
        record.setInfo(chatRecord.getInfo());
        record.setUserId(chatRecord.getUserId());
        record.setUserName(chatRecord.getUserName());
        return record;
    }
}
