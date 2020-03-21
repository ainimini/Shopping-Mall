package com.mall.service.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.api.system.pojo.Admin;
import com.mall.api.system.pojo.LoginLog;
import com.mall.api.system.pojo.Resource;
import com.mall.security.util.JwtTokenUtil;
import com.mall.service.system.bo.AdminUserDetails;
import com.mall.service.system.dao.AdminMapper;
import com.mall.service.system.dao.LoginLogMapper;
import com.mall.service.system.dao.ResourceMapper;
import com.mall.service.system.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/****
 * @Author:X
 * @Description:Admin业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 根据用户名获取后台管理员
     */
    @Override
    public Admin getAdminByUsername(String username) {
        Admin admin = new Admin();
        admin.setUsername(username);
        Example example = createExample(admin);
        List<Admin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        Admin admin = getAdminByUsername(username);
        LoginLog loginLog = new LoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        Admin admin = getAdminByUsername(username);
        if (admin != null) {
            List<Resource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 获取指定用户的可访问资源
     */
    @Override
    public List<Resource> getResourceList(Long adminId) {
        return resourceMapper.getResourceList(adminId);
    }

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * Admin构建查询对象
     *
     * @param admin
     * @return
     */
    public Example createExample(Admin admin) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        if (admin != null) {
            //
            if (!StringUtils.isEmpty(admin.getId())) {
                criteria.andEqualTo("id", admin.getId());
            }
            //
            if (!StringUtils.isEmpty(admin.getUsername())) {
                criteria.andEqualTo("username", admin.getUsername());
            }
            //
            if (!StringUtils.isEmpty(admin.getPassword())) {
                criteria.andEqualTo("password", admin.getPassword());
            }
            // 头像
            if (!StringUtils.isEmpty(admin.getIcon())) {
                criteria.andEqualTo("icon", admin.getIcon());
            }
            // 邮箱
            if (!StringUtils.isEmpty(admin.getEmail())) {
                criteria.andEqualTo("email", admin.getEmail());
            }
            // 昵称
            if (!StringUtils.isEmpty(admin.getNickName())) {
                criteria.andEqualTo("nickName", admin.getNickName());
            }
            // 备注信息
            if (!StringUtils.isEmpty(admin.getNote())) {
                criteria.andEqualTo("note", admin.getNote());
            }
            // 创建时间
            if (!StringUtils.isEmpty(admin.getCreateTime())) {
                criteria.andEqualTo("createTime", admin.getCreateTime());
            }
            // 最后登录时间
            if (!StringUtils.isEmpty(admin.getLoginTime())) {
                criteria.andEqualTo("loginTime", admin.getLoginTime());
            }
            // 帐号启用状态：0->禁用；1->启用
            if (!StringUtils.isEmpty(admin.getStatus())) {
                criteria.andEqualTo("status", admin.getStatus());
            }
        }
        return example;
    }
}
