package springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 21:32
 */

@RestController
@Slf4j
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "globalHandler")
public class OrderFeignController {

    @Resource
    private PaymentService paymentService;

    /**
     * 可以在需要容错的类上添加@DefaultProperties注解，添加默认的容错处理方法，再在需要默认容错的方法上添加@HystrixCommand
     * 即可使用类全局服务降级处理
     * 注意： 一对一的HystrixCommand对应的fallback需要和本方法相同的形参，而 defaultFallback全局容错方法则不可以有形参
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "getPaymentTimeoutHandler", commandProperties = {
//           @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000")
//    })
    //@HystrixCommand
    @GetMapping("/payment/ok/{id}")
    public String getPaymentOK(@PathVariable("id") long id) {
       // int i = 10 / 0;
        return paymentService.getPaymentOK(id);
    }


//    @HystrixCommand(fallbackMethod = "getPaymentTimeoutHandler", commandProperties = {
//            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000")
//    })
    @GetMapping("/payment/timeout/{id}")
    public String paymentTimeout(@PathVariable("id") long id) {
        return paymentService.getPaymentTimeout(id);
    }


    public String getPaymentTimeoutHandler(@PathVariable("id") long id) {
        return "客户端80   " + Thread.currentThread().getName() + "  运行出错或超时";
    }

    public String globalHandler(){
        return "this id global fallback method";
    }
}