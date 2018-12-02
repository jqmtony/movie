package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �û���¼��־
 * @author yxh
 *
 */
public class UserLoginRecord implements Serializable {
	private Long ulrId;  //ID
	private Timestamp ulrLoginTime;  //��¼ʱ��
	private String ulrLoginIp;  //��¼IP
	private Users user;  //��Ӧ��User
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
