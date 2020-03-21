package com.mall.api.system.feign;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Resource;
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
@RequestMapping("/resource")
public interface ResourceFeign {

    /***
     * Resource分页条件搜索实现
     * @param resource
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) Resource resource, @PathVariable int page, @PathVariable int size);

    /***
     * Resource分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable int page, @PathVariable int size);

    /***
     * 多条件搜索品牌数据
     * @param resource
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<Resource>> findList(@RequestBody(required = false) Resource resource);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Long id);

    /***
     * 修改Resource数据
     * @param resource
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody Resource resource, @PathVariable Long id);

    /***
     * 新增Resource数据
     * @param resource
     * @return
     */
    @PostMapping
    Result add(@RequestBody Resource resource);

    /***
     * 根据ID查询Resource数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Resource> findById(@PathVariable Long id);

    /***
     * 查询Resource全部数据
     * @return
     */
    @GetMapping
    Result<List<Resource>> findAll();
}