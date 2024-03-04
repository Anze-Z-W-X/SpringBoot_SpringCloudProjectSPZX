package com.anze.spzx.manager;

import com.anze.spzx.common.log.annotation.EnableLogAspect;
import com.anze.spzx.manager.properties.MinioProperties;
import com.anze.spzx.manager.properties.UserAuthProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.anze.spzx"})
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
@EnableScheduling   //开启定时任务注解
@EnableLogAspect
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
