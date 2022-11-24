package com.example.backend.config;

import com.example.backend.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] exclude = {
                "/user/sign-in",
                "/user/sign-up",
                "/forbidden",
                "/v2/api-docs",             // for swagger-ui
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**"
        };

        registry.addInterceptor(new LogInterceptor()).excludePathPatterns(exclude);
    }
}
