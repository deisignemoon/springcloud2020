package com.atguigu.springcloud.MyRoundSelectRule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 20:28
 */

public interface SelfRoundRule {

    ServiceInstance getForceUri(List<ServiceInstance> instances);

}
