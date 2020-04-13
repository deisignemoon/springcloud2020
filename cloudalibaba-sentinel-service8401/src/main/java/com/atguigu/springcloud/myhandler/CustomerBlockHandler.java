package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/29 19:09
 */

public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException e){
        return new CommonResult(444,"自定义限流全局公共处理1限流测试OK",null);
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"自定义限流全局公共处理限2流测试OK",null);
    }
}
