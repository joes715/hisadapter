package com.hospital.adapt.config;

import com.hospital.adapt.interceptor.ClientInterceptor;
import com.hospital.adapt.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/hospital/**");
        registry.addInterceptor(new ClientInterceptor()).addPathPatterns("/client/**").excludePathPatterns("/client/board/**");
    }
}
