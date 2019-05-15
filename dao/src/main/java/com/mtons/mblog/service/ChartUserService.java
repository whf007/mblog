package com.mtons.mblog.service;/**
 * Created by Administrator on 2019/5/15.
 */

import com.mtons.mblog.config.DatabaseContextHolder;
import com.mtons.mblog.enums.DatabaseType;
import com.mtons.mblog.mapper.ChatUserRecordMapper;
import com.mtons.mblog.pojo.ChatUserRecord;
import com.mtons.mblog.pojo.ChatUserRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/5/15.
 **/
@Component
public class ChartUserService {
    @Autowired
    ChatUserRecordMapper chatUserRecordMapper;
    public List<ChatUserRecord> selectAll(){
        DatabaseContextHolder.setDatabaseType(DatabaseType.mytestdb2);
        ChatUserRecordExample example = new ChatUserRecordExample();
        return chatUserRecordMapper.selectByExample(example);
    }
}
