package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 19:06
 */

@SpringBootApplication
@EnableEurekaClient
public class StreamMQConsumerMain8803 {
    public static void main(String[] args){
        SpringApplication.run(StreamMQConsumerMain8803.class,args);
    }
}
