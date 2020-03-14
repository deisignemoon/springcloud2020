package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 20:09
 */

@Configuration
public class SetSelectRule {
    @Bean
    public IRule getRandomRule(){
        return new RandomRule();
    }
}
