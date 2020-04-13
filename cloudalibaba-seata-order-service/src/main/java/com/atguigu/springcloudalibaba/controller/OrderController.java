package com.atguigu.springcloudalibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloudalibaba.entities.Order;
import com.atguigu.springcloudalibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:55
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/creat")
    public CommonResult createOrder(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建完成");
    }
}
