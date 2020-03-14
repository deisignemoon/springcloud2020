package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/13 2:57
 */

@Service
public class PaymentService {
    public String getPaymentOK() {
        return "sprinf cloud hystrix   " + Thread.currentThread().getName() + "   response is OK!!!!!";
    }

    @HystrixCommand(fallbackMethod = "getPaymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String getPaymentTimeout() {
        int time = 500;
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "spring cloud hystrix   " + Thread.currentThread().getName() + "   response is Timeout!!!!!";
    }

    public String getPaymentTimeoutHandler() {
        return "服务端8001   " + Thread.currentThread().getName() + "  运行出错或超时";
    }


    /**
     * HystrixCommand默认配置类：HystrixCommandProperties
     * 测试服务熔断
     * 服务熔断有三个状态，非熔断状态，熔断状态，半熔断状态
     * 当处于非熔断状态，服务调用成功，正常处理逻辑为主逻辑
     * 当非熔断状态下，熔断窗口时间内，请求数量达到或超过设置的阈值，且所有请求中错误发生比例达到设置的阈值，服务发生熔断（必须三个条件都满足，否则不发生熔断）
     * 熔断状态下，fallback逻辑取代正常逻辑成为主逻辑，在进入熔断状态之后的窗口时间内，所有请求都走fallback
     * 离开窗口时间后，进入半熔断状态，在该状态下，选择一个请求，如果是一个正常请求，则进入非熔断状态，进入非熔断状态，如果没有，则进入熔断状态，返回上一条
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "serviceCircuitBreakerPaymentFallback",commandProperties = {
            //开启服务熔断机制（默认为true）
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED,value = "true"),
            //服务熔断休眠窗口时间（默认为5000）
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "10000"),
            //服务熔断窗口时间内，请求数量阈值（默认为20）
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),
            //服务熔断窗口时间内，请求数量阈值达到，其中error请求达到阈值，则触发熔断（默认为50）
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "60")
    })
    public String serviceCircuitBreakerPayment(long id) {
        if (id < 0) {
            throw new RuntimeException();
        }
        return Thread.currentThread().getName()+"  CircuitBreaker Payment  " + IdUtil.simpleUUID() + " id:" + id;
    }

    public String serviceCircuitBreakerPaymentFallback(long id) {
        return Thread.currentThread().getName()+"  服务熔断了 " + "id:" + id;
    }
}
