package com.mall.service.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author X
 * @version 1.0
 * @ClassName HrmApplication
 * @description 人事管理服务模块启动类
 * @date 2020/4/21
 **/
@SpringBootApplication
@EnableFeignClients
public class HrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmApplication.class);
    }
}
