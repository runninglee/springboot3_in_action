package com.sp3.chapter17.config;

import com.sp3.chapter17.common.interceptor.JwtAuthenticationInterceptor;
import com.sp3.chapter17.common.interceptor.SessionAuthenticationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private SessionAuthenticationInterceptor sessionAuthenticationInterceptor;

    @Resource
    private JwtAuthenticationInterceptor jwtAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionAuthenticationInterceptor)
                .addPathPatterns("/session/**")
                .excludePathPatterns("/session/user/login");
        registry.addInterceptor(jwtAuthenticationInterceptor)
                .addPathPatterns("/jwt/**")
                .excludePathPatterns("/jwt/user/login");
    }
}