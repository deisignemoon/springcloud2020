package com.atguigu.springcloudalibaba.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:36
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping("/account/decr")
    CommonResult decrAccount(@RequestParam("userId") long userId, @RequestParam("money") BigDecimal money);
}
