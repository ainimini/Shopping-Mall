package com.mall.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.RoleResource;
import com.mall.service.system.dao.RoleResourceMapper;
import com.mall.service.system.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:X
 * @Description:RoleResource业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;


    /**
     * RoleResource条件+分页查询
     * @param roleResource 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<RoleResource> findPage(RoleResource roleResource, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(roleResource);
        //执行搜索
        return new PageInfo<RoleResource>(roleResourceMapper.selectByExample(example));
    }

    /**
     * RoleResource分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<RoleResource> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<RoleResource>(roleResourceMapper.selectAll());
    }

    /**
     * RoleResource条件查询
     * @param roleResource
     * @return
     */
    @Override
    public List<RoleResource> findList(RoleResource roleResource){
        //构建查询条件
        Example example = createExample(roleResource);
        //根据构建的条件查询数据
        return roleResourceMapper.selectByExample(example);
    }


    /**
     * RoleResource构建查询对象
     * @param roleResource
     * @return
     */
    public Example createExample(RoleResource roleResource){
        Example example=new Example(RoleResource.class);
        Example.Criteria criteria = example.createCriteria();
        if(roleResource!=null){
            // 
            if(!StringUtils.isEmpty(roleResource.getRoleId())){
                    criteria.andEqualTo("roleId",roleResource.getRoleId());
            }
            // 
            if(!StringUtils.isEmpty(roleResource.getResourceId())){
                    criteria.andEqualTo("resourceId",roleResource.getResourceId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        roleResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改RoleResource
     * @param roleResource
     */
    @Override
    public void update(RoleResource roleResource){
        roleResourceMapper.updateByPrimaryKey(roleResource);
    }

    /**
     * 增加RoleResource
     * @param roleResource
     */
    @Override
    public void add(RoleResource roleResource){
        roleResourceMapper.insert(roleResource);
    }

    /**
     * 根据ID查询RoleResource
     * @param id
     * @return
     */
    @Override
    public RoleResource findById(Integer id){
        return  roleResourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询RoleResource全部数据
     * @return
     */
    @Override
    public List<RoleResource> findAll() {
        return roleResourceMapper.selectAll();
    }
}
