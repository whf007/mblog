package com.mtons.mblog.mapper;

import com.mtons.mblog.pojo.RadioRecord;
import com.mtons.mblog.pojo.RadioRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface RadioRecordMapper {
    long countByExample(RadioRecordExample example);

    int deleteByExample(RadioRecordExample example);

    int deleteByPrimaryKey(String requestId);

    int insert(RadioRecord record);

    int insertSelective(RadioRecord record);

    List<RadioRecord> selectByExample(RadioRecordExample example);

    RadioRecord selectByPrimaryKey(String requestId);

    int updateByExampleSelective(@Param("record") RadioRecord record, @Param("example") RadioRecordExample example);

    int updateByExample(@Param("record") RadioRecord record, @Param("example") RadioRecordExample example);

    int updateByPrimaryKeySelective(RadioRecord record);

    int updateByPrimaryKey(RadioRecord record);
}