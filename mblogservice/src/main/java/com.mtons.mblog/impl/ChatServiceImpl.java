package com.mtons.mblog.impl;

import com.mtons.mblog.ChatService;
import com.mtons.mblog.entity.GroupUser;
import com.mtons.mblog.mapper.ChatGroupMapper;
import com.mtons.mblog.pojo.ChatGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by raden on 2019/5/16.
 */

public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatGroupMapper chatGroupMapper;
    @Override
    public int createGroup(GroupUser groupUser) {
        ChatGroup group = new ChatGroup();
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());
        group.setUserId(groupUser.getUserId());
        group.setUserName(groupUser.getUserName());
        return chatGroupMapper.insert(group);
    }

    @Override
    public boolean roleGroupUser() {
        return false;
    }

    @Override
    public int addChatRecord() {
        return 0;
    }
}
