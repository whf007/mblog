package com.mtons.mblog.aop;/**
 * Created by Administrator on 2019/5/21.
 */

import com.mtons.mblog.config.DatabaseContextHolder;
import com.mtons.mblog.enums.DatabaseType;
import com.mtons.mblog.inte.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @program: mblog
 * @description:
 * @author: whf
 * @create: on 2019/5/21.
 **/
@Aspect
@Order(-11)//保证该AOP在@Transactional之前执行
@Component
@Slf4j
public class JpaDataSourceAspect {


    @Pointcut("execution(public * com.mtons.mblog.modules.repository..*.*(..))")
    public void dataSource(){

    }

    @Around("dataSource()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Class<?> aClass = point.getTarget().getClass();
        TargetDataSource ds = (TargetDataSource)aClass.getAnnotation(TargetDataSource.class);
        if (ds != null) {
            //获取当前的指定的数据源;
            DatabaseType dsId =ds.name();
            //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
            DatabaseContextHolder.setDatabaseType(dsId);
        } else {
            DatabaseContextHolder.setDatabaseType(DatabaseType.mblog);
            log.info("set datasource is " + DatabaseType.mblog.name());
        }
        try {
            return point.proceed();
        } finally {
            DatabaseContextHolder.clearDataBaseType();
            log.debug("clean datasource");
        }


    }

}
