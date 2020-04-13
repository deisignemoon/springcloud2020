package com.atguigu.springcloudalibaba.service.impl;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloudalibaba.dao.StorageDao;
import com.atguigu.springcloudalibaba.service.StorageService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/4/11 20:39
 */
@Service
public class StorageServiceImpl implements StorageService
{
    @Resource
    private StorageDao storageDao;

    @Override
    public void decrStorage(@Param("productId") long productId, @Param("count") Integer count) {
        storageDao.decrStorage(productId,count);
    }
}
