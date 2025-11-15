package com.example.demo.configurations.interceptors;

import com.example.demo.configurations.interceptors.components.IpInterceptorComponents;
import com.example.demo.configurations.interceptors.components.LogInterceptorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private IpInterceptorComponents interceptor_IP;

    @Autowired
    private LogInterceptorComponent interceptor_LOG;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Them cac thanh phan interceptor tai day
        registry.addInterceptor(interceptor_IP).excludePathPatterns
                (
                        "/js/**",
                        "/css/**",
                        "/home/**",
                        "/about/**",
                        "/favicon.io",
                        "/assets/**",
                        "/auth/login/**",
                        "/auth/register/**", "/error"
                );
        registry.addInterceptor(interceptor_LOG).excludePathPatterns
                (
                        "/js/**",
                        "/css/**",
                        "/home/**",
                        "/favicon.io",
                        "/assets/**",
                        "/about/**",
                        "/auth/login/**",
                        "/auth/register/**", "/error"
                );
    }
}
