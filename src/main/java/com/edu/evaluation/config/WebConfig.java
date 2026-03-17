package com.edu.evaluation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/login", "/css/**", "/js/**", "/error", "/evaluation/login"); // 排除登录和静态资源
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 默认 Spring Boot 已经处理了 classpath:/static/
        // 只有当你需要上传文件到磁盘（如 /uploads/）时才需要额外加映射
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}