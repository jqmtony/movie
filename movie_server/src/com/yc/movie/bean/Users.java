package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * �û�
 * @author yxh
 *
 */
public class Users implements Serializable {
	private Long userId; //�û�ID
	private String userName; //�û�����
	private String userEmail;  //�û�����
	private String userPwd; //�û���¼����
	private Timestamp userCreateTime; //ע��ʱ��
	private String userTel;  //�û��ֻ���
	private String userPayNum; //�û�֧���˺�
	private String userPayPwd;  //֧������
	private List<Ticket> ticketList;  //��Ӧ�ĵ�ӰƱ����
	private List<Images> imgList;  //��ӦͼƬ����
	private List<Indent> indentList;  //��Ӧ��������
	private Integral integral;	//��Ӧ���ֿ�
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
