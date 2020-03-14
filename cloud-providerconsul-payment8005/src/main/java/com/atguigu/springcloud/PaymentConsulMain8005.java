package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 17:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulMain8005 {
    public static void main(String[] args){
        SpringApplication.run(PaymentConsulMain8005.class,args);
    }
}
