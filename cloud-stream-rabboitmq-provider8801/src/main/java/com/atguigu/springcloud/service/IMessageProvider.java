package com.atguigu.springcloud.service;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/17 18:16
 */

public interface IMessageProvider {
    /**
    * 发送信息到rabbitmq
    *@param
    *@return
    **/
    String send();
}
