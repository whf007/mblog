package com.mtons.mblog.mapper;

import com.mtons.mblog.pojo.ChatGroupUser;
import com.mtons.mblog.pojo.ChatGroupUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatGroupUserMapper {
    long countByExample(ChatGroupUserExample example);

    int deleteByExample(ChatGroupUserExample example);

    int insert(ChatGroupUser record);

    int insertSelective(ChatGroupUser record);

    List<ChatGroupUser> selectByExample(ChatGroupUserExample example);

    int updateByExampleSelective(@Param("record") ChatGroupUser record, @Param("example") ChatGroupUserExample example);

    int updateByExample(@Param("record") ChatGroupUser record, @Param("example") ChatGroupUserExample example);
}