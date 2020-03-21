package com.mall.api.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Integer;
/****
 * @Author:X
 * @Description:RoleResource构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_role_resource")
public class RoleResource implements Serializable{

	@Id
    @Column(name = "role_id")
	private Integer roleId;//

    @Column(name = "resource_id")
	private Integer resourceId;//



	//get方法
	public Integer getRoleId() {
		return roleId;
	}

	//set方法
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	//get方法
	public Integer getResourceId() {
		return resourceId;
	}

	//set方法
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}


}
