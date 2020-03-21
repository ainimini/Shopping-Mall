package com.mall.service.system.service;

import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.LoginLog;

import java.util.List;

/****
 * @Author:X
 * @Description:LoginLog业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface LoginLogService {

    /***
     * LoginLog多条件分页查询
     * @param loginLog
     * @param page
     * @param size
     * @return
     */
    PageInfo<LoginLog> findPage(LoginLog loginLog, int page, int size);

    /***
     * LoginLog分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<LoginLog> findPage(int page, int size);

    /***
     * LoginLog多条件搜索方法
     * @param loginLog
     * @return
     */
    List<LoginLog> findList(LoginLog loginLog);

    /***
     * 删除LoginLog
     * @param id
     */
    void delete(Long id);

    /***
     * 修改LoginLog数据
     * @param loginLog
     */
    void update(LoginLog loginLog);

    /***
     * 新增LoginLog
     * @param loginLog
     */
    void add(LoginLog loginLog);

    /**
     * 根据ID查询LoginLog
     * @param id
     * @return
     */
     LoginLog findById(Long id);

    /***
     * 查询所有LoginLog
     * @return
     */
    List<LoginLog> findAll();
}
