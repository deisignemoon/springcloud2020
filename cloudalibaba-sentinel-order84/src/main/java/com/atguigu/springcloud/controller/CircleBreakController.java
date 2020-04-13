package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

import com.atguigu.springcloud.service.Payment84Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/29 19:43
 */
@RestController
public class CircleBreakController {
    private static final String SERVERURL = "http://nacos-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    @GetMapping("/consumer/fallback/{id}")
    public CommonResult<Payment> fallback(@PathVariable long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVERURL + "/provider/fallback/" + id, CommonResult.class);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常。。。。");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该id没有对应的数据，空指针异常。。。");
        }
        return result;
    }
    public CommonResult<Payment> handlerFallback(@PathVariable long id) {
        return new CommonResult<Payment>(400,"fallback function",null);
    }
    public CommonResult<Payment> blockHandler(@PathVariable long id, BlockException e) {
        return new CommonResult<Payment>(444,"blockHandler function",null);
    }

    /**
     * 合并Feign使用Sentinel
     */
    @Resource
    private Payment84Service paymentService;
    @GetMapping("/consumer/getByID/{id}")
    public CommonResult<Payment> getByID(@PathVariable long id){
        CommonResult<Payment> result = paymentService.fallback(id);
        return result;
    }
}
