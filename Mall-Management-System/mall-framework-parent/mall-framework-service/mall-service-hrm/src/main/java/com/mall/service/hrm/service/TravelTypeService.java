package com.mall.service.hrm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.service.hrm.entity.pojo.LeaveType;
import com.mall.service.hrm.entity.pojo.TravelType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author X
 * @since 2020-04-22
 */
public interface TravelTypeService extends IService<TravelType> {

    /***
     * 获取管理出差种类分页列表
     * @param travelTypePage
     * @param travelType
     * @return
     */
    Map<String, Object> pageInfo(Page<TravelType> travelTypePage, TravelType travelType);
}
