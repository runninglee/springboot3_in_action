package com.sp3.chapter14.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {
    //权限
    String value() default "";

    //鉴权资源类型
    Class<?> entity() default Void.class;

    //鉴权资源ID
    String id() default "";

    //鉴权资源Key
    String key() default "org_id";

}
