package com.mall.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Resource;
import com.mall.service.system.dao.ResourceMapper;
import com.mall.service.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:X
 * @Description:Resource业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> listAll() {
        return resourceMapper.selectAll();
    }


    /**
     * Resource条件+分页查询
     * @param resource 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Resource> findPage(Resource resource, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(resource);
        //执行搜索
        return new PageInfo<Resource>(resourceMapper.selectByExample(example));
    }

    /**
     * Resource分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Resource> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Resource>(resourceMapper.selectAll());
    }

    /**
     * Resource条件查询
     * @param resource
     * @return
     */
    @Override
    public List<Resource> findList(Resource resource){
        //构建查询条件
        Example example = createExample(resource);
        //根据构建的条件查询数据
        return resourceMapper.selectByExample(example);
    }


    /**
     * Resource构建查询对象
     * @param resource
     * @return
     */
    public Example createExample(Resource resource){
        Example example=new Example(Resource.class);
        Example.Criteria criteria = example.createCriteria();
        if(resource!=null){
            // 
            if(!StringUtils.isEmpty(resource.getId())){
                    criteria.andEqualTo("id",resource.getId());
            }
            // 创建时间
            if(!StringUtils.isEmpty(resource.getCreateTime())){
                    criteria.andEqualTo("createTime",resource.getCreateTime());
            }
            // 资源名称
            if(!StringUtils.isEmpty(resource.getName())){
                    criteria.andLike("name","%"+resource.getName()+"%");
            }
            // 资源URL
            if(!StringUtils.isEmpty(resource.getUrl())){
                    criteria.andEqualTo("url",resource.getUrl());
            }
            // 描述
            if(!StringUtils.isEmpty(resource.getDescription())){
                    criteria.andEqualTo("description",resource.getDescription());
            }
            // 资源分类ID
            if(!StringUtils.isEmpty(resource.getCategoryId())){
                    criteria.andEqualTo("categoryId",resource.getCategoryId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id){
        resourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Resource
     * @param resource
     */
    @Override
    public void update(Resource resource){
        resourceMapper.updateByPrimaryKey(resource);
    }

    /**
     * 增加Resource
     * @param resource
     */
    @Override
    public void add(Resource resource){
        resourceMapper.insert(resource);
    }

    /**
     * 根据ID查询Resource
     * @param id
     * @return
     */
    @Override
    public Resource findById(Long id){
        return  resourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Resource全部数据
     * @return
     */
    @Override
    public List<Resource> findAll() {
        return resourceMapper.selectAll();
    }
}
