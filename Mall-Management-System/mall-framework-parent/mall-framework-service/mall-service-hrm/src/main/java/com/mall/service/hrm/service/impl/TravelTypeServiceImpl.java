package com.mall.service.hrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.service.hrm.entity.pojo.LeaveType;
import com.mall.service.hrm.entity.pojo.TravelType;
import com.mall.service.hrm.mapper.TravelTypeMapper;
import com.mall.service.hrm.service.TravelTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author X
 * @since 2020-04-22
 */
@Service
public class TravelTypeServiceImpl extends ServiceImpl<TravelTypeMapper, TravelType> implements TravelTypeService {

    /***
     * 获取管理出差种类分页列表
     * @param travelTypePage
     * @param travelType
     * @return
     */
    @Override
    public Map<String, Object> pageInfo(Page<TravelType> travelTypePage, TravelType travelType) {
        //条件构造
        QueryWrapper<TravelType> wrapper = new QueryWrapper<>();
        //查询请出差类
        if (!StringUtils.isEmpty(travelType.getTravelType())) {
            wrapper.eq("travel_type", travelType.getTravelType());
        }
        baseMapper.selectPage(travelTypePage, wrapper);
        /***
         * 封装
         */
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", travelTypePage.getRecords());
        map.put("current", travelTypePage.getCurrent());
        map.put("pages", travelTypePage.getPages());
        map.put("size", travelTypePage.getSize());
        map.put("total", travelTypePage.getTotal());
        map.put("hasNext", travelTypePage.hasNext());
        map.put("hasPrevious", travelTypePage.hasPrevious());
        return map;
    }
}
