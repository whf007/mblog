package com.mtons.mblog.mapper;

import com.mtons.mblog.pojo.ChatGroup;
import com.mtons.mblog.pojo.ChatGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatGroupMapper {
    long countByExample(ChatGroupExample example);

    int deleteByExample(ChatGroupExample example);

    int deleteByPrimaryKey(Long chatGroupId);

    int insert(ChatGroup record);

    int insertSelective(ChatGroup record);

    List<ChatGroup> selectByExample(ChatGroupExample example);

    ChatGroup selectByPrimaryKey(Long chatGroupId);

    int updateByExampleSelective(@Param("record") ChatGroup record, @Param("example") ChatGroupExample example);

    int updateByExample(@Param("record") ChatGroup record, @Param("example") ChatGroupExample example);

    int updateByPrimaryKeySelective(ChatGroup record);

    int updateByPrimaryKey(ChatGroup record);
}