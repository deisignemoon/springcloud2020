package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 15:27
 */

@RestController
@Slf4j
public class OrderZkController {
    private static final String ZKPAYMENTURL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentZk(){
        log.info("##########Order.paymnetZK#########");
        return restTemplate.getForObject(ZKPAYMENTURL+"/payment/zk",String.class);
    }
}
