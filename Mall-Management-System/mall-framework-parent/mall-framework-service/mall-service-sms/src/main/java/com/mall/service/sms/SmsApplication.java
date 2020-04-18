package com.mall.service.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author X
 * @version 1.0
 * @ClassName SmsApplication
 * @description 短信服务模块
 * @date 2020/4/17
 **/
//取消数据源自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
