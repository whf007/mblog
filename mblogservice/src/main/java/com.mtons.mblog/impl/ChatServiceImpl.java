package com.mtons.mblog.impl;

import com.mtons.mblog.ChatService;
import com.mtons.mblog.convert.ConvertChat;
import com.mtons.mblog.entity.ChatRecord;
import com.mtons.mblog.entity.GroupRole;
import com.mtons.mblog.entity.GroupUser;
import com.mtons.mblog.mapper.ChatGroupMapper;
import com.mtons.mblog.mapper.ChatGroupUserMapper;
import com.mtons.mblog.mapper.ChatUserRecordMapper;
import com.mtons.mblog.pojo.ChatGroup;
import com.mtons.mblog.pojo.ChatGroupUser;
import com.mtons.mblog.pojo.ChatUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by raden on 2019/5/16.
 */

public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatGroupMapper chatGroupMapper;
    @Autowired
    private ChatGroupUserMapper chatGroupUserMapper;
    @Autowired
    private ChatUserRecordMapper chatUserRecordMapper;
    @Override
    @Transactional
    public int createGroup(GroupUser groupUser) {
        ChatGroup group = ConvertChat.groupUserTochatGroup(groupUser);
        chatGroupMapper.insert(group);
        // 赋值权限
        ChatGroupUser chatGroupUser = ConvertChat.groupRole(groupUser);
        int insert = chatGroupUserMapper.insert(chatGroupUser);
        return insert;
    }
    @Override
    public boolean addColeGroupUser(GroupRole groupRole) {
        // 赋值权限
        ChatGroupUser chatGroupUser = ConvertChat.groupRoleToChatGroup(groupRole);
        chatGroupUserMapper.insert(chatGroupUser);
        return true;
    }
    @Override
    public int addChatRecord(ChatRecord chatRecord) {
        ChatUserRecord record = ConvertChat.ChatRecordToChatUserRecord(chatRecord);
        return chatUserRecordMapper.insert(record);
    }
}
