package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
}
