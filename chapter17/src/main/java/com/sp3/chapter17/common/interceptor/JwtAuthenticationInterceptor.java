package com.sp3.chapter17.common.interceptor;

import com.sp3.chapter17.util.exception.GraceException;
import com.sp3.chapter17.util.jwt.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Value("${app.jwt.header:Authorization}")
    private String header;

    @Value("${app.jwt.tokenPrefix:Bearer}")
    private String tokenPrefix;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String authorizationHeader = request.getHeader(header);
        Long user_id;
        if (authorizationHeader != null) {
            System.out.println(authorizationHeader);
            if (jwtUtil.validateToken(authorizationHeader)) {
                user_id = jwtUtil.getSubject(authorizationHeader.replace(tokenPrefix + " ", ""));
                response.addDateHeader("user_id", user_id);
                return true;
            } else {
                GraceException.display("令牌 TOKEN 无效", 401);
                return false;
            }
        } else {
            GraceException.display("请求参数缺少Token信息");
            return false;
        }
    }
}