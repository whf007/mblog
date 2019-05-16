package com.mtons.mblog;

import com.mtons.mblog.entity.GroupUser;
import org.springframework.stereotype.Service;

/**
 * Created by raden on 2019/5/16.
 */
@Service
public interface ChatService {
    /**
     * 创建聊天组
     * @return
     */
    public int createGroup(GroupUser groupUser);

    /**
     * 给用户赋予聊天组权限
     */
    public boolean roleGroupUser();

    /**
     * 添加聊天记录
     * @return
     */
    public int addChatRecord();
}
