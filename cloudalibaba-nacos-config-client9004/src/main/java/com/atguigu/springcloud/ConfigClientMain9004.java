package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/18 21:57
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientMain9004 {
    public static void main(String[] args){
        SpringApplication.run(ConfigClientMain9004.class,args);
    }
}
