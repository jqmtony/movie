package com.yc.movie.bean;

/**
 * ���ı�
 * @author Administrator
 *
 */
public class Sub {
	private Long subId;  //id
	private String subEmail;	//��������
	public Long getSubId() {
		return subId;
	}
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	public String getSubEmail() {
		return subEmail;
	}
	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}
	@Override
	public String toString() {
		return "Sub [subId=" + subId + ", subEmail=" + subEmail + "]";
	}
	
}
