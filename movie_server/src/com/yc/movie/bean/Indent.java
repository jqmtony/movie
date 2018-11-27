package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ����
 * @author yxh
 *
 */
public class Indent implements Serializable {
	private Long indentId; //����ID
	private Users user; //��Ӧ�û�
	private String indentStatus; //����״̬
	private String indentRemark;  //������ע
	private Timestamp indentCreateTime; //��������ʱ��
	public Long getIndentId() {
		return indentId;
	}
	public void setIndentId(Long indentId) {
		this.indentId = indentId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getIndentStatus() {
		return indentStatus;
	}
	public void setIndentStatus(String indentStatus) {
		this.indentStatus = indentStatus;
	}
	public String getIndentRemark() {
		return indentRemark;
	}
	public void setIndentRemark(String indentRemark) {
		this.indentRemark = indentRemark;
	}
	public Timestamp getIndentCreateTime() {
		return indentCreateTime;
	}
	public void setIndentCreateTime(Timestamp indentCreateTime) {
		this.indentCreateTime = indentCreateTime;
	}
	@Override
	public String toString() {
		return "Indent [indentId=" + indentId + ", user=" + user + ", indentStatus=" + indentStatus + ", indentRemark="
				+ indentRemark + ", indentCreateTime=" + indentCreateTime + "]";
	}
	
}
