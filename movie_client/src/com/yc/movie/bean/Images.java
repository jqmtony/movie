package com.yc.movie.bean;

import java.io.Serializable;

/**
 * 图片
 * @author yxh
 *
 */
public class Images implements Serializable {
	private Long imgId; //图片ID
	private Movies movie; //对应电影
	private Teleplay teleplay; //对应电视剧
	private Admins admin;  //对应管理员
	private Users user; //对应用户
	private Merchant merchant; //对应商户
	private Ticket ticket; //对应电影票
	private News news;  //对应新闻
	private String imgStatus; ///*图片的展示地方    1为封面  2为订单展示   3为剧情介绍*/
	private String imgPath;  //图片路径
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
