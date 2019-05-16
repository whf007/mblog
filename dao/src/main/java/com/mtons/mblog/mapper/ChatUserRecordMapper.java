package com.mtons.mblog.mapper;

import com.mtons.mblog.pojo.ChatUserRecord;
import com.mtons.mblog.pojo.ChatUserRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ChatUserRecordMapper {
    long countByExample(ChatUserRecordExample example);

    int deleteByExample(ChatUserRecordExample example);

    int insert(ChatUserRecord record);

    int insertSelective(ChatUserRecord record);

    List<ChatUserRecord> selectByExample(ChatUserRecordExample example);

    int updateByExampleSelective(@Param("record") ChatUserRecord record, @Param("example") ChatUserRecordExample example);

    int updateByExample(@Param("record") ChatUserRecord record, @Param("example") ChatUserRecordExample example);
}