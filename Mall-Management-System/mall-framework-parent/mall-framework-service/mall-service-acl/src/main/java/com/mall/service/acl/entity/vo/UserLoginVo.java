package com.mall.service.acl.entity.vo;

import com.mall.service.acl.entity.pojo.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author X
 * @version 1.0
 * @ClassName UserLoginVo
 * @description 员工注册验证封装
 * @date 2020/4/17
 **/
@Data
public class UserLoginVo extends User {

    @ApiModelProperty(value = "验证码")
    private String code;
}
