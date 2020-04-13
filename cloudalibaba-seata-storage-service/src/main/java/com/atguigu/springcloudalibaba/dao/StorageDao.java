package com.atguigu.springcloudalibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:20
 */
@Mapper
public interface StorageDao {
    void decrStorage(@Param("productId") long productId,@Param("count") Integer count);
}
