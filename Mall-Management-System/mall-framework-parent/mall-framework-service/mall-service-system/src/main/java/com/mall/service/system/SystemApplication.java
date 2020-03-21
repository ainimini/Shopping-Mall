package com.mall.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author X
 * @version 1.0
 * @ClassName SystemApplication
 * @description 后台系统启动类
 * @date 2020/3/20
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableDiscoveryClient
/**
 * 开启通用mapper的包扫描
 */
@MapperScan(basePackages = {"com.mall.service.system.dao"})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
