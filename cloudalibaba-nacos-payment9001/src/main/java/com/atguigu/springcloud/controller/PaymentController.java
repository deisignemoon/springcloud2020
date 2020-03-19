package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 23:07
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${serverInfo.info}")
    private String serverInfo;

    @GetMapping("/payment/nacos/{id}")
    public String helloNacos(@PathVariable("id") long id){
        return serverInfo+"\t"+"server.port:"+serverPort;
    }
}
