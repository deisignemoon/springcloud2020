package com.atguigu.springcloudalibaba.dao;

import com.atguigu.springcloudalibaba.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:05
 */
@Mapper
public interface OrderDao {
    /**
     * 创建订单，状态为0
     * @param order
     */
    void create(Order order);

    /**
     * 完成事务后更新订单状态为1
     * @param id
     * @param status
     */
    void updateStatus(@Param("id") long id,@Param("status") Integer status);
}
