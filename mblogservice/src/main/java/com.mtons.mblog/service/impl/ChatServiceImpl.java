package com.mtons.mblog.service.impl;

import com.mtons.mblog.inte.TargetDataSource;
import com.mtons.mblog.mapper.TmpChatGroupUserMapper;
import com.mtons.mblog.pojo.*;
import com.mtons.mblog.service.ChatService;
import com.mtons.mblog.config.DatabaseContextHolder;
import com.mtons.mblog.convert.ConvertChat;
import com.mtons.mblog.entity.ChatRecord;
import com.mtons.mblog.entity.GroupRole;
import com.mtons.mblog.entity.GroupUser;
import com.mtons.mblog.enums.DatabaseType;
import com.mtons.mblog.mapper.ChatGroupMapper;
import com.mtons.mblog.mapper.ChatGroupUserMapper;
import com.mtons.mblog.mapper.ChatUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by raden on 2019/5/16.
 */
@Service("chatService")
@TargetDataSource(name=DatabaseType.chat)
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatGroupMapper chatGroupMapper;
    @Autowired
    private ChatGroupUserMapper chatGroupUserMapper;
    @Autowired
    private ChatUserRecordMapper chatUserRecordMapper;
    @Autowired
    private TmpChatGroupUserMapper tmpChatGroupUserMapper;
    @Override
    @Transactional
    public int createGroup(GroupUser groupUser) {
        ChatGroup group = ConvertChat.groupUserTochatGroup(groupUser);
        chatGroupMapper.insert(group);
        // 赋值权限
        ChatGroupUser chatGroupUser = ConvertChat.groupRole(groupUser);
        chatGroupUser.setChatGroupId(group.getChatGroupId());
        int insert = chatGroupUserMapper.insert(chatGroupUser);
        return insert;
    }

    @Override
    public int addGroupUser(GroupUser groupUser) {
        TmpChatGroupUser tmpChatUser = ConvertChat.convert(groupUser);
        int insert = tmpChatGroupUserMapper.insert(tmpChatUser);
        return insert;
    }
    @Override
    public int removeGroupUser(GroupUser groupUser) {
        TmpChatGroupUser tmpChatUser = ConvertChat.convert(groupUser);
        TmpChatGroupUserExample example = new TmpChatGroupUserExample();
        TmpChatGroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andChatGroupIdEqualTo(groupUser.getChatGroupId()).andUserIdEqualTo(groupUser.getUserId());
        int insert = tmpChatGroupUserMapper.deleteByExample(example);
        return insert;
    }
    @TargetDataSource(name=DatabaseType.chat)
    public Long queryGroup(Long userId){
        // 查询用户是否存在直播组
        ChatGroupExample example = new ChatGroupExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ChatGroup> chatGroups = chatGroupMapper.selectByExample(example);
        if(chatGroups.size() > 0) {
            return chatGroups.get(0).getChatGroupId();
        } else {
            return null;
        }
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
