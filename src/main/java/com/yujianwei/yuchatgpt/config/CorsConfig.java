package com.yujianwei.yuchatgpt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径
        registry.addMapping("/**")
                // 允许的源
                .allowedOrigins("http://localhost:8080", "http://43.130.240.98", "http://yujianwei.top")
                // 允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的请求头
                .allowedHeaders("*")
                // 允许凭据
                .allowCredentials(true)
                // 预检请求的有效期
                .maxAge(3600);
        System.out.println("CORS成功");
    }
}

