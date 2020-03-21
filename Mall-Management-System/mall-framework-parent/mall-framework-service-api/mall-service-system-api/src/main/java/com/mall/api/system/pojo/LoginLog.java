package com.mall.api.system.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
/****
 * @Author:X
 * @Description:LoginLog构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_login_log")
public class LoginLog implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;//

    @Column(name = "admin_id")
	private Long adminId;//

    @Column(name = "create_time")
	private Date createTime;//

    @Column(name = "ip")
	private String ip;//

    @Column(name = "address")
	private String address;//

    @Column(name = "user_agent")
	private String userAgent;//浏览器登录类型



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
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public String getIp() {
		return ip;
	}

	//set方法
	public void setIp(String ip) {
		this.ip = ip;
	}
	//get方法
	public String getAddress() {
		return address;
	}

	//set方法
	public void setAddress(String address) {
		this.address = address;
	}
	//get方法
	public String getUserAgent() {
		return userAgent;
	}

	//set方法
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}


}
