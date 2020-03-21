package com.mall.api.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:X
 * @Description:Admin构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_admin")
public class Admin implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "username")
	private String username;//

    @Column(name = "password")
	private String password;//

    @Column(name = "icon")
	private String icon;//头像

    @Column(name = "email")
	private String email;//邮箱

    @Column(name = "nick_name")
	private String nickName;//昵称

    @Column(name = "note")
	private String note;//备注信息

    @Column(name = "create_time")
	private Date createTime;//创建时间

    @Column(name = "login_time")
	private Date loginTime;//最后登录时间

    @Column(name = "status")
	private Integer status;//帐号启用状态：0->禁用；1->启用



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
	}
	//get方法
	public String getPassword() {
		return password;
	}

	//set方法
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public String getNickName() {
		return nickName;
	}

	//set方法
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	//get方法
	public String getNote() {
		return note;
	}

	//set方法
	public void setNote(String note) {
		this.note = note;
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
	public Date getLoginTime() {
		return loginTime;
	}

	//set方法
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	//get方法
	public Integer getStatus() {
		return status;
	}

	//set方法
	public void setStatus(Integer status) {
		this.status = status;
	}


}
