package com.hanssem.remodeling.common.common.config;

import com.hanssem.remodeling.common.common.interceptor.OnlyLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebResolverConfig implements WebMvcConfigurer {

    private final OnlyLoginInterceptor onlyLoginInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(CustomPageableResolver.newInstance());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(onlyLoginInterceptor);
    }
}
