package com.mall.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author X
 * @version 1.0
 * @ClassName EuerkaApplication
 * @description 注册中心启动类
 * @date 2020/3/22
 **/
@SpringBootApplication
@EnableEurekaServer
public class EuerkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuerkaApplication.class);
    }
}
