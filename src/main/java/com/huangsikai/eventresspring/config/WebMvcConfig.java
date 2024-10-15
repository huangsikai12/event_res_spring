package com.huangsikai.eventresspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private  TokenInterceptor tokenInterceptor;
    @Autowired
    private  AdminOperateInterceptor adminOperateInterceptor ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/add")
                .excludePathPatterns("/user/get")
                .excludePathPatterns("/join/get/user")
                .excludePathPatterns("/event/verSignPwd/**")
                .excludePathPatterns("/test/**")
                .excludePathPatterns("/home/**")
                .excludePathPatterns("/email/**")
                .excludePathPatterns("/event/all")
                ;

        registry.addInterceptor(adminOperateInterceptor)
                .addPathPatterns("/home/setTopInfo")
                .addPathPatterns("/home/setHomeGrid")
                .addPathPatterns("/home/delHomeGrid")
                .addPathPatterns("/home/updateHomeGrid")
                .addPathPatterns("/event/delete/**")
                .addPathPatterns("/event/update/**")
                .addPathPatterns("/event/setSignPwd/**")
                .addPathPatterns("/add")

        ;
    }
}
