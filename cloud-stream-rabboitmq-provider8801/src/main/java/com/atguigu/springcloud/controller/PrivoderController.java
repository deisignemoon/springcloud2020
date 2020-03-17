package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 18:25
 */
@RestController
public class PrivoderController {
    @Resource
    private IMessageProvider provider;
    @GetMapping("/sendMessage")
    public String sendMessage(){
        String send = provider.send();
        return send;
    }
}
