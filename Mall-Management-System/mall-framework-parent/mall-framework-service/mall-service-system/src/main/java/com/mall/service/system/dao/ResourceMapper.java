package com.mall.service.system.dao;

import com.mall.api.system.pojo.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:X
 * @Description:Resource的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface ResourceMapper extends Mapper<Resource> {

    /***
     * 获取用户所有可访问资源
     *
     * @param adminId
     * @return
     */
    @Select(value = "SELECT\n" +
            "\tur.id id,\n" +
            "\tur.create_time createTime,\n" +
            "\tur.`name` `name`,\n" +
            "\tur.url url,\n" +
            "\tur.description description,\n" +
            "\tur.category_id categoryId \n" +
            "FROM\n" +
            "\ttb_admin_role ar\n" +
            "\tLEFT JOIN tb_role r ON ar.role_id = r.id\n" +
            "\tLEFT JOIN tb_role_resource rrr ON r.id = rrr.role_id\n" +
            "\tLEFT JOIN tb_resource ur ON ur.id = rrr.resource_id \n" +
            "WHERE\n" +
            "\tar.admin_id = #{adminId}\n" +
            "\t\n" +
            "\tAND ur.id IS NOT NULL \n" +
            "GROUP BY\n" +
            "\tur.id")
    List<Resource> getResourceList(@Param("adminId") Long adminId);
}
