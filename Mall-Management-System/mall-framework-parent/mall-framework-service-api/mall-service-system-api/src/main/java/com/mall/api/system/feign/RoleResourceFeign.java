package com.mall.api.system.feign;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.RoleResource;
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
@RequestMapping("/roleResource")
public interface RoleResourceFeign {

    /***
     * RoleResource分页条件搜索实现
     * @param roleResource
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) RoleResource roleResource, @PathVariable int page, @PathVariable int size);

    /***
     * RoleResource分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param roleResource
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<RoleResource>> findList(@RequestBody(required = false) RoleResource roleResource);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Integer id);

    /***
     * 修改RoleResource数据
     * @param roleResource
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody RoleResource roleResource, @PathVariable Integer id);

    /***
     * 新增RoleResource数据
     * @param roleResource
     * @return
     */
    @PostMapping
    Result add(@RequestBody RoleResource roleResource);

    /***
     * 根据ID查询RoleResource数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<RoleResource> findById(@PathVariable Integer id);

    /***
     * 查询RoleResource全部数据
     * @return
     */
    @GetMapping
    Result<List<RoleResource>> findAll();
}