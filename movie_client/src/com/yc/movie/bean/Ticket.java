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
	private Long ticketMerId;  //�̻�ID
	private Merchant merchant;  //��Ӧ�̻�
	private String ticketStatus; //��ӰƱ״̬  1Ϊ����  0Ϊ����
	private Users user; //��Ӧ���Ʊ���û�
	private Long ticketBuyBy; //�����û�ID
	private Timestamp ticketStartTime; //��Ӱ��ʼʱ��
	private Long ticketIndentId;  //��Ӧ����ID
	private Indent indent;  //��Ӧ����
	private String ticketLocation;  //��λ
	private String ticketMovieTheater;  //��ӳ����
	private Timestamp ticketMovieEndTime;  //����ʱ��
	private Timestamp ticketMovieStartTime; //��ӳʱ��
	private Long ticketLocationNum;  //��ӰƱ��λ�ö�Ӧ������  1-204
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
