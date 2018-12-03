package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ����Ա
 * @author yxh
 *
 */
public class Admins implements Serializable{    //���Ҫ������   ��Ҫȫ������
	private Long adminId; //����Աid
	private String adminRegisterCode;	//����Աע����
	private String adminName;	//����Ա����
	private String adminTel;	//����Ա�ֻ���
	private String adminAddr;	//����Ա��ַ
	private Timestamp adminCreateTime;	//����Ա����ʱ��
	private Long adminWeight;	//����ԱȨֵ
	private String adminEmail;	//����Ա�����ַ
	private String adminPwd;	//����Ա����
	private List<AdminLoginRecord> alrList;  //��Ӧ�ĵ�¼��¼���󼯺�
	private List<Images> imgList;  //��ӦͼƬ���󼯺�
	
	public List<AdminLoginRecord> getAlrList() {
		return alrList;
	}
	public void setAlrList(List<AdminLoginRecord> alrList) {
		this.alrList = alrList;
	}
	public List<Images> getImgList() {
		return imgList;
	}
	public void setImgList(List<Images> imgList) {
		this.imgList = imgList;
	}
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
		return "Admins [adminId=" + adminId + ", adminRegisterCode=" + adminRegisterCode + ", adminName=" + adminName
				+ ", adminTel=" + adminTel + ", adminAddr=" + adminAddr + ", adminCreateTime=" + adminCreateTime
				+ ", adminWeight=" + adminWeight + ", adminEmail=" + adminEmail + ", adminPwd=" + adminPwd + "]";
	}

}
