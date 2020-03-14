package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiacong
 * @version 1.0
 * @date 2020/3/11 21:28
 */

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
public class OrderFeignHystrixMain80 {
    public static void main(String[] args){
        SpringApplication.run(OrderFeignHystrixMain80.class,args);
    }
}
