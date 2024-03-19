package com.anze.spzx.pay;

import com.anze.spzx.common.anno.EnableUserWebMvcConfiguration;
import com.anze.spzx.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {
        "com.anze.spzx.feign.order",
        "com.anze.spzx.feign.product"
})
@EnableConfigurationProperties(value = { AlipayProperties.class })
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }

}
