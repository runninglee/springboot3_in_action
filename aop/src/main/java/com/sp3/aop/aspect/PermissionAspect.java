package com.sp3.aop.aspect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp3.aop.annotation.DataPermission;
import com.sp3.aop.app.aop.request.BaseRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

@Component
@Aspect
public class PermissionAspect {

    @Before("@annotation(com.sp3.aop.annotation.HandlePermission)")
    public void getHandlerPermission(JoinPoint joinPoint) {
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }

    @Before("@annotation(com.sp3.aop.annotation.DataPermission)")
    public void getDataPermission(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DataPermission dataPermission = method.getAnnotation(DataPermission.class);
        String permission = dataPermission.value();
        //获取权限
        System.out.println(permission);
        String id = null;
        Object[] args = joinPoint.getArgs();
        //获取请求参数中的ID
        if (args[0] instanceof BaseRequest) {
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, Object> params = objectMapper.convertValue(args[0], new TypeReference<>() {
            });
            id = (String) params.get(dataPermission.id());
        } else {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 获取请求路径参数
            id = request.getParameter(dataPermission.id());
        }
        System.out.println(id);
    }

}
