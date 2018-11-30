package com.yc.movie.bean;

import java.io.Serializable;

/**
 * ͼƬ
 * @author yxh
 *
 */
public class Images implements Serializable {
	private Long imgId; //ͼƬID
	private Long imgMovieId;  //��Ӧ��ӰID
	private Movies movie; //��Ӧ��Ӱ
	private Long imgTeleplayId; //��Ӧ���Ӿ�Id
	private Teleplay teleplay; //��Ӧ���Ӿ�
	private Long imgAdminId;  //��Ӧ����ԱID
	private Admins admin;  //��Ӧ����Ա
	private Long imgUserId;  //��Ӧ�û�ID
	private Users user; //��Ӧ�û�
	private Long imgMerchantId;  //��Ӧ�̻�ID
	private Merchant merchant; //��Ӧ�̻�
	private Long imgTicketId;  //��Ӧ��ӰƱID
	private Ticket ticket; //��Ӧ��ӰƱ
	private Long imgNewId; //��Ӧ����ID
	private News news;  //��Ӧ����
	private String imgStatus; ///*ͼƬ��չʾ�ط�    1Ϊ����  2Ϊ����չʾ   3Ϊ�������*/
	private String imgPath;  //ͼƬ·��
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public Long getImgMovieId() {
		return imgMovieId;
	}
	public void setImgMovieId(Long imgMovieId) {
		this.imgMovieId = imgMovieId;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Long getImgTeleplayId() {
		return imgTeleplayId;
	}
	public void setImgTeleplayId(Long imgTeleplayId) {
		this.imgTeleplayId = imgTeleplayId;
	}
	public Teleplay getTeleplay() {
		return teleplay;
	}
	public void setTeleplay(Teleplay teleplay) {
		this.teleplay = teleplay;
	}
	public Long getImgAdminId() {
		return imgAdminId;
	}
	public void setImgAdminId(Long imgAdminId) {
		this.imgAdminId = imgAdminId;
	}
	public Admins getAdmin() {
		return admin;
	}
	public void setAdmin(Admins admin) {
		this.admin = admin;
	}
	public Long getImgUserId() {
		return imgUserId;
	}
	public void setImgUserId(Long imgUserId) {
		this.imgUserId = imgUserId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Long getImgMerchantId() {
		return imgMerchantId;
	}
	public void setImgMerchantId(Long imgMerchantId) {
		this.imgMerchantId = imgMerchantId;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Long getImgTicketId() {
		return imgTicketId;
	}
	public void setImgTicketId(Long imgTicketId) {
		this.imgTicketId = imgTicketId;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Long getImgNewId() {
		return imgNewId;
	}
	public void setImgNewId(Long imgNewId) {
		this.imgNewId = imgNewId;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getImgStatus() {
		return imgStatus;
	}
	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	@Override
	public String toString() {
		return "Images [imgId=" + imgId + ", imgMovieId=" + imgMovieId + ", movie=" + movie + ", imgTeleplayId="
				+ imgTeleplayId + ", teleplay=" + teleplay + ", imgAdminId=" + imgAdminId + ", admin=" + admin
				+ ", imgUserId=" + imgUserId + ", user=" + user + ", imgMerchantId=" + imgMerchantId + ", merchant="
				+ merchant + ", imgTicketId=" + imgTicketId + ", ticket=" + ticket + ", imgNewId=" + imgNewId
				+ ", news=" + news + ", imgStatus=" + imgStatus + ", imgPath=" + imgPath + "]";
	}
	
}
