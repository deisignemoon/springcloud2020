package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 17:36
 */
@RestController
@Slf4j
public class OrderConsulControllrt {

    private static final String CONSULPAYMENTURL="http://cloud-consul-paymeny";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String paymentConsul(){
        return restTemplate.getForObject(CONSULPAYMENTURL+"/payment/consul",String.class);
    }
}

