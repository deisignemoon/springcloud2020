package com.atguigu.springcloudalibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloudalibaba.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:53
 */
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping("/account/decr")
    public CommonResult decrAccount(@RequestParam("userId") long userId, @RequestParam("money") BigDecimal money){
        accountService.decrAccount(userId,money);
        return new CommonResult(200,"扣减账户成功");
    }
}
