package com.mall.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.entity.dto.CustomException;
import com.mall.service.acl.entity.pojo.Post;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.vo.PostQueryVo;
import com.mall.service.acl.mapper.PostMapper;
import com.mall.service.acl.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author X
 * @since 2020-04-20
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    /***
     * 修改岗位
     * @param post
     * @return
     */
    @Override
    public boolean updatePostById(Post post) {
        //查询岗位是否存在
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        Integer count = baseMapper.selectCount(wrapper);
        if (count <= 0) {
            throw new CustomException(20001, "岗位不存在");
        }
        int update = baseMapper.updateById(post);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /***
     * 获取岗位条件分页列表
     * @param postPage
     * @param postQueryVo
     * @return
     */
    @Override
    public Map<String, Object> pageInfo(Page<Post> postPage, PostQueryVo postQueryVo) {
        //条件构造
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        //查询岗位姓名
        if (!StringUtils.isEmpty(postQueryVo.getPostName())) {
            wrapper.eq("post_name", postQueryVo.getPostName());
        }
        //查询岗位编号
        if (!StringUtils.isEmpty(postQueryVo.getPostNumber())) {
            wrapper.eq("post_number", postQueryVo.getPostNumber());
        }
        //查询创建时间范围
        if (!StringUtils.isEmpty(postQueryVo.getBegin()) && !StringUtils.isEmpty(postQueryVo.getEnd())) {
            wrapper.between("gmt_create", postQueryVo.getBegin(), postQueryVo.getEnd());
        }
        wrapper.eq("is_deleted",0);
        baseMapper.selectPage(postPage, wrapper);

        /***
         * 封装
         */
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", postPage.getRecords());
        map.put("current", postPage.getCurrent());
        map.put("pages", postPage.getPages());
        map.put("size", postPage.getSize());
        map.put("total", postPage.getTotal());
        map.put("hasNext", postPage.hasNext());
        map.put("hasPrevious", postPage.hasPrevious());
        return map;
    }
}
