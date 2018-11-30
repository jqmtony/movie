package com.yc.movie.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ��ӰƱ
 * @author yxh
 *
 */
public class Ticket implements Serializable {
	private Long ticketId; //��ӰƱID
	private Movies movie; //��Ӧ��Ӱ
	private Long ticketMovieId;  //��Ӧ��ӰID
	private String ticketStatus; //��ӰƱ״̬  1Ϊ����  0Ϊ����
	private Users user; //��Ӧ���Ʊ���û�
	private Long ticjetBuyBy; //�����û�ID
	private Timestamp ticketStartTime; //��Ӱ��ʼʱ��
	private Indent indent;  //��Ӧ����
	private List<Images> imgList;  //��ӦͼƬ����
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
