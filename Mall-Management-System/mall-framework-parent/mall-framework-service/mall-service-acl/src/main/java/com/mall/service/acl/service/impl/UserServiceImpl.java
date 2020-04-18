package com.mall.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.common.entity.Result;
import com.mall.common.entity.dto.CustomException;
import com.mall.common.util.DateUtil;
import com.mall.common.util.RandomUtil;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.vo.UserLoginVo;
import com.mall.service.acl.mapper.UserMapper;
import com.mall.service.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author X
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /***
     * 员工注册
     * TODO 员工入职岗位
     * @param userLoginVo
     */
    @Override
    public String register(UserLoginVo userLoginVo) {
        /***
         * 获取用户注册信息
         */
        if (StringUtils.isEmpty(userLoginVo.getUsername())) {
            throw new CustomException(20001, "请输入姓名");
        }
        if (StringUtils.isEmpty(userLoginVo.getPassword())) {
            throw new CustomException(20001, "请输入密码");
        }
        if (StringUtils.isEmpty(userLoginVo.getSex())) {
            throw new CustomException(20001, "请输入性别");
        }
        if (StringUtils.isEmpty(userLoginVo.getAvatar())) {
            throw new CustomException(20001, "请输入头像");
        }
        if (StringUtils.isEmpty(userLoginVo.getBirthday())) {
            throw new CustomException(20001, "请输入生日");
        }
        if (StringUtils.isEmpty(userLoginVo.getNickName())) {
            throw new CustomException(20001, "请输入昵称");
        }
        if (StringUtils.isEmpty(userLoginVo.getPhoto())) {
            throw new CustomException(20001, "请输入员工照片");
        }
        if (StringUtils.isEmpty(userLoginVo.getPhone())) {
            throw new CustomException(20001, "请输入手机号");
        }
        if (StringUtils.isEmpty(userLoginVo.getIdNumber())) {
            throw new CustomException(20001, "请输入身份证号");
        }
        if (StringUtils.isEmpty(userLoginVo.getCode())) {
            throw new CustomException(20001, "请输入验证码");
        }
        /***
         * 获取redis中的验证码
         */
        //通过手机号查询redis验证码
       /* String code = redisTemplate.opsForValue().get(userLoginVo.getPhone());
        if (!code.equals(userLoginVo.getCode())) {
            throw new CustomException(20001, "请输入正确的验证码");
        }*/
        //判断手机号是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", userLoginVo.getPhone());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new CustomException(20001, "手机号已经注册");
        }
        /***
         * 封装
         */
        //采用BCrypt加密密码
        userLoginVo.setPassword(BCrypt.hashpw(userLoginVo.getPassword(), BCrypt.gensalt()));
        //自动生成员工工号
        //获取员工生日
        Date birthday = userLoginVo.getBirthday();
        System.out.println(birthday);
        String birthdayStr = DateUtil.date2Str(birthday);
        //打乱生日生成八位的随机数
        String randomString = RandomUtil.getStrRandom(birthdayStr, 6);
        //随机生成四位的随机数
        String fourBitRandom = RandomUtil.getFourBitRandom();
        //获取员工性别
        Integer sex = userLoginVo.getSex();
        //封装员工工号
        userLoginVo.setJobNumber(fourBitRandom + randomString + sex);
        baseMapper.insert(userLoginVo);
        return userLoginVo.getJobNumber();
    }
}
