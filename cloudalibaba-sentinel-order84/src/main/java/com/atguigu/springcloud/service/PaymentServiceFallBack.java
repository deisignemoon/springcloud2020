package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/29 21:06
 */
@Component
public class PaymentServiceFallBack implements Payment84Service {
    @Override
    public CommonResult<Payment> fallback(long id) {
        return new CommonResult<Payment>(444,"Feign的fallback方法",new Payment(id,null));
    }
}
