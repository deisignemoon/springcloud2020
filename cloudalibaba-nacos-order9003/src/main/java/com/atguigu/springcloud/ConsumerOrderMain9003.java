package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 23:16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderMain9003 {
    public static void main(String[] args){
        SpringApplication.run(ConsumerOrderMain9003.class,args);
    }
}
