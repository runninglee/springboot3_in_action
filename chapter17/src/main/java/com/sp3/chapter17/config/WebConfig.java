package com.sp3.chapter17.config;

import com.sp3.chapter17.common.interceptor.JwtAuthenticationInterceptor;
import com.sp3.chapter17.common.interceptor.SessionAuthenticationInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

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