package com.mall.api.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:X
 * @Description:Menu构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_menu")
public class Menu implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "parent_id")
	private Long parentId;//父级ID

    @Column(name = "create_time")
	private Date createTime;//创建时间

    @Column(name = "title")
	private String title;//菜单名称

    @Column(name = "level")
	private Integer level;//菜单级数

    @Column(name = "sort")
	private Integer sort;//菜单排序

    @Column(name = "name")
	private String name;//前端名称

    @Column(name = "icon")
	private String icon;//前端图标

    @Column(name = "hidden")
	private Integer hidden;//前端隐藏



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getParentId() {
		return parentId;
	}

	//set方法
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public String getTitle() {
		return title;
	}

	//set方法
	public void setTitle(String title) {
		this.title = title;
	}
	//get方法
	public Integer getLevel() {
		return level;
	}

	//set方法
	public void setLevel(Integer level) {
		this.level = level;
	}
	//get方法
	public Integer getSort() {
		return sort;
	}

	//set方法
	public void setSort(Integer sort) {
		this.sort = sort;
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
	public String getIcon() {
		return icon;
	}

	//set方法
	public void setIcon(String icon) {
		this.icon = icon;
	}
	//get方法
	public Integer getHidden() {
		return hidden;
	}

	//set方法
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}


}
