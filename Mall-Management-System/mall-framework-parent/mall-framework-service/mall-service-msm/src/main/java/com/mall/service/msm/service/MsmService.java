package com.mall.service.msm.service;

import java.util.HashMap;

/**
 * @author X
 * @version 1.0
 * @ClassName MsmService
 * @description 短信服务模块server层接口
 * @date 2020/4/17
 **/
public interface MsmService {

    /***
     * 发送短信验证码
     * @param param
     * @param phone
     * @return
     */
    boolean send(HashMap<String, String> param, String phone);
}
