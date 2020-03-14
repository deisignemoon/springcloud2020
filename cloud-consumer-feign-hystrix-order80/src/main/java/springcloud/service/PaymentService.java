package springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 伪装成RPC调用模式，实际还是HttpClient，@FeignClient可以用于服务调用，也可以容错
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 21:29
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping("/payment/ok/{id}")
    String getPaymentOK(@PathVariable("id")long id);



    @GetMapping("/payment/timrout/{id}")
    String getPaymentTimeout(@PathVariable("id")long id);



}