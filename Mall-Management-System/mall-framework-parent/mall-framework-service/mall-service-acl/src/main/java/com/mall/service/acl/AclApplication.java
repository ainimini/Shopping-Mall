package com.mall.service.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author X
 * @version 1.0
 * @ClassName AclApplication
 * @description
 * @date 2020/4/17
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class);
    }
}
