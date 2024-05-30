package com.sp3.chapter7.util.rule.exist;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistValidator.class)
public @interface Exist {

    String message() default "";

    String table() default "";

    String field() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
