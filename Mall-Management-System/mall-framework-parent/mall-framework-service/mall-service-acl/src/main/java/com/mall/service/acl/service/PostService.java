package com.mall.service.acl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.service.acl.entity.pojo.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.vo.PostQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author X
 * @since 2020-04-20
 */
public interface PostService extends IService<Post> {

    /***
     * 修改岗位
     * @param post
     * @return
     */
    boolean updatePostById(Post post);

    /***
     * 获取岗位条件分页列表
     * @param postPage
     * @param postQueryVo
     * @return
     */
    Map<String, Object> pageInfo(Page<Post> postPage, PostQueryVo postQueryVo);
}
