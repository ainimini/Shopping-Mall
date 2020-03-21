package com.mall.api.system.feign;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Admin;
import com.mall.common.entity.CommonResult;
import com.mall.common.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:X
 * @Description:
 * @Date 2019/6/18 13:58
 *****/
@FeignClient(name="system")
@RequestMapping("/admin")
public interface AdminFeign {

    @RequestMapping(value = "/findAdmin", method = RequestMethod.GET)
    CommonResult<Admin> findAdminByUsername(String username);

    /***
     * Admin分页条件搜索实现
     * @param admin
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Admin admin, @PathVariable int page, @PathVariable int size);

    /***
     * Admin分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param admin
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Admin>> findList(@RequestBody(required = false) Admin admin);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Long id);

    /***
     * 修改Admin数据
     * @param admin
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Admin admin, @PathVariable Long id);

    /***
     * 新增Admin数据
     * @param admin
     * @return
     */
    @PostMapping
    Result add(@RequestBody Admin admin);

    /***
     * 根据ID查询Admin数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Admin> findById(@PathVariable Long id);

    /***
     * 查询Admin全部数据
     * @return
     */
    @GetMapping
    Result<List<Admin>> findAll();
}