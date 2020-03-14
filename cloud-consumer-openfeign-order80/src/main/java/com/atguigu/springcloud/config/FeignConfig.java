package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign日志增强配置
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 22:32
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level getloggerLevel(){
        return Logger.Level.FULL;
    }
}
