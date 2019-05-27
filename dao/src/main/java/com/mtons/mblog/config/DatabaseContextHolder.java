package com.mtons.mblog.config;/**
 * Created by Administrator on 2019/5/15.
 */

import com.mtons.mblog.enums.DatabaseType;

/**
 * 作用：
 * 1、保存一个线程安全的DatabaseType容器
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }
    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
    public static void ClearDataBaseType(){
        contextHolder.remove();
    }
}
