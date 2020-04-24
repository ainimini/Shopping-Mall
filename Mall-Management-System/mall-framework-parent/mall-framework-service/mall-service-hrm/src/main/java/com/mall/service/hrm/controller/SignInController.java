package com.mall.service.hrm.controller;


import com.alibaba.fastjson.JSON;
import com.mall.common.entity.Result;
import com.mall.common.entity.dto.CustomException;
import com.mall.common.util.JwtUtil;
import com.mall.service.hrm.client.UserClient;
import com.mall.service.hrm.entity.pojo.SignIn;
import com.mall.service.hrm.entity.pojo.TravelType;
import com.mall.service.hrm.entity.vo.TravelTypeRequestVo;
import com.mall.service.hrm.service.SignInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author X
 * @since 2020-04-21
 */
@Api(description = "HRM人事管理 签到")
@RestController
@RequestMapping("/hrm/sign-in")
@CrossOrigin
public class SignInController {

    @Autowired
    private SignInService signInService;

    /***
     * 员工签到
     * @param request
     * @return
     */
    @PostMapping("/signIn")
    @ApiOperation("员工签到")
    public Result signIn(HttpServletRequest request) {
        boolean flag = signInService.signIn(request);
        if (!flag) {
            return Result.ok().message("希望你度过美好的一天！！！");
        }
        return Result.error().message("你已迟到");
    }

    /***
     * 员工打卡下班
     * @param request
     * @return
     */
    @PostMapping("/offWork")
    @ApiOperation("员工打卡下班")
    public Result offWork(HttpServletRequest request) {
        boolean flag = signInService.offWork(request);
        if (!flag) {
            return Result.ok().message("真是美好的一天！！！");
        }
        return Result.error().message("你早退啦");
    }

    /***
     * 员工出差
     * @param travelTypeRequestVo
     * @param request
     * @return
     */
    @PostMapping("/travel")
    @ApiOperation("员工出差")
    public Result travel(TravelTypeRequestVo travelTypeRequestVo, HttpServletRequest request) {
        boolean flag = signInService.travel(travelTypeRequestVo,request);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }
}

