package com.atguigu.springcloudalibaba.service;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:36
 */

public interface AccountService {
    void decrAccount(@Param("userId") long userId,@Param("money") BigDecimal money);
}
