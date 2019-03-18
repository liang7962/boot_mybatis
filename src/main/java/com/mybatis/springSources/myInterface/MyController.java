package com.mybatis.springSources.myInterface;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2019/3/18.
 */
@Target(ElementType.TYPE)//生效在类上面
@Documented
@Component
public @interface MyController {

    String value() default "";
}
