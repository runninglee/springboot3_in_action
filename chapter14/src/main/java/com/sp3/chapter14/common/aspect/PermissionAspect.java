package com.sp3.chapter14.common.aspect;

import com.sp3.chapter14.common.annotation.DataPermission;
import com.sp3.chapter14.common.annotation.HandlePermission;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class PermissionAspect {

    @Before("@annotation(com.sp3.chapter14.common.annotation.HandlePermission)")
    public void getHandlerPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HandlePermission handlePermission = method.getAnnotation(HandlePermission.class);
        System.out.println(handlePermission.value());
    }

    @Before("@annotation(com.sp3.chapter14.common.annotation.DataPermission)")
    public void getDataPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataPermission dataPermission = signature.getMethod().getAnnotation(DataPermission.class);

        System.out.println(dataPermission.value());
        System.out.println(dataPermission.entity());
        System.out.println(getDataPermissionId(joinPoint, signature, dataPermission));
    }

    private Object getDataPermissionId(JoinPoint joinPoint, MethodSignature signature, DataPermission dataPermission) {
        Object[] args = joinPoint.getArgs();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        String idExpression = dataPermission.id();
        return parser.parseExpression(idExpression).getValue(context, Long.class);
    }

}
