package com.yc.movie.bean;

import java.io.Serializable;

/**
 * 图片
 * @author yxh
 *
 */
public class Images implements Serializable {
	private Long imgId; //图片ID
	private Long imgMovieId;  //对应电影ID
	private Movies movie; //对应电影
	private Long imgTeleplayId; //对应电视剧Id
	private Teleplay teleplay; //对应电视剧
	private Long imgAdminId;  //对应管理员ID
	private Admins admin;  //对应管理员
	private Long imgUserId;  //对应用户ID
	private Users user; //对应用户
	private Long imgMerchantId;  //对应商户ID
	private Merchant merchant; //对应商户
	private Long imgTicketId;  //对应电影票ID
	private Ticket ticket; //对应电影票
	private Long imgNewId; //对应新闻ID
	private News news;  //对应新闻
	private String imgStatus; ///*图片的展示地方    1为封面  2为订单展示   3为剧情介绍*/
	private String imgPath;  //图片路径
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
