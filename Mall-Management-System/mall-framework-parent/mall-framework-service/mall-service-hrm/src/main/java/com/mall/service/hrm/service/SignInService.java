package com.mall.service.hrm.service;

import com.mall.service.hrm.entity.pojo.SignIn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.service.hrm.entity.pojo.TravelType;
import com.mall.service.hrm.entity.vo.TravelTypeRequestVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author X
 * @since 2020-04-21
 */
public interface SignInService extends IService<SignIn> {

    /***
     * 员工签到
     * @param request
     * @return
     */
    boolean signIn(HttpServletRequest request);

    /***
     * 员工打卡下班
     * @param request
     * @return
     */
    boolean offWork(HttpServletRequest request);

    /***
     * 员工出差
     * @param travelTypeRequestVo
     * @param request
     * @return
     */
    boolean travel(TravelTypeRequestVo travelTypeRequestVo, HttpServletRequest request);
}
