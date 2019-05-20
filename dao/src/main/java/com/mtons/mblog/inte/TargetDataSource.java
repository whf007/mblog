package com.mtons.mblog.inte;

/**
 * Created by Administrator on 2019/5/20.
 */
import com.mtons.mblog.enums.DatabaseType;

import java.lang.annotation.*;

/**
 * @Author whf
 * @Date 2019-05-23
 * @Description 作用于类、接口或者方法上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    DatabaseType name();
}
