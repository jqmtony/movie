package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

/**
 * �û�
 * @author yxh
 *
 */
public class Users implements Serializable {
	private Long userId; //�û�ID
	private String userName; //�û�����
	private String userAccount;  //�û���
	private String userEmail;  //�û�����
	private String userPwd; //�û���¼����
	private Timestamp userCreateTime; //ע��ʱ��
	private String userTel;  //�û��ֻ���
	private String userAddr;  //�û���ַ
	private String userStatus;  //�û�״̬  0����  1����ʹ��
	private Date userBirthday;  //��������
	private Long userAge;  //����
	private List<Ticket> ticketList;  //��Ӧ�ĵ�ӰƱ����
	private List<Images> imgList;  //��ӦͼƬ����
	private List<Indent> indentList;  //��Ӧ��������
	private Integral integral;	//��Ӧ���ֿ�
	private List<UserLoginRecord> ulrList;  //��Ӧ��¼��־
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