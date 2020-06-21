package com.eureka.server;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("POST")
        .allowedOrigins("http://localhost:4200")
        .allowCredentials(true)
        .allowedHeaders("*");
    }
}