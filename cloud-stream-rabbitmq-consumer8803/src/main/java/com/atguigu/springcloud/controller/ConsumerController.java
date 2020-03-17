package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 18:54
 */

@Component
@EnableBinding(Sink.class)
public class ConsumerController {
    @Value("${server.port}")
    private String serverPort;
    @StreamListener(Sink.INPUT)
    public void recover(Message<String> message){
        System.out.println("消费者2号，得到消息："+message.getPayload()+"  port:"+serverPort);
    }

}
