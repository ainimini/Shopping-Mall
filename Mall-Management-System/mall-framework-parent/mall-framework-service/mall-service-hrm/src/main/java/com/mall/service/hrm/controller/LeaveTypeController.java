package com.mall.service.hrm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.entity.Result;
import com.mall.service.hrm.entity.pojo.LeaveType;
import com.mall.service.hrm.service.LeaveTypeService;
import com.mall.service.hrm.service.SignInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(description = "HRM人事管理 请假")
@RestController
@RequestMapping("/hrm/leave-type")
@CrossOrigin
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService;

    /***
     * 添加请假种类
     * @param leaveType
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加请假种类")
    public Result add(@RequestBody LeaveType leaveType) {
        boolean flag = leaveTypeService.save(leaveType);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id删除请假种类
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("根据id删除请假种类")
    public Result delete(@PathVariable(value = "id") String id) {
        boolean flag = leaveTypeService.removeById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id列表删除请假种类
     * @param idList
     * @return
     */
    @ApiOperation(value = "根据id列表删除请假种类")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean flag = leaveTypeService.removeByIds(idList);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id查询该请假数据
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询该请假数据")
    @GetMapping("/findOne/{id}")
    public Result findOne(@PathVariable(value = "id") String id) {
        LeaveType leaveType = leaveTypeService.getById(id);
        return Result.ok().data("leaveType", leaveType);
    }

    /***
     * 获取管理请假种类分页列表
     * @param page
     * @param limit
     * @param leaveType
     * @return
     */
    @ApiOperation(value = "获取管理请假种类分页列表")
    @PostMapping("/{page}/{limit}")
    public Result page(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQuery", value = "查询对象", required = false)
            @RequestBody LeaveType leaveType) {
        Page<LeaveType> leaveTypePage = new Page<>(page, limit);
        Map<String, Object> map = leaveTypeService.pageInfo(leaveTypePage, leaveType);
        return Result.ok().data("map", map);
    }
}

