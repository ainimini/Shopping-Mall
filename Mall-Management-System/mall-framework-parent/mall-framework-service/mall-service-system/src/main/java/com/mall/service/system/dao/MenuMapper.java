package com.mall.service.system.dao;
import com.mall.api.system.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:X
 * @Description:Menu的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface MenuMapper extends Mapper<Menu> {

    /**
     * 根据管理员ID获取对应菜单
     */
    @Select(value = "SELECT\n" +
            "            m.id id,\n" +
            "            m.parent_id parentId,\n" +
            "            m.create_time createTime,\n" +
            "            m.title title,\n" +
            "            m.level level,\n" +
            "            m.sort sort,\n" +
            "            m.name name,\n" +
            "            m.icon icon,\n" +
            "            m.hidden hidden\n" +
            "        FROM\n" +
            "            tb_admin_role arr\n" +
            "                LEFT JOIN tb_role r ON arr.role_id = r.id\n" +
            "                LEFT JOIN tb_role_menu rmr ON r.id = rmr.role_id\n" +
            "                LEFT JOIN tb_menu m ON rmr.menu_id = m.id\n" +
            "        WHERE\n" +
            "            arr.admin_id = #{adminId}\n" +
            "          AND m.id IS NOT NULL\n" +
            "        GROUP BY\n" +
            "            m.id")
    List<Menu> getMenuList(@Param("adminId") Long adminId);
}
