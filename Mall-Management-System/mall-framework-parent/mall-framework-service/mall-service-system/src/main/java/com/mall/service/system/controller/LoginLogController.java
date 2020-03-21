package com.mall.service.system.controller;

import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.LoginLog;
import com.mall.service.system.service.LoginLogService;
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
@RequestMapping("/loginLog")
@CrossOrigin
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    /***
     * LoginLog分页条件搜索实现
     * @param loginLog
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) LoginLog loginLog, @PathVariable  int page, @PathVariable  int size){
        //调用LoginLogService实现分页条件查询LoginLog
        PageInfo<LoginLog> pageInfo = loginLogService.findPage(loginLog, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * LoginLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用LoginLogService实现分页查询LoginLog
        PageInfo<LoginLog> pageInfo = loginLogService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索LoginLog数据
     * @param loginLog
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<LoginLog>> findList(@RequestBody(required = false) LoginLog loginLog){
        //调用LoginLogService实现条件查询LoginLog
        List<LoginLog> list = loginLogService.findList(loginLog);
        return new Result<List<LoginLog>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除LoginLog数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用LoginLogService实现根据主键删除
        loginLogService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改LoginLog数据
     * @param loginLog
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody LoginLog loginLog, @PathVariable Long id){
        //设置主键值
        loginLog.setId(id);
        //调用LoginLogService实现修改LoginLog
        loginLogService.update(loginLog);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增LoginLog数据
     * @param loginLog
     * @return
     */
    @PostMapping
    public Result add(@RequestBody LoginLog loginLog){
        //调用LoginLogService实现添加LoginLog
        loginLogService.add(loginLog);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询LoginLog数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<LoginLog> findById(@PathVariable Long id){
        //调用LoginLogService实现根据主键查询LoginLog
        LoginLog loginLog = loginLogService.findById(id);
        return new Result<LoginLog>(true,StatusCode.OK,"查询成功",loginLog);
    }

    /***
     * 查询LoginLog全部数据
     * @return
     */
    @GetMapping
    public Result<List<LoginLog>> findAll(){
        //调用LoginLogService实现查询所有LoginLog
        List<LoginLog> list = loginLogService.findAll();
        return new Result<List<LoginLog>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
