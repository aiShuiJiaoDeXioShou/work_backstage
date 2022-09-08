package com.yangteng.workbackstage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowDomain = {"http://localhost:8081/","http://172.24.74.207:8081/","http://localhost/"};
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOrigins(allowDomain)//多url跨域
                .allowCredentials(true)//是否允许证书 不写默认开启
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH") //设置允许的方法
                .maxAge(3600);//跨域允许时间
    }
}
