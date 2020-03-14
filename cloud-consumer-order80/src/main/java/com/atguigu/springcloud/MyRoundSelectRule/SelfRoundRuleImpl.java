package com.atguigu.springcloud.MyRoundSelectRule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 20:33
 */
@Component
public class SelfRoundRuleImpl implements SelfRoundRule {

    private AtomicInteger count=new AtomicInteger(0);

    /**
    * 自旋cas递增
    *@param
    *@return
    **/
    private final int getAndIncr(AtomicInteger count){
        int current;
        int next;
        do {
            current=count.get();
            next=count.get()>=2100000000?0:current+1;
        }while (!count.compareAndSet(current,next));
        return next;
    }

    /**
    * 获得下一个server
    *@param
    *@return
    **/
    @Override
    public ServiceInstance getForceUri(List<ServiceInstance> instances) {
        int next = getAndIncr(count) % instances.size();
        return instances.get(next);
    }
}
