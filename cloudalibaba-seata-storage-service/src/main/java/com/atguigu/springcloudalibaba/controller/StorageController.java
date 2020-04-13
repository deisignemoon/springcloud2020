package com.atguigu.springcloudalibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloudalibaba.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:41
 */
@RestController
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping("/storage/decr")
    public CommonResult decrStorage(@RequestParam("productId") long productId
            , @RequestParam("count") Integer count){
        storageService.decrStorage(productId,count);
        return new CommonResult(200,"扣减库存成功");
    }
}
