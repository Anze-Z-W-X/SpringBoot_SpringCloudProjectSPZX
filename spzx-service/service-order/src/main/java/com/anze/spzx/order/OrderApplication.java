package com.anze.spzx.order;

import com.anze.spzx.common.anno.EnableUserTokenFeignInterceptor;
import com.anze.spzx.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.anze.spzx.feign.cart",
        "com.anze.spzx.feign.user",
        "com.anze.spzx.feign.product"
})
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }

}