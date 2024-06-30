package com.sp3.chapter17.common.interceptor;

import com.sp3.chapter17.common.auth.UserContext;
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
        String token = request.getHeader(header);
        if (token != null && !token.isEmpty()) {
            token = token.replace(tokenPrefix + " ", "");
            try {
                if (jwtUtil.validateToken(token)) {
                    UserContext.setUser(jwtUtil.parse(token));
//                    request.setAttribute("user", jwtUtil.parse(token));
                    return true;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}