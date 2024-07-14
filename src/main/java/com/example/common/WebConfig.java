package com.example.common;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //指定controller统一的接口前绷，相当于:在url上拼了一个 /api/xxx
        configurer.addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(RestController.class));
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
       registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
               .excludePathPatterns("/api/user/login")
               .excludePathPatterns("/api/user/signup");
    }
}