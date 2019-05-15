package com.mtons.mblog.dao;/**
 * Created by Administrator on 2019/5/15.
 */

import com.mtons.mblog.config.DatabaseContextHolder;
import com.mtons.mblog.enums.DatabaseType;
import com.mtons.mblog.mapper.UserMapper;
import com.mtons.mblog.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
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
public class UserDao   {
    @Autowired
    UserMapper userMapper;
    public List<User> selectAll(){
        DatabaseContextHolder.setDatabaseType(DatabaseType.mytestdb2);
        return userMapper.selectAll();
    }
}
