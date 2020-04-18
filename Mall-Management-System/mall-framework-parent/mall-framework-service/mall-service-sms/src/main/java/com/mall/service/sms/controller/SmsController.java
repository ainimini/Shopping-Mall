package com.mall.service.sms.controller;

import com.mall.common.entity.Result;
import com.mall.common.util.RandomUtil;
import com.mall.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author X
 * @version 1.0
 * @ClassName SmsController
 * @description 短信服务模块控制层
 * @date 2020/4/17
 **/
@Api(description = "SMS")
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private SmsService smsService;

    /***
     * 发送短信验证码
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送短信验证码")
    @GetMapping("/send/{phone}")
    public Result sendMsm(@PathVariable(value = "phone") String phone) {
        //判断Redis中该手机号是否已经获取验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok().message("短信已发送到你的手机上，请及时查看");
        }
        //随机生成6为数的验证码
        String sixBitRandom = RandomUtil.getSixBitRandom();
        HashMap<String, String> param = new HashMap<>();
        param.put("code", sixBitRandom);
        boolean isSend = smsService.send(param,phone);
        if (isSend) {
            //设置Redis中的有效期
            redisTemplate.opsForValue().set(phone, sixBitRandom, 5, TimeUnit.MINUTES);
            return Result.ok();
        }
        return Result.error().message("短信发送失败");
    }
}
