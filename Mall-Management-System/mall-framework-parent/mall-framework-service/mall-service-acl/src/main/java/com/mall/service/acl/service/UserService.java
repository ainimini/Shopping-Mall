package com.mall.service.acl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.service.acl.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.service.acl.entity.vo.UserQueryVo;
import com.mall.service.acl.entity.vo.UserRegisterVo;
import com.mall.service.acl.entity.vo.UserUpdateVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author X
 * @since 2020-04-17
 */
public interface UserService extends IService<User> {

    /***
     * 员工注册
     * @param userRegisterVo
     */
    Map<String, String> register(UserRegisterVo userRegisterVo);

    /***
     * 获取管理员工分页列表
     * @param userPage
     * @param userQueryVo
     * @return
     */
    Map<String, Object> pageInfo(Page<User> userPage, UserQueryVo userQueryVo);

    /***
     * 修改管理员工
     * @param userUpdateVo
     * @return
     */
    boolean updateUserById(UserUpdateVo userUpdateVo);

    /***
     * 员工离职实现逻辑删除
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /***
     * 禁用员工 员工在职只是没有权限
     * @param id
     * @return
     */
    boolean disabledById(String id);
}
