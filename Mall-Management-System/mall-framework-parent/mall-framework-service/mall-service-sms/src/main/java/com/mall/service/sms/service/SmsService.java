package com.mall.service.sms.service;

import java.util.HashMap;

/**
 * @author X
 * @version 1.0
 * @ClassName SmsService
 * @description 短信服务模块server层接口
 * @date 2020/4/17
 **/
public interface SmsService {

    /***
     * 发送短信验证码
     * @param param
     * @param phone
     * @return
     */
    boolean send(HashMap<String, String> param, String phone);
}
