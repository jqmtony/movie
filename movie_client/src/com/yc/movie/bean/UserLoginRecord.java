package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserLoginRecord implements Serializable{
	private Long ulrId;  //登录记录ID
	private Timestamp ulrLoginTime;	//登陆时间
	private String ulrLoginIp;	//登录IP
	private Users user;	//登录的管理员对象
	private String ulrStatus;	//登录状态
	public Long getUlrId() {
		return ulrId;
	}
	public void setUlrId(Long ulrId) {
		this.ulrId = ulrId;
	}
	public Timestamp getUlrLoginTime() {
		return ulrLoginTime;
	}
	public void setUlrLoginTime(Timestamp ulrLoginTime) {
		this.ulrLoginTime = ulrLoginTime;
	}
	public String getUlrLoginIp() {
		return ulrLoginIp;
	}
	public void setUlrLoginIp(String ulrLoginIp) {
		this.ulrLoginIp = ulrLoginIp;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getUlrStatus() {
		return ulrStatus;
	}
	public void setUlrStatus(String ulrStatus) {
		this.ulrStatus = ulrStatus;
	}
	@Override
	public String toString() {
		return "UserLoginRecord [ulrId=" + ulrId + ", ulrLoginTime=" + ulrLoginTime + ", ulrLoginIp=" + ulrLoginIp
				+ ", user=" + user + ", ulrStatus=" + ulrStatus + "]";
	}
	
}
