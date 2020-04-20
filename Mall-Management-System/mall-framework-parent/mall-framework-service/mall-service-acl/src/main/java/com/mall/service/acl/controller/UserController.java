package com.mall.service.acl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.entity.Result;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.vo.UserQueryVo;
import com.mall.service.acl.entity.vo.UserRegisterVo;
import com.mall.service.acl.entity.vo.UserUpdateVo;
import com.mall.service.acl.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author X
 * @since 2020-04-17
 */
@Api(description = "用户")
@RestController
//@RequestMapping("/acl/user")
@RequestMapping("/api/admin")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * 员工注册
     * @param userRegisterVo
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("员工注册")
    public Result register(@RequestBody UserRegisterVo userRegisterVo) {
        Map<String, String> map = userService.register(userRegisterVo);
        String jobNumber = map.get("jobNumber");
        String nickName = map.get("nickName");
        return Result.ok().message("恭喜" + nickName + "注册成功，成为我们的一员！你的工号为" + jobNumber + "一定要牢记呦！！！");
    }

    /***
     * 修改管理员工
     * @param userUpdateVo
     * @return
     */
    @ApiOperation(value = "修改管理员工")
    @PutMapping("/update")
    public Result updateById(@RequestBody UserUpdateVo userUpdateVo) {
        boolean flag = userService.updateUserById(userUpdateVo);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    /***
     * 删除员工
     * @param id
     * @return
     */
    @ApiOperation(value = "删除员工")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        userService.removeById(id);
        return Result.ok();
    }

    /***
     * 根据id列表删除管理员工
     * @param idList
     * @return
     */
    @ApiOperation(value = "根据id列表删除管理员工")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Result.ok();
    }

    /***
     * 员工离职实现逻辑删除
     * @param id
     * @return
     */
    @ApiOperation(value = "员工离职实现逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        boolean flag = userService.deleteById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 禁用员工 员工在职只是没有权限
     * @param id
     * @return
     */
    @ApiOperation(value = "禁用员工")
    @PostMapping("/disabled/{id}")
    public Result disabled(@PathVariable String id) {
        boolean flag = userService.disabledById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 获取管理员工分页列表
     * @param page
     * @param limit
     * @param userQueryVo
     * @return
     */
    @ApiOperation(value = "获取管理员工分页列表")
    @PostMapping("/{page}/{limit}")
    public Result page(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody UserQueryVo userQueryVo) {
        Page<User> userPage = new Page<>(page, limit);
        Map<String, Object> map = userService.pageInfo(userPage, userQueryVo);
        return Result.ok().data("map", map);
    }

    /***
     * 根据id查询该员工数据
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询该员工数据")
    @GetMapping("/findOne/{id}")
    public Result findOne(@PathVariable(value = "id") String id) {
        User user = userService.getById(id);
        return Result.ok().data("user", user);
    }


    /*****************************************************************************/

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @ApiOperation(value = "所有讲师列表")
    @GetMapping(value = "/info")
    public Result info() {
        return Result.ok().data("roles", "admin").data("name", "admin");
    }
}

