package com.mall.api.system.pojo;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:X
 * @Description:RoleMenu构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_role_menu")
public class RoleMenu implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "role_id")
	private Long roleId;//角色ID

    @Column(name = "menu_id")
	private Long menuId;//菜单ID



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getRoleId() {
		return roleId;
	}

	//set方法
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	//get方法
	public Long getMenuId() {
		return menuId;
	}

	//set方法
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}


}
