package com.sp3.chapter17.config;

import com.sp3.chapter17.common.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
