package com.mall.service.hrm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.common.entity.Result;
import com.mall.common.entity.dto.CustomException;
import com.mall.common.util.DateUtil;
import com.mall.common.util.JwtUtil;
import com.mall.service.hrm.client.UserClient;
import com.mall.service.hrm.entity.pojo.SignIn;
import com.mall.service.hrm.entity.pojo.TravelType;
import com.mall.service.hrm.entity.vo.TravelTypeRequestVo;
import com.mall.service.hrm.mapper.SignInMapper;
import com.mall.service.hrm.service.SignInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author X
 * @since 2020-04-21
 */
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements SignInService {

    /***
     * 早上上班时间
     */
    private static final String AM_SINGIN = "09:00:00";
    /***
     * 打卡下班时间
     */
    private static final String PM_SINGIN = "17:00:00";

    @Autowired
    private UserClient userClient;

    /***
     * 员工签到
     * @param request
     * @return
     */
    @Override
    public boolean signIn(HttpServletRequest request) {
        //从token中获取登录的员工信息
        String userId = JwtUtil.getMemberIdByJwtToken(request);
        //远程调用通过员工id查询员工信息接口
        Result result = userClient.findOne(userId);
        if (result == null || result.getData() == null) {
            throw new CustomException(20001, "你的信息存在错误，请及时通知人事部！");
        }
        //获取员工姓名
        Object user = result.getData().get("user");
        String jsonString = JSON.toJSONString(user);
        Map map = JSON.parseObject(jsonString, Map.class);

        //拼接员工上班时间
        Date date = new Date();
        String dateStr = DateUtil.date_str(date);
        //拼接时间
        String sigInDate = dateStr + " " + AM_SINGIN;
        Date strDate = DateUtil.Str2Date(sigInDate);

        //封装
        SignIn signIn = new SignIn();
        signIn.setUserId(userId);
        signIn.setGmtCreate(date);
        signIn.setUsername((String) map.get("username"));
        //存入数据库
        int insert = baseMapper.insert(signIn);
        if (insert <= 0) {
            throw new CustomException(20001, "签到失败");
        }
        //判断该员工是否迟到
        QueryWrapper<SignIn> wrapper = new QueryWrapper<>();
        wrapper.eq("id", signIn.getId());
        signIn = new SignIn();
        if (date.before(strDate)) {
            return false;
        }
        signIn.setUserId(userId);
        signIn.setIsLast(true);
        baseMapper.update(signIn, wrapper);
        return true;
    }

    /***
     * 员工打卡下班
     * @param request
     * @return
     */
    @Override
    public boolean offWork(HttpServletRequest request) {
        //从token中获取登录的员工信息
        String userId = JwtUtil.getMemberIdByJwtToken(request);
        //远程调用通过员工id查询员工信息接口
        Result result = userClient.findOne(userId);
        if (result == null || result.getData() == null) {
            throw new CustomException(20001, "你的信息存在错误，请及时通知人事部！");
        }

        //拼接员工下班时间
        Date date = new Date();
        //dateStr yyyy-MM-dd
        String dateStr = DateUtil.date_str(date);
        //拼接时间
        String sigInDate = dateStr + " " + PM_SINGIN;
        Date strDate = DateUtil.Str2Date(sigInDate);

        //封装
        SignIn signIn = new SignIn();
        QueryWrapper<SignIn> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<SignIn> signIns = baseMapper.selectList(wrapper);
        //遍历
        for (SignIn sign : signIns) {
            Date gmtCreate = sign.getGmtCreate();
            String date_str = DateUtil.date_str(gmtCreate);
            if (date_str.equals(dateStr)) {
                String signId = sign.getId();
                //判断该员工是否早退
                if (strDate.before(date)) {
                    return false;
                }
                signIn.setId(signId);
                signIn.setIsLeaveEarly(true);
                baseMapper.updateById(signIn);
                return true;
            }
        }
        throw new CustomException(20001, "打卡失败");
    }

    /***
     * 员工出差
     * @param travelTypeRequestVo
     * @param request
     * @return
     */
    @Override
    public boolean travel(TravelTypeRequestVo travelTypeRequestVo, HttpServletRequest request) {
        //从token中获取登录的员工信息
        String userId = JwtUtil.getMemberIdByJwtToken(request);
        //获取前端传过来的信息
        String travelType = travelTypeRequestVo.getTravelType();
        String description = travelTypeRequestVo.getDescription();
        //远程调用通过员工id查询员工信息接口
        Result result = userClient.findOne(userId);
        if (result == null || result.getData() == null) {
            throw new CustomException(20001, "你的信息存在错误，请及时通知人事部！");
        }
        //获取员工姓名
        Object user = result.getData().get("user");
        String jsonString = JSON.toJSONString(user);
        Map map = JSON.parseObject(jsonString, Map.class);
        //封装
        SignIn signIn = new SignIn();
        signIn.setUserId(userId);
        signIn.setUsername((String) map.get("username"));
        signIn.setTravelType(travelType);
        signIn.setIsTravel(true);
        signIn.setDescription(description);
        //保存数据
        int insert = baseMapper.insert(signIn);
        if (insert > 0) {
            return true;
        }
        return false;
    }
}
