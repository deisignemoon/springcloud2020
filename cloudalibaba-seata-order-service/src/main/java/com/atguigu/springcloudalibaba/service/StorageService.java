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
@FeignClient(value = "seata-storage-service")
public interface StorageService {
    @PostMapping("/storage/decr")
    CommonResult decrStorage(@RequestParam("productId") long productId, @RequestParam("count") Integer count);
}
