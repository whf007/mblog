package com.mtons.mblog.service.impl;/**
 * Created by Administrator on 2019/7/1.
 */

import com.mtons.mblog.enums.DatabaseType;
import com.mtons.mblog.inte.TargetDataSource;
import com.mtons.mblog.mapper.RadioRecordMapper;
import com.mtons.mblog.pojo.RadioRecord;
import com.mtons.mblog.pojo.RadioRecordExample;
import com.mtons.mblog.service.RadioRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/7/1.
 **/
@Service("radioRecordService")
@TargetDataSource(name= DatabaseType.chat)
public class RadioRecordServiceImpl implements RadioRecordService{
    @Autowired
    RadioRecordMapper radioRecordMapper;
    @Override
    public void add(RadioRecord radioRecord) {
        radioRecordMapper.insert(radioRecord);
    }

    @Override
    public void queryByUserId(RadioRecord radioRecord) {
        RadioRecordExample example;
        radioRecordMapper.selectByExample(example)
    }
}
