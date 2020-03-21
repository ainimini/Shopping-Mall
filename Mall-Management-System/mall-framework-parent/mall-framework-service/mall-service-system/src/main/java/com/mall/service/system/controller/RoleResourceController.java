package com.mall.service.system.controller;

import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.RoleResource;
import com.mall.service.system.service.RoleResourceService;
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
@RequestMapping("/roleResource")
@CrossOrigin
public class RoleResourceController {

    @Autowired
    private RoleResourceService roleResourceService;

    /***
     * RoleResource分页条件搜索实现
     * @param roleResource
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) RoleResource roleResource, @PathVariable  int page, @PathVariable  int size){
        //调用RoleResourceService实现分页条件查询RoleResource
        PageInfo<RoleResource> pageInfo = roleResourceService.findPage(roleResource, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * RoleResource分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用RoleResourceService实现分页查询RoleResource
        PageInfo<RoleResource> pageInfo = roleResourceService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索RoleResource数据
     * @param roleResource
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<RoleResource>> findList(@RequestBody(required = false) RoleResource roleResource){
        //调用RoleResourceService实现条件查询RoleResource
        List<RoleResource> list = roleResourceService.findList(roleResource);
        return new Result<List<RoleResource>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除RoleResource数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用RoleResourceService实现根据主键删除
        roleResourceService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改RoleResource数据
     * @param roleResource
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody RoleResource roleResource, @PathVariable Integer id){
        //设置主键值
        roleResource.setRoleId(id);
        //调用RoleResourceService实现修改RoleResource
        roleResourceService.update(roleResource);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增RoleResource数据
     * @param roleResource
     * @return
     */
    @PostMapping
    public Result add(@RequestBody RoleResource roleResource){
        //调用RoleResourceService实现添加RoleResource
        roleResourceService.add(roleResource);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询RoleResource数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<RoleResource> findById(@PathVariable Integer id){
        //调用RoleResourceService实现根据主键查询RoleResource
        RoleResource roleResource = roleResourceService.findById(id);
        return new Result<RoleResource>(true,StatusCode.OK,"查询成功",roleResource);
    }

    /***
     * 查询RoleResource全部数据
     * @return
     */
    @GetMapping
    public Result<List<RoleResource>> findAll(){
        //调用RoleResourceService实现查询所有RoleResource
        List<RoleResource> list = roleResourceService.findAll();
        return new Result<List<RoleResource>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
