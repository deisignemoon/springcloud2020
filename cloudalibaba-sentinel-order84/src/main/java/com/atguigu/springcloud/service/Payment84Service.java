package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/29 21:03
 */

@FeignClient(value = "nacos-provider-payment",fallback = PaymentServiceFallBack.class)
public interface Payment84Service {
    @GetMapping("/provider/fallback/{id}")
     CommonResult<Payment> fallback(@PathVariable(value = "id") long id);
}
