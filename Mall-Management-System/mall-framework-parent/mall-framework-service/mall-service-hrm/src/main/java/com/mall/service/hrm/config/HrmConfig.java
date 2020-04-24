package com.mall.service.hrm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author X
 * @version 1.0
 * @ClassName HrmConfig
 * @description 人事管理服务模块配置类
 * @date 2020/4/21
 **/
@Configuration
@ComponentScan(basePackages = "com.mall")
@MapperScan("com.mall.service.hrm.mapper")
public class HrmConfig {
}
