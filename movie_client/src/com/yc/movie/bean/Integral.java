package com.yc.movie.bean;

import java.io.Serializable;

/**
 * ���ֿ�
 * @author yxh
 *
 */
public class Integral implements Serializable {
	private Long integralId;  //���ֿ�Id
	private Users user; //��Ӧ�û�
	private Long integralCount;  //������
	public Long getIntegralId() {
		return integralId;
	}
	public void setIntegralId(Long integralId) {
		this.integralId = integralId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Long getIntegralCount() {
		return integralCount;
	}
	public void setIntegralCount(Long integralCount) {
		this.integralCount = integralCount;
	}
	@Override
	public String toString() {
		return "Integral [integralId=" + integralId + ", user=" + user + ", integralCount=" + integralCount + "]";
	}
	
}
