package com.anze.spzx.manager.config;

import com.anze.spzx.manager.interceptor.LoginAuthInterceptor;
import com.anze.spzx.manager.properties.UserAuthProperties;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    //不能通过@Autowired注入，因为这样会导致拦截器在启动前便加载，无法实例化
//    @Autowired
//    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private UserAuthProperties userAuthProperties;
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }

    @Bean
    public LoginAuthInterceptor getLoginAuthInterceptor(){
        return new LoginAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginAuthInterceptor())
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }
}
