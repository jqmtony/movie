package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminLoginRecord implements Serializable{
	private Long alrId;  //登录记录ID
	private Timestamp alrLoginTime;	//登陆时间
	private String alrLoginIp;	//登录IP
	private Admins alrAdmin;	//登录的管理员对象
	private String alrStatus;	//登录状态
	public Long getAlrId() {
		return alrId;
	}
	public void setAlrId(Long alrId) {
		this.alrId = alrId;
	}
	public Timestamp getAlrLoginTime() {
		return alrLoginTime;
	}
	public void setAlrLoginTime(Timestamp alrLoginTime) {
		this.alrLoginTime = alrLoginTime;
	}
	public String getAlrLoginIp() {
		return alrLoginIp;
	}
	public void setAlrLoginIp(String alrLoginIp) {
		this.alrLoginIp = alrLoginIp;
	}
	public Admins getAlrAdmin() {
		return alrAdmin;
	}
	public void setAlrAdmin(Admins alrAdmin) {
		this.alrAdmin = alrAdmin;
	}
	public String getAlrStatus() {
		return alrStatus;
	}
	public void setAlrStatus(String alrStatus) {
		this.alrStatus = alrStatus;
	}
	@Override
	public String toString() {
		return "AdminLoginRecord [alrId=" + alrId + ", alrLoginTime=" + alrLoginTime + ", alrLoginIp=" + alrLoginIp
				+ ", alrAdmin=" + alrAdmin + ", alrStatus=" + alrStatus + "]";
	}
	
}
