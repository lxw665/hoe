package com.lxw.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author: lxw
 * @Date: 2022/3/23 12:04
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.lxw"})
@MapperScan("com.lxw.cmsservice.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
