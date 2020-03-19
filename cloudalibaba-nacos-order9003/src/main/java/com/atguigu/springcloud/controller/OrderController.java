package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 23:21
 */

@RestController
@Slf4j
public class OrderController {
   @Value("${service-url.nacos-user-service}")
    private String paymentUri;


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String getOrder(@PathVariable("id") long id){
        return restTemplate.getForObject(paymentUri+"/payment/nacos/"+id,String.class);
    }
}
