package com.mall.service.acl.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author X
 * @version 1.0
 * @ClassName GoodsConfig
 * @description 商品服务模块启动类配置
 * @date 2020/4/16
 **/
@Configuration
@ComponentScan(basePackages = "com.mall")
@MapperScan("com.mall.service.acl.mapper")
public class AclConfig {
}
