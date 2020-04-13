package com.atguigu.springcloudalibaba.service.impl;

import com.atguigu.springcloudalibaba.dao.AccountDao;
import com.atguigu.springcloudalibaba.service.AccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:58
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrAccount(@Param("userId") long userId, @Param("money") BigDecimal money) {
        //支付发生错误
        //int i=10/0;
        accountDao.decrAccount(userId,money);
    }
}
