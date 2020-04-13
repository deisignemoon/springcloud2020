package com.atguigu.springcloudalibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 21:02
 */
@Mapper
public interface AccountDao {
    void decrAccount(@Param("userId") long userId,@Param("money") BigDecimal money);
}
