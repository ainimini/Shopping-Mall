package com.mall.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.entity.dto.CustomException;
import com.mall.common.util.DateUtil;
import com.mall.common.util.JwtUtil;
import com.mall.common.util.RandomUtil;
import com.mall.service.acl.entity.pojo.Post;
import com.mall.service.acl.entity.pojo.User;
import com.mall.service.acl.entity.pojo.UserPostRelated;
import com.mall.service.acl.entity.vo.UserQueryVo;
import com.mall.service.acl.entity.vo.UserRegisterVo;
import com.mall.service.acl.entity.vo.UserUpdateVo;
import com.mall.service.acl.mapper.UserMapper;
import com.mall.service.acl.service.PostService;
import com.mall.service.acl.service.UserPostRelatedService;
import com.mall.service.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private PostService postService;
    @Autowired
    private UserPostRelatedService userPostRelatedService;

    /***
     * 员工注册
     *
     * @param userRegisterVo
     */
    @Override
    public Map<String, String> register(UserRegisterVo userRegisterVo) {
        /***
         * 获取用户注册信息
         */
        if (StringUtils.isEmpty(userRegisterVo.getUsername())) {
            throw new CustomException(20001, "请输入姓名");
        }
        if (StringUtils.isEmpty(userRegisterVo.getPassword())) {
            throw new CustomException(20001, "请输入密码");
        }
        if (StringUtils.isEmpty(userRegisterVo.getSex())) {
            throw new CustomException(20001, "请输入性别");
        }
        if (StringUtils.isEmpty(userRegisterVo.getAvatar())) {
            throw new CustomException(20001, "请输入头像");
        }
        if (StringUtils.isEmpty(userRegisterVo.getBirthday())) {
            throw new CustomException(20001, "请输入生日");
        }
        if (StringUtils.isEmpty(userRegisterVo.getNickName())) {
            throw new CustomException(20001, "请输入昵称");
        }
        if (StringUtils.isEmpty(userRegisterVo.getPhoto())) {
            throw new CustomException(20001, "请输入员工照片");
        }
        if (StringUtils.isEmpty(userRegisterVo.getPhone())) {
            throw new CustomException(20001, "请输入手机号");
        }
        if (StringUtils.isEmpty(userRegisterVo.getIdNumber())) {
            throw new CustomException(20001, "请输入身份证号");
        }
        if (StringUtils.isEmpty(userRegisterVo.getCode())) {
            throw new CustomException(20001, "请输入验证码");
        }
        /***
         * 获取redis中的验证码
         */
        //通过手机号查询redis验证码
        String code = redisTemplate.opsForValue().get(userRegisterVo.getPhone());
        if (!code.equals(userRegisterVo.getCode())) {
            throw new CustomException(20001, "请输入正确的验证码");
        }
        //判断手机号是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", userRegisterVo.getPhone());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new CustomException(20001, "手机号已经注册");
        }
        /***
         * 封装
         */
        //采用BCrypt加密密码
        userRegisterVo.setPassword(BCrypt.hashpw(userRegisterVo.getPassword(), BCrypt.gensalt()));
        //自动生成员工工号
        //获取员工生日
        Date birthday = userRegisterVo.getBirthday();
        System.out.println(birthday);
        String birthdayStr = DateUtil.date2Str(birthday);
        //打乱生日生成八位的随机数
        String randomString = RandomUtil.getStrRandom(birthdayStr, 6);
        //随机生成四位的随机数
        String fourBitRandom = RandomUtil.getFourBitRandom();
        //获取员工性别
        Integer sex = userRegisterVo.getSex();
        //封装员工工号
        userRegisterVo.setJobNumber(fourBitRandom + randomString + sex);
        //存入数据库
        baseMapper.insert(userRegisterVo);
        HashMap<String, String> map = new HashMap<>();
        map.put("jobNumber", userRegisterVo.getJobNumber());
        map.put("nickName", userRegisterVo.getNickName());

        //获得员工入职的岗位
        String post = userRegisterVo.getPost();
        //条件构造
        QueryWrapper<Post> postWrapper = new QueryWrapper<>();
        postWrapper.eq("post_name", post);
        //获得岗位
        Post onePost = postService.getOne(postWrapper);
        //创建员工岗位实体类实体类
        UserPostRelated userPostRelated = new UserPostRelated();
        userPostRelated.setUserId(userRegisterVo.getId());
        userPostRelated.setPostId(onePost.getId());
        userPostRelatedService.save(userPostRelated);
        return map;
    }

    /***
     * 获取管理员工分页列表
     * @param userPage
     * @param userQueryVo
     * @return
     */
    @Override
    public Map<String, Object> pageInfo(Page<User> userPage, UserQueryVo userQueryVo) {
        //条件构造
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //模糊查询员工姓名
        if (!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username", userQueryVo.getUsername());
        }
        //查询员工工号
        if (!StringUtils.isEmpty(userQueryVo.getJobNumber())) {
            wrapper.eq("job_number", userQueryVo.getJobNumber());
        }
        //查询创建时间范围
        if (!StringUtils.isEmpty(userQueryVo.getBegin()) && !StringUtils.isEmpty(userQueryVo.getEnd())) {
            wrapper.between("gmt_create", userQueryVo.getBegin(), userQueryVo.getEnd());
        }
        //查询性别
        if (!StringUtils.isEmpty(userQueryVo.getSex())) {
            wrapper.eq("sex", userQueryVo.getSex());
        }
        wrapper.eq("is_deleted", 0);
        baseMapper.selectPage(userPage, wrapper);
        /***
         * 封装
         */
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", userPage.getRecords());
        map.put("current", userPage.getCurrent());
        map.put("pages", userPage.getPages());
        map.put("size", userPage.getSize());
        map.put("total", userPage.getTotal());
        map.put("hasNext", userPage.hasNext());
        map.put("hasPrevious", userPage.hasPrevious());
        return map;
    }

    /***
     * 修改管理员工
     * TODO 修改头像
     * TODO 修改照片
     * @param userUpdateVo
     * @return
     */
    @Override
    public boolean updateUserById(UserUpdateVo userUpdateVo) {
        //是否存在该员工
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        Integer count = baseMapper.selectCount(wrapper);
        //判断是否存在
        if (count <= 0) {
            throw new CustomException(20001, "该员工不存在");
        }
        //封装
        userUpdateVo.setPassword(BCrypt.hashpw(userUpdateVo.getPassword(), BCrypt.gensalt()));
        User user = new User();
        BeanUtils.copyProperties(userUpdateVo, user);
        int update = baseMapper.updateById(user);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /***
     * 员工离职实现逻辑删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        User user = new User();
        user.setId(id);
        user.setIsDeleted(true);
        int update = baseMapper.updateById(user);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /***
     * 禁用员工 员工在职只是没有权限
     * @param id
     * @return
     */
    @Override
    public boolean disabledById(String id) {
        User user = new User();
        user.setId(id);
        user.setIsDisabled(true);
        int update = baseMapper.updateById(user);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /***
     * 查询数据库当前用用户是否曾经使用过微信登录
     * @param openid
     * @return
     */
    @Override
    public User getByOpenid(String openid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openId", openid);
        User user = baseMapper.selectOne(wrapper);
        return user;
    }

    /***
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        //判断传过来的值是否为空
        String phone = user.getPhone();
        String password = user.getPassword();
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new CustomException(20001, "登录失败");
        }
        //查询用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        User selectOne = baseMapper.selectOne(wrapper);
        if (null == selectOne) {
            throw new CustomException(20001, "登录失败");
        }
        //判断密码是否正确
        if (!BCrypt.checkpw(password, selectOne.getPassword())) {
            throw new CustomException(20001, "登录失败");
        }
        //判断用户是否禁用
        if (selectOne.getIsDisabled()) {
            throw new CustomException(20001, "该用户已被禁用");
        }

        //生成token
        String jwtToken = JwtUtil.getJwtToken(selectOne.getId(), selectOne.getUsername());

        return jwtToken;
    }
}
