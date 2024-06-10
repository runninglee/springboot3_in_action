package com.sp3.chapter13.common.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class LoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 记录请求信息
        long startTime = System.currentTimeMillis();
        logRequestDetails(request);
        filterChain.doFilter(request, response);
        // 记录响应信息
        long duration = System.currentTimeMillis() - startTime;
        logResponseDetails(response, duration);
    }

    private void logRequestDetails(HttpServletRequest request) throws JsonProcessingException {
        log.info((new ObjectMapper()).writeValueAsString(new HashMap<>() {{
            put("method", request.getMethod());
            put("url", request.getRequestURL());
            put("headers", request.getHeaderNames().toString());
            put("query", request.getQueryString());
        }}));
    }

    private void logResponseDetails(HttpServletResponse response, long duration) throws JsonProcessingException {
        log.info((new ObjectMapper()).writeValueAsString(new HashMap<>() {{
            put("status", response.getStatus());
            put("duration", duration + "ms");
        }}));
    }
}
