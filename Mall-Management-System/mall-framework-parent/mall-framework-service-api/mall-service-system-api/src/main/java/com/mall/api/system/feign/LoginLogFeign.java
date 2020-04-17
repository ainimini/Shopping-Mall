package com.mall.api.system.feign;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.LoginLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:X
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="system")
@RequestMapping("/loginLog")
public interface LoginLogFeign {

    /***
     * LoginLog分页条件搜索实现
     * @param loginLog
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) LoginLog loginLog, @PathVariable int page, @PathVariable int size);

    /***
     * LoginLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param loginLog
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<LoginLog>> findList(@RequestBody(required = false) LoginLog loginLog);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Long id);

    /***
     * 修改LoginLog数据
     * @param loginLog
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody LoginLog loginLog, @PathVariable Long id);

    /***
     * 新增LoginLog数据
     * @param loginLog
     * @return
     */
    @PostMapping
    Result add(@RequestBody LoginLog loginLog);

    /***
     * 根据ID查询LoginLog数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<LoginLog> findById(@PathVariable Long id);

    /***
     * 查询LoginLog全部数据
     * @return
     */
    @GetMapping
    Result<List<LoginLog>> findAll();
}