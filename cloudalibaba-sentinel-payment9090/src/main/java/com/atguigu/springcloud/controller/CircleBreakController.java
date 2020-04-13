package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/29 19:43
 */
@RestController
public class CircleBreakController {
    @Value("${server.port}")
    private String serverPort;

    private static HashMap<Long,Payment> map =new HashMap<>();

    static {
        map.put(1L,new Payment(1L,"11111111111111"));
        map.put(2L,new Payment(2L,"22222222222222"));
        map.put(3L,new Payment(3L,"33333333333333"));
    }

    @GetMapping("/provider/fallback/{id}")
    public CommonResult<Payment> fallback(@PathVariable long id){
        Payment payment = map.get(id);
        return new CommonResult<Payment>(200,"from mysql serverPort: "+serverPort,payment);
    }
}
