package com.mall.api.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
/****
 * @Author:X
 * @Description:AdminRole构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_admin_role")
public class AdminRole implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "admin_id")
	private Long adminId;//

    @Column(name = "role_id")
	private Long roleId;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getAdminId() {
		return adminId;
	}

	//set方法
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	//get方法
	public Long getRoleId() {
		return roleId;
	}

	//set方法
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


}
