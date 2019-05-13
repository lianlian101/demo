package com.mvn.test.util.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 创建时间: 2019年5月13日 创建人 zhb 说明: 自定义切面拦截
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AspectLog {

    /**
     * 创建日期: 2019年5月13日 创建人: zhb 说明: 描述内容
     * 
     * @return
     */
    String value() default "";
    
}
