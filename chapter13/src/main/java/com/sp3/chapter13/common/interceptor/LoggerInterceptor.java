package com.sp3.chapter13.common.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws JsonProcessingException {
        log.info((new ObjectMapper()).writeValueAsString(new HashMap<>() {{
            put("url", request.getRequestURI());
            put("query", request.getParameterMap());
            put("started_at", System.currentTimeMillis());
        }}));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws JsonProcessingException {
        log.info((new ObjectMapper()).writeValueAsString(new HashMap<>() {{
            put("started_at", request.getAttribute("started_at"));
            put("stopped_at", System.currentTimeMillis());
        }}));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
    }
}
