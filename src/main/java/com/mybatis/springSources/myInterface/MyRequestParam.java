package com.mybatis.springSources.myInterface;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/3/18.
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";
}
