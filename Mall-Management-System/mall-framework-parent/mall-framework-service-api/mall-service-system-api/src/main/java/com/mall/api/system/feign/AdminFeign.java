package com.mall.api.system.feign;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/****
 * @Author:X
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="system")
@RequestMapping("/admin")
public interface AdminFeign {

    /***
     * 根据ID查询User数据
     *
     * @param username
     * @return
     */
    @GetMapping("/load/{username}")
    Result<Admin> findAdminById(@PathVariable("username") String username);
}