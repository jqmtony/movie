package com.yc.movie.bean;

import java.io.Serializable;

/**
 * 积分卡
 * @author yxh
 *
 */
public class Integral implements Serializable {
	private Long integralId;  //积分卡Id
	private Users user; //对应用户
	private Long integralCount;  //积分数
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
