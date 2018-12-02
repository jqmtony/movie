package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户登录日志
 * @author yxh
 *
 */
public class UserLoginRecord implements Serializable {
	private Long ulrId;  //ID
	private Timestamp ulrLoginTime;  //登录时间
	private String ulrLoginIp;  //登录IP
	private Users user;  //对应的User
	@Override
	public String toString() {
		return "UserLoginRecord [ulrId=" + ulrId + ", ulrLoginTime=" + ulrLoginTime + ", ulrLoginIp=" + ulrLoginIp
				+ "]";
	}
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
	
	
}
