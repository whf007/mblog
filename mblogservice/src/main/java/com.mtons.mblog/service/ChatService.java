package com.mtons.mblog.service;

import com.mtons.mblog.entity.ChatRecord;
import com.mtons.mblog.entity.GroupRole;
import com.mtons.mblog.entity.GroupUser;
import org.springframework.stereotype.Service;

/**
 * Created by raden on 2019/5/16.
 */

public interface ChatService {
    /**
     * 创建聊天组
     * @return
     */
    public int createGroup(GroupUser groupUser);
    // 添加聊天组在线成员
    public int addGroupUser(GroupUser groupUser);
    // 查询聊天组
    public Long queryGroup(Long userId);
    public int removeGroupUser(GroupUser groupUser);

    /**
     * 给用户赋予聊天组权限
     */
    public boolean addColeGroupUser(GroupRole groupRole);

    /**
     * 添加聊天记录
     * @return
     */
    public int addChatRecord(ChatRecord chatRecord);
}
