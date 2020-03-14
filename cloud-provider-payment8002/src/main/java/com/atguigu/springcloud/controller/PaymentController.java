package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/9 20:46
 */

@RestController
@Slf4j//日志
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        try {
            int res = paymentService.create(payment);
            log.info("插入结果：" + res);
            if (res > 0) {
                return new CommonResult(200, "success", res);
            } else {
                return new CommonResult(404, "fail", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(404, "创建失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        try {
            Payment res = paymentService.getPaymentById(id);
            log.info("查询结果" + res);
            if (res != null) {
                return new CommonResult(200, "查询成功，server.port:"+serverPort, res);
            } else {
                return new CommonResult(404, "该Paymnet未找到,ID:" + id, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(404, "查询失败", null);
        }

    }
    @GetMapping("/payment/lb")
    public String paymentLB(){
        return serverPort;
    }

    /**
     * 测试feign超时配置
     *@param
     *@return
     **/
    @GetMapping("/payment/feign/timeout")
    public String paymentTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e){ e.printStackTrace(); }
        return serverPort;
    }
}
