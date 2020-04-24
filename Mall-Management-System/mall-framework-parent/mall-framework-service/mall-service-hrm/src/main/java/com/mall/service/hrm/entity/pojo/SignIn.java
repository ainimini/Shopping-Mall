package com.mall.service.hrm.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author X
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hrm_sign_in")
@ApiModel(value = "SignIn对象", description = "")
public class SignIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "员工id")
    private String userId;

    @ApiModelProperty(value = "员工姓名")
    private String username;

    @ApiModelProperty(value = "是否迟到 1(true)迟到 0(false)没迟到")
    private Boolean isLast;

    @ApiModelProperty(value = "是否早退 1(true)早退 0(false)没有早退")
    private Boolean isLeaveEarly;

    @ApiModelProperty(value = "是否请假 1(true)请假 0(false)没有请假")
    private Boolean isLeave;

    @ApiModelProperty(value = "请假类型")
    private String leaveType;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否出差 1(true)出差 0(false)没有出差")
    private Boolean isTravel;

    @ApiModelProperty(value = "出差类型")
    private String travelType;

    @ApiModelProperty(value = "是否加班 1(true)加班 0(false)没有加班")
    private Boolean isOvertime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
