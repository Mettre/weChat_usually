package com.mettre.usually;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient   //同意注册到注册中心
@EnableFeignClients   //注解用来开启Feign功能
@MapperScan(basePackages = {"com.mettre.usually.mapper"})
public class UsuallyApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuallyApplication.class, args);
    }
}
