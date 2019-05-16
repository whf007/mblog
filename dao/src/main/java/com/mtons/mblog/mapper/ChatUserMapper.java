package com.mtons.mblog.mapper;

import com.mtons.mblog.pojo.ChatUser;
import com.mtons.mblog.pojo.ChatUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatUserMapper {
    long countByExample(ChatUserExample example);

    int deleteByExample(ChatUserExample example);

    int insert(ChatUser record);

    int insertSelective(ChatUser record);

    List<ChatUser> selectByExample(ChatUserExample example);

    int updateByExampleSelective(@Param("record") ChatUser record, @Param("example") ChatUserExample example);

    int updateByExample(@Param("record") ChatUser record, @Param("example") ChatUserExample example);
}