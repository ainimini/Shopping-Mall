package com.mall.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Role;
import com.mall.service.system.dao.RoleMapper;
import com.mall.service.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:X
 * @Description:Role业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    /**
     * Role条件+分页查询
     * @param role 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Role> findPage(Role role, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(role);
        //执行搜索
        return new PageInfo<Role>(roleMapper.selectByExample(example));
    }

    /**
     * Role分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Role> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Role>(roleMapper.selectAll());
    }

    /**
     * Role条件查询
     * @param role
     * @return
     */
    @Override
    public List<Role> findList(Role role){
        //构建查询条件
        Example example = createExample(role);
        //根据构建的条件查询数据
        return roleMapper.selectByExample(example);
    }


    /**
     * Role构建查询对象
     * @param role
     * @return
     */
    public Example createExample(Role role){
        Example example=new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if(role!=null){
            // 
            if(!StringUtils.isEmpty(role.getId())){
                    criteria.andEqualTo("id",role.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(role.getName())){
                    criteria.andLike("name","%"+role.getName()+"%");
            }
            // 描述
            if(!StringUtils.isEmpty(role.getDescription())){
                    criteria.andEqualTo("description",role.getDescription());
            }
            // 后台用户数量
            if(!StringUtils.isEmpty(role.getAdminCount())){
                    criteria.andEqualTo("adminCount",role.getAdminCount());
            }
            // 创建时间
            if(!StringUtils.isEmpty(role.getCreateTime())){
                    criteria.andEqualTo("createTime",role.getCreateTime());
            }
            // 启用状态：0->禁用；1->启用
            if(!StringUtils.isEmpty(role.getStatus())){
                    criteria.andEqualTo("status",role.getStatus());
            }
            // 
            if(!StringUtils.isEmpty(role.getSort())){
                    criteria.andEqualTo("sort",role.getSort());
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
        roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Role
     * @param role
     */
    @Override
    public void update(Role role){
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 增加Role
     * @param role
     */
    @Override
    public void add(Role role){
        roleMapper.insert(role);
    }

    /**
     * 根据ID查询Role
     * @param id
     * @return
     */
    @Override
    public Role findById(Long id){
        return  roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Role全部数据
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleMapper.selectAll();
    }
}
