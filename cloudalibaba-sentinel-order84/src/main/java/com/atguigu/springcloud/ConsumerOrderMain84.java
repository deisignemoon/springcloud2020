package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/29 19:58
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerOrderMain84 {
    public static void main(String[] args){
        SpringApplication.run(ConsumerOrderMain84.class,args);
    }
}
