package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户
 * @author yxh
 *
 */
public class Users implements Serializable {
	private Long userId; //用户ID
	private String userName; //用户姓名
	private String userEmail;  //用户邮箱
	private String userPwd; //用户登录密码
	private Timestamp userCreateTime; //注册时间
	private String userTel;  //用户手机号
	private String userPayNum; //用户支付账号
	private String userPayPwd;  //支付密码
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPwd="
				+ userPwd + ", userCreateTime=" + userCreateTime + ", userTel=" + userTel + ", userPayNum=" + userPayNum
				+ ", userPayPwd=" + userPayPwd + "]";
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Timestamp getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Timestamp userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserPayNum() {
		return userPayNum;
	}
	public void setUserPayNum(String userPayNum) {
		this.userPayNum = userPayNum;
	}
	public String getUserPayPwd() {
		return userPayPwd;
	}
	public void setUserPayPwd(String userPayPwd) {
		this.userPayPwd = userPayPwd;
	}
}
