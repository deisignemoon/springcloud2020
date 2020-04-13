package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/19 19:39
 */

@RestController
public class FlowLimitController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/testA")
    public String testA() {
        return "++++++testA";
    }

    @GetMapping("/testB")
    public String testB() {
        String str = restTemplate.getForObject("http://127.0.0.1:8401/testA", String.class);
        return "++++++testB " + str;
    }

    @GetMapping("/testC")
    public String testC() {
        String str = restTemplate.getForObject("http://127.0.0.1:8401/testA", String.class);
        return "++++++testC " + str;
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "++++++testD ";
    }

    @GetMapping("/testE")
    public String testE() {
        int i=10/0;
        return "++++++testE ";
    }

    /**
    * 测试热点限流
     * 配置SentinelResource，sentinel原本的限流处理方法将失效，必须自己配置
     * blockHandler只管限流规则，fallBack只管熔断规则
    *@param
    *@return
    **/
    @GetMapping("/testKey")
    @SentinelResource(value = "testKey",blockHandler = "deal_testKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "++++++testHtoKey ";
    }
    /**
    * 热点限流处理方法
    *@param
    *@return
    **/
    public String deal_testKey(String p1, String p2, BlockException e){
        return "deal_testKey,TOT"+p1+p2;
    }
}
