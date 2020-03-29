package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/19 19:37
 */

@SpringBootApplication
@EnableDiscoveryClient
public class SentinelServiceMain8401 {
    public static void main(String[] args){
        SpringApplication.run(SentinelServiceMain8401.class,args);
    }
}
