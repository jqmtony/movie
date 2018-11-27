package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminLoginRecord implements Serializable{
	private Long alrId;  //��¼��¼ID
	private Timestamp alrLoginTime;	//��½ʱ��
	private String alrLoginIp;	//��¼IP
	private Admins alrAdmin;	//��¼�Ĺ���Ա����
	private String alrStatus;	//��¼״̬
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
