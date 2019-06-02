//package com.mtons.mblog.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//
///**
// * Created by raden on 2019/6/2.
// */
//@Configuration
//public class DataSourcePrimaryConfig {
//
//    @Bean(name = "primaryDS") @Qualifier("primaryDS")
//    @Primary
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource primaryDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//}
