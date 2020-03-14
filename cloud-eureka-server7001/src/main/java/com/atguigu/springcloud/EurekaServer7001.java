package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/10 13:34
 */

@SpringBootApplication
@EnableEurekaServer//表示自己是服务注册中心
public class EurekaServer7001 {
    public static void main(String[] args){
        SpringApplication.run(EurekaServer7001.class,args);
    }
}
