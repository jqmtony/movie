package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 电影票
 * @author yxh
 *
 */
public class Ticket implements Serializable {
	private Long ticketId; //电影票ID
	private Movies movie; //对应电影
	private Long ticketMovieId;  //对应电影ID
	private Long ticketMerId;  //商户ID
	private Merchant merchant;  //对应商户
	private String ticketStatus; //电影票状态  1为可用  0为禁用
	private Users user; //对应买该票的用户
	private Long ticketBuyBy; //购买用户ID
	private Timestamp ticketStartTime; //电影开始时间
	private Long ticketIndentId;  //对应订单ID
	private Indent indent;  //对应订单
	private String ticketLocation;  //座位
	private String ticketMovieTheater;  //上映厅室
	private Timestamp ticketMovieEndTime;  //结束时间
	private Timestamp ticketMovieStartTime; //上映时间
	private Long ticketLocationNum;  //电影票的位置对应的数字  1-204
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Long getTicketMovieId() {
		return ticketMovieId;
	}
	public void setTicketMovieId(Long ticketMovieId) {
		this.ticketMovieId = ticketMovieId;
	}
	public Long getTicketMerId() {
		return ticketMerId;
	}
	public void setTicketMerId(Long ticketMerId) {
		this.ticketMerId = ticketMerId;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Long getTicketBuyBy() {
		return ticketBuyBy;
	}
	public void setTicketBuyBy(Long ticketBuyBy) {
		this.ticketBuyBy = ticketBuyBy;
	}
	public Timestamp getTicketStartTime() {
		return ticketStartTime;
	}
	public void setTicketStartTime(Timestamp ticketStartTime) {
		this.ticketStartTime = ticketStartTime;
	}
	public Long getTicketIndentId() {
		return ticketIndentId;
	}
	public void setTicketIndentId(Long ticketIndentId) {
		this.ticketIndentId = ticketIndentId;
	}
	public Indent getIndent() {
		return indent;
	}
	public void setIndent(Indent indent) {
		this.indent = indent;
	}
	public String getTicketLocation() {
		return ticketLocation;
	}
	public void setTicketLocation(String ticketLocation) {
		this.ticketLocation = ticketLocation;
	}
	public String getTicketMovieTheater() {
		return ticketMovieTheater;
	}
	public void setTicketMovieTheater(String ticketMovieTheater) {
		this.ticketMovieTheater = ticketMovieTheater;
	}
	public Timestamp getTicketMovieEndTime() {
		return ticketMovieEndTime;
	}
	public void setTicketMovieEndTime(Timestamp ticketMovieEndTime) {
		this.ticketMovieEndTime = ticketMovieEndTime;
	}
	public Timestamp getTicketMovieStartTime() {
		return ticketMovieStartTime;
	}
	public void setTicketMovieStartTime(Timestamp ticketMovieStartTime) {
		this.ticketMovieStartTime = ticketMovieStartTime;
	}
	public Long getTicketLocationNum() {
		return ticketLocationNum;
	}
	public void setTicketLocationNum(Long ticketLocationNum) {
		this.ticketLocationNum = ticketLocationNum;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", movie=" + movie + ", ticketMovieId=" + ticketMovieId
				+ ", ticketMerId=" + ticketMerId + ", merchant=" + merchant + ", ticketStatus=" + ticketStatus
				+ ", user=" + user + ", ticketBuyBy=" + ticketBuyBy + ", ticketStartTime=" + ticketStartTime
				+ ", ticketIndentId=" + ticketIndentId + ", indent=" + indent + ", ticketLocation=" + ticketLocation
				+ ", ticketMovieTheater=" + ticketMovieTheater + ", ticketMovieEndTime=" + ticketMovieEndTime
				+ ", ticketMovieStartTime=" + ticketMovieStartTime + ", ticketLocationNum=" + ticketLocationNum + "]";
	}
	
}
