package com.yc.movie.bean;

import java.io.Serializable;

/**
 * ͼƬ
 * @author yxh
 *
 */
public class Images implements Serializable {
	private Long imgId; //ͼƬID
	private Movies movie; //��Ӧ��Ӱ
	private Teleplay teleplay; //��Ӧ���Ӿ�
	private Admins admin;  //��Ӧ����Ա
	private Users user; //��Ӧ�û�
	private Merchant merchant; //��Ӧ�̻�
	private Ticket ticket; //��Ӧ��ӰƱ
	private News news;  //��Ӧ����
	private String imgStatus; ///*ͼƬ��չʾ�ط�    1Ϊ����  2Ϊ����չʾ   3Ϊ�������*/
	private String imgPath;  //ͼƬ·��
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public Movies getMovie() {
		return movie;
	}
	public void setMovie(Movies movie) {
		this.movie = movie;
	}
	public Teleplay getTeleplay() {
		return teleplay;
	}
	public void setTeleplay(Teleplay teleplay) {
		this.teleplay = teleplay;
	}
	public Admins getAdmin() {
		return admin;
	}
	public void setAdmin(Admins admin) {
		this.admin = admin;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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
		return "Images [imgId=" + imgId + ", movie=" + movie + ", teleplay=" + teleplay + ", admin=" + admin + ", user="
				+ user + ", merchant=" + merchant + ", ticket=" + ticket + ", news=" + news + ", imgStatus=" + imgStatus
				+ ", imgPath=" + imgPath + "]";
	}
	
	
}
