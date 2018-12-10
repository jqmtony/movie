package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

/**
 * 用户
 * @author yxh
 *
 */
public class Users implements Serializable {
	private Long userId; //用户ID
	private String userName; //用户姓名
	private String userAccount;  //用户名
	private String userEmail;  //用户邮箱
	private String userPwd; //用户登录密码
	private Timestamp userCreateTime; //注册时间
	private String userTel;  //用户手机号
	private String userAddr;  //用户地址
	private String userStatus;  //用户状态  0禁用  1正常使用
	private Date userBirthday;  //出生日期
	private Long userAge;  //年龄
	private List<Ticket> ticketList;  //对应的电影票集合
	private List<Images> imgList;  //对应图片集合
	private List<Indent> indentList;  //对应订单集合
	private Integral integral;	//对应积分卡
	private List<UserLoginRecord> ulrList;  //对应登录日志
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
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public Long getUserAge() {
		return userAge;
	}
	
	/*
	 * Long time = userBirthday.getTime() - new java.util.Date().getTime();
		this.userAge = time/1000/60/60/24/365;
	 */
	public void setUserAge(Long userAge){
		this.userAge = userAge;
	}
	public List<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	public List<Images> getImgList() {
		return imgList;
	}
	public void setImgList(List<Images> imgList) {
		this.imgList = imgList;
	}
	public List<Indent> getIndentList() {
		return indentList;
	}
	public void setIndentList(List<Indent> indentList) {
		this.indentList = indentList;
	}
	public Integral getIntegral() {
		return integral;
	}
	public void setIntegral(Integral integral) {
		this.integral = integral;
	}
	public List<UserLoginRecord> getUlrList() {
		return ulrList;
	}
	public void setUlrList(List<UserLoginRecord> ulrList) {
		this.ulrList = ulrList;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", userAccount=" + userAccount + ", userEmail="
				+ userEmail + ", userPwd=" + userPwd + ", userCreateTime=" + userCreateTime + ", userTel=" + userTel
				+ ", userAddr=" + userAddr + ", userStatus=" + userStatus + ", userBirthday=" + userBirthday
				+ ", userAge=" + userAge + ", ticketList=" + ticketList + ", imgList=" + imgList + ", indentList="
				+ indentList + ", integral=" + integral + ", ulrList=" + ulrList + "]";
	}
	
}