package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 23:06
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderPaymentMain9001 {
    public static void main(String[] args){
        SpringApplication.run(ProviderPaymentMain9001.class,args);
    }
}
