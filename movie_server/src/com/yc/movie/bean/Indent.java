package com.yc.movie.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单
 * @author yxh
 *
 */
public class Indent implements Serializable {
	private Long indentId; //订单ID
	private Users user; //对应用户
	private Long indentUserID; //对应用户ID
	private String indentStatus; //订单状态
	private String indentRemark;  //订单备注
	private String indentNum; //订单号
	private BigDecimal indentPrice;  //订单金额
	private Timestamp indentCreateTime; //订单创建时间
	private List<Ticket> ticketList;  //对应电影票集合
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
	public Long getIndentUserID() {
		return indentUserID;
	}
	public void setIndentUserID(Long indentUserID) {
		this.indentUserID = indentUserID;
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
	public String getIndentNum() {
		return indentNum;
	}
	public void setIndentNum(String indentNum) {
		this.indentNum = indentNum;
	}
	public BigDecimal getIndentPrice() {
		return indentPrice;
	}
	public void setIndentPrice(BigDecimal indentPrice) {
		this.indentPrice = indentPrice;
	}
	public Timestamp getIndentCreateTime() {
		return indentCreateTime;
	}
	public void setIndentCreateTime(Timestamp indentCreateTime) {
		this.indentCreateTime = indentCreateTime;
	}
	public List<Ticket> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}
	@Override
	public String toString() {
		return "Indent [indentId=" + indentId + ", user=" + user + ", indentUserID=" + indentUserID + ", indentStatus="
				+ indentStatus + ", indentRemark=" + indentRemark + ", indentNum=" + indentNum + ", indentPrice="
				+ indentPrice + ", indentCreateTime=" + indentCreateTime + ", ticketList=" + ticketList + "]";
	}
	
}
