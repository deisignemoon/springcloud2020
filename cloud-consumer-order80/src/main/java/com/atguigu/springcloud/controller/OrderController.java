package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.MyRoundSelectRule.SelfRoundRule;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/9 23:14
 */

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

    @Resource
    private SelfRoundRule selfRoundRule;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 服务调用url
     */
    //写死
    //private static final String PAYMENT_URL="http://localhost:8001";
    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
    * 使用自己写的轮询算法实现LoadBalance
    *@param
    *@return
    **/
    @GetMapping("/payment/lb")
    public String paymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        URI uri = selfRoundRule.getForceUri(instances).getUri();
        //return restTemplate.getForEntity(PAYMENT_URL+"/payment/lb",String.class).getBody();
        return restTemplate.getForEntity(uri+"/payment/lb",String.class).getBody();
    }

    @GetMapping("/payment/zipkin")
    public String testZipkin(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin", String.class);
    }
}
