package com.mall.gateway.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author X
 * @version 1.0
 * @ClassName SystemGatewayApplication
 * @description 后台系统网关启动类
 * @date 2020/3/22
 **/
@SpringBootApplication
@EnableEurekaClient
public class SystemGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemGatewayApplication.class,args);
    }

    /**
     * 创建用户唯一标识 使用IP作为用户以表示 根据IP进行限流操作
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return new KeyResolver(){
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                Mono<String> ip = Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
                System.out.println("用户唯一标识IP"+ip);
                return ip;
            }
        };
    }
}
