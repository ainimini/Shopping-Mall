package com.mall.common.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author X
 * @version 1.0
 * @ClassName CustomException
 * @description 自定义异常实体类
 * @date 2020/4/3
 **/
@Data
/***
 * 生成有参构造函数
 */
@AllArgsConstructor
/***
 * 生成无参构造函数
 */
@NoArgsConstructor
public class CustomException extends RuntimeException{

    private Integer code;
    private String msg;
}
