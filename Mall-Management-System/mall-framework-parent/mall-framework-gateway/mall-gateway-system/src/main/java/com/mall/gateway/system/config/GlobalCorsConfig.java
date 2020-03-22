package com.mall.gateway.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author X
 * @version 1.0
 * @ClassName GlobalCorsConfig
 * @description 全局跨域配置
 * @date 2020/3/22
 **/
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {

        /***
         * cors跨域配置对象
         */
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许所有域名进行跨域调用 允许的域,不要写*，否则cookie就无法使用了
        corsConfiguration.addAllowedOrigin("http://localhost:8090");
        //允许跨越发送cookie
        corsConfiguration.setAllowCredentials(true);
        //放行全部原始头信息
        corsConfiguration.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        corsConfiguration.addAllowedMethod("*");

        /***
         * 配置源对象
         *
         */
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        //添加映射路径，拦截一切请求
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);

        /***
         * cors过滤器对象
         */
        return new CorsWebFilter(configurationSource);
    }
}
