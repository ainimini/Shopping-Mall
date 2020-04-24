package com.mall.service.hrm.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author X
 * @version 1.0
 * @ClassName TravelTypeRequestVo
 * @description 出差请求的实体类封装
 * @date 2020/4/24
 **/
@Data
public class TravelTypeRequestVo implements Serializable {
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "出差类别")
    private String travelType;

    @ApiModelProperty(value = "出差类别")
    private String description;
}
