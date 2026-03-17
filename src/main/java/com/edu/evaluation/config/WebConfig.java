package com.edu.evaluation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 默认 Spring Boot 已经处理了 classpath:/static/
        // 只有当你需要上传文件到磁盘（如 /uploads/）时才需要额外加映射
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}