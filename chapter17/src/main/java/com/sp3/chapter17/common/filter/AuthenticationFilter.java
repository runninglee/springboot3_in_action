package com.sp3.chapter17.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            response.sendRedirect("/user/login");
        }
    }

    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("uid") != null;
    }
}