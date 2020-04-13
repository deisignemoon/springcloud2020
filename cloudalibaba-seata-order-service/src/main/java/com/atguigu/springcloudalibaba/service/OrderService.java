package com.atguigu.springcloudalibaba.service;

import com.atguigu.springcloudalibaba.dao.OrderDao;
import com.atguigu.springcloudalibaba.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:35
 */
public interface OrderService {
    void create(Order order);
    void updateStatus(@Param("id") long id,@Param("status") Integer status);
}
