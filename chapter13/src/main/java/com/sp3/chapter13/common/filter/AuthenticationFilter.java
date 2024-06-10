package com.sp3.chapter13.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp3.chapter13.util.api.ResultJson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        if (path.startsWith("/user/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 检查用户是否已经登录
        if (isUserLoggedIn(request)) {
            // 用户已登录，继续执行
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write((new ObjectMapper()).writeValueAsString(ResultJson.unauthorized(null)));
        }
    }

    // 实际应用中，这里可以根据会话或其他方式检查用户是否已登录
    private boolean isUserLoggedIn(HttpServletRequest request) {
        // 这里假设用户已登录，实际应用中需要根据会话或其他方式来判断
        return false;
    }
}
