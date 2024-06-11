package com.sp3.chapter14.common.aspect;

import com.sp3.chapter14.app.user.service.impl.UserServiceImpl;
import com.sp3.chapter14.common.annotation.DataPermission;
import com.sp3.chapter14.common.annotation.HandlePermission;
import com.sp3.chapter14.exception.GraceException;
import jakarta.annotation.Resource;
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

    @Resource
    private UserServiceImpl userService;

    @Before("@annotation(com.sp3.chapter14.common.annotation.HandlePermission)")
    public void getHandlerPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HandlePermission handlePermission = method.getAnnotation(HandlePermission.class);
        if (!userService.getHandlePermission(handlePermission.value())) {
            GraceException.display("对不起，您的权限不够，无法访问。", 403);
        }
    }

    @Before("@annotation(com.sp3.chapter14.common.annotation.DataPermission)")
    public void getDataPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataPermission dataPermission = signature.getMethod().getAnnotation(DataPermission.class);
        if (!userService.getDataPermission(dataPermission.value(), dataPermission.entity(), (String) getDataPermissionId(joinPoint, signature, dataPermission), dataPermission.key())) {
            GraceException.display("对不起，您的权限不够，无法访问。", 403);
        }
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
        
        return parser.parseExpression(idExpression).getValue(context, String.class);
    }

}
