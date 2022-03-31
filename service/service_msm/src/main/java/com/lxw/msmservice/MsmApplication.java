package com.lxw.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: lxw
 * @Date: 2022/3/23 21:56
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //表示不去加载数据库的信息
@ComponentScan("com.lxw")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
