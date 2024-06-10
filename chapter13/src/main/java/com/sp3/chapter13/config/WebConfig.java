package com.sp3.chapter13.config;

import com.sp3.chapter13.common.interceptor.AuthenticationInterceptor;
import com.sp3.chapter13.common.interceptor.LoggerInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Resource
    private LoggerInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/api/interceptor/**").excludePathPatterns("/api/interceptor/user/login");
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/api/interceptor/**");
    }
}
