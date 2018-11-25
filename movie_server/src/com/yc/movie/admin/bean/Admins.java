package com.yc.movie.admin.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Admins implements Serializable{
	private Long adminId; //管理员id
	private String adminRegisterCode;	//管理员注册码
	private String adminName;	//管理员姓名
	private String adminTel;	//管理员手机号
	private String adminAddr;	//管理员地址
	private Timestamp adminCreateTime;	//管理员创建时间
	private Long adminWeight;	//管理员权值
	private String adminEmail;	//管理员邮箱地址
	private String adminPwd;	//管理员密码
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getAdminRegisterCode() {
		return adminRegisterCode;
	}
	public void setAdminRegisterCode(String adminRegisterCode) {
		this.adminRegisterCode = adminRegisterCode;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminTel() {
		return adminTel;
	}
	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}
	public String getAdminAddr() {
		return adminAddr;
	}
	public void setAdminAddr(String adminAddr) {
		this.adminAddr = adminAddr;
	}
	public Timestamp getAdminCreateTime() {
		return adminCreateTime;
	}
	public void setAdminCreateTime(Timestamp adminCreateTime) {
		this.adminCreateTime = adminCreateTime;
	}
	public Long getAdminWeight() {
		return adminWeight;
	}
	public void setAdminWeight(Long adminWeight) {
		this.adminWeight = adminWeight;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	@Override
	public String toString() {
		return "admins [adminId=" + adminId + ", adminRegisterCode=" + adminRegisterCode + ", adminName=" + adminName
				+ ", adminTel=" + adminTel + ", adminAddr=" + adminAddr + ", adminCreateTime=" + adminCreateTime
				+ ", adminWeight=" + adminWeight + ", adminEmail=" + adminEmail + ", adminPwd=" + adminPwd + "]";
	}
	
}
