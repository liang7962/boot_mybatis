package com.mybatis.springSources.myInterface;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/3/18.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {

    String value() default "";
}
