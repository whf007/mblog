package com.mtons.mblog.mapper;

import com.mtons.mblog.pojo.TmpChatGroupUser;
import com.mtons.mblog.pojo.TmpChatGroupUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmpChatGroupUserMapper {
    long countByExample(TmpChatGroupUserExample example);

    int deleteByExample(TmpChatGroupUserExample example);

    int insert(TmpChatGroupUser record);

    int insertSelective(TmpChatGroupUser record);

    List<TmpChatGroupUser> selectByExample(TmpChatGroupUserExample example);

    int updateByExampleSelective(@Param("record") TmpChatGroupUser record, @Param("example") TmpChatGroupUserExample example);

    int updateByExample(@Param("record") TmpChatGroupUser record, @Param("example") TmpChatGroupUserExample example);
}