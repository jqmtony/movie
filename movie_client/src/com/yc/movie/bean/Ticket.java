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
	private String ticketStatus; //电影票状态  1为可用  0为禁用
	private Users user; //对应买该票的用户
	private Long ticjetBuyBy; //购买用户ID
	private Timestamp ticketStartTime; //电影开始时间
	private Indent indent;  //对应订单
	private List<Images> imgList;  //对应图片集合
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
	public Long getTicjetBuyBy() {
		return ticjetBuyBy;
	}
	public void setTicjetBuyBy(Long ticjetBuyBy) {
		this.ticjetBuyBy = ticjetBuyBy;
	}
	public Timestamp getTicketStartTime() {
		return ticketStartTime;
	}
	public void setTicketStartTime(Timestamp ticketStartTime) {
		this.ticketStartTime = ticketStartTime;
	}
	public Indent getIndent() {
		return indent;
	}
	public void setIndent(Indent indent) {
		this.indent = indent;
	}
	public List<Images> getImgList() {
		return imgList;
	}
	public void setImgList(List<Images> imgList) {
		this.imgList = imgList;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", movie=" + movie + ", ticketMovieId=" + ticketMovieId
				+ ", ticketStatus=" + ticketStatus + ", user=" + user + ", ticjetBuyBy=" + ticjetBuyBy
				+ ", ticketStartTime=" + ticketStartTime + ", indent=" + indent + ", imgList=" + imgList + "]";
	}
	
}
