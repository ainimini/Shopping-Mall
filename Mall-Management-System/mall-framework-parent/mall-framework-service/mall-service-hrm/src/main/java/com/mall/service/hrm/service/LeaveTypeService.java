package com.mall.service.hrm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.service.hrm.entity.pojo.LeaveType;
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
public interface LeaveTypeService extends IService<LeaveType> {

    /***
     * 获取管理请假种类分页列表
     * @param leaveTypePage
     * @param leaveType
     * @return
     */
    Map<String, Object> pageInfo(Page<LeaveType> leaveTypePage, LeaveType leaveType);
}
