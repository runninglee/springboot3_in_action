package com.sp3.chapter13.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp3.chapter13.util.api.ResultJson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    // 请求处理前的逻辑
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (isUserLoggedIn(request)) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write((new ObjectMapper()).writeValueAsString(ResultJson.unauthorized(null)));
            return false; // 返回 true 继续处理请求，返回 false 拦截请求
        }
    }

    // 请求处理后的逻辑，但在视图渲染之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println(request);
    }

    // 请求完成后的逻辑，即视图渲染之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
    }

    // 实际应用中，这里可以根据会话或其他方式检查用户是否已登录
    private boolean isUserLoggedIn(HttpServletRequest request) {
        System.out.println("用户登录认证信息，request: " + request);
        return false;
    }
}
