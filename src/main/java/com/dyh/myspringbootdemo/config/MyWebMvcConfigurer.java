package com.dyh.myspringbootdemo.config;

import com.dyh.myspringbootdemo.controller.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getMyResolver());
    }

    @Bean
    public UserController.MyHandlerMethodArgumentResolverDecorator getMyResolver() {
        return new UserController.MyHandlerMethodArgumentResolverDecorator();
    }
}
