package com.atguigu.springcloudalibaba.service.impl;

import com.atguigu.springcloudalibaba.dao.OrderDao;
import com.atguigu.springcloudalibaba.entities.Order;
import com.atguigu.springcloudalibaba.service.AccountService;
import com.atguigu.springcloudalibaba.service.OrderService;
import com.atguigu.springcloudalibaba.service.StorageService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:39
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @GlobalTransactional( name = "fsp_tx_group" , rollbackFor = Exception.class)
    @Override
    public void create(Order order) {

        //开启全局事务
        log.info("开启全局事务");

        //创建订单
        log.info("开始创建订单表");
        orderDao.create(order);
        log.info("创建订单表完成");

        //扣减库存
        log.info("开始扣减库存");
        storageService.decrStorage(order.getProductId(),order.getCount());
        log.info("扣减库存完成");

        //扣减余额
        log.info("开始扣减账户");
        accountService.decrAccount(order.getUserId(),order.getMoney());
        log.info("扣减账户完成");
        System.out.println(order);
        //修改订单状态
        log.info("开始修改订单状态为已支付");
        orderDao.updateStatus(order.getId(),order.getStatus());
        log.info("修改订单状态为已支付完成");

        //全局事务完成
        log.info("完成全局事务");

    }

    @Override
    public void updateStatus(long id, Integer status) {
        orderDao.updateStatus(id,status);
    }
}
