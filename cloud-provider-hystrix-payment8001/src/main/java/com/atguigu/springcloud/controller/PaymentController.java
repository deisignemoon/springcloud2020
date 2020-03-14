package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/13 2:56
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/ok/{id}")
    public String getPaymentOK(@PathVariable("id")long id){
        return paymentService.getPaymentOK()+" id="+id;
    }

    @GetMapping("/payment/timrout/{id}")
    public String getPaymentTimeout(@PathVariable("id")long id){
        return paymentService.getPaymentTimeout()+" id="+id;
    }

    @GetMapping("/payment/circurt/{id}")
    public String getPaymentCircurtBreak(@PathVariable("id")long id){
        return paymentService.serviceCircuitBreakerPayment(id);
    }
}
