package com.mall.service.acl.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author X
 * @version 1.0
 * @ClassName PostQueryVo
 * @description 岗位条件查询实体类
 * @date 2020/4/20
 **/
@Data
public class PostQueryVo implements Serializable {

    @ApiModelProperty(value = "岗位编号")
    private String postNumber;

    @ApiModelProperty(value = "岗位名称")
    private String postName;
    /***
     * 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
     */
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

}
