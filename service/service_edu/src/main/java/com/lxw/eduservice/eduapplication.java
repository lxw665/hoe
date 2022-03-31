package com.lxw.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lxw
 * @Date: 2022/2/17 14:50
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient //nacos注解
@EnableFeignClients
@ComponentScan(basePackages = {"com.lxw"})
public class eduapplication {
    public static void main(String[] args) {
        SpringApplication.run(eduapplication.class, args);
    }
}
