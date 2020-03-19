package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/18 21:57
 */
@RestController
@Slf4j
@RefreshScope
public class ConfigInfoController {
    @Value("${config.info}")
    private String configInfo;
    @GetMapping("/nacos/config/info")
    public String getInfo()
    {
        log.info("客户端配置信息："+configInfo);
        return configInfo;
    }
}
