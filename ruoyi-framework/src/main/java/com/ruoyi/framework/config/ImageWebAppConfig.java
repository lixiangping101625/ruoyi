package com.ruoyi.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageWebAppConfig implements WebMvcConfigurer {

    @Value("${file.imagePath}")
    private String imagePath;

    @Value("${file.httpImagePath}")
    private String httpImagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(httpImagePath).addResourceLocations(imagePath);
    }
}