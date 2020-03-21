package com.mall.service.system.service;


import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Admin;
import com.mall.api.system.pojo.Resource;
import com.mall.api.system.pojo.Role;
import com.mall.common.entity.CommonResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/****
 * @Author:X
 * @Description:Admin业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface AdminService {
    /**
     * 根据用户名获取后台管理员
     */
    Admin getAdminByUsername(String username);

    /**
     * 注册功能
     */
  // Admin register(Admin admin);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
  //  String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
  //  Admin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
  //  List<Admin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
  //  int update(Long id, Admin admin);

    /**
     * 删除指定用户
     */
  //  int delete(Long id);

    /**
     * 修改用户角色关系
     */
  //  @Transactional
   // int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    //ist<Role> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<Resource> getResourceList(Long adminId);

    /**
     * 修改用户的+-权限
     */
   // @Transactional
   // int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
   // List<Permission> getPermissionList(Long adminId);

    /**
     * 修改密码
     */
    //int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}