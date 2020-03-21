package com.mall.api.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:X
 * @Description:Role构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_role")
public class Role implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "name")
	private String name;//名称

    @Column(name = "description")
	private String description;//描述

    @Column(name = "admin_count")
	private Integer adminCount;//后台用户数量

    @Column(name = "create_time")
	private Date createTime;//创建时间

    @Column(name = "status")
	private Integer status;//启用状态：0->禁用；1->启用

    @Column(name = "sort")
	private Integer sort;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getDescription() {
		return description;
	}

	//set方法
	public void setDescription(String description) {
		this.description = description;
	}
	//get方法
	public Integer getAdminCount() {
		return adminCount;
	}

	//set方法
	public void setAdminCount(Integer adminCount) {
		this.adminCount = adminCount;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public Integer getStatus() {
		return status;
	}

	//set方法
	public void setStatus(Integer status) {
		this.status = status;
	}
	//get方法
	public Integer getSort() {
		return sort;
	}

	//set方法
	public void setSort(Integer sort) {
		this.sort = sort;
	}


}
