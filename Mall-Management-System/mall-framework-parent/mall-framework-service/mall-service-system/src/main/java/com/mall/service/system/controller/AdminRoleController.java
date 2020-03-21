package com.mall.service.system.controller;

import com.github.pagehelper.PageInfo;
import com.mall.service.system.service.AdminRoleService;
import com.mall.api.system.pojo.AdminRole;
import com.mall.common.entity.Result;
import com.mall.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:X
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/adminRole")
@CrossOrigin
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    /***
     * AdminRole分页条件搜索实现
     * @param adminRole
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  AdminRole adminRole, @PathVariable  int page, @PathVariable  int size){
        //调用AdminRoleService实现分页条件查询AdminRole
        PageInfo<AdminRole> pageInfo = adminRoleService.findPage(adminRole, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * AdminRole分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AdminRoleService实现分页查询AdminRole
        PageInfo<AdminRole> pageInfo = adminRoleService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索AdminRole数据
     * @param adminRole
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<AdminRole>> findList(@RequestBody(required = false)  AdminRole adminRole){
        //调用AdminRoleService实现条件查询AdminRole
        List<AdminRole> list = adminRoleService.findList(adminRole);
        return new Result<List<AdminRole>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除AdminRole数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用AdminRoleService实现根据主键删除
        adminRoleService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改AdminRole数据
     * @param adminRole
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  AdminRole adminRole,@PathVariable Long id){
        //设置主键值
        adminRole.setId(id);
        //调用AdminRoleService实现修改AdminRole
        adminRoleService.update(adminRole);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增AdminRole数据
     * @param adminRole
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   AdminRole adminRole){
        //调用AdminRoleService实现添加AdminRole
        adminRoleService.add(adminRole);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询AdminRole数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<AdminRole> findById(@PathVariable Long id){
        //调用AdminRoleService实现根据主键查询AdminRole
        AdminRole adminRole = adminRoleService.findById(id);
        return new Result<AdminRole>(true,StatusCode.OK,"查询成功",adminRole);
    }

    /***
     * 查询AdminRole全部数据
     * @return
     */
    @GetMapping
    public Result<List<AdminRole>> findAll(){
        //调用AdminRoleService实现查询所有AdminRole
        List<AdminRole> list = adminRoleService.findAll();
        return new Result<List<AdminRole>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
