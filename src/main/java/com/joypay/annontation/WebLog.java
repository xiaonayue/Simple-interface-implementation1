package com.joypay.annontation;

import java.lang.annotation.*;

/**
 * @author dcj
 * @Date 2023/7/18 18:05
 * 演示:自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
