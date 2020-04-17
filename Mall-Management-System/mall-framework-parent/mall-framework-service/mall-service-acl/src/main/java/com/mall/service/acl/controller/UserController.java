package com.mall.service.acl.controller;


import com.mall.common.entity.Result;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.vo.UserLoginVo;
import com.mall.service.acl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author X
 * @since 2020-04-17
 */
@Api(description = "用户")
@RestController
@RequestMapping("/acl/user")
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * 员工注册
     * @param userLoginVo
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("员工注册")
    public Result register(@RequestBody UserLoginVo userLoginVo){
        userService.register(userLoginVo);
        return Result.ok().message("恭喜你注册成功，成为我们的一员！");
    }

}

