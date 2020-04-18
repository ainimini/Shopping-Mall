package com.mall.service.acl.service;

import com.mall.common.entity.Result;
import com.mall.service.acl.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.service.acl.entity.vo.UserLoginVo;

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
     * @param userLoginVo
     */
    String register(UserLoginVo userLoginVo);
}
