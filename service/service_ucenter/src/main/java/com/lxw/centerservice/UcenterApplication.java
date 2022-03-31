package com.lxw.centerservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.rmi.activation.ActivationGroup_Stub;

/**
 * @Author: lxw
 * @Date: 2022/3/24 14:49
 * @Description:
 */

@ComponentScan({"com.lxw"})
@SpringBootApplication
@MapperScan("com.lxw.centerservice.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
