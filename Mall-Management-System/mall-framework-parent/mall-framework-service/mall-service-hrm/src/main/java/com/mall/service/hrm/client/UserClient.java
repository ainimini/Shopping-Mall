package com.mall.service.hrm.client;

import com.mall.common.entity.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author X
 * @version 1.0
 * @ClassName UserClient
 * @description 员工的远程feign调用接口
 * @date 2020/4/21
 **/
@Component
@FeignClient("service-acl")
@RequestMapping("/acl/user")
public interface UserClient {

    /***
     * 根据id查询该员工数据
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询该员工数据")
    @GetMapping("/findOne/{id}")
    public Result findOne(@PathVariable(value = "id") String id);
}
