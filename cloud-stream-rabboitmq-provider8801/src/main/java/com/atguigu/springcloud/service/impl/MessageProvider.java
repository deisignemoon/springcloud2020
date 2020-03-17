package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 18:20
 */

@EnableBinding(value = Source.class)
public class MessageProvider implements IMessageProvider {
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(msg).build());
        System.out.println("msg = " + msg);
        return msg;
    }
}
