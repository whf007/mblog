package com.mtons.mblog.aop;/**
 * Created by Administrator on 2019/5/21.
 */

import com.mtons.mblog.config.DatabaseContextHolder;
import com.mtons.mblog.enums.DatabaseType;
import com.mtons.mblog.inte.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.util.Enumeration;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/5/21.
 **/
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DataSourceAspect {


    @Pointcut("execution(public * com.mtons.mblog.service.impl..*.*(..))")
    public void dataSource(){

    }

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point,TargetDataSource ds) throws Throwable {
        //获取当前的指定的数据源;
        DatabaseType dsId =ds.name();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        DatabaseContextHolder.setDatabaseType(dsId);

    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {

        System.out.println("Revert DataSource : {} > {}"+ds.name()+point.getSignature());

        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。

        DatabaseContextHolder.ClearDataBaseType();

    }


}
