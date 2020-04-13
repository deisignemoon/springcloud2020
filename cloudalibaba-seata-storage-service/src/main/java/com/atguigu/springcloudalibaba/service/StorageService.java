package com.atguigu.springcloudalibaba.service;

import com.atguigu.springcloud.entities.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 19:36
 */

public interface StorageService {
    void decrStorage(@Param("productId") long productId,@Param("count") Integer count);
}
