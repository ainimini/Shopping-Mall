package com.mall.service.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author X
 * @version 1.0
 * @ClassName ConstantPropertiesUtils
 * @description 短信服务模块常量读取类
 * @date 2020/4/17
 **/
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    /***
     * 读取配置文件内容
     */
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
