package com.mall.service.hrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.service.hrm.entity.pojo.LeaveType;
import com.mall.service.hrm.mapper.LeaveTypeMapper;
import com.mall.service.hrm.service.LeaveTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author X
 * @since 2020-04-22
 */
@Service
public class LeaveTypeServiceImpl extends ServiceImpl<LeaveTypeMapper, LeaveType> implements LeaveTypeService {

    /***
     * 获取管理请假种类分页列表
     * @param leaveTypePage
     * @param leaveType
     * @return
     */
    @Override
    public Map<String, Object> pageInfo(Page<LeaveType> leaveTypePage, LeaveType leaveType) {
        //条件构造
        QueryWrapper<LeaveType> wrapper = new QueryWrapper<>();
        //查询请假种类
        if (!StringUtils.isEmpty(leaveType.getLeaveType())) {
            wrapper.eq("leave_type", leaveType.getLeaveType());
        }
        baseMapper.selectPage(leaveTypePage, wrapper);
        /***
         * 封装
         */
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", leaveTypePage.getRecords());
        map.put("current", leaveTypePage.getCurrent());
        map.put("pages", leaveTypePage.getPages());
        map.put("size", leaveTypePage.getSize());
        map.put("total", leaveTypePage.getTotal());
        map.put("hasNext", leaveTypePage.hasNext());
        map.put("hasPrevious", leaveTypePage.hasPrevious());
        return map;
    }
}
