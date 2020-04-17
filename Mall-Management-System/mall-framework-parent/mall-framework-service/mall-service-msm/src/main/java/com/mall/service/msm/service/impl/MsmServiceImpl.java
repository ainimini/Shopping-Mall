package com.mall.service.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.mall.service.msm.service.MsmService;
import com.mall.service.msm.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author X
 * @version 1.0
 * @ClassName MsmServiceImpl
 * @description 短信服务模块service层
 * @date 2020/4/17
 **/
@Service
public class MsmServiceImpl implements MsmService {

    /***
     * 发送短信验证码
     * @param param
     * @param phone
     * @return
     */
    @Override
    public boolean send(HashMap<String, String> param,String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        DefaultProfile profile =
                DefaultProfile.getProfile("default", ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        /***
         * 设置参数 固定参数
         */
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        /***
         * 设置参数 发送相关的参数
         */
        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //申请阿里云的签名名称
        request.putQueryParameter("SignName", "我的头条资讯在线网络平台");
        //申请阿里云的模板code
        request.putQueryParameter("TemplateCode", "SMS_187740974");
        //验证码数据 传送需要JSON数据传递
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
