package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用代码配置路由转发
 * @author xiacong
 * @version 1.0
 * @date 2020/3/14 12:18
 */

//@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator getRouteLocator1(RouteLocatorBuilder builder){
        return builder.routes().
                route("gateway", r ->r.path("/projects/**")
                        .uri("https://spring.io"))
                .route("",r -> r.path("/video/**")
                        .uri("https://www.bilibili.com")).build();
    }
}
