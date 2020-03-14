package springcloud.service;

import org.springframework.stereotype.Component;

/**
 *
 * 对feign的接口容错类,可以处理服务端掉线宕机时的fallback，但只能处理调用端异常
 * 这个可以和@HystrixCommand一起使用，用于处理自我端的异常
 * @author xiacong
 * @version 1.0
 * @date 2020/3/13 19:23
 */
@Component
public class PaymentFallbackService implements PaymentService{


    @Override
    public String getPaymentOK(long id) {
        return "这是实现了PaymentService接口的fallback，getPaymentOK";
    }

    @Override
    public String getPaymentTimeout(long id) {
        return "这是实现了PaymentService接口的fallback，getPaymentTimeout";
    }
}
