package com.mall.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.LoginLog;
import com.mall.service.system.dao.LoginLogMapper;
import com.mall.service.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****
 * @Author:X
 * @Description:LoginLog业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;


    /**
     * LoginLog条件+分页查询
     * @param loginLog 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<LoginLog> findPage(LoginLog loginLog, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(loginLog);
        //执行搜索
        return new PageInfo<LoginLog>(loginLogMapper.selectByExample(example));
    }

    /**
     * LoginLog分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<LoginLog> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<LoginLog>(loginLogMapper.selectAll());
    }

    /**
     * LoginLog条件查询
     * @param loginLog
     * @return
     */
    @Override
    public List<LoginLog> findList(LoginLog loginLog){
        //构建查询条件
        Example example = createExample(loginLog);
        //根据构建的条件查询数据
        return loginLogMapper.selectByExample(example);
    }


    /**
     * LoginLog构建查询对象
     * @param loginLog
     * @return
     */
    public Example createExample(LoginLog loginLog){
        Example example=new Example(LoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(loginLog!=null){
            // 
            if(!StringUtils.isEmpty(loginLog.getId())){
                    criteria.andEqualTo("id",loginLog.getId());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getAdminId())){
                    criteria.andEqualTo("adminId",loginLog.getAdminId());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getCreateTime())){
                    criteria.andEqualTo("createTime",loginLog.getCreateTime());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getIp())){
                    criteria.andEqualTo("ip",loginLog.getIp());
            }
            // 
            if(!StringUtils.isEmpty(loginLog.getAddress())){
                    criteria.andEqualTo("address",loginLog.getAddress());
            }
            // 浏览器登录类型
            if(!StringUtils.isEmpty(loginLog.getUserAgent())){
                    criteria.andEqualTo("userAgent",loginLog.getUserAgent());
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
        loginLogMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改LoginLog
     * @param loginLog
     */
    @Override
    public void update(LoginLog loginLog){
        loginLogMapper.updateByPrimaryKey(loginLog);
    }

    /**
     * 增加LoginLog
     * @param loginLog
     */
    @Override
    public void add(LoginLog loginLog){
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据ID查询LoginLog
     * @param id
     * @return
     */
    @Override
    public LoginLog findById(Long id){
        return  loginLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询LoginLog全部数据
     * @return
     */
    @Override
    public List<LoginLog> findAll() {
        return loginLogMapper.selectAll();
    }
}
