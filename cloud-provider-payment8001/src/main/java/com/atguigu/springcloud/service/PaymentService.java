package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/9 20:40
 */

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(@Param("id")long id);
}
