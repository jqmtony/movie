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
	private String ticketStatus; //��ӰƱ״̬  1Ϊ����  0Ϊ����
	private Users user; //��Ӧ���Ʊ���û�
	private Timestamp ticketStartTime; //��Ӱ��ʼʱ��
	private Indent indent;  //��Ӧ����
	private List<Images> imgList;  //��ӦͼƬ����
	public List<Images> getImgList() {
		return imgList;
	}
	public void setImgList(List<Images> imgList) {
		this.imgList = imgList;
	}
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
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", movie=" + movie + ", ticketStatus=" + ticketStatus + ", user=" + user
				+ ", ticketStartTime=" + ticketStartTime + ", indent=" + indent + "]";
	}
	
}