package com.ecommerceapi.ecommerceapi.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecommerceapi.ecommerceapi.util.UserArgumentResolver;


@Configuration
public class GeneralConfig implements WebMvcConfigurer{
@Override
public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new UserArgumentResolver());
}
}