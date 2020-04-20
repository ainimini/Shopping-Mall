package com.mall.service.acl.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author X
 * @version 1.0
 * @ClassName UserUpdateVo
 * @description 员工信息修改封装实体类
 * @date 2020/4/19
 **/
@Data
public class UserUpdateVo implements Serializable {

    @ApiModelProperty(value = "员工ID")
    private String id;

    @ApiModelProperty(value = "名称")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "员工照片")
    private String photo;

    @ApiModelProperty(value = "联系方式")
    private String phone;

}
