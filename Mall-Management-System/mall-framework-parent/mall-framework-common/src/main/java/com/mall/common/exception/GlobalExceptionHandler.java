package com.mall.common.exception;

import com.mall.common.entity.Result;
import com.mall.common.entity.dto.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author X
 * @version 1.0
 * @ClassName GlobalExceptionHandler
 * @description 全局异常处理器
 * @date 2020/4/2
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /***
     * 全局异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("统一异常处理器->出错啦重启服务器！");
    }

    /***
     * 特定异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("特定异常处理器->出错啦重启服务器！");
    }

    /***
     * 自定义异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(CustomException e){
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}