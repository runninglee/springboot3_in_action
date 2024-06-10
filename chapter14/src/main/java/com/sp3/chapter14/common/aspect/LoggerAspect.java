package com.sp3.chapter14.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.sp3.chapter14.app.aop.AopController.*(..))")
    public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("切面执行了");
        return joinPoint.proceed();
    }


    @Around("execution(Object com.sp3.chapter14.app.aop.AopController.*(..))")
    public Object allObjectReturningMethodsInServicePackage(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("切面执行了");
        return joinPoint.proceed();
    }

    @Around("execution(Object com.sp3.chapter14.app.aop.AopController.*(String,..))")
    public Object methodsWithStringArg(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("切面执行了");
        return joinPoint.proceed();
    }
}
