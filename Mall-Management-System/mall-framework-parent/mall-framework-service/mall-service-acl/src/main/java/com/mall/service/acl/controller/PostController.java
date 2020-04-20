package com.mall.service.acl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.entity.Result;
import com.mall.service.acl.entity.pojo.Post;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.vo.PostQueryVo;
import com.mall.service.acl.entity.vo.UserQueryVo;
import com.mall.service.acl.entity.vo.UserRegisterVo;
import com.mall.service.acl.entity.vo.UserUpdateVo;
import com.mall.service.acl.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author X
 * @since 2020-04-20
 */
@Api(description = "SM系统管理")
@RestController
@RequestMapping("/acl/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    /***
     * 岗位新增
     * @param post
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("岗位新增")
    public Result add(@RequestBody Post post) {
        boolean flag = postService.save(post);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 删除岗位
     * @param id
     * @return
     */
    @ApiOperation(value = "删除岗位")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        boolean flag = postService.removeById(id);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 根据id列表删除岗位
     * @param idList
     * @return
     */
    @ApiOperation(value = "根据id列表删除岗位")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        boolean flag = postService.removeByIds(idList);
        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    /***
     * 修改岗位
     * @param post
     * @return
     */
    @ApiOperation(value = "修改岗位")
    @PutMapping("/update")
    public Result updateById(@RequestBody Post post) {
        boolean flag = postService.updatePostById(post);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    /***
     * 获取岗位条件分页列表
     * @param page
     * @param limit
     * @param postQueryVo
     * @return
     */
    @ApiOperation(value = "获取岗位条件分页列表")
    @PostMapping("/{page}/{limit}")
    public Result page(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "postQuery", value = "查询对象", required = false)
            @RequestBody PostQueryVo postQueryVo) {
        Page<Post> postPage = new Page<>(page, limit);
        Map<String, Object> map = postService.pageInfo(postPage, postQueryVo);
        return Result.ok().data("map", map);
    }

    /***
     * 根据id查询该岗位数据
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询该岗位数据")
    @GetMapping("/findOne/{id}")
    public Result findOne(@PathVariable(value = "id") String id) {
        Post post = postService.getById(id);
        return Result.ok().data("post", post);
    }

    /***
     * 查询所有岗位数据
     * @return
     */
    @ApiOperation(value = "查询所有岗位数据")
    @GetMapping("/findAll")
    public Result findAll() {
        //条件
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted",0);
        List<Post> postList = postService.list(wrapper);
        return Result.ok().data("postList", postList);
    }
}

