package com.mybatis.springSources.myInterface;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/3/18.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //javadoc
public @interface MyAutowired {
    String value() default "";
}
