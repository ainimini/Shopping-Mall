package com.mall.service.hrm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.entity.Result;
import com.mall.service.hrm.entity.pojo.LeaveType;
import com.mall.service.hrm.entity.pojo.TravelType;
import com.mall.service.hrm.service.LeaveTypeService;
import com.mall.service.hrm.service.TravelTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author X
 * @since 2020-04-22
 */
@Api(description = "HRM人事管理 出差")
@RestController
@RequestMapping("/hrm/travel-type")
@CrossOrigin
public class TravelTypeController {

    @Autowired
    private TravelTypeService travelTypeService;

    /***
     * 添加出差种类
     * @param travelType
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加出差种类")
    public Result add(@RequestBody TravelType travelType) {
        boolean flag = travelTypeService.save(travelType);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id删除出差种类
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("根据id删除出差种类")
    public Result delete(@PathVariable(value = "id") String id) {
        boolean flag = travelTypeService.removeById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id列表删除出差种类
     * @param idList
     * @return
     */
    @ApiOperation(value = "根据id列表删除出差种类")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean flag = travelTypeService.removeByIds(idList);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id查询该出差数据
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询该出差数据")
    @GetMapping("/findOne/{id}")
    public Result findOne(@PathVariable(value = "id") String id) {
        TravelType travelType = travelTypeService.getById(id);
        return Result.ok().data("travelType", travelType);
    }

    /***
     * 获取管理出差种类分页列表
     * @param page
     * @param limit
     * @param travelType
     * @return
     */
    @ApiOperation(value = "获取管理出差种类分页列表")
    @PostMapping("/{page}/{limit}")
    public Result page(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQuery", value = "查询对象", required = false)
            @RequestBody TravelType travelType) {
        Page<TravelType> travelTypePage = new Page<>(page, limit);
        Map<String, Object> map = travelTypeService.pageInfo(travelTypePage, travelType);
        return Result.ok().data("map", map);
    }

}

