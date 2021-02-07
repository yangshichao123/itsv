package com.data.itsv.aspect;

import java.lang.annotation.*;

/**
 * @description: 自定义log注解
 * @author: Daigl
 * @create: 2021-02-01 21:33
 **/
@Target({ ElementType.PARAMETER, ElementType.METHOD }) //可以作用在类上，也可以作用在方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 日志类型
     */
    public int  logType() default BusinessType.SYSTEM_LOG;

    /**
     * 操作类型
     */
    public int  operatorType() default  OperatorType.USER_LOGIN;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

}
